package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.events.KeyInput;
import edu.sdccd.cisc191.wizardGame.events.MouseInput;
import edu.sdccd.cisc191.wizardGame.gui.Action.ActionManager;
import edu.sdccd.cisc191.wizardGame.gui.anim.Camera;
import edu.sdccd.cisc191.wizardGame.gui.screen.levels.AbstractLevel;
import edu.sdccd.cisc191.wizardGame.gui.screen.levels.Level;
import edu.sdccd.cisc191.wizardGame.objects.Handler;
import edu.sdccd.cisc191.wizardGame.save.DataManager;
import edu.sdccd.cisc191.wizardGame.save.SaveData;
import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

/**
 * The Game panel for {@code Window} class.
 * Overrides {@code GeneralPanel} paintComponent to paint its own with
 * {@code Canvas}.
 *
 * @author Mark Lucernas
 * @author Jordan Tobin
 *
 * Date: 2020-07-24
 */
public class GamePanel extends GeneralPanel implements Runnable {

    /** Action manager */
    protected ActionManager am;

    /** Class references */
    private Game game;
    private Window frame;
    private Canvas canvas;

    /** Thread variables */
    private boolean isRunning = false;
    private Thread gameThread;

    /** Current level instance */
    private AbstractLevel currLevel;

    /** Camera and handler */
    private Camera camera;
    private Handler handler;

    /** SpriteSheets for imagery */
    private BufferedImageLoader loader = new BufferedImageLoader();
    private SpriteSheet ss; // Sprite sheet
    private SpriteSheet cs; // Character sheet

    /** Buttons */
    private JButton pauseBtn, muteBtn, saveBtn, respawnBtn;

    /** Listeners */
    private MouseInput mouseInput;
    private KeyInput keyInput;

    /**
     * No args GamePanel constructor.
     */
    public GamePanel() { super(); }

    /**
     * GamePanel constructor.
     * @param frame     {@code Window} to place the panel in.
     */
    public GamePanel(Window frame) {
        super(frame);
        this.frame = frame;
        this.game = frame.getGame();
        this.canvas = new Canvas();
        this.am = frame.getActionManager();

        // Load in the sprite sheets. One for the levels, one for characters.
        ss = new SpriteSheet(loader.loadImage("/main_sheet.png"));
        cs = new SpriteSheet(loader.loadImage("/wizard_sheet.png")); // character sheet
        this.changeLevel();  // Start with level 1


        // Create layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        // Create buttons
        this.pauseBtn = new JButton("PAUSE");
        this.pauseBtn.addActionListener(am.getWizardGameAction("pauseAction"));
        this.muteBtn = new JButton("MUTE");
        this.muteBtn.addActionListener(am.getWizardGameAction("muteAction"));
        this.saveBtn = new JButton("SAVE");
        this.saveBtn.addActionListener(am.getWizardGameAction("saveAction"));
        this.respawnBtn = new JButton("TRY AGAIN?");
        this.respawnBtn.addActionListener(am.getWizardGameAction("respawnAction"));
        this.respawnBtn.setVisible(false);

        // Add canvas
        layeredPane.add(canvas, new Integer(1));
        // Add buttons. Make sure its greater than 1 (the canvas) to stack on top.
        layeredPane.add(pauseBtn, new Integer(2));
        layeredPane.add(muteBtn, new Integer(2));
        layeredPane.add(saveBtn, new Integer(2));
        layeredPane.add(respawnBtn, new Integer(2));


        canvas.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        pauseBtn.setBounds(225, 5, 100, 50);  // Underneath HUD, frame.getWidth() could cause problems due to device type.
        muteBtn.setBounds(325, 5, 100, 50);
        saveBtn.setBounds(425, 5, 100, 50);
        respawnBtn.setBounds((frame.getWidth() / 2) - 100, (frame.getHeight() / 2) - 25, 200, 50);  // Center

        canvas.setFocusable(true);

        this.add(layeredPane);
    }

    /**
     * Start new Thread and run this class.
     */
    public synchronized void start() {
        if (!isRunning) {
            isRunning = true;
            gameThread = new Thread(this, "GameController");
            gameThread.start();
        }
    }

    /**
     * Gracefully stops Thread and exit {@code Window}.
     */
    public synchronized void stop() {
        if (isRunning) {
            frame.quitGame();
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

            if (game.isGamePaused()) {
                // If the game is paused then interrupt game thread.
                gameThread.interrupt();
            }

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
        if (!game.isGamePaused())
        currLevel.tick();
    }

    public void render() {
        // Obtain buffer strategy. 3 Frames will be preloaded each render.
        // 3 is the ideal buffer strategy.
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        // Obtain the current frame from the buffer
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        currLevel.render(g);

        g.dispose();
        bs.show();

    } // end render method

    public void update(){
        // Update handler, camera and listeners.
        // Useful when changing to a new level or refreshing game.
        handler = getHandler();
        camera = getCamera();

        mouseInput = new MouseInput(handler, camera, this.game, ss, cs);
        keyInput = new KeyInput(handler);

        canvas.addMouseListener(mouseInput);
        canvas.addKeyListener(keyInput);  // is getting null for some reason?
        canvas.requestFocusInWindow();
    }

    public void resetGame() {
        // Resets hp, lives and resets entire game back to level One.
        handler.clearHandler();      // Remove all game objects from handler.
        this.game.setHp(100);        // set hp to full
        this.game.setAmmo(50);       // refill ammo
        this.game.setLives(3);       // refill lives
        this.game.setLevelNumber(1); // begin at level 1.
        changeLevel();               // Load level.
    }

    public void loadGame() {
        try {
            SaveData data = (SaveData) DataManager.load("1.save");
            this.game.setAmmo(data.ammo);
            this.game.setHp(data.hp);
            this.game.setLives(data.lives);
            this.game.setLevelNumber(data.level);
            changeLevel();
        }
        catch (Exception e) {
            System.out.println("Couldn't load save data: " + e.getMessage());
        }
    }

    public void showRespawn() {
        game.wizardDied();
        respawnBtn.setVisible(true);
    }

    public void respawn() {
        currLevel.respawn();
    }

    /** Accessor methods */
    public boolean isGameRunning()                   { return this.isRunning; }
    public AbstractLevel getLevel()                  { return this.currLevel; }
    public Handler getHandler()                      { return this.currLevel.getHandler(); }
    public Camera getCamera()                        { return this.currLevel.getCamera(); }
    public Frame getFrame()                          { return this.frame; }
    public Canvas getCanvas()                        { return this.canvas; }
    public MouseInput getMouseInput()                { return this.mouseInput; }
    public KeyInput getKeyInput()                    { return this.keyInput; }
    public JButton getRespawnBtn()                   { return respawnBtn; }
    public Game getGame()                              { return this.game; }

    /** Modifier methods */
    public void setHandler(Handler handler)          { this.handler = handler; }
    public void setCamera(Camera camera)             { this.camera = camera; }
    public void setFrame(Window frame)               { this.frame = frame; }
    public void setCanvas(Canvas canvas)             { this.canvas = canvas; }
    public void setMouseInput(MouseInput mouseInput) { this.mouseInput = mouseInput; }
    public void setKeyInput(KeyInput keyInput)       { this.keyInput = keyInput; }
    public void changeLevel()                        { this.currLevel = new Level(this.game, this); this.update(); }


    /**
     * Nullifies parent {@code GeneralPanel} paintComponent method to have
     * this GamePanel paint its own contents with Canvas.
     */
    @Override
    protected void paintComponent(Graphics g) {}


}
