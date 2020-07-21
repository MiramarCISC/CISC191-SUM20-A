package edu.sdccd.cisc191.wizardGame.utils.images;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
    // Loads an image such as sprite sheet etc.

    private BufferedImage image;

    public BufferedImage loadImage(String path ) {
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
