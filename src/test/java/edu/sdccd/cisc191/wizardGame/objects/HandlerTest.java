package edu.sdccd.cisc191.wizardGame.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * @author Mark Lucernas
 * Date: 2020-07-12
 */
class HandlerTest {

    private Handler handler;

    @BeforeEach
    void setUp() {
        handler = new Handler();
    }

    @Test
    @DisplayName("Test Handler default movement fields")
    void testDefaultMovements() {
        assertFalse(handler.isUp());
        assertFalse(handler.isDown());
        assertFalse(handler.isRight());
        assertFalse(handler.isLeft());
    }

    @Test
    @DisplayName("Test Handler movement modifier methods")
    void testMovementModifiers() {
        handler.setUp(true);
        handler.setDown(true);
        handler.setRight(true);
        handler.setLeft(true);
        assertTrue(handler.isUp());
        assertTrue(handler.isDown());
        assertTrue(handler.isRight());
        assertTrue(handler.isLeft());
    }

    @Nested
    class HandlerObjectTests {

        @BeforeEach
        void setUp() {
            handler = new Handler();
        }

        @Test
        @DisplayName("Test Handler addObject()")
        void testAddObject() {
            handler.addObject(new Wizard());
            handler.addObject(new Minion());

            assertEquals(2, handler.getObject().size(), "Test object size after addObject()");
        }

        @Test
        @DisplayName("Test Handler removeObject()")
        void testRemoveObject() {
            Wizard wizard = new Wizard();
            Minion minion = new Minion();
            handler.addObject(wizard);
            handler.addObject(minion);

            handler.removeObject(wizard);

            assertEquals(1, handler.getObject().size(), "Test object size after removeObject()");
        }


        @Test
        @DisplayName("Test Handler clearHandler()")
        void testClearHandler() {
            handler.addObject(new Wizard());
            handler.addObject(new Minion());

            handler.clearHandler();

            assertEquals(0, handler.getObject().size(), "Test object size after clearHandler()");
        }
    }
}
