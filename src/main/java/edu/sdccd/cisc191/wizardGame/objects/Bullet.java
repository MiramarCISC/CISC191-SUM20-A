package edu.sdccd.cisc191.wizardGame.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

/**
 * Bullet game object.
 * Bullet velocity depends on how far away the mouse click from the
 * {@code Wizard} instance.
 *
 * @author Jordan Tobin
 *
 * Editor: Mark Lucernas
 * Date: 2020-06-08
 */
public class Bullet extends GameObject {

    private Handler handler;

    /**
     * No args Bullet constructor.
     */
    public Bullet() { super(); }

    /**
     * Bullet constructor.
     * @param x         X coordinate
     * @param y         Y coordinate
     * @param id        Object ID
     * @param handler   Handler to handle all GameObjects
     * @param mx        Bullet X speed
     * @param my        Bullet Y speed
     * @param ss        Main Spritesheet
     */
    public Bullet(int x, int y, ID id, Handler handler, int mx, int my, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;


        velX = (mx - x) / 10;
        velY = (my - y) / 10;
    }

    /**
     * Tick method to remove Bullet object from handler on collision.
     */
    public void tick() {
        x += velX;
        y += velY;

        // If bullet intersects with a block, remove said bullet.
        for(int i = 0; i < handler.getObject().size(); i++) {
            GameObject tempObject = handler.getObject().get(i);

            if (tempObject.getId() == ID.Block)
                if (getBounds().intersects(tempObject.getBounds()))
                    handler.removeObject(this);
        }
    }

    /**
     * Render method to draw in {@code Canvas}.
     */
    public void render(Graphics g) {
        g.setColor(Color.magenta);
        g.fillOval(x, y, 8, 8);
    }

    /**
     *  Get Bullet current bounds.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 8);
    }

    /** Accessor methods */
    public Handler getHandler()             { return this.handler; }

    /** Modifier methods */
    public void setHandler(Handler handler) { this.handler = handler; }
}
