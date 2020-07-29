package edu.sdccd.cisc191.wizardGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
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
        game.init();
    }

    @Nested
    class AccessorMethodsTest {

        @Test
        void testGetAmmo50() {
            int actual = game.getAmmo();
            assertEquals(50, actual);
        }

        @Test
        void testGetLives3() {
            int actual = game.getLives();
            assertEquals(3, actual);
        }

        @Test
        void testGetHp100() {
            int actual = game.getHp();
            assertEquals(100, actual);
        }

        @Test
        void testHasWizardDied() {
            boolean actual = game.isWizardDead();
            assertFalse(actual);
        }

        @Test
        void testGamePaused() {
            boolean actual = game.isGamePaused();
            assertFalse(actual);
        }
    }

    @Nested
    class ModifierMethodsTest  {

        @Test
        void testSetHp() {
            int testNum = 20;
            game.setHp(testNum);
            assertEquals(testNum, game.getHp());
        }

        @Test
        void testSetHpNegative() {
            int testNegNum = -1;
            game.setHp(testNegNum);
            assertFalse(game.getHp() < 0);
        }

        @Test
        void testSetAmmo () {
            int testNum = 30;
            game.setAmmo(testNum);
            assertEquals(testNum, game.getAmmo());
        }

        @Test
        void testSetAmmoNegative() {
            int testPosNum = -1;
            game.setAmmo(testPosNum);
            assertFalse(game.getAmmo() < 0);
        }

        @Test
        void testSetLives() {
            int testNum = 3;
            game.setLives(testNum);
            assertEquals(testNum, game.getLives());
        }

        @Test
        void testSetLivesNegative() {
            int testNegNum = -1;
            game.setLives(testNegNum);
            assertFalse(game.getLives() < 0);
        }

        @Test
        void testIncAmmo() {
            int currAmmo = game.getAmmo();
            int increment = 5;
            game.incAmmo(increment);
            assertEquals(currAmmo + increment, game.getAmmo());
        }

        @Test
        void testDecAmmo() {
            game.setAmmo(50);
            int currAmmo = game.getAmmo();
            game.decAmmo();
            assertEquals(currAmmo - 1, game.getAmmo());
        }

        @Test
        void testDecHp() {
            game.setHp(100);
            int currHp = game.getHp();
            game.decHp();
            assertEquals(currHp - 1, game.getHp());
        }

        @Test
        void testDecLives() {
            game.setLives(3);
            int currLives = game.getLives();
            game.decLives();
            assertEquals(currLives - 1, game.getLives());
        }

        @Test
        void testWizardDied() {
            game.wizardDied();
            assertTrue(game.isWizardDead());
        }

        @Test
        void testWizardRespawn() {
            game.wizardRespawn();
            assertFalse(game.isWizardDead());
        }

        @Test
        void testPauseGame() {
            game.pauseGame();
            assertTrue(game.isGamePaused());
        }

        @Test
        void testResumeGame() {
            game.resumeGame();
            assertFalse(game.isGamePaused());
        }
    }
}
