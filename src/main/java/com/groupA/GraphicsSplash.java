package com.groupA;

import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author Mark Lucernas
 * Date: 2020-07-16
 *
 * Resources:
 * https://www.youtube.com/watch?v=-3KjiEga-cI
 */
public class GraphicsSplash extends JPanel implements ActionListener {

  Timer timer = new Timer(200, this);
  int x = 0;
  int y = 0;
  int velocityX = 3;
  int velocityY = 1;

  JLabel label;
  private SpriteSheet cs;
  private Image[] wizardImage = new Image[4];
  private Image[] mushroomImage = new Image[8];
  int index = 0;

  public GraphicsSplash() {
    BufferedImageLoader loader = new BufferedImageLoader();
    BufferedImage char_sheet = loader.loadImage("/wizard_sheet.png");
    cs = new SpriteSheet(char_sheet);

    wizardImage[0] = cs.grabImage(13, 8, 32, 32).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    wizardImage[1] = cs.grabImage(14, 8, 32, 32).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    wizardImage[2] = cs.grabImage(15, 8, 32, 32).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    wizardImage[3] = cs.grabImage(16, 8, 32, 32).getScaledInstance(100, 100, Image.SCALE_SMOOTH);

    mushroomImage[0] = cs.grabImage(1, 11, 32, 32).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    mushroomImage[1] = cs.grabImage(2, 11, 32, 32).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    mushroomImage[2] = cs.grabImage(3, 11, 32, 32).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    mushroomImage[3] = cs.grabImage(4, 11, 32, 32).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    mushroomImage[4] = cs.grabImage(5, 11, 32, 32).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    mushroomImage[5] = cs.grabImage(6, 11, 32, 32).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    mushroomImage[6] = cs.grabImage(7, 11, 32, 32).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    mushroomImage[7] = cs.grabImage(8, 11, 32, 32).getScaledInstance(100, 100, Image.SCALE_SMOOTH);

    label = new JLabel();
    label.setSize(300, 100);
    label.setForeground(Color.WHITE);
    label.setText("Some random text here...");
    label.setLocation(350, 300);
    this.setLayout(null);
    this.add(label);

    timer.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setBackground(new Color(50, 50, 50));

    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.YELLOW);
    g2d.drawImage(mushroomImage[index], 250, 200, null);
    g2d.drawImage(wizardImage[index], 450, 200, null);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (index < 3)
      index++;
    else
      index = 0;

    repaint();
  }

}
