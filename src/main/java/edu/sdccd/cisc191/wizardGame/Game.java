package edu.sdccd.cisc191.wizardGame;

import edu.sdccd.cisc191.wizardGame.gui.screen.Window;

/**
 * Controller class controls GameObject properties and behaviors.
 *
 * @author Jordan Tobin
 * @author Mark Lucernas
 * Date: 2020-07-24
 */
public class Game {

    private Window frame;

    // Variables that are critical for controlling the Game.
    private int ammo, hp, lives;

    /**
     * Game constructor.
     * Creates new instance of {@code Window} class to set up the view.
     */
    public Game() {
        this.frame = new Window(this, 1980, 1080, "Wizard Game");
    }

    /** Initialize game */
    public void init() {
        // Initialize Wizard stats
        this.ammo = 50;
        this.hp = 100;
        this.lives = 3;
        // Initialize frame
        this.frame.init();
    }

    /** Accessors methods */
    public Window getFrame()              { return frame; }
    public int getAmmo()                  { return ammo; }
    public int getLives()                 { return lives; }
    public int getHp()                    { return hp; }

    /** Modifiers methods */
    public void setHp(int hp)             { this.hp = hp; }
    public void setAmmo (int ammo)        { this.ammo = ammo; }
    public void setLives(int lives)       { this.lives = lives; }
    public void incAmmo(int inc)          { this.ammo += inc; }
    public void decAmmo()                 { this.ammo-= 1; }
    public void decHp()                   { this.hp--; }
    public void decLives()                { this.lives--; }

    /**
     * Main method to instantiate and initialize this Game class.
     */
    public static void main(String[] args) {
        new Game().init();
    }
}
