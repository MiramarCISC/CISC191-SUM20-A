package edu.sdccd.cisc191.wizardGame.gui.anim;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Mark Lucernas
 * Date: 2020-07-14
 */
class AnimationTest {

    private Animation animation;

    @BeforeEach
    void setUp() {
        animation = new Animation();
    }

    @Test
    @DisplayName("Test Animation accessor methods")
    void testAnimationAccessorMethods() {
        assertEquals(0, animation.getInterval(), "Test Animation.getInterval()");
        assertEquals(0, animation.getIndex(), "Test Animation.getIndex()");
        assertEquals(0, animation.getTimer(), "Test Animation.getTimer()");
        assertEquals(0, animation.getNow(), "Test Animation.getNow()");
        assertTrue(animation.getLastTime() > 0);
        assertNull(animation.getImages());
    }

    @Test
    @DisplayName("Test Animation modifier methods")
    void testAnimationModifierMethods() {
        animation.setInterval(1);
        animation.setIndex(1);
        animation.setTimer(1);
        animation.setNow(1);
        animation.setLastTime(1);
        animation.setImages(new BufferedImage[2]);

        assertEquals(1, animation.getInterval(), "Test Animation.setInterval()");
        assertEquals(1, animation.getIndex(), "Test Animation.setIndex()");
        assertEquals(1, animation.getTimer(), "Test Animation.setTimer()");
        assertEquals(1, animation.getNow(), "Test Animation.setNow()");
        assertEquals(1, animation.getLastTime(), "Test Animation.setLastTime()");
        assertEquals(2, animation.getImages().length, "Test Animation.setImages()");
    }
}
