package edu.sdccd.cisc191.wizardGame.events;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Canvas;
import java.awt.event.KeyEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import edu.sdccd.cisc191.wizardGame.objects.Handler;
import edu.sdccd.cisc191.wizardGame.objects.ID;
import edu.sdccd.cisc191.wizardGame.objects.Wizard;

/**
 * @author Mark Lucernas
 * Date: 2020-07-14
 */
class KeyInputTest {

    private Canvas canvas;
    private Handler handler;
    private KeyInput keyInput;

    @BeforeEach
    void setUp() {
        // Init fields
        canvas = new Canvas();
        handler = new Handler();
        keyInput = new KeyInput(handler);
        Wizard wizard = new Wizard();

        // Add Wizard into Handler GameObjects
        wizard.setId(ID.Player);
        handler.addObject(wizard);

        // Add KeyInput as Canvas KeyListener
        canvas.addKeyListener(keyInput);
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
            assertFalse(handler.isUp()); // Pre-keyPressed handler check
            keyInput.keyPressed(key);    // Execute key
            assertTrue(handler.isUp());  // Post-keyPressed handler check
        }

        @Test
        @DisplayName("Test KeyInput A keyPressed()")
        void testAKeyPressed() {
            // Set up KeyEvent for 'A'
            KeyEvent key = new KeyEvent(canvas, KeyEvent.KEY_PRESSED,
                                        System.currentTimeMillis(), 0,
                                        KeyEvent.VK_A, 'a');
            assertFalse(handler.isLeft()); // Pre-keyPressed handler check
            keyInput.keyPressed(key);      // Execute key
            assertTrue(handler.isLeft());  // Post-keyPressed handler check
        }

        @Test
        @DisplayName("Test KeyInput S keyPressed()")
        void testSKeyPressed() {
            // Set up KeyEvent for 'S'
            KeyEvent key = new KeyEvent(canvas, KeyEvent.KEY_PRESSED,
                                        System.currentTimeMillis(), 0,
                                        KeyEvent.VK_S, 's');
            assertFalse(handler.isDown()); // Pre-keyPressed handler check
            keyInput.keyPressed(key);      // Execute key
            assertTrue(handler.isDown());  // Post-keyPressed handler check
        }

        @Test
        @DisplayName("Test KeyInput D keyPressed()")
        void testDKeyPressed() {
            // Set up KeyEvent for 'D'
            KeyEvent key = new KeyEvent(canvas, KeyEvent.KEY_PRESSED,
                                        System.currentTimeMillis(), 0,
                                        KeyEvent.VK_D, 'd');
            assertFalse(handler.isRight()); // Pre-keyPressed handler check
            keyInput.keyPressed(key);       // Execute key
            assertTrue(handler.isRight());  // Post-keyPressed handler check
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
            keyInput.keyPressed(key);    // Execute key

            assertTrue(handler.isUp());  // Pre-keyReleased handler check
            keyInput.keyReleased(key);   // Release key
            assertFalse(handler.isUp()); // Post-keyReleased handler check
        }

        @Test
        @DisplayName("Test KeyInput A keyReleased()")
        void testAKeyReleased() {
            // Set up KeyEvent for 'W'
            KeyEvent key = new KeyEvent(canvas, KeyEvent.KEY_PRESSED,
                                        System.currentTimeMillis(), 0,
                                        KeyEvent.VK_A, 'a');
            keyInput.keyPressed(key);      // Execute key

            assertTrue(handler.isLeft());  // Pre-keyReleased handler check
            keyInput.keyReleased(key);     // Release key
            assertFalse(handler.isLeft()); // Post-keyReleased handler check
        }

        @Test
        @DisplayName("Test KeyInput S keyReleased()")
        void testSKeyReleased() {
            // Set up KeyEvent for 'W'
            KeyEvent key = new KeyEvent(canvas, KeyEvent.KEY_PRESSED,
                                        System.currentTimeMillis(), 0,
                                        KeyEvent.VK_S, 's');
            keyInput.keyPressed(key);      // Execute key

            assertTrue(handler.isDown());  // Pre-keyReleased handler check
            keyInput.keyReleased(key);     // Release key
            assertFalse(handler.isDown()); // Post-keyReleased handler check
        }

        @Test
        @DisplayName("Test KeyInput D keyReleased()")
        void testDKeyReleased() {
            // Set up KeyEvent for 'W'
            KeyEvent key = new KeyEvent(canvas, KeyEvent.KEY_PRESSED,
                                        System.currentTimeMillis(), 0,
                                        KeyEvent.VK_D, 'd');
            keyInput.keyPressed(key);       // Execute key

            assertTrue(handler.isRight());  // Pre-keyReleased handler check
            keyInput.keyReleased(key);      // Release key
            assertFalse(handler.isRight()); // Post-keyReleased handler check
        }
    }
}
