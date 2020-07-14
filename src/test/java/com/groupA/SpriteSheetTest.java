package com.groupA;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;

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
    spriteSheetImage = loader.loadImage("../../../../main/resources/com/groupA/main_sheet.png");
    spriteSheet = new SpriteSheet(spriteSheetImage);

    // Checks if image grab successful
    floor = spriteSheet.grabImage(6, 6, 32, 32);
    assertNotNull(floor);
  }

}
