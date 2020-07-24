package edu.sdccd.cisc191.wizardGame.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.screen.AbstractLevel;
import edu.sdccd.cisc191.wizardGame.gui.screen.LevelOne;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

public class Block extends GameObject {

    AbstractLevel level; // Needs access to game in order to access level

    private BufferedImage block_image;

    public Block(int x, int y, ID id, SpriteSheet ss, AbstractLevel level) {
        super(x, y, id, ss);

        this.level = level; // Needs access to level to determine which level we are on.

        // Changes block look for each new level.
        /*switch (game.level_numb) {
            case 1: block_image = ss.grabImage(6, 9 ,32, 32);
                    break;
            case 2: block_image = ss.grabImage(26, 11, 32, 32);
                    break;*/

        block_image = ss.grabImage(6, 9 ,32, 32);
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
