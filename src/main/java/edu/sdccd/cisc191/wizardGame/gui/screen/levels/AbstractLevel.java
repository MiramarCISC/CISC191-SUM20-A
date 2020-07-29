package edu.sdccd.cisc191.wizardGame.gui.screen.levels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.anim.Camera;
import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
import edu.sdccd.cisc191.wizardGame.objects.*;
import edu.sdccd.cisc191.wizardGame.objects.Horizontal;
import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

public abstract class AbstractLevel {

    // Several utility variables.
    protected Game game;
    protected GamePanel gamePanel;
    protected BufferedImageLoader loader = new BufferedImageLoader();

    // Load camera and handler.
    protected Camera camera;
    protected Handler handler;

    // Load level images and sprite sheets.
    protected SpriteSheet ss = new SpriteSheet(loader.loadImage("/main_sheet.png")); // Who says we need to declare variables?
    protected SpriteSheet cs = new SpriteSheet(loader.loadImage("/wizard_sheet.png")); // character sheet

    // Track level number.
    protected int levelNumber;

    /** Level Maps, Floors and Blocks  @see bufferedLevelImages */
    protected LinkedList<BufferedImage> levelImages = new LinkedList<BufferedImage>();
    protected LinkedList<BufferedImage> floorImages = new LinkedList<BufferedImage>();
    protected LinkedList<BufferedImage> blockImages = new LinkedList<BufferedImage>();


    // Images that change each level, Map, Floor and Block. Instantiated in Extended class.
    protected BufferedImage currentMap;
    protected BufferedImage currentFloor;
    protected BufferedImage currentBlock;

    // Sprite used to display lives in HUD
    protected BufferedImage livesImage = cs.grabImage(13, 8, 32, 32); // Sprite to display lives.

    // Constructor
    public AbstractLevel(Game game, GamePanel gamePanel) {
        this.game = game;
        this.gamePanel = gamePanel;
        camera = new Camera(0, 0); // and the camera
        handler = new Handler(); // make sure the handler is started from new.

        // Buffer all level, floor and block images in level resource and sprite sheet.
        bufferLevelImages();
        bufferFloorImages();
        bufferBlockImages();
    }

    /**
     * Loads all images dynamically in level resource directory into levelImages
     * LinkedList<BufferedImage>.
     */
    public void bufferLevelImages() {
        // Count all level images in resources
        File file= new File("src/main/resources/levels");
        int numLevels = file.list().length;

        // Load all level into levelImages list in order
        for (int i = 0; i < numLevels; i++) {
            this.levelImages.add(loader.loadImage("/levels/level_" + (i + 1) + ".png"));  // +1 to offset index 0
        }
    }

    /**
     * To change the floor or wall blocks.
     * Alter images in the following linked list.
     */

    public void bufferFloorImages() {
        // Just add particular tiles to linked list in order.
        // This is the place to change level design look.
        floorImages.add(ss.grabImage(6, 6, 32, 32)); // Level one....
        floorImages.add(ss.grabImage(7, 2, 32, 32));
        floorImages.add(ss.grabImage(6, 3, 32, 32));
        floorImages.add(ss.grabImage(7, 9, 32, 32));
        floorImages.add(ss.grabImage(6, 5, 32, 32));
    }

    public void bufferBlockImages() {
        // Just add particular tiles to linked list in order.
        blockImages.add(ss.grabImage(6, 9, 32, 32)); // Level one..
        blockImages.add(ss.grabImage(6, 10, 32, 32));
        blockImages.add(ss.grabImage(6, 13, 32, 32));
        blockImages.add(ss.grabImage(6, 12, 32, 32));
        blockImages.add(ss.grabImage(6, 13, 32, 32));
    }

    public void loadLevel(BufferedImage image) {
        currentMap = image;

        int w = image.getWidth();
        int h = image.getHeight();

        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                // Color map. Determines which sprites render to the map.
                if (red == 255 && green == 0 && blue == 0) handler.addObject(new Block(xx * 32, yy * 32, ID.Block, ss, this)); // pure red
                if (red == 0 && green == 0 && blue == 255) handler.addObject(new Wizard(xx * 32, yy * 32, ID.Player, handler, game, this, cs)); // pure blue
                if (red == 0 && green == 255 && blue == 0) handler.addObject(new Minion(xx * 32, yy * 32, ID.Minion, handler, cs)); // pure green
                if (red == 0 && green == 255 && blue == 255) handler.addObject(new Crate(xx * 32, yy * 32, ID.Crate, ss)); // pure cyan
                if (red == 255 && green == 255 && blue == 0) handler.addObject(new Totem(xx * 32, yy * 32, ID.Totem, ss)); // pure yellow
                if (red == 255 && green == 0 && blue == 255) handler.addObject(new Knight(xx * 32, yy * 32, ID.Knight, handler, this, cs)); //pure magenta
                if (red == 0 && green == 153 && blue == 102) handler.addObject(new Ent(xx * 32, yy * 32, ID.Ent, handler, this, cs)); // // # 009966 green
                if (red == 255 && green == 153 && blue == 51) handler.addObject(new Horizontal(xx * 32, yy * 32, ID.Horizontal, handler, cs)); // # ff9933 vivid orange
            }
        }
    }

    /**
     * Respawn {@code Wizard} after death.
     */
    public void respawn() {
        game.setHp(100); // Reset hp to 100.
        game.setAmmo(50); // Reset ammo.
        // Set all key releases to true.
        handler.setUp(false);
        handler.setDown(false);
        handler.setLeft(false);
        handler.setRight(false);

        // Use current map file to load Wizard back in original position.
        int w = currentMap.getWidth();
        int h = currentMap.getHeight();

        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = currentMap.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                // Color map determines which sprites render to the map.
                if (red == 0 && green == 0 && blue == 255) // pure blue
                    handler.addObject(new Wizard(xx * 32, yy * 32, ID.Player, handler, game, this, cs));
            }
        }
    }

    public void tick() {
        // Tick all current game objects, and have camera follow player.
        for(int i = 0; i < this.handler.getObject().size(); i++) {
            if(this.handler.getObject().get(i).getId() == ID.Player) {
                camera.tick(this.handler.getObject().get(i));
            }
        }
        this.handler.tick();
    } // end tick method

    public void render(Graphics2D g) {

        // Create background.
        g.setColor(Color.black);
        g.fillRect(0, 0, 1920, 1080);

        // Translate location of camera.
        g.translate(-camera.getX(), -camera.getY());

        for(int xx = 0; xx < 30*92; xx += 32) { //debug
            for(int yy = 0; yy < 30*72; yy += 32) {
                g.drawImage(currentFloor, xx, yy, null);
            }
        }

        // Render all GameObjects
        handler.render(g);

        // Keep translating camera as coords change. (Follow player)
        g.translate(camera.getX(), camera.getY());

        // HUD: Creating health bar.
        g.setColor(Color.gray);
        g.fillRect(5, 5, 200, 32);
        g.setColor(Color.red);
        g.fillRect(5, 5, this.getHp()*2, 32);
        g.setColor(Color.black);
        g.drawRect(5, 5, 200, 32);
        g.setColor(Color.white);
        g.drawString("HEALTH", 5, 25);

        // Creating ammo HUD.
        g.setColor(Color.white);
        g.drawString("Ammo: " + this.getAmmo(), 5, 50);

        // Creating level HUD.
        g.setColor(Color.white);
        g.drawString("Level: " + game.getLevelNumber(), 5, 70); // Change to level numb variable

        // Creating lives HUD.
        // for the amount of lives render an image.
        int x = 100; // Create x coordinate
        for (int i = this.getLives(); i > 0; i--) {
            g.drawImage(livesImage, x, 35, null);
            x += 20;
        }

        // Handle player death event. Still with additional lives).
        if(this.getHp() <= 0 && this.getLives() > 0) {
            gamePanel.showRespawn(); // Show respawn button.

            camera.setX(0); //reset camera so button coordinates don't glitch.
            camera.setY(0);
        }

        if(this.getLives() <= 0) {
            // Handle game over event.
            // Go back to the menu.
            this.game.getFrame().changePanel("menu");
            // call reset Game();
            gamePanel.resetGame();
        }
    } // end render

    /** Accessor methods */
    public Game getGame()                        { return this.game; }
    public int getHp()                           { return this.game.getHp(); }
    public int getAmmo()                         { return this.game.getAmmo(); }
    public int getLives()                        { return this.game.getLives(); }
    public Handler getHandler()                  { return this.handler; }
    public Camera getCamera()                    { return this.camera; }
    public BufferedImage getLevelMap(int numLevel ) { return this.levelImages.get(numLevel - 1); }  // -1 to offset index 0
    public BufferedImage getFloorImage(int numLevel ) { return this.floorImages.get(numLevel - 1); }  // -1 to offset index 0
    public BufferedImage getBlockImage(int numLevel ) { return this.blockImages.get(numLevel - 1); }
    public int getLevelNumber()                      { return game.getLevelNumber(); } // Refactor?

    /** Modifier methods */
    public void decHp()                          { this.game.decHp(); }
    public void decLives()                       { this.game.decLives(); }
    public void incAmmo(int inc)                 { this.game.incAmmo(inc); }
}
