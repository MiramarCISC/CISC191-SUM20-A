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
 *
 * Date: 2020-07-23
 */
public class Window extends JFrame {

    /** Game class reference */
    private Game game;

    /** Frame components */
    private int frameWidth;
    private int frameHeight;
    private JLayeredPane layeredPane;

    /** Game panels Map */
    private Map<String, GeneralPanel> allPanels
        = new HashMap<String, GeneralPanel>();

    /** Last opened panel name */
    private String lastOpenPanel;

    /** Currently open panel name */
    private String currOpenPanel;

    /** Used for frame fullscreen */
    private GraphicsDevice device =
        GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    /**
     * No args Window constructor.
     */
    public Window() { super(); }

    /**
     * Window constructor.
     * @param game      {@code Game} instance reference
     * @param width     Width of the window
     * @param height    Height of the window
     * @param title     Title of the window
     */
    public Window(Game game, int width, int height, String title) {
        super(title);

        // Instantiate fields
        this.game = game;
        frameWidth = width;
        frameHeight = height;

        // Frame configs
        this.setSize(new Dimension(frameWidth, frameHeight));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable((false));
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
        allPanels.put("menu", menuPanel);
        allPanels.put("pause", pausePanel);
        allPanels.put("help", helpPanel);
        allPanels.put("load", loadPanel);
        allPanels.put("game", gamePanel);

        // Add all panels into layers
        layeredPane.add(menuPanel, new Integer(1));
        layeredPane.add(pausePanel, new Integer(1));
        layeredPane.add(helpPanel, new Integer(1));
        layeredPane.add(loadPanel, new Integer(1));
        layeredPane.add(gamePanel, new Integer(1));

        // Start with menu panel
        menuPanel.setVisible(true);
        this.currOpenPanel = "menu";

        // Frame configs
        this.add(layeredPane);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.device.setFullScreenWindow(this); // Sets fullscreen based on device.
    }

    /**
     * Display specified game panel and hide the others.
     * @param panelName         Name of the panel to display
     * @param showLoadScreen    Switch if loading panel will display
     */
    public void changePanel(String panelName, boolean showLoadScreen) {
        // Quit immediately on quit button click
        if (panelName.equals("quit")) { this.quitGame(); return; }

        if (showLoadScreen)
            showLoadScreen(4000);

        this.allPanels.get(getCurrOpenPanelName()).setVisible(false);

        // Change panel
        this.allPanels.get(panelName).setVisible(true);

        // Starts the game only if not already
        if (panelName.equals("game")) {
            GamePanel gamePanel = (GamePanel) allPanels.get("game");
            if (!gamePanel.isGameRunning())
                gamePanel.start();
        }

        this.lastOpenPanel = getCurrOpenPanelName();
        this.currOpenPanel = panelName;
    }

    /**
     * Hide current panel and show loading screen for given duration.
     * @param duration  Loading screen duration in milliseconds
     */
    public void showLoadScreen(int duration) {
        String currOpenPanel = getCurrOpenPanelName();
        // Hide current panel
        if (currOpenPanel != null)
            if (this.allPanels.get(currOpenPanel).isVisible())
                this.allPanels.get(currOpenPanel).setVisible(false);

        LoadPanel load = (LoadPanel) allPanels.get("load");
        load.setDuration(duration);

        Thread t1 = new Thread(new Runnable(){

            @Override
            public void run() {
                LoadPanel load = (LoadPanel) allPanels.get("load");
                load.start();
                try {
                    Thread.sleep(duration);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                load.stop();
            }
        });
        t1.start();

        // Restore current open panel
        allPanels.get(currOpenPanel).setVisible(true);
    }

    /** Quit game */
    public void quitGame() {
        this.setVisible(false); this.dispose(); System.exit(0);
    }

    /** Accessor methods */
    public Game getGame()                           { return this.game; }
    public Map<String, GeneralPanel> getAllPanels() { return this.allPanels; }
    public GeneralPanel getPanel(String panelName)  { return this.allPanels.get(panelName); }
    public String getLastOpenPanelName()            { if (this.lastOpenPanel != null) return this.lastOpenPanel; else return null; }
    public String getCurrOpenPanelName()            { if (this.currOpenPanel != null) return this.currOpenPanel; else return null; }
    public GeneralPanel getLastOpenPanel()          { if (this.lastOpenPanel != null) return this.allPanels.get(this.lastOpenPanel); return null; }
    public GeneralPanel getCurrOpenPanel()          { if (this.currOpenPanel != null) return this.allPanels.get(this.currOpenPanel); return null; }

    /** Modifier methods */
    public void setGame(Game game) { this.game = game; }
}
