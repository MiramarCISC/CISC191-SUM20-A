package com.groupA;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CameraTest {

  private static float x, y;
  private static Camera camera;

  @BeforeAll
  static void init() {
    x = 0;
    y = 0;
    camera = new Camera(x, y);
  }

  @Test
  void tickTest() {
    // TODO: Figure out
  }

  @Test
  void getXTest() {
    float expected = x;
    float actual = camera.getX();

    assertEquals(expected, actual, "Gets Camera x value");
  }

  @Test
  void setXTest() {
    float expected = x;
    camera.setX(x);
    float actual = camera.getX();

    assertEquals(expected, actual, "Sets Camera x value");
  }

  @Test
  void getYTest() {
    float expected = y;
    float actual = camera.getY();

    assertEquals(expected, actual, "Gets Camera y value");
  }

  @Test
  void setYTest() {
    float expected = y;
    camera.setY(y);
    float actual = camera.getY();

    assertEquals(expected, actual, "Sets Camera y value");
  }
}
