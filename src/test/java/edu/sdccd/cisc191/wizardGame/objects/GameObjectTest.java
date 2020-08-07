// TODO: Skip abstract classes?
//
// package edu.sdccd.cisc191.wizardGame.objects;
// 
// import static org.junit.jupiter.api.Assertions.assertEquals;
// 
// import java.awt.image.BufferedImage;
// 
// import org.junit.Assert;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;
// 
// import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;
// import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;
// 
// /**
//  * @author Mark Lucernas
//  * @since 2020-07-12
//  */
// class GameObjectTest {
// 
//   static private GameObject object;
//   static private ID id;
//   static private int x, y;
//   static private float velX, velY;
//   static private BufferedImageLoader loader;
//   static private BufferedImage spriteSheetImage;
//   static private SpriteSheet ss;
// 
//   @BeforeAll
//   static void init() {
//     loader = new BufferedImageLoader();
//     spriteSheetImage = loader.loadImage("/main_sheet.png");
// 
//     x = 1;
//     y = 1;
//     // velX = 5;
//     // velY = 5;
//     id = ID.Player;
//     ss = new SpriteSheet(spriteSheetImage);
//   }
// 
//   @Test
//   void getIdTest() {
//     ID expected = id;
//     ID actual = object.getId();
// 
//     assertEquals(expected, actual, "Gets the GameObject ID");
//   }
// 
//   @Test
//   void setIdTest() {
//     ID expected = id;
//     object.setId(expected); // Set up
//     ID actual = object.getId();
// 
//     assertEquals(expected, actual, "Sets the GameObject ID");
//   }
// 
//   @Test
//   void getXTest() {
//     int expected = x;
//     int actual = object.getX();
// 
//     assertEquals(expected, actual, "Gets the GameObject x value");
//   }
// 
//   @Test
//   void setXTest() {
//     int expected = x;
//     object.setX(expected); // Set up
//     int actual = object.getX();
// 
//     assertEquals(expected, actual, "Sets the GameObject x value");
//   }
// 
//   @Test
//   void getYTest() {
//     int expected = y;
//     int actual = object.getY();
// 
//     assertEquals(expected, actual, "Gets the GameObject y value");
//   }
// 
//   @Test
//   void setYTest() {
//     int expected = y;
//     object.setY(expected); // Set up
//     int actual = object.getY();
// 
//     assertEquals(expected, actual, "Sets the GameObject y value");
//   }
// 
//   @Test
//   void getVelXTest() {
//     float expected = velX;
//     float actual = object.getVelX();
// 
//     assertEquals(expected, actual, "Sets the GameObject velX value");
//   }
// 
//   @Test
//   void setVelXTest() {
//     float expected = velX;
//     object.setVelX(expected); // Set up
//     float actual = object.getVelX();
// 
//     assertEquals(expected, actual, "Sets the GameObject velX value");
//   }
// 
//   @Test
//   void getVelYTest() {
//     float expected = velY;
//     float actual = object.getVelY();
// 
//     assertEquals(expected, actual, "Sets the GameObject velY value");
//   }
// 
//   @Test
//   void setVelYTest() {
//     float expected = velY;
//     object.setVelY(expected); // Set up
//     float actual = object.getVelY();
// 
//     assertEquals(expected, actual, "Sets the GameObject velY value");
//   }
// 
// }
