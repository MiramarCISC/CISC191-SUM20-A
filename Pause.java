import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class Pause {

    // Buttons of course...
    public Rectangle playButton = new Rectangle(810, 150, 150, 75);
    public Rectangle quitButton = new Rectangle(810, 750, 150, 75);

    // Image handling
    private BufferedImage background;

    public void render(Graphics g) {
        // Casting graphics 2D to g2d.
        Graphics2D g2d = (Graphics2D) g;

        // Load background for menu as a buffered Image
        BufferedImageLoader loader = new BufferedImageLoader();
        background = loader.loadImage("/pause.jpg");

        // Now draw it full size in center of screen (background).
        g.drawImage(background, 0, 0, null);



        // Create and draw fonts.
        Font fnt0 = new Font("arial", Font.BOLD, 100);
        Font fnt1 = new Font("arial", Font.BOLD, 38);
        g.setFont(fnt0);
        g.setColor(Color.black);
        g.drawString("Paused", 680, 100);

        // Draw font for buttons.
        g.setFont(fnt1);
        g.drawString("Play", playButton.x + 35, playButton.y + 45);
        g.drawString("Quit", quitButton.x + 35, quitButton.y + 45);

        //Draw buttons.
        g2d.draw(playButton);
        g2d.draw(quitButton);
    }
}


