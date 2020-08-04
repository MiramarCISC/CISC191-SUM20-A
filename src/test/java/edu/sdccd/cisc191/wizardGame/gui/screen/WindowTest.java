package edu.sdccd.cisc191.wizardGame.gui.screen;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.sdccd.cisc191.wizardGame.Game;

/**
 * @author Mark Lucernas
 * Date: 2020-07-14
 */
class WindowTest {

    private static Window window;

    @BeforeAll
    static void init() {
        window = new Window(new Game(), 1920, 1080, "Test Window");
    }

    @Test
    void quitGameTest() {
        // TODO
    }

}
