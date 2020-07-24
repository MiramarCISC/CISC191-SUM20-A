package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;

public class Menu {

    // Buttons of course...
    public Rectangle playButton = new Rectangle(810, 150, 150, 75);
    public Rectangle helpButton = new Rectangle(810, 450, 150, 75);
    public Rectangle quitButton = new Rectangle(810, 750, 150, 75);

    // Image handling
    private BufferedImage background;

    public void render(Graphics g) {
        // Casting graphics 2D to g2d.

        Graphics2D g2d = (Graphics2D) g;

        //g.fillRect(810, 150, 150, 75);

        // Load background for menu as a buffered Image
        BufferedImageLoader loader = new BufferedImageLoader();
        background = loader.loadImage("/menu.jpg");

        // Now draw it full size in center of screen (background).
        g.drawImage(background, 0, 0, null);

        // Fill buttons.
        g.setColor(Color.cyan);
        g.fillRect(810, 150, 150, 75);
        g.fillRect(810, 450, 150, 75);
        g.fillRect(810, 750, 150, 75);




        // Create and draw fonts.
        g.setColor(Color.cyan);
        Font fnt0 = new Font("arial", Font.BOLD, 100);
        Font fnt1 = new Font("arial", Font.BOLD, 38);
        g.setFont(fnt0);
        //g.fillRect(810, 150, 150, 75);

        g.drawString("Wizard Game 2.0", 580, 100);

        // Draw font for buttons.
        g.setColor(Color.black);
        g.setFont(fnt1);
        g.drawString("Play", playButton.x + 35, playButton.y + 45);
        g.drawString("Help", helpButton.x + 35, helpButton.y + 45);
        g.drawString("Quit", quitButton.x + 35, quitButton.y + 45);

        //Draw buttons.
        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);
    }
}


