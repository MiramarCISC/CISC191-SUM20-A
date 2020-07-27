package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.events.KeyInput;
import edu.sdccd.cisc191.wizardGame.events.MouseInput;
import edu.sdccd.cisc191.wizardGame.gui.anim.Camera;
import edu.sdccd.cisc191.wizardGame.gui.screen.levels.AbstractLevel;
import edu.sdccd.cisc191.wizardGame.gui.screen.levels.LevelOne;
import edu.sdccd.cisc191.wizardGame.gui.screen.levels.LevelTwo;
import edu.sdccd.cisc191.wizardGame.objects.Handler;
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

    /** Class references */
    private Game game;
    private Window frame;
    private Canvas canvas = new Canvas();

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
    private JButton pauseBtn, respawnBtn;

    /**
     * GamePanel constructor.
     * @param frame     {@code Window} to place the panel in.
     */
    public GamePanel(Window frame) {
        super(frame);
        this.frame = frame;
        this.game = frame.getGame();

        // Load in the sprite sheets. One for the levels, one for characters.
        ss = new SpriteSheet(loader.loadImage("/main_sheet.png"));
        cs = new SpriteSheet(loader.loadImage("/wizard_sheet.png")); // character sheet
        this.setLevel(1);  // Start with level 1


        // Create layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        // Create buttons
        this.pauseBtn = new JButton("PAUSE");
        this.respawnBtn = new JButton("TRY AGAIN?");
        this.respawnBtn.setVisible(false);

        // Add canvas
        layeredPane.add(canvas, new Integer(1));
        // Add buttons. Make sure its greater than 1 (the canvas) to stack on top.
        layeredPane.add(pauseBtn, new Integer(2));
        layeredPane.add(respawnBtn, new Integer(2));

        canvas.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        pauseBtn.setBounds(225, 5, 100, 50);  // Underneath HUD, frame.getWidth() could cause problems due to device type.
        respawnBtn.setBounds((frame.getWidth() / 2) - 100, (frame.getHeight() / 2) - 25, 200, 50);  // Center

        this.add(layeredPane);
        this.addButtonListeners();
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
                render();
                frames++;
            }

            if (game.gamePaused()) {
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
        if (!game.gamePaused())
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

        canvas.addMouseListener(new MouseInput(handler, camera, this.game, ss, cs));
        canvas.addKeyListener(new KeyInput(handler));  // is getting null for some reason?
    }

    public void resetGame() {
        // Resets hp, lives and resets entire game back to level One.
        handler.clearHandler();
        this.game.setHp(100); // debug
        this.game.setAmmo(50);
        this.game.setLives(3);
        setLevel(1);

    }

    public void showRespawn() {
        game.wizardDied();
        respawnBtn.setVisible(true);
    }

    public void respawn() {
        currLevel.respawn();
    }

    /** Accessor methods */
    public boolean isGameRunning()            { return this.isRunning; }
    public AbstractLevel getLevel()           { return this.currLevel; }
    public Handler getHandler()               { return this.currLevel.getHandler(); }
    public Camera getCamera()                 { return this.currLevel.getCamera(); }

   //public Frame getFrame()                   { return this.getFrame(); }

    /** Modifier methods */
    public void setHandler()                  { this.handler = currLevel.getHandler(); }
    public void setLevel(int levelNumb){
        // Important method, determines which level to control.
        switch (levelNumb){
            case 1: currLevel = new LevelOne(this.game, this);
                    break;
            case 2: currLevel = new LevelTwo(this.game, this);
                    break;
        }
        this.update();
    }

    /**
     * Add all button listeners.
     */
    protected void addButtonListeners() {
        /** Pause button mouse listener */
        pauseBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {frame.changePanel("pause"); game.pauseGame(); releaseKeys(); }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        /** Respawn button mouse listener */
        respawnBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                respawn();
                frame.getGame().wizardRespawn();
                respawnBtn.setVisible(false);
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    /**
     * Releases all keys.
     * To prevent wizard from continuing movement during change of state.
     */

    public void releaseKeys() {
        handler.setUp(false);
        handler.setDown(false);
        handler.setLeft(false);
        handler.setRight(false);
    }

    /**
     * Nullifies parent {@code GeneralPanel} paintComponent method to have
     * this GamePanel paint its own contents with Canvas.
     */
    @Override
    protected void paintComponent(Graphics g) {}
}
