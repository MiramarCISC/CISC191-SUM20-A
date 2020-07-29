package edu.sdccd.cisc191.wizardGame.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import edu.sdccd.cisc191.wizardGame.gui.anim.Animation;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

/**
 * Minion enemy game object.
 *
 * @author Jordan Tobin
 *
 * Editor: Mark Lucernas
 * Date: 2020-06-08
 */
public class Minion extends GameObject {

    private Handler handler;
    private BufferedImage[] minionImages = new BufferedImage[8];
    private Animation anim;

    // Import random as enemies will roam around randomly.
    Random r = new Random();
    int choose = 0;
    int hp = 100;

    /**
     * No args Minion constructor.
     */
    public Minion() { super(); }

    /**
     * Minion constructor.
     * @param x         X coordinate
     * @param y         Y coordinate
     * @param id        Object ID
     * @param handler   Handler to handle all GameObjects
     * @param cs        Character SpriteSheet
     */
    public Minion (int x, int y, ID id, Handler handler, SpriteSheet cs) {
        super(x, y, id, cs);
        this.handler = handler;

        minionImages[0] = cs.grabImage(1, 11, 32, 32);
        minionImages[1] = cs.grabImage(2, 11, 32, 32);
        minionImages[2] = cs.grabImage(3, 11, 32, 32);
        minionImages[3] = cs.grabImage(4, 11, 32, 32);
        minionImages[4] = cs.grabImage(5, 11, 32, 32);
        minionImages[5] = cs.grabImage(6, 11, 32, 32);
        minionImages[6] = cs.grabImage(7, 11, 32, 32);
        minionImages[7] = cs.grabImage(8, 11, 32, 32);

        anim = new Animation(minionImages, 150);
    }

    /**
     * Tick method to define Minion movements.
     * Delete Minion on hp == 0.
     */
    public void tick() {
        x += velX;
        y += velY;

        /* Constantly 'choose' is a random variable from 0-9.
           If choose == 0, enemy moves in a different direction. */
        choose = r.nextInt(10);

        // If enemy collides, they automatically pick a new direction.
        for (int i = 0; i < handler.getObject().size(); i++) {
            GameObject tempObject = handler.getObject().get(i);

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

        // Remove this Minion if hp at 0.
        if (hp <= 0) handler.removeObject(this);
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
