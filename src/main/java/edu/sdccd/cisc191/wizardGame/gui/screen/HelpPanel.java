package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;

import edu.sdccd.cisc191.wizardGame.gui.Action.ActionManager;

/**
 * Help menu panel for {@code Window} class.
 *
 * @author Jordan Tobin
 * @author Mark Lucernas
 *
 * Date: 2020-07-23
 * Create Help Menu and positioned button in correct spot
 *
 * @author Tamer Elsawaf
 * Date: 2020-08-03
 */
public class HelpPanel extends GeneralPanel {

    /** Panel components */
    protected JButton backBtn;
    private Dimension buttonSize = new Dimension(350, 50);

    /** Action manager */
    private ActionManager am;

    /**
     * HelpPanel constructor.
     * @param frame     {@code Window} to place the panel in.
     */
    public HelpPanel(Window frame) {
        super(frame);
        this.am = frame.getActionManager();
        this.setLayout(new GridBagLayout());
        addBackground("/help.jpg", GeneralPanel.BGOrientation.CENTER);

        // Add Buttons
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(500, 15, 15, 15); // Button spacing

        // Back button
        this.backBtn = new JButton("BACK");
        this.backBtn.addActionListener(am.getWizardGameAction("backAction"));
        backBtn.setPreferredSize(buttonSize);
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        this.add(backBtn, gbc);
        // Back button Position
    }
}
