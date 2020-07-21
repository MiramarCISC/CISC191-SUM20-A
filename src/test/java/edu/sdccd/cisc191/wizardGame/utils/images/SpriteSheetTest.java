package edu.sdccd.cisc191.wizardGame.utils.images;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;

import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

/**
 * @author Mark Lucernas
 * Date: 2020-07-12
 */
class SpriteSheetTest {

  private BufferedImageLoader loader;
  private BufferedImage spriteSheetImage;
  private BufferedImage floor;
  private SpriteSheet spriteSheet;

  @Test
  void grabImageTest() {
    loader = new BufferedImageLoader();
    spriteSheetImage = loader.loadImage("/main_sheet.png");
    spriteSheet = new SpriteSheet(spriteSheetImage);

    // Checks if image grab successful
    floor = spriteSheet.grabImage(6, 6, 32, 32);
    assertNotNull(floor);
  }

}
