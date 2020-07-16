import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Hound extends GameObject {

    private Handler handler;
    private BufferedImage[] hound_image = new BufferedImage[8];
    Animation anim;

    // Import random as enemies will roam around randomly.
    Random r = new Random();
    int choose = 0;
    int hp = 100;

    public Hound (int x, int y, ID id, Handler handler, SpriteSheet cs) {
        super(x, y, id, cs);
        this.handler = handler;

        hound_image[0] = cs.grabImage(9, 4, 32, 32);
        hound_image[1] = cs.grabImage(10, 4, 32, 32);
        hound_image[2] = cs.grabImage(11, 4, 32, 32);
        hound_image[3] = cs.grabImage(12, 4, 32, 32);
        hound_image[4] = cs.grabImage(13, 4, 32, 32);
        hound_image[5] = cs.grabImage(14, 4, 32, 32);
        hound_image[6] = cs.grabImage(15, 4, 32, 32);
        hound_image[7] = cs.grabImage(16, 4, 32, 32);


        anim = new Animation(hound_image, 150);
    }

    public void tick() {
        x += velX;
        y += velY;

        /* Constantly 'choose' is a random variable from 0-9.
        If choose == 0, enemy moves in a different direction. */
        choose = r.nextInt(10);

        // If enemy collides, they automatically pick a new direction.
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Block) {
                // Play around with this to get a better enemy AI. Lil' glitchy...
                if(getBoundsBig().intersects(tempObject.getBounds())) {
                    x += (velX*5) * -1;
                    y += (velY*5) * -1; //Invert velocity and shoot it back (ricochet)
                    velX *= -1;
                    velY *= -1;
                } else if (choose == 0) {
                    // Random between -4 and 4 algorithm.
                    velX = (r.nextInt(2 - -2) + -2);
                    velY = (r.nextInt(2 - -2) + -2);
                }
            }

            if(tempObject.getId() == ID.Bullet) {
                // If bullet hits minion...
                if(getBounds().intersects(tempObject.getBounds())) {
                    hp -= 50;
                    handler.removeObject(tempObject);
                }
            }
        }

        anim.tick();
        // If hp at 0 delete.
        if(hp <= 0) handler.removeObject(this);
    } //End tick method

    public void render(Graphics g) {
        anim.render(g, x, y, 32, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public Rectangle getBoundsBig() {
        // Makes bounding box slightly bigger for colliding with walls as opposed to bullets (not so tight).
        return new Rectangle (x-16, y-16, 64, 64);
    }
}