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
     * Window constructor.
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
        device.setFullScreenWindow(this); // Sets fullscreen based on device.
    }

    /**
     * Display specified game panel and hide the others.
     * @param panelName     Name of the panel to display
     */
    public void changePanel(String panelName) {
        // Quit immediately on quit button click
        if (panelName.equals("quit")) { this.quitGame(); return; }

        allPanels.get(getCurrOpenPanel()).setVisible(false);
        // TODO: Fix loading screen
        // this.showLoadScreen();
        // System.out.println("Loading done");

        // Change panel
        allPanels.get(panelName).setVisible(true);

        // Starts the game only if not already
        if (panelName.equals("game")) {
            GamePanel gamePanel = (GamePanel) allPanels.get("game");
            if (!gamePanel.isGameRunning())
                gamePanel.start();
        }

        this.lastOpenPanel = getCurrOpenPanel();
        this.currOpenPanel = panelName;
    }

    // TODO: Fix with multi-threading
    public void showLoadScreen() {
        // Hide current panel
        if (getCurrOpenPanel() != null)
            if (allPanels.get(getCurrOpenPanel()).isVisible())
                allPanels.get(getCurrOpenPanel()).setVisible(false);

        LoadPanel load = (LoadPanel) allPanels.get("load");

        Thread t1 = new Thread(new Runnable(){

            @Override
            public void run() {
                LoadPanel load = (LoadPanel) allPanels.get("load");
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

    /** Quit game */
    public void quitGame() {
        this.setVisible(false); this.dispose(); System.exit(0);
    }


    /** Accessor methods */
    public Game getGame()                           { return this.game; }
    public Map<String, GeneralPanel> getAllPanels() { return allPanels; }
    public GeneralPanel getPanel(String panelName)  { return allPanels.get(panelName); }
    public String getLastOpenPanel()                { if (lastOpenPanel != null) { return lastOpenPanel; } else { return null; } }
    public String getCurrOpenPanel()                { if (currOpenPanel != null) { return currOpenPanel; } else { return null; } }
}
