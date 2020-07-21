package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.*;
import javax.swing.JFrame;

public class Window {

    protected static JFrame frame; // This is protected and static, so other classes can access the window for closing, resizing etc.

    protected static GraphicsDevice device = GraphicsEnvironment // Used for fullscreen.
            .getLocalGraphicsEnvironment().getScreenDevices()[0];

    public Window(int width, int height, String title) {

        frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        //frame.add(game);
        frame.setResizable((false));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        device.setFullScreenWindow(frame); // Careful with this
    }

    public static void quitGame(){
        // If quit game is activated, the window will close and the program will exit.
        frame.setVisible(false);
        frame.dispose();
        System.exit(0);
    }

    public static void changeLevel(Canvas game) {
        // takes current game, current lives and current level and paints it to the window.
        frame.getContentPane().removeAll();
        frame.add(game);
        frame.validate();
    }
}
