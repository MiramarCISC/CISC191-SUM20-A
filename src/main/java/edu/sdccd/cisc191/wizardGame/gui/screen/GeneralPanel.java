package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
public class GeneralPanel extends JPanel {

    /** Enum for background orientation */
    protected enum BGOrientation {
        CENTER,
        STRETCH,
    };

    private BufferedImage background;
    private BGOrientation orientation;
    protected Window frame;

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
                int x = (this.getWidth() - background.getWidth(null)) / 2;
                int y = (this.getHeight() - background.getHeight(null)) / 2;
                g2d.drawImage(background, x, y, null);
            } else if (this.orientation == BGOrientation.STRETCH) {  // Scale the background relative to the panel's width
                g2d.drawImage(background.getScaledInstance(this.getWidth(), -1, Image.SCALE_SMOOTH), 0, 0, null);
            }
        } else {  // Default background
            g2d.setColor(Color.GRAY);
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
            g2d.setColor(Color.WHITE);
            Font font = new Font("Arial", Font.PLAIN, 18);
            g2d.setFont(font);
            g2d.drawString("Default Background", this.getWidth() / 2, this.getHeight() / 2);
        }
    }
}
