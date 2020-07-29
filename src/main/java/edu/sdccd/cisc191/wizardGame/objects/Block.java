package edu.sdccd.cisc191.wizardGame.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import edu.sdccd.cisc191.wizardGame.gui.screen.levels.AbstractLevel;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

public class Block extends GameObject {

    private AbstractLevel level;
    private BufferedImage blockImage;

    /**
     * No args Block constructor.
     */
    public Block() { super(); }

    public Block(int x, int y, ID id, SpriteSheet ss, AbstractLevel level) {
        super(x, y, id, ss);
        this.level = level;

        // Get block image based on current level number.
        this.blockImage = this.level.getBlockImage(this.level.getLevelNumber());
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(blockImage, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
