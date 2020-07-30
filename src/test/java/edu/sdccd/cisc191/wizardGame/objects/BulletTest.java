package edu.sdccd.cisc191.wizardGame.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Rectangle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Mark Lucernas
 * Date: 2020-07-14
 */
class BulletTest {

    /** Class instance fields */
    private Bullet bullet;
    private Handler handler;

    /** Class static fields */
    private static int x;
    private static int y;
    private static int velX;
    private static int velY;
    private static Rectangle tempRectangle;

    @BeforeAll
    static void init() {
        // Initialize static fields
        x = 1;
        y = 1;
        velX = 0; // Make Bullet immobile
        velY = 0;
        tempRectangle = new Rectangle(x, y, 8, 8); // set up for getBounds()
    }

    @BeforeEach
    void setUp() {
        handler = new Handler();

        // Set up Bullet
        bullet = new Bullet();
        bullet.setX(x);
        bullet.setY(y);
        bullet.setVelX(velX);
        bullet.setVelY(velY);
        bullet.setId(ID.Bullet);
        bullet.setHandler(handler);

        handler.addObject(bullet);
    }

    @Test
    @DisplayName("Test Bullet tick() Block collision")
    void testTickBlockCollision() {
        // Set up Block
        Block block = new Block();
        block.setX(x += velX); // Match Bullet x coords after tick()
        block.setY(y += velY); // Match Bullet y coords after tick()
        block.setId(ID.Block);
        handler.addObject(block);

        bullet.tick();
        GameObject object = handler.getObject().getFirst();
        assertEquals(1, handler.getObject().size(), "Test handler size if Bullet is removed");
        assertEquals(ID.Block, object.getId(), "Test handler remaining object is Block");
    }

    @Test
    @DisplayName("Test Bullet tick() non-Block collision")
    void testTickNonBlockCollision() {
        // Set up Block
        Minion minion = new Minion();
        minion.setX(x += velX); // Match Bullet x coords after tick()
        minion.setY(y += velY); // Match Bullet y coords after tick()
        minion.setId(ID.Minion);
        handler.addObject(minion);

        bullet.tick();
        assertEquals(2, handler.getObject().size(), "Test handler size if Bullet is not removed");
    }

    @Test
    @DisplayName("Test Bullet Rectangle bounds")
    void testGetBounds() {
        Rectangle actual = bullet.getBounds();
        assertEquals(tempRectangle, actual);
    }
}
