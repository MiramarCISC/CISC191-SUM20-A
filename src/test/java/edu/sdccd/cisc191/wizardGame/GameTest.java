package edu.sdccd.cisc191.wizardGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

/**
 * @author Mark Lucernas
 * Date: 2020-07-14
 */
class GameTest {

    private static Game game;

    @BeforeAll
    static void init() {
        game = new Game();
    }

    @Test
    @Order(1)
    @DisplayName("Test Game Accessor Methods")
    void testAccessorMethods() {
        int ammo = game.getAmmo();
        int lives = game.getLives();
        int hp = game.getHp();
        boolean isWizardDead = game.isWizardDead();
        boolean isGamePaused = game.isGamePaused();

        assertEquals(50, ammo, "Test getAmmo()");
        assertEquals(3, lives, "Test getLives()");
        assertEquals(100, hp, "Test getHp()");
        assertFalse(isWizardDead, "Test isWizardDead()");
        assertFalse(isGamePaused, "Test isGamePaused()");
    }

    @Nested
    class ModifierMethodsTest  {

        @Test
        @Order(2)
        @DisplayName("Test setHp()")
        void testSetHp() {
            int testHp = 20;
            game.setHp(testHp);
            assertEquals(testHp, game.getHp());
        }

        @Test
        @Order(3)
        @DisplayName("Test setHp() with negative number")
        void testSetHpNegative() {
            int testNegHp = -1;
            game.setHp(testNegHp);
            assertFalse(game.getHp() < 0);
        }

        @Test
        @Order(4)
        @DisplayName("Test setAmmo()")
        void testSetAmmo() {
            int testAmmo = 30;
            game.setAmmo(testAmmo);
            assertEquals(testAmmo, game.getAmmo());
        }

        @Test
        @Order(5)
        @DisplayName("Test setAmmo() with negative number")
        void testSetAmmoNegative() {
            int testNegAmmo = -1;
            game.setAmmo(testNegAmmo);
            assertFalse(game.getAmmo() < 0);
        }

        @Test
        @Order(6)
        @DisplayName("Test setLives()")
        void testSetLives() {
            int testLives = 3;
            game.setLives(testLives);
            assertEquals(testLives, game.getLives());
        }

        @Test
        @Order(7)
        @DisplayName("Test setLives() with negative number")
        void testSetLivesNegative() {
            int testNegNum = -1;
            game.setLives(testNegNum);
            assertFalse(game.getLives() < 0);
        }

        @Test
        @Order(8)
        @DisplayName("Test incAmmo()")
        void testIncAmmo() {
            int currAmmo = game.getAmmo();
            int increment = 5;
            game.incAmmo(increment);
            assertEquals(currAmmo + increment, game.getAmmo());
        }

        @Test
        @Order(9)
        @DisplayName("Test decAmmo()")
        void testDecAmmo() {
            game.setAmmo(50);
            int currAmmo = game.getAmmo();
            game.decAmmo();
            assertEquals(currAmmo - 1, game.getAmmo());
        }

        @Test
        @Order(10)
        @DisplayName("Test decHp()")
        void testDecHp() {
            game.setHp(100);
            int currHp = game.getHp();
            game.decHp();
            assertEquals(currHp - 1, game.getHp());
        }

        @Test
        @Order(11)
        @DisplayName("Test decLives()")
        void testDecLives() {
            game.setLives(3);
            int currLives = game.getLives();
            game.decLives();
            assertEquals(currLives - 1, game.getLives());
        }

        @Test
        @Order(12)
        @DisplayName("Test wizardDied()")
        void testWizardDied() {
            game.wizardDied();
            assertTrue(game.isWizardDead());
        }

        @Test
        @Order(13)
        @DisplayName("Test wizardRespawn()")
        void testWizardRespawn() {
            game.wizardRespawn();
            assertFalse(game.isWizardDead());
        }

        @Test
        @Order(14)
        @DisplayName("Test pauseGame()")
        void testPauseGame() {
            game.pauseGame();
            assertTrue(game.isGamePaused());
        }

        @Test
        @Order(15)
        @DisplayName("Test resumeGame()")
        void testResumeGame() {
            game.resumeGame();
            assertFalse(game.isGamePaused());
        }
    }
}
