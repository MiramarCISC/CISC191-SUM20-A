package edu.sdccd.cisc191.wizardGame.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

public abstract class GameObject {

    protected int x, y; // Location of the object
    protected float velX = 0, velY = 0; // Speed at which object moves, in particular direction.
    protected ID id; // Easy way to track objects type.
    protected SpriteSheet ss; // Location of particular image for rendering.

    public abstract void tick(); // Updates position.
    public abstract void render(Graphics g); // Draws image and animation to that position.
    public abstract Rectangle getBounds(); // Track collision detection.

    public GameObject(int x, int y, ID id, SpriteSheet ss){
        this.x = x;
        this.y = y;
        this.id = id;
        this.ss = ss;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
}

