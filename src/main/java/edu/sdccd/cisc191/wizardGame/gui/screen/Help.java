package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;


public class Help {

    // Buttons of course...
    public Rectangle menuButton = new Rectangle(810, 750, 150, 75);

    // Image handling
    private BufferedImage background;

    public void render(Graphics g) {
        // Casting graphics 2D to g2d.
        Graphics2D g2d = (Graphics2D) g;

        // Load background for menu as a buffered Image
        BufferedImageLoader loader = new BufferedImageLoader();
        background = loader.loadImage("/help.jpg");

        // Now draw it full size in center of screen (background).
        g.setColor(Color.black);
        g.fillRect(0, 0, 1920, 1080);
        g.drawImage(background, 0, 0, null);



        // Create and draw fonts.
        Font fnt0 = new Font("arial", Font.BOLD, 75);
        Font fnt1 = new Font("arial", Font.BOLD, 38);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("AND THIS WHERE I'D PUT THE HELP MENU", 50, 100);
        g.drawString("IF I HAD ONE!", 600, 500);
        // Draw font for buttons.
        g.setFont(fnt1);

        g.drawString("MENU", menuButton.x + 35, menuButton.y + 45);

        //Draw buttons.
        g2d.draw(menuButton);
    }
}
