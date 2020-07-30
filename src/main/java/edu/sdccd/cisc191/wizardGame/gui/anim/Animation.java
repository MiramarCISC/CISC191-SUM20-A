package edu.sdccd.cisc191.wizardGame.gui.anim;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] images;
    private int interval, index;
    private long timer, now, lastTime;

    /**
     * No args Animation constructor.
     */
    public Animation() {}

    public Animation(BufferedImage[] images, int interval) {
        // Renders array of BufferedImages successively
        this.images = images;
        this.interval = interval;
        index = 0;
        timer = 0;
        now = 0;
        lastTime = System.currentTimeMillis();
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
    public BufferedImage[] getImages()            { return images; }
    public int getInterval()                      { return interval; }
    public int getIndex()                         { return index; }
    public long getTimer()                        { return timer; }
    public long getNow()                          { return now; }
    public long getLastTime()                     { return lastTime; }

    /** Modifier methods */
    public void setImages(BufferedImage[] images) { this.images = images; }
    public void setInterval(int interval)         { this.interval = interval; }
    public void setIndex(int index)               { this.index = index; }
    public void setTimer(long timer)              { this.timer = timer; }
    public void setNow(long now)                  { this.now = now; }
    public void setLastTime(long lastTime)        { this.lastTime = lastTime; }
}
