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

    /**
     * PausePanel constructor.
     * @param frame     {@code Window} to place the panel in.
     */
    public PausePanel(Window frame) {
        super(frame);
        this.setLayout(new GridBagLayout());
        addBackground("/pause.jpg", GeneralPanel.BGOrientation.STRETCH);


        /* Add Buttons */
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);  // Button spacing
        Font font = new Font("Arial", Font.BOLD, 18);

        // Resume button
        resumeBtn = new JButton("RESUME");
        resumeBtn.setPreferredSize(buttonSize);
        resumeBtn.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        this.add(resumeBtn, gbc);

        // Help button
        helpBtn = new JButton("HELP");
        helpBtn.setPreferredSize(buttonSize);
        helpBtn.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        this.add(helpBtn, gbc);

        // Quit button
        quitBtn = new JButton("QUIT");
        quitBtn.setPreferredSize(buttonSize);
        quitBtn.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        this.add(quitBtn, gbc);

        this.addButtonListeners();
    }

    /**
     * Add all button listeners.
     */
    protected void addButtonListeners() {

        /** Resume button mouse listener */
        resumeBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) { frame.changePanel("game"); frame.getGame().resumeGame(); }  // Go back to last open panel
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        /** Help button mouse listener */
        helpBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) { frame.changePanel("help"); }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        /** Quit button mouse listener */
        quitBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) { frame.changePanel("quit"); }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
}
