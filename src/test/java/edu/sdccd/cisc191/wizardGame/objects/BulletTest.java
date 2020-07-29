package edu.sdccd.cisc191.wizardGame.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Rectangle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

/**
 * @author Mark Lucernas
 * Date: 2020-07-14
 */
class BulletTest {

    private Bullet bullet;
    private Rectangle rectangle;
    private Handler handler;
    private static int x;
    private static int y;
    private static int mx;
    private static int my;
    private static int velX;
    private static int velY;

    @BeforeAll
    static void init() {
        // Initialize static fields
        x = 1;
        y = 1;
        velX = (mx - x) / 10;
        velY = (my - y) / 10;
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

        // Add to handler (the order matters)
        handler.addObject(bullet);


        rectangle = new Rectangle(x, y, 8, 8);
    }

    @Test
    void testTickBlockCollision() {
        // Set up Block
        Block block = new Block();
        block.setX(x += velX); // Match Bullet x coords after tick()
        block.setY(y += velY); // Match Bullet y coords after tick()
        block.setId(ID.Block);
        handler.addObject(block);

        bullet.tick();
        GameObject object = handler.getObject().getFirst();
        assertEquals(1, handler.getObject().size(), "Check if Bullet is removed");
        assertEquals(ID.Block, object.getId(), "Check if remaining object is Block");
    }

    @Test
    void testTickNonBlockCollision() {
        // Set up Block
        Minion minion = new Minion();
        minion.setX(x += velX); // Match Bullet x coords after tick()
        minion.setY(y += velY); // Match Bullet y coords after tick()
        minion.setId(ID.Minion);
        handler.addObject(minion);

        bullet.tick();
        assertEquals(2, handler.getObject().size(), "Check if Bullet is not removed");
    }

    @Test
    void testGetBounds() {
        Rectangle actual = bullet.getBounds();
        assertEquals(rectangle, actual, "Gets Bullet Rectangle bounds");
    }
}
