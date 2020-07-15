import java.awt.*;
import java.awt.image.BufferedImage;

public class Totem extends GameObject {
    // Represents the doorway to new level

    private BufferedImage totem_image;

    public Totem(int x, int y, ID id, SpriteSheet ss) {

        super(x, y, id, ss);

        totem_image = ss.grabImage(8, 7, 32, 32); // paint crate
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(totem_image, x ,y, null);

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
