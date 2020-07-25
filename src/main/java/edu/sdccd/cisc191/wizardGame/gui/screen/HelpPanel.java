package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/**
 * Help menu panel for {@code Window} class.
 *
 * @author Jordan Tobin
 * @author Mark Lucernas
 *
 * Date: 2020-07-23
 */
public class HelpPanel extends GeneralPanel {

    /** Panel components */
    protected JButton backBtn;
    private Dimension buttonSize = new Dimension(400, 50);

    /**
     * HelpPanel constructor.
     * @param frame     {@code Window} to place the panel in.
     */
    public HelpPanel(Window frame) {
        super(frame);
        this.setLayout(new GridBagLayout());
        addBackground("/help.jpg", GeneralPanel.BGOrientation.CENTER);

        // Add Buttons
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Button spacing

        // Back button
        backBtn = new JButton("BACK");
        backBtn.setPreferredSize(buttonSize);
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        this.add(backBtn, gbc);

        this.addButtonListeners();
    }

    /**
     * Add all button listeners.
     */
    protected void addButtonListeners() {

        /** Back button mouse listener */
        backBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) { frame.changePanel(frame.getLastOpenPanel()); }  // Go back to last open panel
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
}
