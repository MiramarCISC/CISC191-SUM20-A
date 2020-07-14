package com.groupA;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BulletTest {

  private static int x, y;
  private static int mx, my;
  private static int velX, velY;
  private static ID id;
  private static Handler handler;
  private static BufferedImageLoader loader;
  private static BufferedImage spriteSheetImage;
  private static SpriteSheet spriteSheet;
  private static Bullet bullet;
  private static Rectangle rectangle;

  @BeforeAll
  static void init() {
    x = 1;
    y = 1;
    mx = 5;
    my = 5;
    velX = (mx - x) / 10;
    velY = (my - y) / 10;
    id = ID.Bullet;
    handler = new Handler();
    rectangle = new Rectangle(x, y, 8, 8);

    loader = new BufferedImageLoader();
    spriteSheetImage = loader.loadImage("../../../../main/resources/com/groupA/main_sheet.png");
    spriteSheet = new SpriteSheet(spriteSheetImage);
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
