import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends GameObject {

    Game game; // Needs access to game in order to access level

    private BufferedImage block_image;

    public Block(int x, int y, ID id, SpriteSheet ss, Game game) {
        super(x, y, id, ss);

        this.game = game;

        // Changes block look for each new level.
        switch (game.level_numb) {
            case 1: block_image = ss.grabImage(6, 9 ,32, 32);
                    break;
            case 2: block_image = ss.grabImage(26, 11, 32, 32);
                    break;
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(block_image, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
