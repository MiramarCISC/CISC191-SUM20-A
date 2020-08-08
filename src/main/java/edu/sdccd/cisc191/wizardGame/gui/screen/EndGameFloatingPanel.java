package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.Action.ActionManager;

/**
 * Floating end game {@link JPanel} that is displayed when the game concludes.
 * Inherits from {@link GeneralPanel}
 *
 * @author Mark Lucernas <https://github.com/marklcrns>
 *
 * Date: 2020-08-07
 */
public class EndGameFloatingPanel extends GeneralPanel {

    /** Class references */
    private Window frame;
    private Game game;

    /** Panel components */
    private JButton restartBtn, mainMenuBtn, quitBtn;
    private Dimension buttonSize = new Dimension(200, 50);

    /** Action manager */
    private ActionManager am;

    /**
     * No args EndGamePanel constructor.
     */
    public EndGameFloatingPanel() { super(); }

    /**
     * EndGamePanel constructor.
     * @param frame     {@code Window} to place the panel in.
     */
    public EndGameFloatingPanel(Window frame) {
        super(frame);
        this.frame = frame;
        this.game = frame.getGame();
        this.am = frame.getActionManager();

        // Set size for bounds JLayeredPanel setBounds() reference
        this.setSize(new Dimension(800, 500));

        this.setLayout(new GridBagLayout());

        // Add Buttons
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Button spacing

        // Restart button
        this.restartBtn = new JButton("RESTART");
        // TODO: Create RestartAction
        // this.restartBtn.addActionListener(am.getWizardGameAction("restartAction"));
        restartBtn.setPreferredSize(buttonSize);
        restartBtn.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        this.add(restartBtn, gbc);
        // Back button Position

        // Main menu button
        this.mainMenuBtn = new JButton("MAIN MENU");
        // TODO: Create MenuAction
        // this.mainMenuBtn.addActionListener(am.getWizardGameAction("menuAction"));
        mainMenuBtn.setPreferredSize(buttonSize);
        mainMenuBtn.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        this.add(mainMenuBtn, gbc);
        // Back button Position

        // Quit button
        this.quitBtn = new JButton("QUIT");
        this.quitBtn.addActionListener(am.getWizardGameAction("quitAction"));
        quitBtn.setPreferredSize(buttonSize);
        quitBtn.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        this.add(quitBtn, gbc);
        // Back button Position
    }

    // Show winner background
    public void displayWinner() {
        addBackground("/game_.jpg", GeneralPanel.BGOrientation.HORIZONTAL_STRETCH);
    }

    // Show loser background
    public void displayLoser() {
        addBackground("/game_over.png", GeneralPanel.BGOrientation.HORIZONTAL_STRETCH);
    }
}
