package edu.sdccd.cisc191.wizardGame.events;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Canvas;
import java.awt.event.MouseEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.anim.Camera;
import edu.sdccd.cisc191.wizardGame.objects.Handler;
import edu.sdccd.cisc191.wizardGame.objects.ID;
import edu.sdccd.cisc191.wizardGame.objects.Wizard;

/**
 * @author Mark Lucernas
 * Date: 2020-07-14
 */
class MouseInputTest {

    private MouseInput mouseInput;
    private Game game;
    private Camera camera;
    private Canvas canvas;
    private Handler handler;

    @BeforeEach
    void setUp() {
        // Init fields
        mouseInput = new MouseInput();
        game = new Game();
        camera = new Camera(1, 1);
        canvas = new Canvas();
        handler = new Handler();
        Wizard wizard = new Wizard();

        // Add ammo to Game
        game.setAmmo(20);

        // Add Wizard into Handler GameObjects
        wizard.setId(ID.Player);
        handler.addObject(wizard);

        // Set up MouseInput
        mouseInput.setGame(game);
        mouseInput.setHandler(handler);
        mouseInput.setCamera(camera);

        // Add KeyInput as Canvas KeyListener
        canvas.addMouseListener(mouseInput);
    }

    @Test
    @Order(1)
    @DisplayName("Test MouseInput fireBullet() decreases ammo and create Bullet")
    void testFireBullet() {
        // Pre-click ammo count test
        assertEquals(20, game.getAmmo(), "Test MouseInput pre-click ammo count");

        mouseInput.fireBullet(); // Fires bullet

        // Valid post-click Bullet and ammo test
        assertEquals(19, game.getAmmo(), "Test MouseInput post-click ammo count");
        assertEquals(2, handler.getObject().size(), "Test Handler GameObject size after fireBullet()");
        assertEquals(ID.Bullet, handler.getObject().getLast().getId(), "Test Handler last added GameObject if is Bullet");
    }


    @Test
    @Order(2)
    @DisplayName("Test MouseInput mousePressed() decreases ammo")
    void testMousePressed() {
        int xClick = 50, yClick = 50;
        MouseEvent click = new MouseEvent(canvas, 1, System.currentTimeMillis(),
                                          0, xClick, yClick, 1, false,
                                          MouseEvent.BUTTON1);
        // Pre-click ammo count test
        assertEquals(20, game.getAmmo(), "Test MouseInput pre-click ammo count");

        mouseInput.mousePressed(click); // Execute valid click

        // Valid post-click ammo count test
        assertEquals(19, game.getAmmo(), "Test MouseInput post-click ammo count");
    }
}
