package edu.sdccd.cisc191.wizardGame.utils.images;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        return image.getSubimage((col*32) -32, (row*32)-32, width, height);
    }

}
