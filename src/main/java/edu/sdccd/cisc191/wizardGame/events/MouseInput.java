package edu.sdccd.cisc191.wizardGame.events;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.anim.Camera;
import edu.sdccd.cisc191.wizardGame.gui.sound.SoundEffect;
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

    private Camera camera;
    private Handler handler;
    private Game game;
    private SpriteSheet ss;
    private SpriteSheet cs; //Needs two sprite sheets, that's the reason I would like to just have one big sprite sheet.

    /** Coords */
    private int x, y;  // Click location
    private int mx, my;  // Bullet speed

    /**
     * No args MouseInput constructor.
     */
    public MouseInput() {}

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
    public void fireBullet() {
        // Bullet firing.
        for (int i = 0; i < handler.getObject().size(); i++) {
            GameObject tempObject = handler.getObject().get(i);
            if (tempObject.getId() == ID.Player && game.getAmmo() >= 1) {
                // Fire bullet from player location
                handler.addObject(new Bullet(tempObject.getX() + 16, tempObject.getY() + 24, ID.Bullet, handler, mx, my, ss));
                game.decAmmo();
                SoundEffect.BULLET.play();
            }
        }
    }

    /** Accessor methods */
    public Camera getCamera()               { return this.camera; }
    public Handler getHandler()             { return this.handler; }
    public Game getGame()                   { return this.game; }
    public int getX()                       { return this.x; }
    public int getY()                       { return this.y; }
    public int getMx()                      { return this.mx; }
    public int getMy()                      { return this.my; }

    /** Modifier methods */
    public void setCamera(Camera camera)    { this.camera = camera; }
    public void setHandler(Handler handler) { this.handler = handler; }
    public void setGame(Game game)          { this.game = game; }
    public void setX(int x)                 { this.x = x; }
    public void setY(int y)                 { this.y = y; }
    public void setMx(int mx)               { this.mx = mx; }
    public void setMy(int my)               { this.my = my; }


} // End of class
