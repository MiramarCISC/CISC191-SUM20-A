package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @author Mark Lucernas
 * Date: 2020-07-16
 */
public class SplashScreen extends JFrame {

  private GraphicsSplash splash = new GraphicsSplash();

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          SplashScreen frame = new SplashScreen();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public SplashScreen() {
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(splash);
    setVisible(true);
  }
}
