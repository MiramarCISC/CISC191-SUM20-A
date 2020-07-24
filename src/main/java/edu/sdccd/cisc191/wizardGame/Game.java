package edu.sdccd.cisc191.wizardGame;

import edu.sdccd.cisc191.wizardGame.events.KeyInput;
import edu.sdccd.cisc191.wizardGame.events.MouseInput;
import edu.sdccd.cisc191.wizardGame.gui.anim.Camera;
import edu.sdccd.cisc191.wizardGame.gui.screen.Menu;
import edu.sdccd.cisc191.wizardGame.gui.screen.*;
import edu.sdccd.cisc191.wizardGame.objects.Bullet;
import edu.sdccd.cisc191.wizardGame.objects.GameObject;
import edu.sdccd.cisc191.wizardGame.objects.Handler;
import edu.sdccd.cisc191.wizardGame.objects.ID;
import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

    // To be used in serialization.
    private static final long serialVersionUID = 1L;

    // Set first state to the main menu.
    private static STATE State = STATE.MENU;

    // Fullscreen size.
    private final int SCREEN_WIDTH = 1920;
    private final int SCREEN_HEIGHT = 1080;

    // Thread variables.
    private boolean isRunning = false;
    private Thread gameThread;

    // Camera and handler.
    private Camera camera;
    private Handler handler;

    // Objects used for menus
    private Menu menu;
    private Help help;
    private Pause pause;

    // Spritesheets for imagery,
    private BufferedImageLoader loader = new BufferedImageLoader();
    private SpriteSheet ss;
    private SpriteSheet cs; // character sheet
    private BufferedImage sprite_sheet = null;
    private BufferedImage char_sheet = null;

    // GUI Frame variables.
    private JFrame frame;
    private GraphicsDevice device = GraphicsEnvironment // Used for fullscreen.
            .getLocalGraphicsEnvironment().getScreenDevices()[0];

    // Variables that are critical for controlling the Game.
    private int ammo = 50;
    private int hp = 100;
    private int lives = 3;
    private AbstractLevel level;

    // List of all states in the game.
    public enum STATE{
        MAIN_MENU,
        MENU,
        GAME,
        HELP,
        PAUSE,
    };

    public Game() {

        // Create a new fullscreen window (1080p)
        frame = new JFrame("Wizard Game");
        frame.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_WIDTH));
        frame.setMaximumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        frame.setMinimumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        // Set fundamental attributes.
        frame.setResizable((false));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //device.setFullScreenWindow(frame); // Uncomment for true fullscreen

        // Upon the games beginning, the level variable is set to a new instance of the level one class.
        level = new LevelOne(this);

        // Create instances of every menu.
        menu = new Menu();
        help = new Help();
        pause = new Pause();

        // Load in the sprite sheets. One for the levels, one for characters.
        ss = new SpriteSheet(loader.loadImage("/main_sheet.png"));
        cs = new SpriteSheet(loader.loadImage("/wizard_sheet.png")); // character sheet

        // Update the game, handler, camera etc.
        this.Update();

        // Add everything to the frame, validate and begin thread.
        frame.add(this);
        frame.validate();
        start();
    }

    public synchronized void start() {
        if (!isRunning) {
            isRunning = true;
            gameThread = new Thread(this, "GameController");
            gameThread.start();
        }
    }

    public synchronized void stop() {
        if (isRunning) {
            isRunning = false;
            System.exit(0);
        }
    }

    public void run() {
        // Main game loop timer.
        // Ticks 60 times a second. (Motion)
        // Renders 1000 times a second. (Drawing)

        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    public void tick() {
        // Level variable determines which level to tick.
        level.tick();
    }

    public void render() {
        // Obtain bugger strategy. 3 Frames will be preloaded each render.
        // 3 is the ideal buffer strategy.
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        // Obtain the current frame from the buffer
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        // Decide what to render depending on level.
        switch (State) {
            case MENU: menu.render(g);
                        break;
            case HELP: help.render(g);
                        break;
            case PAUSE: pause.render(g);
                        break;
            default:level.render(g);
            }

        g.dispose();
        bs.show();
    } // end render method


    public static void quit(){
        // If quit game is activated, the window will close and the program will exit.
        System.exit(0);
    }

    public void Update(){
        // Update handler, camera and listeners.
        // Useful when changing to a new level or refreshing game.
        handler = getHandler();
        camera = getCamera();
        this.addMouseListener(new MouseInput(handler, camera, this, ss, cs));
        this.addKeyListener(new KeyInput(handler)); // is getting null for some reason?
    }

    public void resetGame() {
        // Resets hp, lives and resets entire game back to level One. Really should be called resetGame()?
        setHp(1); // debug
        setLives(3);
        setLevel(1);
        handler.clearHandler();
        level = new LevelOne(this);
        Update();
    }

    /* Setters and getters for private variables. */
    public STATE getGameState(){
        return State;
    }

    public void setGameState(STATE State){
        this.State = State;
    }
    public int getAmmo(){
        return ammo;
    }

    public void setAmmo (int ammo){
        this.ammo = ammo;
    }

    public void incAmmo(int inc){
        ammo += inc;
    }

    public void decAmmo() {
        ammo-= 1;
    }

    public int getHp(){
        return hp;
    }

    public void setHp(int hp){
        this.hp = hp;
    }

    public void decHp(){
        hp--;
    }

    public int getLives(){
        return lives;
    }

    public void decLives(){
        lives--;
    }

    public void setLives(int lives){
        this.lives = lives;
    }

    public Handler getHandler(){

        return level.getHandler();
    }

    public void setHandler(){
        this.handler = level.getHandler();

    }

    public Camera getCamera(){
        return level.getCamera();
    }

    public void setLevel(int levelNumb){
        // Important method, determines which level to control.
        switch (levelNumb){
            case 1: level = new LevelOne(this);
                            break;
            case 2: level = new LevelTwo(this);
                            Update();
                            break;
        }
    }

    public AbstractLevel getLevel(){
        return level;
    }

    public void respawn() {
        level.respawn();
    }

    // Main method starts one Game instance and thus one GameController thread.
    public static void main(String[] args) {
        new Game();
    }
}
