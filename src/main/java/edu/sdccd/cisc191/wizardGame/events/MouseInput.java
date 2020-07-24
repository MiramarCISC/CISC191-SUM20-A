package edu.sdccd.cisc191.wizardGame.events;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.Game.STATE;
import edu.sdccd.cisc191.wizardGame.gui.anim.Camera;
import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
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

    // These buttons are here so we can check for their size values and calculate clicks. Why are these public...good question.
    private Rectangle resButton = new Rectangle(810, 150, 150, 75);
    private Rectangle playButton = new Rectangle(810, 150, 150, 75);
    private Rectangle helpButton = new Rectangle(810, 450, 150, 75);
    private Rectangle quitButton = new Rectangle(810, 750, 150, 75);
    private Rectangle escButton = new Rectangle(250, 5, 75, 25);

    private Handler handler;
    private Camera camera;
    private Game game;
    private GamePanel gamePanel;
    private SpriteSheet ss;
    private SpriteSheet cs; //Needs two sprite sheets, that's the reason I would like to just have one big sprite sheet.

    private int mx;
    private int my;

    private int x;
    private int y;

    public MouseInput(Handler handler, Camera camera, Game game, GamePanel gamePanel, SpriteSheet ss, SpriteSheet cs) {
        this.handler = handler;
        this.camera = camera;
        this.game = game;
        this.gamePanel = gamePanel;
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

        // Switch statement to determine which method to use for checks.
        switch (game.getGameState()) {
            case GAME: checkGame();
                        break;
            case MENU: checkMenu();
                        break;
            case PAUSE: checkPause();
                        break;
            case HELP: checkHelp();
                        break;
        }
    }

    /*CHECKING AND UTIlITY FUNCTIONS*/

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

    /* All these function could go in a listener class if MouseInput exports the coordinate variables.*/
    private void checkGame() {
        // Checks for all possible mouse events in game
        fireBullet();

        if (resButton.contains(mx, my) && game.getHp() <= 0) {
            // Reset button.
            game.setHp(100);
            gamePanel.respawn();
        }

        else if (escButton.contains(x, y)) {
            // Pauses the game when pause button is clicked during game.
            game.setGameState(STATE.PAUSE);
        }
    }

    private void checkMenu() {
        // Checks for all possible menu events in game
        if (playButton.contains(x, y)) {
            game.setGameState(STATE.GAME);
        }

        if (helpButton.contains(x, y)) {
            game.setGameState(STATE.HELP);
        }

        if (quitButton.contains(x, y)) {
            // gamePanel.quit();
        }
    }

    private void checkPause() {
        if (playButton.contains(x, y)) {
            game.setGameState(STATE.GAME);
        }

        if (quitButton.contains(x, y)) {

            // gamePanel.quit();
        }
    }

    private void checkHelp() {
        if (quitButton.contains(x, y)) {
            game.setGameState(STATE.MENU);
        }
    }

} // End of class
