import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Knight extends GameObject {

    private Handler handler;
    private BufferedImage[] knight_image = new BufferedImage[8];
    Animation anim;

    // Import random as enemies will roam around randomly.
    Random r = new Random();
    int choose = 0;
    int hp = 100;

    int px; // players x and y location
    int py;

    public Knight(int x, int y, ID id, Handler handler, SpriteSheet cs) {
        super(x, y, id, cs);
        this.handler = handler;

        knight_image[0] = cs.grabImage(9, 5, 32, 32);
        knight_image[1] = cs.grabImage(10, 5, 32, 32);
        knight_image[2] = cs.grabImage(11, 5, 32, 32);
        knight_image[3] = cs.grabImage(12, 5, 32, 32);
        knight_image[4] = cs.grabImage(13, 5, 32, 32);
        knight_image[5] = cs.grabImage(14, 5, 32, 32);
        knight_image[6] = cs.grabImage(15, 5, 32, 32);
        knight_image[7] = cs.grabImage(16, 5, 32, 32);


        anim = new Animation(knight_image, 150);
    }

    public void tick() {

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                // If the player intersects with a knight they will get shot back.
                // But we want it so if the knight comes close to the player they get shot back
                px = tempObject.getX(); // Players x and y location
                py = tempObject.getY();

                if (getBoundsBig().intersects(tempObject.getBounds())) {
                    // If the player intersects with a Knight
                    velX = (px + x) / 100; // Knight will reverse from the player
                    velY = (py + y) / 100;

                    x += (velX*2); // Knight will jump forward back quick like a rubber band snap. // turned to 1-- just now
                    y += (velY*2);

                    x *= velX / 10; // Then continue reversing.
                    y *= velY / 10;

                } else {
                    velX = (px - x) / 100; // Basically Knight will lock onto Player location and follow.
                    velY = (py - y) / 100;

                    x += velX;
                    y += velY;
                }
            }
        }

        choose = r.nextInt(10);

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                // Play around with this to get a better enemy AI. Lil' glitchy...
                if (tempObject.getId() == ID.Bullet) {
                    // If enemy collides with bullet, remove bullet/hp.
                    if (getBounds().intersects(tempObject.getBounds())) {
                        hp -= 50;
                        handler.removeObject(tempObject);
                    }
                }
            }


            anim.tick();
            // If no hp, bye bye.
            if (hp <= 0) handler.removeObject(this);
        }
    } //End tick method

    public void render (Graphics g){
        anim.render(g, x, y, 32, 32);
    }

    public Rectangle getBounds () {
        return new Rectangle(x, y, 62, 62);
    }

    public Rectangle getBoundsBig () {
        // Makes bounding box slightly bigger for colliding with walls as opposed to bullets (not so tight).
        return new Rectangle(x - 16, y - 16, 200, 200);
    }
}
