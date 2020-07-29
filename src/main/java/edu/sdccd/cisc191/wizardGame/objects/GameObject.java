package edu.sdccd.cisc191.wizardGame.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

/**
 * Parent game object class that serves as in-game elements scaffolding.
 *
 * @author Jordan Tobin
 *
 * Editor: Mark Lucernas
 * @since 2020-06-08
 */
public abstract class GameObject {

    protected int x, y;          // Location of the object
    protected float velX , velY; // Speed at which object moves, in particular direction.
    protected ID id;             // Easy way to track objects type.
    protected SpriteSheet ss;    // Location of particular image for rendering.

    /**
     * No args GameObject constructor.
     * Initializes x, y fields.
     */
    public GameObject() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * GameObject constructor.
     * @param x     Object X coordinate
     * @param y     Object Y coordinate
     * @param id    Object {@code ID}
     * @param ss    Object {@code Spritesheet} reference
     */
    public GameObject(int x, int y, ID id, SpriteSheet ss){
        this.x = x;
        this.y = y;
        this.id = id;
        this.ss = ss;
    }

    /** Accessor methods */
    public ID getId()               { return id; }
    public int getX()               { return x; }
    public int getY()               { return y; }
    public float getVelX()          { return velX; }
    public float getVelY()          { return velY; }

    /** Modifier methods */
    public void setId(ID id)        { this.id = id; }
    public void setX(int x)         { this.x = x; }
    public void setY(int y)         { this.y = y; }
    public void setVelX(float velX) { this.velX = velX; }
    public void setVelY(float velY) { this.velY = velY; }

    /** Abstract methods */
    public abstract void tick();             // Updates position.
    public abstract void render(Graphics g); // Draws image and animation to that position.
    public abstract Rectangle getBounds();   // Track collision detection.
}

