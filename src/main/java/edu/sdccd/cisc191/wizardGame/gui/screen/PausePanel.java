package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import edu.sdccd.cisc191.wizardGame.gui.Action.ActionManager;

/**
 * Pause menu panel for {@code Window} class.
 *
 * @author Jordan Tobin
 * @author Mark Lucernas
 *
 * Date: 2020-07-23
 */
public class PausePanel extends GeneralPanel {

    // Buttons of course...
    protected JButton resumeBtn, helpBtn, quitBtn;
    private Dimension buttonSize = new Dimension(200, 75);

    /** Action manager */
    ActionManager am;

    /**
     * PausePanel constructor.
     * @param frame     {@code Window} to place the panel in.
     */
    public PausePanel(Window frame) {
        super(frame);
        this.am = frame.getActionManager();
        this.setLayout(new GridBagLayout());
        addBackground("/pause.jpg", GeneralPanel.BGOrientation.STRETCH);


        /* Add Buttons */
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);  // Button spacing
        Font font = new Font("Arial", Font.BOLD, 18);

        // Resume button
        this.resumeBtn = new JButton("RESUME");
        this.resumeBtn.addActionListener(am.getWizardGameAction("resumeAction"));
        resumeBtn.setPreferredSize(buttonSize);
        resumeBtn.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        this.add(resumeBtn, gbc);

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
