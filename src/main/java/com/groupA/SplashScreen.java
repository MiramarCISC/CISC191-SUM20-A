package com.groupA;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author Mark Lucernas
 * Date: 2020-07-16
 */
public class SplashScreen extends JFrame {

  private GraphicsSplash splash = new GraphicsSplash();

  /**
   * Launch the application.
   */
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

  /**
   * Create the frame.
   */
  public SplashScreen() {
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(splash);
    setVisible(true);

    // setBounds(100, 100, 450, 300);
    // contentPane = new JPanel();
    // contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    // contentPane.setLayout(new BorderLayout(0, 0));
    // setContentPane(contentPane);
  }
}
