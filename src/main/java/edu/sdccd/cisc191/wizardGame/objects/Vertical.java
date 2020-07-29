package edu.sdccd.cisc191.wizardGame.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import edu.sdccd.cisc191.wizardGame.gui.anim.Animation;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

public class Vertical extends GameObject {

    private Handler handler;
    private BufferedImage[] vert_image = new BufferedImage[8];
    Animation anim;

    // Enemies will move right until they hit a block.
    int velY = 3;
    int hp = 100;

    public Vertical (int x, int y, ID id, Handler handler, SpriteSheet cs) {
        super(x, y, id, cs);
        this.handler = handler;

        vert_image[0] = cs.grabImage(1, 10, 32, 32);
        vert_image[1] = cs.grabImage(2, 10, 32, 32);
        vert_image[2] = cs.grabImage(3, 10, 32, 32);
        vert_image[3] = cs.grabImage(4, 10, 32, 32);
        vert_image[4] = cs.grabImage(5, 10, 32, 32);
        vert_image[5] = cs.grabImage(6, 10, 32, 32);
        vert_image[6] = cs.grabImage(7, 10, 32, 32);
        vert_image[7] = cs.grabImage(8, 10, 32, 32);


        anim = new Animation(vert_image, 150);
    }



    public void tick() {

        x += velX;
        y += velY;

        // If enemy collides, they automatically pick a new direction.
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Block) {
                // Play around with this to get a better enemy AI. Lil' glitchy...
                if(getBoundsBig().intersects(tempObject.getBounds())) {
                    velY *= -1;
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
