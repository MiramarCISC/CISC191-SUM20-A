package edu.sdccd.cisc191.wizardGame.gui.screen;

import edu.sdccd.cisc191.wizardGame.Game;

public class MainTest {
    public static void main(String[] args) {
        Window window = new Window(new Game(), 1980, 1080, "Wizard Game");
        window.init();
    }
}
