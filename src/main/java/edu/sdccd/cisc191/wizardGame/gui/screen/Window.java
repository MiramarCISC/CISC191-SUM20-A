package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.Action.ActionManager;

/**
 * The main frame of the game.
 * Stacks JPanels on top of each other for fast switching using JLayeredPane.
 *
 * @author Jordan Tobin
 * @author Mark Lucernas
 *
 * Date: 2020-07-23
 * Resize for All Displays
 * @author Tamer Elsawaf
 * 2020-08-03
 */
public class Window extends JFrame {

    /** Game class reference */
    private Game game;

    /** Action Manager */
    private ActionManager actionManager;

    /** Frame components */
    private int frameWidth;
    private int frameHeight;
    private JLayeredPane layeredPane;

    /** Game panels Map */
    public Map<String, GeneralPanel> allPanels
        = new HashMap<String, GeneralPanel>();

    /** Last opened panel name */
    private String lastOpenPanel;

    /** Currently open panel name */
    private String currOpenPanel;

    /** Used for frame fullscreen */
    private GraphicsDevice device =
        GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    /** Window measurements */
    private int xCenter;
    private int yCenter;

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

        // TODO: remove width and height parameter
        frameWidth = width;
        frameHeight = height;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frameWidth = (int) screenSize.getWidth();
        this.frameHeight = (int) screenSize.getHeight();

        this.xCenter = this.frameWidth / 2;
        this.yCenter = this.frameHeight / 2;

        // the screen height
        screenSize.getHeight();
        // the screen width
        screenSize.getWidth();
        // Frame configs
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setMaximumSize(new Dimension(frameWidth, frameHeight));
        this.setMinimumSize(new Dimension(frameWidth, frameHeight));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable((false));

        actionManager = new ActionManager(this);
        actionManager.initializeActions(this);
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
        EndGameFloatingPanel endGamePanel = new EndGameFloatingPanel(this);

        // Set panel bounds
        menuPanel.setBounds(0, 0, frameWidth, frameHeight);
        gamePanel.setBounds(0, 0, frameWidth, frameHeight);
        helpPanel.setBounds(0, 0, frameWidth, frameHeight);
        pausePanel.setBounds(0, 0, frameWidth, frameHeight);
        loadPanel.setBounds(0, 0, frameWidth, frameHeight);
        // Set floating panel bounds centered
        endGamePanel.setBounds(this.xCenter - (endGamePanel.getWidth() / 2),
                               this.yCenter - (endGamePanel.getHeight() / 2),
                               endGamePanel.getWidth(), endGamePanel.getHeight());

        // Store panels into gamePanels
        allPanels.put("menu", menuPanel);
        allPanels.put("pause", pausePanel);
        allPanels.put("help", helpPanel);
        allPanels.put("load", loadPanel);
        allPanels.put("game", gamePanel);
        allPanels.put("endgame", endGamePanel);

        // Add all panels into layers
        layeredPane.add(menuPanel, new Integer(1));
        layeredPane.add(pausePanel, new Integer(1));
        layeredPane.add(helpPanel, new Integer(1));
        layeredPane.add(loadPanel, new Integer(1));
        layeredPane.add(gamePanel, new Integer(1));
        layeredPane.add(endGamePanel, new Integer(2)); // Floating panel

        // Start with menu panel
        menuPanel.setVisible(true);
        this.currOpenPanel = "menu";

        // Frame configs
        this.add(layeredPane);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.device.setFullScreenWindow(this); // Sets fullscreen based on device.

        /** Dirty quick fix to address the {@code RespawnAction} and {@code PauseAction}
         * from throwing null pointer exception.
         */
        actionManager.getWizardGameAction("pauseAction").gamePanel = gamePanel;
        actionManager.getWizardGameAction("respawnAction").gamePanel = gamePanel;
        actionManager.getWizardGameAction("saveAction").gamePanel = gamePanel;
        actionManager.getWizardGameAction("loadAction").gamePanel = gamePanel;
        actionManager.getWizardGameAction("muteAction").gamePanel = gamePanel;
    }

    /**
     * Display specified game panel and hide the others.
     * @param panelName     Name of the panel to display
     * @param isLoadScreen  Switch if loading panel will display
     */
    public void changePanel(String panelName, boolean isLoadScreen) {
        // Quit immediately on quit button click
        if (panelName.equals("quit")) { this.quitGame(); return; }

        if (isLoadScreen)
            this.showLoadScreen(4000);

        // Do not hide current panel if end game
        if (!panelName.equals("endgame"))
            allPanels.get(getCurrOpenPanel()).setVisible(false);

        allPanels.get(panelName).setVisible(true);

        if (panelName.equals("game")) {
            allPanels.get(panelName).setVisible(true);
            GamePanel gamePanel = (GamePanel) allPanels.get("game");
            gamePanel.getCanvas().requestFocusInWindow();
            // Starts the game only if not already
            if (!gamePanel.isGameRunning())
                gamePanel.start();
        }

        this.lastOpenPanel = getCurrOpenPanel();
        this.currOpenPanel = panelName;
    }

    /**
     * Hide current panel and show loading screen for given duration.
     * @param duration  Loading screen duration in milliseconds
     */
    public void showLoadScreen(int duration) {
        String currOpenPanel = getCurrOpenPanel();
        // Hide current panel
        if (currOpenPanel != null)
            if (allPanels.get(currOpenPanel).isVisible())
                allPanels.get(currOpenPanel).setVisible(false);

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

    public void endGame(boolean isWinner) {
        EndGameFloatingPanel endGamePanel = (EndGameFloatingPanel) this.getAllPanels().get("endgame");
        if (isWinner)
            endGamePanel.displayWinner();
        else
            endGamePanel.displayLoser();

        // Show endgame floating window
        this.changePanel("endgame", false);
    }

    /** Quit game */
    public void quitGame() {
        this.setVisible(false); this.dispose(); System.exit(0);
    }


    /** Accessor methods */
    public Game getGame()                           { return this.game; }
    public Map<String, GeneralPanel> getAllPanels() { return this.allPanels; }
    public GeneralPanel getPanel(String panelName)  { return this.allPanels.get(panelName); }
    public String getLastOpenPanel()                { if (this.lastOpenPanel != null) { return this.lastOpenPanel; } else { return null; } }
    public String getCurrOpenPanel()                { if (this.currOpenPanel != null) { return this.currOpenPanel; } else { return null; } }
    public ActionManager getActionManager()         { return this.actionManager; }
}
