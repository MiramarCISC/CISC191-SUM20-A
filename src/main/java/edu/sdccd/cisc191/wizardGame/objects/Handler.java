package edu.sdccd.cisc191.wizardGame.objects;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 * The purpose of the handler class is to update every GameObject in the
 * application at one time, in one place. As opposed to doing that separately,
 * in the child class for example
 *
 * @author Jordan Tobin
 *
 * Editors: Mark Lucernas
 *
 * Date: 2020-07-30
 */
public class Handler {

    private LinkedList<GameObject> object = new LinkedList<GameObject>();
    private boolean up, down, right, left = false;

    /**
     * No args Handler constructor.
     */
    public Handler() {}

    public void tick() {
        // Updates each game object.
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        // Draws or "animates" each object.
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    /** Accessor methods */
    public LinkedList<GameObject> getObject()   { return this.object; }
    public boolean isUp()                       { return this.up; }
    public boolean isDown()                     { return this.down; }
    public boolean isRight()                    { return this.right; }
    public boolean isLeft()                     { return this.left; }

    /** Modifier methods */
    public void setUp(boolean up)               { this.up = up; }
    public void setDown(boolean down)           { this.down = down; }
    public void setRight(boolean right)         { this.right = right; }
    public void setLeft(boolean left)           { this.left = left; }
    public void addObject(GameObject object)    { this.object.add(object); }
    public void removeObject(GameObject object) { this.object.remove(object); }
    public void clearHandler()                  { this.object.clear(); }
}

