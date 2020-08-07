package edu.sdccd.cisc191.wizardGame.gui.screen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JProgressBar;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

/**
 * @author Mark Lucernas
 * Date: 2020-07-17
 */
class LoadPanelTest {

    private static Window frame;
    private LoadPanel loadPanel;

    @BeforeAll
    static void init() {
        Window frame = new Window();
    }

    @BeforeEach
    void setUp() {
        loadPanel = new LoadPanel(frame);
        loadPanel.init();
    }

    @Test
    @Disabled("Impelemnt testInit()")
    void testInit() {
        // TODO: Implement
    }

    @Test
    @Disabled("Implement testStart()")
    void testStart() {
        // TODO: Implement
    }

    @Test
    @Disabled("Implement stop()")
    void testStop() {
        // TODO: Implement
    }

    @Test
    @Disabled("Implement testFillSpriteImage()")
    void testFillSpriteImage() {
        // TODO: Implement
    }

    @Test
    @Disabled("Implement testDrawScene()")
    void testDrawScene() {
        // TODO: Implement
    }

    @Test
    @Disabled("Implement testDrawTerrain")
    void testDrawTerrain() {
        // TODO: Implement
    }

    @Test
    @DisplayName("Test LoadPanel accessor and modifier methods")
    void testAccessorAndModifierMethods() {
        // Test duration
        int duration = 5000; // 5 seconds
        // Test Spritesheets instances
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage charSheetImage = loader.loadImage("/wizard_sheet.png");
        BufferedImage mainSheetImage = loader.loadImage("/main_sheet.png");
        SpriteSheet charSheet = new SpriteSheet(charSheetImage);
        SpriteSheet mainSheet = new SpriteSheet(mainSheetImage);
        // Test JProgressBar instance
        JProgressBar progressBar = new JProgressBar();
        // Test terrain Image
        Image terrain = mainSheet.grabImage(6, 6, 32, 32).getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        // Modify LoadPanel fields
        loadPanel.setDuration(duration);
        loadPanel.setCharSpriteSheet(charSheet);
        loadPanel.setMainSpriteSheet(mainSheet);
        loadPanel.setProgressBar(progressBar);
        loadPanel.setTerrainImage(terrain);

        // Assert tests
        assertEquals(duration, loadPanel.getDuration(), "Test LoadPanel.setDuration()");
        assertEquals(charSheet, loadPanel.getCharSpriteSheet(), "Test LoadPanel.setCharSpriteSheet()");
        assertEquals(mainSheet, loadPanel.getMainSpriteSheet(), "Test LoadPanel.setMainSpriteSheet()");
        assertEquals(progressBar, loadPanel.getProgressBar(), "Test LoadPanel.setProgressBar()");
        assertEquals(terrain, loadPanel.getTerrainImage(), "Test LoadPanel.setTerrainImage()");
    }
}
