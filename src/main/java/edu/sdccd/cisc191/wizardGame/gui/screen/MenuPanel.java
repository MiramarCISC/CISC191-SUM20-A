package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;

import edu.sdccd.cisc191.wizardGame.gui.Action.ActionManager;

/**
 * Main menu panel for {@code Window} class.
 *
 * @author Jordan Tobin
 * @author Mark Lucernas
 *
 * Date: 2020-07-23
 */
public class MenuPanel extends GeneralPanel {

    /** Menu Buttons */
    protected JButton playBtn, helpBtn, quitBtn;
    private Dimension buttonSize = new Dimension(200, 75);

    /** Action manager */
    ActionManager am;

    /**
     * MenuPanel constructor.
     * @param frame     {@code Window} to place the panel in.
     */
    public MenuPanel(Window frame) {
        super(frame);
        this.am = frame.getActionManager();
        this.setLayout(new GridBagLayout());
        addBackground("/menu_background.jpg", GeneralPanel.BGOrientation.STRETCH);

        /* Add buttons */
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);  // Button spacing
        Font font = new Font("Arial", Font.BOLD, 18);

        // Play button
        this.playBtn = new JButton("PLAY");
        this.playBtn.addActionListener(am.getWizardGameAction("playAction"));
        playBtn.setPreferredSize(buttonSize);
        playBtn.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        this.add(playBtn, gbc);

        // Help button
        this.helpBtn = new JButton("HELP");
        this.helpBtn.addActionListener(am.getWizardGameAction("helpAction"));
        helpBtn.setPreferredSize(buttonSize);
        helpBtn.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        this.add(helpBtn, gbc);

        // Quit button
        this.quitBtn = new JButton("QUIT");
        this.quitBtn.addActionListener(am.getWizardGameAction("quitAction"));
        quitBtn.setPreferredSize(buttonSize);
        quitBtn.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        this.add(quitBtn, gbc);


    }

}
