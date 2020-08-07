package edu.sdccd.cisc191.wizardGame.utils.images;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;

import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;

/**
 * @author Mark Lucernas
 * Date: 2020-07-12
 */
class BufferedImageLoaderTest {

  private BufferedImageLoader loader;
  private BufferedImage image;

  @Test
  void loadImageTest() {
    loader = new BufferedImageLoader();
    image = loader.loadImage("/main_sheet.png");
    // Checks if image load successful
    assertNotNull(image);
  }

}
