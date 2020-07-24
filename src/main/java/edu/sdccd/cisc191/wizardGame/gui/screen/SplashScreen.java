package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @author Mark Lucernas
 * Date: 2020-07-16
 */
public class SplashScreen extends JFrame {

  private LoadPanel splash = new LoadPanel(null);

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
    setSize(1980, 1080);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(splash);
    splash.start();
    setVisible(true);
  }
}
