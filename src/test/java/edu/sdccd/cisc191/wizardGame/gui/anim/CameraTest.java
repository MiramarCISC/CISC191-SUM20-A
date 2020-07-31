package edu.sdccd.cisc191.wizardGame.gui.anim;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Mark Lucernas
 * Date: 2020-07-14
 */
class CameraTest {

    private Camera camera;
    private float x, y;

    @BeforeEach
    void setUp() {
        this.x = 1;
        this.y = 1;
        camera = new Camera(x, y);
    }

    @Test
    @DisplayName("Test Camera accessor methods")
    void testAccessorMethods() {
        assertEquals(this.x, camera.getX(), "Test Camera getX()");
        assertEquals(this.y, camera.getY(), "Test Camera getY()");
    }

    @Test
    @DisplayName("Test Camera modifier methods")
    void testModifierMethods() {
        camera.setX(2);
        camera.setY(2);

        assertEquals(2, camera.getX(), "Test Camera setX()");
        assertEquals(2, camera.getY(), "Test Camera setY()");
    }
}
