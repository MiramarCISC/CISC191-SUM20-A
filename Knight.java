import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Knight extends GameObject {

    private Handler handler;
    private Game game;
    private BufferedImage[] knight_image = new BufferedImage[8];
    Animation anim;

    // Import random as enemies will roam around randomly.
    Random r = new Random();
    int choose = 0;
    int hp = 100;
    int counter = 0; // Counter used to count amount of times block is hit

    int px; // players x and y location
    int py;

    public Knight(int x, int y, ID id, Handler handler, Game game, SpriteSheet cs) {
        super(x, y, id, cs);
        this.handler = handler;
        this.game = game;

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
        x += velX;
        y += velY;

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                px = tempObject.getX(); // Players x and y location
                py = tempObject.getY();

                if(getBoundsBig().intersects(tempObject.getBounds())) {
                    x += (velX*25) * -1; // Change velX/Y*int to change bounce level
                    y += (velY*25) * -1; //Invert velocity and shoot it back (ricochet)
                    velX *= -1;
                    velY *= -1;

                    game.hp--;
                }
            }

            if(tempObject.getId() == ID.Bullet) {
                // Shoot and remove health point.
                if (getBounds().intersects(tempObject.getBounds())) {
                    x += (velX*25) * -1; // Change velX/Y*int to change bounce level
                    y += (velY*25) * -1; //Invert velocity and shoot it back (ricochet)
                    velX *= -1;
                    velY *= -1;

                    hp -= 50;
                    handler.removeObject(tempObject);
                }
            }

            else if (tempObject.getId() == ID.Block) {
                if(getBoundsBig().intersects(tempObject.getBounds())) {
                    x += (velX*12) * -1; // Change velX/Y*int to change bounce level
                    y += (velY*12) * -1; //Invert velocity and shoot it back (ricochet)
                    velX *= -1;
                    velY *= -1;

                    counter++; // Increment counter

                    if(counter > 5) {
                        //If counter greater than 5 destroy block.
                        handler.removeObject(tempObject);
                        counter = 0;
                    }
                }
            }
        }

        velX = (px - x) / 35; // Basically Knight will lock onto Player location and follow.
        velY = (py - y) / 35; // until it reach certain point then stays there. Change division int to change speed (higher int = lower speed).

        anim.tick(); // render animation
        if(hp <= 0) handler.removeObject(this); // remove Knight if less than 0 hp.
    }

    public void render (Graphics g){
        anim.render(g, x, y, 32, 32);
    }

    public Rectangle getBounds () {
        return new Rectangle(x, y, 32, 32);
    }

    public Rectangle getBoundsBig () {
        // Makes bounding box slightly bigger for colliding with walls as opposed to bullets (not so tight).
        return new Rectangle(x - 16, y - 16, 64, 64);
    }
}