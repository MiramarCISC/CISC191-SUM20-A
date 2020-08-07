package edu.sdccd.cisc191.wizardGame.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.anim.Animation;
import edu.sdccd.cisc191.wizardGame.gui.screen.levels.AbstractLevel;
import edu.sdccd.cisc191.wizardGame.gui.screen.levels.Level;

/**
 * @author Mark Lucernas
 * Date: 2020-07-14
 */
class WizardTest {

    /** Class instance fields */
    private Wizard wizard;
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
        velX = 0; // Make Wizard immobile
        velY = 0;
        tempRectangle = new Rectangle(x, y, 62, 62); // set up for getBounds()
    }

    @BeforeEach
    void setUp() {
        handler = new Handler();

        // Set up Bullet
        wizard = new Wizard();
        wizard.setX(x);
        wizard.setY(y);
        wizard.setId(ID.Player);
        wizard.setHandler(handler);

        handler.addObject(wizard);
    }

    @Nested
    class CollisionTests {

        @Test
        @DisplayName("Test Wizard collision with Block")
        void testBlockCollision() {
            // Set up Block
            Block block = new Block();
            block.setX(x); // Match Wizard x coords
            block.setY(y); // Match Wizard y coords
            block.setId(ID.Block);
            handler.addObject(block);

            // Set up wizard
            wizard.setVelX(10);
            wizard.setVelY(10);
            wizard.collision();

            assertEquals(0, wizard.getVelX(), "Test if Wizard X velocity is 0 on Block collision");
            assertEquals(0, wizard.getVelY(), "Test if Wizard Y velocity is 0 on Block collision");
        }

        @Test
        @DisplayName("Test Wizard collision with a Crate")
        void testCrateCollision() {
            // Set up Create
            Crate crate = new Crate();
            crate.setX(x); // Match Wizard x coords
            crate.setY(y); // Match Wizard y coords
            crate.setId(ID.Crate);
            handler.addObject(crate);

            // Set up wizard
            Game game = new Game();            // Game setup
            game.setAmmo(10);
            AbstractLevel level = new Level(); // Level setup
            level.setGame(game);
            level.setLevel(1);
            wizard.setLevel(level);
            wizard.collision();

            GameObject object = handler.getObject().getLast();
            assertEquals(1, handler.getObject().size(), "Test handler size if Crate is removed");
            assertEquals(ID.Player, object.getId(), "Test handler if remaining object is Wizard");
            assertEquals(20, game.getAmmo(), "Test if Game Crate collision increments ammo by 10");
        }

        // TODO: Fix with less requirement for testing
        // @Test
        // @DisplayName("Test Wizard collision with a Totem")
        // void testTotemCollision() {
        //
        //     // Set up Totem
        //     Totem totem = new Totem();
        //     totem.setX(x); // Match Wizard x coords
        //     totem.setY(y); // Match Wizard y coords
        //     totem.setId(ID.Totem);
        //     handler.addObject(totem);
        //
        //     // Set up wizard
        //     Game game = new Game();            // Game setup
        //     GamePanel gamePanel = new GamePanel();
        //     game.setAmmo(10);
        //     AbstractLevel level = new Level(); // Level setup
        //     level.setGame(game);
        //     level.setGamePanel(gamePanel);
        //     level.setLevel(1);
        //     wizard.setLevel(level);
        //     wizard.collision();
        // }

        @Test
        @DisplayName("Test Wizard collision with a Minion")
        void testMinionCollision() {
            // Set up Minion
            Minion minion = new Minion();
            minion.setX(x); // Match Wizard x coords
            minion.setY(y); // Match Wizard y coords
            minion.setId(ID.Minion);
            handler.addObject(minion);

            // Set up wizard
            Game game = new Game();            // Game setup
            game.setHp(100);
            AbstractLevel level = new Level(); // Level setup
            level.setGame(game);
            level.setLevel(1);
            wizard.setLevel(level);
            wizard.collision();

            assertEquals(2, handler.getObject().size(), "Test handler size if Minion not removed");
            assertEquals(99, game.getHp(), "Test if Game Minion collision decrease hp by 1");
        }
    }

    @Nested
    class TickTests {

        private Game game;
        private Animation anim;
        private AbstractLevel level;

        @BeforeEach
        void tickSetUp() {
            game = new Game();      // Game setup
            level = new Level();    // Level setup
            level.setGame(game);
            anim = new Animation(); // Animation setup
            anim.setImages(new BufferedImage[0]);
            wizard.setLevel(level); // Wizard setup
            wizard.setAnimation(anim);
            wizard.setVelX(velX);
            wizard.setVelY(velY);
        }

        @Test
        @DisplayName("Test tick() game over")
        void testTickGameOver() {
            game.setLives(0);
            wizard.tick();

            assertEquals(0, handler.getObject().size(), "Test handler if Wizard is removed on game over");
        }

        @Test
        @DisplayName("Test tick() 0 Wizard hp")
        void testTickZeroHp() {
            game.setHp(0);
            game.setLives(3);
            wizard.tick();

            assertEquals(0, handler.getObject().size(), "Test handler if Wizard is removed on zero hp");
            assertEquals(2, game.getLives(), "Test if zero hp decrease Wizard Lives by 1");
        }

        @Test
        @DisplayName("Test tick() movement handling")
        void testTickMoveHandling() {
            handler.setUp(true); // Up movement test
            wizard.tick();
            assertEquals(-5, wizard.getVelY(), "Test Wizard tick() up movement handling");
            handler.setUp(false);

            handler.setDown(true); // Down movement test
            wizard.tick();
            assertEquals(5, wizard.getVelY(), "Test Wizard tick() down movement handling");
            handler.setDown(false);

            handler.setLeft(true); // Left movement test
            wizard.tick();
            assertEquals(-5, wizard.getVelX(), "Test Wizard tick() left movement handling");
            handler.setLeft(false);

            handler.setRight(true); // Right movement test
            wizard.tick();
            assertEquals(5, wizard.getVelX(), "Test Wizard tick() right movement handling");
        }
    }

    @Test
    @DisplayName("Test Wizard placeFree()")
    void placeFreeTest() {
        // Set up Block
        Block block = new Block();
        block.setX(x); // Match Wizard x coords
        block.setY(y); // Match Wizard y coords
        block.setId(ID.Block);
        handler.addObject(block);

        // Rec Bounds occupied
        boolean isBlockRecFree = wizard.placeFree(x, x, wizard.getBounds(), block.getBounds());
        assertFalse(isBlockRecFree);
        // Rec Bounds free
        boolean isRecFree = wizard.placeFree(x, x, wizard.getBounds(), new Rectangle(100, 100, 62, 62));
        assertTrue(isRecFree);
    }

    @Test
    @Disabled("Skipping render testing. Omit or Implement later?")
    void testRender() {
        // TODO: Skip this test?
    }

    @Test
    @DisplayName("Test Wizard getBounds()")
    void getBoundsTest() {
        assertEquals(tempRectangle.getBounds(), wizard.getBounds());
    }
}
