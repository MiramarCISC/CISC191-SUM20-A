package edu.sdccd.cisc191.wizardGame.events;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.anim.Camera;
import edu.sdccd.cisc191.wizardGame.objects.Bullet;
import edu.sdccd.cisc191.wizardGame.objects.GameObject;
import edu.sdccd.cisc191.wizardGame.objects.Handler;
import edu.sdccd.cisc191.wizardGame.objects.ID;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

/* Note about whats happening here
 MouseInput is looking for mouse clicks that match the coordinates of the buttons rectangle.
 If the input matches then a button is pushed.
 */

public class MouseInput extends MouseAdapter {

    private Handler handler;
    private Camera camera;
    private Game game;
    private SpriteSheet ss;
    private SpriteSheet cs; //Needs two sprite sheets, that's the reason I would like to just have one big sprite sheet.

    /** Coords */
    private int mx, my;  // Bullet speed
    private int x, y;  // Click location

    public MouseInput(Handler handler, Camera camera, Game game, SpriteSheet ss, SpriteSheet cs) {
        this.handler = handler;
        this.camera = camera;
        this.game = game;
        this.ss = ss;
        this.cs = cs;
    }

    public void mousePressed(MouseEvent e) {
        // Current mouse click and camera location (players location)
        mx = (int) (e.getX() + camera.getX());
        my = (int) (e.getY() + camera.getY());

        // Raw coordinates no camera, use for menu, buttons etc.
        x = e.getX();
        y = e.getY();

        // Animate bullet fire
        fireBullet();
    }

    /* CHECKING AND UTIlITY FUNCTIONS */
    private void fireBullet() {
        // Bullet firing.
        for (int i = 0; i < handler.getObject().size(); i++) {
            GameObject tempObject = handler.getObject().get(i);
            if (tempObject.getId() == ID.Player && game.getAmmo() >= 1) {
                // Fire bullet from player location
                handler.addObject(new Bullet(tempObject.getX() + 16, tempObject.getY() + 24, ID.Bullet, handler, mx, my, ss));
                game.decAmmo();
            }
        }
    }

} // End of class
