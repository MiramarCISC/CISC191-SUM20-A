package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 * The main frame of the game.
 * Stacks JPanels on top of each other for fast switching using JLayeredPane.
 *
 * @author Jordan Tobin
 * @author Mark Lucernas

 * Date: 2020-07-23
 */
public class Window extends JFrame {

    /** Frame components */
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
    public Window(int width, int height, String title) {
        super(title);
        frameWidth = width;
        frameHeight = height;
    }

    public void init() {
        // Create layered pane
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, frameWidth, frameHeight);

        // Init panels
        HelpPanel helpPanel = new HelpPanel(this);
        MenuPanel menuPanel = new MenuPanel(this);
        PausePanel pausePanel = new PausePanel(this);

        // Set panel bounds
        helpPanel.setBounds(0, 0, frameWidth, frameHeight);
        menuPanel.setBounds(0, 0, frameWidth, frameHeight);
        pausePanel.setBounds(0, 0, frameWidth, frameHeight);

        // Store panels into gamePanels
        gamePanels.put("help", helpPanel);
        gamePanels.put("menu", menuPanel);
        gamePanels.put("pause", pausePanel);

        // Add all panels into layers
        layeredPane.add(helpPanel, new Integer(1));
        layeredPane.add(menuPanel, new Integer(1));
        layeredPane.add(pausePanel, new Integer(1));

        // Start with menu panel
        menuPanel.setVisible(true);
        this.currOpenPanel = "menu";

        // Frame configs
        this.setSize(new Dimension(frameWidth, frameHeight));
        this.add(layeredPane);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(frameWidth, frameHeight));
        this.setLayout(null);
        this.setResizable((false));
        this.setLocationRelativeTo(null);
        device.setFullScreenWindow(this);
    }

    /**
     * Display specified game panel and hide the others.
     * @param panelName     Name of the panel to display
     */
    protected void changePanel(String panelName) {
        // Quit immediately on quit button click
        if (panelName.equals("quit")) { this.quitGame(); return; }

        // Change panel
        for (Map.Entry<String, GeneralPanel> entry : gamePanels.entrySet()) {
            String key = entry.getKey();
            GeneralPanel panel = entry.getValue();

            // Save last opened panel
            if (panel.isVisible()) { lastOpenPanel = key; }

            // Switch panel
            if (key.equals(panelName)) {
                // TODO: this.showLoadScreen();
                panel.setVisible(true);
                currOpenPanel = panelName;
            } else {
                panel.setVisible(false);
            }
        }
    }

    // TODO: Implement
    protected void showLoadScreen() {
        if (getCurrOpenPanel() != null)
            if (gamePanels.get(getCurrOpenPanel()).isVisible())
                gamePanels.get(getCurrOpenPanel()).setVisible(false);
    }

    /**
     * Quit game.
     */
    public void quitGame(){
        // If quit game is activated, the window will close and the program will exit.
        this.setVisible(false);
        this.dispose();
        System.exit(0);
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
