package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

/**
 * @author Mark Lucernas
 * Date: 2020-07-16
 *
 * Resources:
 * https://www.youtube.com/watch?v=-3KjiEga-cI
 */
public class GraphicsSplash extends JPanel implements ActionListener {

  Timer timer = new Timer(50, this);
  Random rand = new Random();
  int x = 0;
  int y = 0;
  double xCenter = this.getWidth() / 2;
  double yCenter = this.getHeight() / 2;
  int furthestDistance = (int) Math.sqrt(Math.pow(xCenter - 0, 2) + Math.pow(yCenter - 0, 2));
  int scene;

  int coverage = 0;
  boolean isShrinking = false;

  JLabel label;
  private SpriteSheet charSheet;
  private SpriteSheet mainSheet;
  private Image[] wizardImages = new Image[4];
  private Image[] mushroomImages = new Image[8];
  private Image[] knightImages = new Image[8];
  private Image[] rotateFireImages = new Image[4];
  private Image terrain;
  int wizardIndex = 0;
  int mushroomIndex = 0;
  int knightIndex = 0;
  int rotateFireIndex = 0;

  public GraphicsSplash() {

    // Load sprite sheets
    BufferedImageLoader loader = new BufferedImageLoader();
    BufferedImage charSheetImage = loader.loadImage("/wizard_sheet.png");
    BufferedImage mainSheetImage = loader.loadImage("/main_sheet.png");
    charSheet = new SpriteSheet(charSheetImage);
    mainSheet = new SpriteSheet(mainSheetImage);

    // Load Images
    fillSpriteImage(wizardImages, 13, 8, 80, 80, charSheet);
    fillSpriteImage(mushroomImages, 1, 11, 80, 80, charSheet);
    fillSpriteImage(knightImages, 9, 5, 80, 80, charSheet);
    fillSpriteImage(rotateFireImages, 52, 37, 50, 50, mainSheet);
    terrain = mainSheet.grabImage(6, 6, 32, 32).getScaledInstance(20, 20, Image.SCALE_SMOOTH);

    // Add label
    label = new JLabel();
    label.setText("Wizard Game by Group A");
    label.setFont(new Font("Helvetica Neue", Font.PLAIN, 10));
    label.setSize(label.getPreferredSize());
    label.setForeground(Color.WHITE);
    label.setLocation(50, this.getHeight() - 50);
    this.setLayout(null);
    this.add(label);

    timer.start();
  }

  public void fillSpriteImage(Image[] imageArr, int startLoc, int row,
                              int width, int height, SpriteSheet ss) {
    for (int i = 0; i < imageArr.length; i++) {
      imageArr[i] = ss.grabImage(startLoc, row, 32, 32).getScaledInstance(width, height, Image.SCALE_SMOOTH);
      startLoc++;
    }
  }

  public void vignette(Color color, Graphics2D g2d, int coverage) {
    /*
     * Euclidean distance between the center and the furthest point from the
     * center. Formula: sqrt((x2 - x1)^2 + (y2 - y1)^2)
     */
    int distance;

    // loop over pixels
    for (int y = 0; y < this.getHeight(); y++) {
      for (int x = 0; x < this.getWidth(); x++) {
        // draw to current pixel

        // get the Euclidean distance between the point and the center
        distance = (int) Math.sqrt(Math.pow(xCenter - x, 2) + Math.pow(yCenter - y, 2));
        // add coverage offset to distance
        distance += coverage;

        if (distance > furthestDistance) {
          g2d.setColor(color);
          g2d.drawLine(x, y, x, y);
        }
      }
    }
  }

  public void drawScene(Graphics2D g2d, int scene) {
    switch (scene) {
      case 1:
        g2d.drawImage(wizardImages[wizardIndex], (int) xCenter + 100, (int) yCenter - 50, null);
        g2d.drawImage(mushroomImages[mushroomIndex], (int) xCenter - 200, (int) yCenter - 50, null);
        break;
      case 2:
        g2d.drawImage(wizardImages[wizardIndex], (int) xCenter + 100, (int) yCenter - 50, null);
        g2d.drawImage(knightImages[knightIndex], (int) xCenter - 200, (int) yCenter - 50, null);
        break;
      case 3:
        g2d.drawImage(wizardImages[wizardIndex], (int) xCenter + 100, (int) yCenter - 50, null);
        g2d.drawImage(mushroomImages[mushroomIndex], (int) xCenter - 100, (int) yCenter - 100, null);
        g2d.drawImage(mushroomImages[mushroomIndex], (int) xCenter - 20, (int) yCenter - 50, null);
        g2d.drawImage(mushroomImages[mushroomIndex], (int) xCenter - 130, (int) yCenter, null);
        g2d.drawImage(knightImages[knightIndex], (int) xCenter - 200, (int) yCenter - 150, null);
        g2d.drawImage(knightImages[knightIndex], (int) xCenter - 200, (int) yCenter - 50, null);
        g2d.drawImage(knightImages[knightIndex], (int) xCenter - 250, (int) yCenter + 50, null);
        break;
    }
  }

  /**
   * Draw background with tiled terrain
   * @param g2d Graphics 2D
   * @param terrain Image to tile the terrain with
   */
  public void drawTerrain(Graphics2D g2d, Image terrain) {
    int terrainWidth = terrain.getWidth(null);
    int terrainHeight = terrain.getHeight(null);

    int numXTiles = this.getWidth() / terrainWidth;
    int numYTiles = this.getHeight() / terrainHeight;

    for (int x = 0; x < numXTiles; x++) {
      for (int y = 0; y < numYTiles; y++) {
        g2d.drawImage(terrain, x * terrainWidth, y * terrainHeight, null);
      }
    }
  }

  /**
   * Increment index to go to next sprite pose.
   * @param spriteImages Array of sprite images to render
   * @param index Currernt index value to access sprite image
   * @return Next index value
   */
  public int updateSpriteIndex(Image[] spriteImages, int index) {
    if (index < spriteImages.length - 1)
      return index + 1;
    else
      return 0;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    // For drawing 2D
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.YELLOW);

    // Draw terrain
    drawTerrain(g2d, terrain);

    // Draw elements
    drawScene(g2d, scene);

    vignette(Color.BLACK, g2d, coverage);

    g2d.drawImage(rotateFireImages[rotateFireIndex], this.getWidth() - 100, this.getHeight() - 100, null);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    // Update sprite pose
    wizardIndex = updateSpriteIndex(wizardImages, wizardIndex);
    mushroomIndex = updateSpriteIndex(mushroomImages, mushroomIndex);
    knightIndex = updateSpriteIndex(knightImages, knightIndex);
    rotateFireIndex = updateSpriteIndex(rotateFireImages, rotateFireIndex);

    // Update vignette coverage
    if (isShrinking) {
      coverage -= 10;
      if (coverage <= 0) {
        coverage = 0;
        isShrinking = false;
      }
    } else {
      coverage += 10;
      if (coverage >= furthestDistance) {
        coverage = furthestDistance;
        isShrinking = true;

        // Get new unique scene
        int oldScene = scene;
        while (oldScene == scene)
          scene = rand.nextInt(3) + 1;
      }
    }

    // Update metrics
    xCenter = this.getWidth() / 2;
    yCenter = this.getHeight() / 2;
    furthestDistance = (int) Math.sqrt(Math.pow(xCenter - 0, 2) + Math.pow(yCenter - 0, 2));

    // Update label
    label.setLocation(50, this.getHeight() - 50);

    repaint();
  }
}
