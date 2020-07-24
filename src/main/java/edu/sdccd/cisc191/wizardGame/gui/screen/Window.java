package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import edu.sdccd.cisc191.wizardGame.Game;

/**
 * The main frame of the game.
 * Stacks JPanels on top of each other for fast switching using JLayeredPane.
 *
 * @author Jordan Tobin
 * @author Mark Lucernas

 * Date: 2020-07-23
 */
public class Window {

    /** Game class reference */
    private Game game;

    /** Frame components */
    private JFrame frame = new JFrame();
    private int frameWidth;
    private int frameHeight;
    private JLayeredPane layeredPane;

    /** Game panels Map */
    private Map<String, GeneralPanel> gamePanels
        = new HashMap<String, GeneralPanel>();

    /** Last opened panel name */
    private String lastOpenPanel;

    /** Currently open panel name */
    private String currOpenPanel;

    /** Used for frame fullscreen */
    private GraphicsDevice device =
        GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    /**
     * Window constructor.
     * @param width     Width of the window
     * @param height    Height of the window
     * @param title     Title of the window
     */
    public Window(Game game, int width, int height, String title) {
        this.game = game;
        frame.setTitle(title);
        frameWidth = width;
        frameHeight = height;
    }

    public void init() {
        // Create layered pane
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, frameWidth, frameHeight);

        // Init panels
        MenuPanel menuPanel = new MenuPanel(this);
        GamePanel gamePanel = new GamePanel(this);
        HelpPanel helpPanel = new HelpPanel(this);
        PausePanel pausePanel = new PausePanel(this);
        LoadPanel loadPanel = new LoadPanel(this);

        // Set panel bounds
        menuPanel.setBounds(0, 0, frameWidth, frameHeight);
        gamePanel.setBounds(0, 0, frameWidth, frameHeight);
        helpPanel.setBounds(0, 0, frameWidth, frameHeight);
        pausePanel.setBounds(0, 0, frameWidth, frameHeight);
        loadPanel.setBounds(0, 0, frameWidth, frameHeight);

        // Store panels into gamePanels
        gamePanels.put("menu", menuPanel);
        gamePanels.put("game", gamePanel);
        gamePanels.put("help", helpPanel);
        gamePanels.put("pause", pausePanel);
        gamePanels.put("load", loadPanel);

        // Add all panels into layers
        layeredPane.add(menuPanel, new Integer(1));
        layeredPane.add(gamePanel, new Integer(1));
        layeredPane.add(helpPanel, new Integer(1));
        layeredPane.add(pausePanel, new Integer(1));
        layeredPane.add(loadPanel, new Integer(1));

        // Start with menu panel
        menuPanel.setVisible(true);
        this.currOpenPanel = "menu";

        // Frame configs
        frame.setSize(new Dimension(frameWidth, frameHeight));
        frame.add(layeredPane);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(frameWidth, frameHeight));
        frame.setLayout(null);
        frame.setResizable((false));
        frame.setLocationRelativeTo(null);
        device.setFullScreenWindow(frame);
    }

    /**
     * Display specified game panel and hide the others.
     * @param panelName     Name of the panel to display
     */
    protected void changePanel(String panelName) {
        // Quit immediately on quit button click
        if (panelName.equals("quit")) { this.quitGame(); return; }

        gamePanels.get(getCurrOpenPanel()).setVisible(false);
        // this.showLoadScreen();
        // System.out.println("Loading done");

        // Change panel
        gamePanels.get(panelName).setVisible(true);

        if (panelName.equals("game")) {
            GamePanel gamePanel = (GamePanel) gamePanels.get("game");
            if (!gamePanel.isGameRunning())
                gamePanel.start();
        }

        this.lastOpenPanel = getCurrOpenPanel();
        this.currOpenPanel = panelName;
    }

    // TODO: Fix with multi-threading
    protected void showLoadScreen() {
        // Hide current panel
        if (getCurrOpenPanel() != null)
            if (gamePanels.get(getCurrOpenPanel()).isVisible())
                gamePanels.get(getCurrOpenPanel()).setVisible(false);

        LoadPanel load = (LoadPanel) gamePanels.get("load");
        Thread t1 = new Thread(new Runnable(){

            @Override
            public void run() {
                load.start();
                try {
                    Thread.sleep(2000);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                load.stop();
            }
        });
        t1.start();
    }

    /**
     * Quit game.
     */
    public void quitGame(){
        // If quit game is activated, the window will close and the program will exit.
        frame.setVisible(false);
        frame.dispose();
        System.exit(0);
    }

    public Game getGame() {
        return this.game;
    }

    /**
     * Get last opened panel name.
     * @return Name of {@code lastOpenPanel}, else null
     */
    public String getLastOpenPanel() {
        if (lastOpenPanel != null) { return lastOpenPanel; } else { return null; }
    }

    /**
     * Get currently open panel name.
     * @return Name of {@code lastOpenPanel}, else null
     */
    public String getCurrOpenPanel() {
        if (currOpenPanel != null) { return currOpenPanel; } else { return null; }
    }
}
