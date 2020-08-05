package edu.sdccd.cisc191.wizardGame.gui.screen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
        window = new Window();
        window.setGame(new Game());
    }

    @BeforeEach
    void setUp() {
        window.init();
    }

    @Test
    @DisplayName("Test changePanel()")
    void testChangePanel() {
        assertEquals("menu", window.getCurrOpenPanelName(), "Test pre changePanel()");
        window.changePanel("pause", false);
        assertEquals("pause", window.getCurrOpenPanelName(), "Test post changePanel() to pause panel without loadscreen");
        window.changePanel("help", true);
        assertEquals("help", window.getCurrOpenPanelName(), "Test post changePanel() to help panel with loadscreen");
    }

    // TODO
    // @Test
    // @DisplayName("Test showLoadScreen()")
    // void testShowLoadScreen() {
    //     window.showLoadScreen(4000);
    //     assertEquals("load", window.getCurrOpenPanelName(), "Test current open panel after showLoadScreen()");
    // }
}
