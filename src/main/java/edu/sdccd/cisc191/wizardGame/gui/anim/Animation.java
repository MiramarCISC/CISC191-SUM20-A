package edu.sdccd.cisc191.wizardGame.gui.anim;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * @author Jordan Tobin
 *
 * Editor: Mark Lucernas
 *
 * Date: 2020-07-30
 */
public class Animation {

    private BufferedImage[] images;
    private int interval, index = 0;
    private long timer, now = 0;
    private long lastTime = System.currentTimeMillis();

    /**
     * No args Animation constructor.
     */
    public Animation() {}

    public Animation(BufferedImage[] images, int interval) {
        // Renders array of BufferedImages successively
        this.images = images;
        this.interval = interval;
    }

    public void tick() {

        now = System.currentTimeMillis();
        timer += now - lastTime;
        lastTime = now;

        if(timer >= interval) {
            index++;
            timer = 0;

            if(index >= images.length)
                index = 0;
        }
    }

    public void render(Graphics g, int x, int y, int width, int height) {
        g.drawImage(images[index], x, y, width, height, null);
    }

    /** Accessor methods */
    public BufferedImage[] getImages()            { return this.images; }
    public int getInterval()                      { return this.interval; }
    public int getIndex()                         { return this.index; }
    public long getTimer()                        { return this.timer; }
    public long getNow()                          { return this.now; }
    public long getLastTime()                     { return this.lastTime; }

    /** Modifier methods */
    public void setImages(BufferedImage[] images) { this.images = images; }
    public void setInterval(int interval)         { this.interval = interval; }
    public void setIndex(int index)               { this.index = index; }
    public void setTimer(long timer)              { this.timer = timer; }
    public void setNow(long now)                  { this.now = now; }
    public void setLastTime(long lastTime)        { this.lastTime = lastTime; }
}
