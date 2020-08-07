package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;

/**
 * General panel template for {@code Window} class.
 * Uses paintComponent method to render background image.
 *
 * @author Mark Lucernas
 *
 * Date: 2020-07-23
 */
public abstract class GeneralPanel extends JPanel {

    /** Enum for background orientation */
    protected enum BGOrientation {
        CENTER,
        HORIZONTAL_STRETCH,
        VERTICAL_STRETCH,
    };

    private BufferedImage background;
    private BGOrientation orientation;
    protected Window frame;

    /**
     * No args GeneralPanel constructor.
     */
    public GeneralPanel() {}

    /**
     * GeneralPanel constructor.
     * @param frame     {@code Window} to place the panel in.
     */
    public GeneralPanel(Window frame) {
        this.setVisible(false);
        this.frame = frame;
    }

    /**
     * Add panel background
     * @param backgroundName    Name of the background
     * @param orientation       Background {@code BGOrientation}
     */
    protected void addBackground(String backgroundName, BGOrientation orientation) {
        BufferedImageLoader loader = new BufferedImageLoader();
        background = loader.loadImage(backgroundName);
        this.orientation = orientation;
    }

    /**
     * Paints the panel background.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Paint background
        if (background != null) {
            // Respect background orientation
            if (this.orientation == BGOrientation.CENTER) {  // Center background in the panel
                // Calculate Center location
                int x = (this.getWidth() - background.getWidth(null)) / 2;
                int y = (this.getHeight() - background.getHeight(null)) / 2;
                // Draw Image
                g2d.drawImage(background, x, y, null);
            } else if (this.orientation == BGOrientation.HORIZONTAL_STRETCH) {  // Scale the background relative to the panel's width
                Image scaledBackground = background.getScaledInstance(this.getWidth(), -1, Image.SCALE_SMOOTH);
                // Y axis padding to center vertically
                int yPadding = (this.getHeight() - scaledBackground.getHeight(null)) / 2;
                // Draw image
                g2d.drawImage(scaledBackground, 0, yPadding, null);
            } else if (this.orientation == BGOrientation.VERTICAL_STRETCH) {  // Scale the background relative to the panel's width
                Image scaledBackground = background.getScaledInstance(-1, this.getHeight(), Image.SCALE_SMOOTH);
                // X axis padding to center vertically
                int xPadding = (this.getWidth() - scaledBackground.getWidth(null)) / 2;
                // Draw image
                g2d.drawImage(scaledBackground, xPadding, 0, null);
            }
        } else {  // Default background
            g2d.setColor(new Color(20, 20, 20));
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
            g2d.setColor(Color.WHITE);
            Font font = new Font("Arial", Font.PLAIN, 18);
            g2d.setFont(font);
            g2d.drawString("Default Background", this.getWidth() / 2, this.getHeight() / 2);
        }
    }
}
