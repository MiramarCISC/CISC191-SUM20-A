package edu.sdccd.cisc191.wizardGame.events;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Canvas;
import java.awt.event.KeyEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
import edu.sdccd.cisc191.wizardGame.objects.Handler;
import edu.sdccd.cisc191.wizardGame.objects.ID;
import edu.sdccd.cisc191.wizardGame.objects.Wizard;

/**
 * @author Mark Lucernas
 * Date: 2020-07-14
 */
class KeyInputTest {

    private GamePanel gamePanel;
    private Canvas canvas;
    private Handler handler;
    private KeyInput keyInput;

    @BeforeEach
    void setUp() {
        // Init fields
        gamePanel = new GamePanel();
        canvas = new Canvas();
        handler = new Handler();
        keyInput = new KeyInput(handler);
        Wizard wizard = new Wizard();

        // Add Wizard into Handler GameObjects
        wizard.setId(ID.Player);
        handler.addObject(wizard);

        // Add KeyInput as Canvas KeyListener
        canvas.addKeyListener(keyInput);

        // Add Canvas and Handler to GamePanel
        gamePanel.setCanvas(canvas);
        gamePanel.setHandler(handler);
    }

    @Nested
    class WASDKeyPressedTests {

        @Test
        @DisplayName("Test KeyInput W keyPressed()")
        void testWKeyPressed() {
            // Set up KeyEvent for 'W'
            KeyEvent key = new KeyEvent(canvas, KeyEvent.KEY_PRESSED,
                                        System.currentTimeMillis(), 0,
                                        KeyEvent.VK_W, 'w');
            // Execute key
            keyInput.keyPressed(key);

            assertTrue(handler.isUp());
        }

        @Test
        @DisplayName("Test KeyInput A keyPressed()")
        void testAKeyPressed() {
            // Set up KeyEvent for 'A'
            KeyEvent key = new KeyEvent(canvas, KeyEvent.KEY_PRESSED,
                                        System.currentTimeMillis(), 0,
                                        KeyEvent.VK_A, 'a');
            // Execute key
            keyInput.keyPressed(key);

            assertTrue(handler.isLeft());
        }

        @Test
        @DisplayName("Test KeyInput S keyPressed()")
        void testSKeyPressed() {
            // Set up KeyEvent for 'S'
            KeyEvent key = new KeyEvent(canvas, KeyEvent.KEY_PRESSED,
                                        System.currentTimeMillis(), 0,
                                        KeyEvent.VK_S, 's');
            // Execute key
            keyInput.keyPressed(key);

            assertTrue(handler.isDown());
        }

        @Test
        @DisplayName("Test KeyInput D keyPressed()")
        void testDKeyPressed() {
            // Set up KeyEvent for 'D'
            KeyEvent key = new KeyEvent(canvas, KeyEvent.KEY_PRESSED,
                                        System.currentTimeMillis(), 0,
                                        KeyEvent.VK_D, 'd');
            // Execute key
            keyInput.keyPressed(key);

            assertTrue(handler.isRight());
        }

    }

    @Nested
    class WASDKeyReleasedTests {

        @Test
        @DisplayName("Test KeyInput W keyReleased()")
        void testWKeyReleased() {
            // Set up KeyEvent for 'W'
            KeyEvent key = new KeyEvent(canvas, KeyEvent.KEY_PRESSED,
                                        System.currentTimeMillis(), 0,
                                        KeyEvent.VK_W, 'w');
            // Execute key
            keyInput.keyPressed(key);
            // Release key
            keyInput.keyReleased(key);

            assertFalse(handler.isUp());
        }

        @Test
        @DisplayName("Test KeyInput A keyReleased()")
        void testAKeyReleased() {
            // Set up KeyEvent for 'W'
            KeyEvent key = new KeyEvent(canvas, KeyEvent.KEY_PRESSED,
                                        System.currentTimeMillis(), 0,
                                        KeyEvent.VK_A, 'a');
            // Execute key
            keyInput.keyPressed(key);
            // Release key
            keyInput.keyReleased(key);

            assertFalse(handler.isLeft());
        }

        @Test
        @DisplayName("Test KeyInput S keyReleased()")
        void testSKeyReleased() {
            // Set up KeyEvent for 'W'
            KeyEvent key = new KeyEvent(canvas, KeyEvent.KEY_PRESSED,
                                        System.currentTimeMillis(), 0,
                                        KeyEvent.VK_S, 's');
            // Execute key
            keyInput.keyPressed(key);
            // Release key
            keyInput.keyReleased(key);

            assertFalse(handler.isDown());
        }

        @Test
        @DisplayName("Test KeyInput D keyReleased()")
        void testDKeyReleased() {
            // Set up KeyEvent for 'W'
            KeyEvent key = new KeyEvent(canvas, KeyEvent.KEY_PRESSED,
                                        System.currentTimeMillis(), 0,
                                        KeyEvent.VK_D, 'd');
            // Execute key
            keyInput.keyPressed(key);
            // Release key
            keyInput.keyReleased(key);

            assertFalse(handler.isRight());
        }
    }
}
