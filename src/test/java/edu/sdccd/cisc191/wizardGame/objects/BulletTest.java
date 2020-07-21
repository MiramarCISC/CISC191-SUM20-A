package edu.sdccd.cisc191.wizardGame.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

/**
 * @author Mark Lucernas
 * Date: 2020-07-14
 */
class BulletTest {

  private static Bullet bullet;
  private static Rectangle rectangle;

  @BeforeAll
  static void init() {
    int x = 1;
    int y = 1;
    int mx = 5;
    int my = 5;
    int velX = (mx - x) / 10;
    int velY = (my - y) / 10;
    ID id = ID.Bullet;
    Handler handler = new Handler();
    rectangle = new Rectangle(x, y, 8, 8);

    BufferedImageLoader loader = new BufferedImageLoader();
    BufferedImage spriteSheetImage = loader.loadImage("/main_sheet.png");
    SpriteSheet spriteSheet = new SpriteSheet(spriteSheetImage);
    bullet = new Bullet(x, y, id, handler, mx, my, spriteSheet);
  }

  @Test
  void tickTest() {
    bullet.tick();
    // TODO: Figure out
  }

  @Test
  void renderTest() {
    // TODO: Figure out
  }

  @Test
  void getBoundsTest() {
    Rectangle expected = rectangle;
    Rectangle actual = bullet.getBounds();

    assertEquals(expected, actual, "Gets Bullet bounds");
  }
}
