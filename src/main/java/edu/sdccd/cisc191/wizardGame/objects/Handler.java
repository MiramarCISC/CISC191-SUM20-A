package edu.sdccd.cisc191.wizardGame.objects;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    /* The purpose of the handler class is to update
        every GameObject in the application at one time, in one place.
        As opposed to doing that separately, in the child class for example */

    // Essentially - This is an array of every object in the game.
   // public Handler() {
       // object = new LinkedList<GameObject>(); // Everytime a new level is made we get a new handler.
  //  }


    private boolean up = false, down = false, right = false, left = false, pause = false;

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

    public void clearHandler()
            // Empties the linked list.
            // Will be used to clear the list upon starting a new level or state.
    {
        object.clear();
    }

    public void addObject(GameObject tempObject) {
        object.add(tempObject);
    }

    public void removeObject(GameObject tempObject) {
        object.remove(tempObject);
    }

    public LinkedList<GameObject> getObject() {
      return object;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}

