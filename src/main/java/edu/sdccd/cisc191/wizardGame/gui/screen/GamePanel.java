package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.events.KeyInput;
import edu.sdccd.cisc191.wizardGame.events.MouseInput;
import edu.sdccd.cisc191.wizardGame.gui.anim.Camera;
import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;
import edu.sdccd.cisc191.wizardGame.objects.Handler;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GamePanel extends GeneralPanel implements Runnable {

    /** Reference to the Game class */
    private Game game;
    private Canvas canvas = new Canvas();

    // Thread variables.
    private boolean isRunning = false;
    private Thread gameThread;

    private AbstractLevel level;

    // Camera and handler.
    private Camera camera;
    private Handler handler;

    // Spritesheets for imagery,
    private BufferedImageLoader loader = new BufferedImageLoader();
    private SpriteSheet ss;
    private SpriteSheet cs; // character sheet
    private BufferedImage sprite_sheet;
    private BufferedImage char_sheet;

    /**
     * GamePanel constructor.
     * @param frame     {@code Window} to place the panel in.
     */
    public GamePanel(Window frame) {
        super(frame);
        this.add(canvas);
        canvas.setPreferredSize(new Dimension(1980, 1080));
        this.game = frame.getGame();

        // Upon the games beginning, the level variable is set to a new instance of the level one class.
        this.setLevel(new LevelOne(this.game, this));

        // Load in the sprite sheets. One for the levels, one for characters.
        ss = new SpriteSheet(loader.loadImage("/main_sheet.png"));
        cs = new SpriteSheet(loader.loadImage("/wizard_sheet.png")); // character sheet
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
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        // Obtain the current frame from the buffer
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        // // Decide what to render depending on level.
        // switch (this.game.getGameState()) {
        //     case MENU: menu.render(g);
        //                break;
        //     case HELP: help.render(g);
        //                break;
        //     case PAUSE: pause.render(g);
        //                 break;
        //     default:level.render(g);
        // }

        level.render(g);

        g.dispose();
        bs.show();
    } // end render method

    public void update(){
        // Update handler, camera and listeners.
        // Useful when changing to a new level or refreshing game.
        handler = getHandler();
        camera = getCamera();
        this.addMouseListener(new MouseInput(handler, camera, this.game, this, ss, cs));
        this.addKeyListener(new KeyInput(handler)); // is getting null for some reason?
    }

    public void resetGame() {
        // Resets hp, lives and resets entire game back to level One. Really should be called resetGame()?
        this.game.setHp(1); // debug
        this.game.setLives(3);
        setLevel(1);
        handler.clearHandler();
        level = new LevelOne(this.game, this);
        update();
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
            case 1: level = new LevelOne(this.game, this);
                    break;
            // TODO:
            // case 2: level = new LevelTwo(this.game, this);
            //         update();
            //         break;
        }
    }

    public boolean isGameRunning() {
        return isRunning;
    }

    public AbstractLevel getLevel(){
        return level;
    }

    public void setLevel(AbstractLevel level) {
        this.level = level;
    }

    public void respawn() {
        level.respawn();
    }

    /**
     * Nullifies parent {@code GeneralPanel} paintComponent to have GamePanel 
     * paint its own content with Canvas.
     */
    @Override
    protected void paintComponent(Graphics g) {}
}
