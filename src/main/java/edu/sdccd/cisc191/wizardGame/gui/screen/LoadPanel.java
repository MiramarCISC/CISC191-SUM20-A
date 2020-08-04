package edu.sdccd.cisc191.wizardGame.gui.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

/**
 * Load screen panel for {@link Window} class.
 * Creates separate {@link Thread} to update the value of {@link JProgressBar}
 * and uses {@link Timer} to render the frames of the loading screen at
 * specified rate of change.
 *
 * Defines an inner {@link ProgressBarThread} class extending Thread to
 * implement {@code JProgressBar} value updater matching the {@code duration}
 * of a LoadPanel instance.
 *
 * Also generates random predefined "scene" or sprite images arrangement to
 * display in the {@link JPanel} to make loading screen interesting.
 *
 * @author Mark Lucernas
 * Date: 2020-07-16
 *
 * @see Window
 * @see GeneralPanel
 */
public class LoadPanel extends GeneralPanel implements ActionListener {

    /** Timer for animation */
    Timer timer = new Timer(50, this);

    /** Default load screen duration */
    private int duration = 2000;

    /** Random for scene switch */
    private Random rand = new Random();
    private int scene;
    private boolean changeScene = true;

    /** Scene object images */
    private SpriteSheet charSheet;
    private SpriteSheet mainSheet;
    private Image terrain;
    private Image[] wizardImages = new Image[4];
    private Image[] mushroomImages = new Image[8];
    private Image[] knightImages = new Image[8];
    private Image[] rotateFireImages = new Image[4];

    /** Load panel metrics */
    int x = 0;
    int y = 0;
    double xCenter = this.getWidth() / 2;
    double yCenter = this.getHeight() / 2;
    int furthestDistance = (int) Math.sqrt(Math.pow(xCenter - 0, 2) + Math.pow(yCenter - 0, 2));

    /** Vignette effects fields */
    int coverage = 0;
    boolean isShrinking = false;

    /** Load screen components */
    private JLabel label;
    private JProgressBar progressBar;

    /** Progress bar Thread */
    private int minProgress;
    private int maxProgress;
    private int progress = 0;
    private Thread progressThread;

    /** Sprite index for cycling through sprite poses */
    private int wizardIndex = 0;
    private int mushroomIndex = 0;
    private int knightIndex = 0;
    private int rotateFireIndex = 0;

    /**
     * No args LoadPanel constructor.
     */
    public LoadPanel() {}

    /**
     * LoadPanel constructor.
     * Takes in {@code Window} as parameter with default duration and initialize
     * LoadPanel instance.
     * @param frame     {@code Window} instance reference.
     */
    public LoadPanel(Window frame) {
        super(frame);
        this.init();
    }

    /**
     * LoadPanel constructor.
     * Takes in {@code Window} as parameter with specified default duration and
     * initialize LoadPanel instance.
     * @param frame     {@code Window} instance reference
     * @param duration  Loading screen default duration
     */
    public LoadPanel(Window frame, int duration) {
        super(frame);
        this.duration = duration;
    }

    /**
     * Initializes LoadPanel instance.
     *
     * Loads all images and creates new JComponents instances to be added to
     * this JPanel.
     */
    public void init() {
        // Disable panel layout
        this.setLayout(null);
        // Load sprite sheets
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage charSheetImage = loader.loadImage("/wizard_sheet.png");
        BufferedImage mainSheetImage = loader.loadImage("/main_sheet.png");
        charSheet = new SpriteSheet(charSheetImage);
        mainSheet = new SpriteSheet(mainSheetImage);
        // Load Images
        fillSpriteImage(wizardImages, 13, 8, 100, 100, charSheet);
        fillSpriteImage(mushroomImages, 1, 11, 100, 100, charSheet);
        fillSpriteImage(knightImages, 9, 5, 80, 100, charSheet);
        fillSpriteImage(rotateFireImages, 52, 37, 80, 80, mainSheet);
        terrain = mainSheet.grabImage(6, 6, 32, 32).getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        // Add label
        label = new JLabel();
        label.setText("Wizard Game by Group A");
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        label.setSize(label.getPreferredSize());
        label.setForeground(Color.WHITE);
        label.setLocation(50, this.getHeight() - 50);
        this.add(label);
        // Add progress bar
        progressBar = new JProgressBar();
        progressBar.setSize(500, 30);
        progressBar.setForeground(Color.ORANGE);
        progressBar.setBackground(new Color(200, 200, 200));
        progressBar.setLocation((int) this.xCenter - (progressBar.getWidth() / 2), this.getHeight() - 50);
        this.add(progressBar);
    }

    /**
     * Start loading screen animation.
     * Starts {@code Timer} and {@code ProgressBarThread}.
     */
    public void start() {
        this.setVisible(true);
        progressThread = new ProgressBarThread(this.progressBar, duration);
        progressThread.start();
        timer.start();
    }

    /**
     * Kill loading screen animation.
     * Stops {@code Timer} and {@code ProgressBarThread}.
     */
    public void stop() {
        progressThread = null;
        timer.stop();
        this.setVisible(false);
    }

    /**
     * Sets loading screen duration.
     * @param duration      Loading screen duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Fill image array and set their location and size in the content pane.
     * @param imageArr      Image array container
     * @param startLoc      Start location to grab from {@code SpriteSheet}
     * @param row           Row number to grab the images from
     * @param width         Image width in the content pane
     * @param height        Image height in the content pane
     * @param ss            SpriteSheet to grab the image from
     */
    public void fillSpriteImage(Image[] imageArr, int startLoc, int row,
            int width, int height, SpriteSheet ss) {
        for (int i = 0; i < imageArr.length; i++) {
            imageArr[i] = ss.grabImage(startLoc, row, 32, 32).getScaledInstance(width, height, Image.SCALE_SMOOTH);
            startLoc++;
        }
    }

    /** DEPRECATED:
     * Vignette effects that expand and shrink.
     *
     * Euclidean distance between the center and the furthest point from the
     * center. Formula: sqrt((x2 - x1)^2 + (y2 - y1)^2)
     *
     * @param color     Vignette color
     * @param g2d       {@code Graphics2D} instance reference
     * @param coverage  Vignette coverage in the content pane.
     */
    public void vignette(Color color, Graphics2D g2d, int coverage) {
        int distance;

        // loop over pixels
        for (int y = 0; y < this.getHeight(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                // draw to current pixel

                // get the Euclidean distance between the point and the center
                distance = (int) Math.sqrt(Math.pow(xCenter - x, 2) + Math.pow(yCenter - y, 2));
                // add coverage offset to distance
                distance += coverage;

                if (distance > furthestDistance) {
                    g2d.setColor(color);
                    g2d.drawLine(x, y, x, y);
                }
            }
        }
    }

    /**
     * Draw pre-defined template scenes to render selected images centered in
     * the content pane.
     * @param g2d       {@code java.awt.Graphics2D} instance reference
     * @param scene     Template scene number to draw
     */
    public void drawScene(Graphics2D g2d, int scene) {
        switch (scene) {
            case 1:
                g2d.drawImage(wizardImages[wizardIndex], (int) xCenter + 200, (int) yCenter - 50, null);
                g2d.drawImage(mushroomImages[mushroomIndex], (int) xCenter - 300, (int) yCenter - 50, null);
                break;
            case 2:
                g2d.drawImage(wizardImages[wizardIndex], (int) xCenter + 200, (int) yCenter - 50, null);
                g2d.drawImage(knightImages[knightIndex], (int) xCenter - 300, (int) yCenter - 50, null);
                break;
            case 3:
                g2d.drawImage(wizardImages[wizardIndex], (int) xCenter + 200, (int) yCenter - 50, null);
                g2d.drawImage(mushroomImages[mushroomIndex], (int) xCenter - 200, (int) yCenter - 100, null);
                g2d.drawImage(mushroomImages[mushroomIndex], (int) xCenter - 120, (int) yCenter - 50, null);
                g2d.drawImage(mushroomImages[mushroomIndex], (int) xCenter - 230, (int) yCenter, null);
                g2d.drawImage(knightImages[knightIndex], (int) xCenter - 300, (int) yCenter - 150, null);
                g2d.drawImage(knightImages[knightIndex], (int) xCenter - 300, (int) yCenter - 50, null);
                g2d.drawImage(knightImages[knightIndex], (int) xCenter - 350, (int) yCenter + 50, null);
                break;
        }
    }

    /**
     * Draw background with tiled terrain
     * @param g2d Graphics 2D
     * @param terrain Image to tile the terrain with
     */
    public void drawTerrain(Graphics2D g2d, Image terrain) {
        int terrainWidth = terrain.getWidth(null);
        int terrainHeight = terrain.getHeight(null);

        int numXTiles = this.getWidth() / terrainWidth;
        int numYTiles = this.getHeight() / terrainHeight;

        for (int x = 0; x < numXTiles; x++) {
            for (int y = 0; y < numYTiles; y++) {
                g2d.drawImage(terrain, x * terrainWidth, y * terrainHeight, null);
            }
        }
    }

    /**
     * Increment index to go to next sprite pose.
     * @param spriteImages  Array of sprite images to render
     * @param index         Current index value to access sprite image
     * @return              Next index value
     */
    public int updateSpriteIndex(Image[] spriteImages, int index) {
        if (index < spriteImages.length - 1)
            return index + 1;
        else
            return 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // For drawing 2D
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);

        g2d.setColor(new Color(20, 20, 20));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Draw terrain
        // drawTerrain(g2d, terrain);

        // Draw elements
        drawScene(g2d, scene);

        // vignette(Color.BLACK, g2d, coverage);

        g2d.drawImage(rotateFireImages[rotateFireIndex], this.getWidth() - 200, this.getHeight() - 100, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Update sprite poses
        wizardIndex = updateSpriteIndex(wizardImages, wizardIndex);
        mushroomIndex = updateSpriteIndex(mushroomImages, mushroomIndex);
        knightIndex = updateSpriteIndex(knightImages, knightIndex);
        rotateFireIndex = updateSpriteIndex(rotateFireImages, rotateFireIndex);

        // DEPRECATED: Update vignette coverage
        // if (isShrinking) {
        //     coverage -= 10;
        //     if (coverage <= 0) {
        //         coverage = 0;
        //         isShrinking = false;
        //     }
        // } else {
        //     coverage += 10;
        //     if (coverage >= furthestDistance) {
        //         coverage = furthestDistance;
        //         isShrinking = true;
        //     }
        // }

        // Change to unique scene if true
        if (this.changeScene) {
            int oldScene = scene;
            scene = rand.nextInt(3) + 1;

            while (oldScene == scene)
                scene = rand.nextInt(3) + 1;

            this.changeScene = false;
        }

        // Update screen metrics for dynamic resizing
        xCenter = this.getWidth() / 2;
        yCenter = this.getHeight() / 2;
        furthestDistance = (int) Math.sqrt(Math.pow(xCenter - 0, 2) + Math.pow(yCenter - 0, 2));

        // Update JComponents location
        label.setLocation(50, this.getHeight() - 50);
        progressBar.setLocation((int) this.xCenter - (progressBar.getWidth() / 2), this.getHeight() - 100);

        repaint();
    }

    /**
     * Inner class to update {@code JProgressBar}.
     */
    class ProgressBarThread extends Thread {

        private JProgressBar bar;
        private int durationStep = 5;

        public ProgressBarThread(JProgressBar progressBar, int duration) {
            this.bar = progressBar;
            minProgress = 0;
            maxProgress = duration / durationStep;
        }

        @Override
        public void run() {
            bar.setMinimum(minProgress);
            bar.setMaximum(maxProgress);
            bar.setValue(0);

            for (int i = minProgress; i <= maxProgress; i++) {
                progress = i;
                bar.setValue(i);
                bar.setStringPainted(true);

                // Strategic pauses to sync progress value with loading duration
                try {
                    sleep(durationStep);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

                // Get new unique scene every third of progress bar
                if  (!changeScene && (progress == maxProgress / 3 || progress == (maxProgress / 3) * 2)) {
                    changeScene = true;
                }
            }
        }
    }
}
