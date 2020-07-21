package edu.sdccd.cisc191.wizardGame.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import edu.sdccd.cisc191.wizardGame.gui.anim.Animation;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

public class Enemy extends GameObject {

    private Handler handler;
    private BufferedImage[] enemy_image = new BufferedImage[8];
    Animation anim;

    // Import random as enemies will roam around randomly.
    Random r = new Random();
    int choose = 0;
    int hp = 100;

    public Enemy (int x, int y, ID id, Handler handler, SpriteSheet cs) {
        super(x, y, id, cs);
        this.handler = handler;

        enemy_image[0] = cs.grabImage(1, 11, 32, 32);
        enemy_image[1] = cs.grabImage(2, 11, 32, 32);
        enemy_image[2] = cs.grabImage(3, 11, 32, 32);
        enemy_image[3] = cs.grabImage(4, 11, 32, 32);
        enemy_image[4] = cs.grabImage(5, 11, 32, 32);
        enemy_image[5] = cs.grabImage(6, 11, 32, 32);
        enemy_image[6] = cs.grabImage(7, 11, 32, 32);
        enemy_image[7] = cs.grabImage(8, 11, 32, 32);


        anim = new Animation(enemy_image, 150);
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
                // If shooty shoot time take away health point.
                if(getBounds().intersects(tempObject.getBounds())) {
                    hp -= 50;
                    handler.removeObject(tempObject);
                }
            }
        }

        anim.tick();
        // If no hp, bye bye.
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
