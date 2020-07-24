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

    // Set first state to the main menu.
    private static STATE State = STATE.MENU;

    // Variables that are critical for controlling the Game.
    private int ammo, hp, lives;

    // List of all states in the game.
    public enum STATE{
        MAIN_MENU, MENU, GAME, HELP, PAUSE,
    };

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
        ammo = 50;
        hp = 100;
        lives = 3;
        // Initialize frame
        frame.init();
    }

    /** Accessors methods */
    public STATE getGameState()           { return State; }
    public Window getFrame()                 { return frame; }
    public int getAmmo()                  { return ammo; }
    public int getLives()                 { return lives; }
    public int getHp()                    { return hp; }

    /** Modifiers methods */
    public void setGameState(STATE State) { this.State = State; }
    public void setHp(int hp)             { this.hp = hp; }
    public void setAmmo (int ammo)        { this.ammo = ammo; }
    public void setLives(int lives)       { this.lives = lives; }
    public void incAmmo(int inc)          { ammo += inc; }
    public void decAmmo()                 { ammo-= 1; }
    public void decHp()                   { hp--; }
    public void decLives()                { lives--; }

    /**
     * Main method to instantiate and initialize this Game class.
     */
    public static void main(String[] args) {
        new Game().init();
    }
}
