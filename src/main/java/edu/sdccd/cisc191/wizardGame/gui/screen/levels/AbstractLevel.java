package edu.sdccd.cisc191.wizardGame.gui.screen.levels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.anim.Camera;
import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
import edu.sdccd.cisc191.wizardGame.objects.Block;
import edu.sdccd.cisc191.wizardGame.objects.Crate;
import edu.sdccd.cisc191.wizardGame.objects.Ent;
import edu.sdccd.cisc191.wizardGame.objects.Handler;
import edu.sdccd.cisc191.wizardGame.objects.Hound;
import edu.sdccd.cisc191.wizardGame.objects.ID;
import edu.sdccd.cisc191.wizardGame.objects.Knight;
import edu.sdccd.cisc191.wizardGame.objects.Minion;
import edu.sdccd.cisc191.wizardGame.objects.Totem;
import edu.sdccd.cisc191.wizardGame.objects.Wizard;
import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

public abstract class AbstractLevel {

    // Several utility variables.
    protected Game game;
    protected GamePanel gamePanel;
    protected int levelNumb;
    protected BufferedImageLoader loader = new BufferedImageLoader();

    // Load camera and handler.
    protected Camera camera;
    protected Handler handler;

    // Load level images and sprite sheets.
    protected SpriteSheet ss = new SpriteSheet(loader.loadImage("/main_sheet.png")); // Who says we need to declare variables?
    protected SpriteSheet cs = new SpriteSheet(loader.loadImage("/wizard_sheet.png")); // character sheet

    // Level Maps
    protected BufferedImage levelOneImage = loader.loadImage("/level_one.png");
    protected BufferedImage levelTwoImage = loader.loadImage("/level_two.png");
    protected BufferedImage levelThreeImage = loader.loadImage("/level_two.png");
    protected BufferedImage levelFourImage = loader.loadImage("/level_two.png");
    protected BufferedImage levelFiveImage = loader.loadImage("/level_one.png");

    // Floor Tile Images
    protected BufferedImage floorOne = ss.grabImage(6, 6, 32, 32);
    protected BufferedImage floorTwo = ss.grabImage(7, 2, 32, 32);
    protected BufferedImage floorThree = ss.grabImage(6, 6, 32, 32);
    protected BufferedImage floorFour = ss.grabImage(7, 2, 32, 32);
    protected BufferedImage floorFive = ss.grabImage(6, 6, 32, 32);

    // Current Level Map and Current Floor. Instantiated in Extended class.
    protected BufferedImage currentMap;
    protected BufferedImage floor;

    // Sprite used to display lives in HUD
    protected BufferedImage livesImage = cs.grabImage(13, 8, 32, 32); // Sprite to display lives.

    // Constructor
    public AbstractLevel(Game game, GamePanel gamePanel) {
        this.game = game;
        this.gamePanel = gamePanel;
        camera = new Camera(0, 0); // and the camera
        handler = new Handler(); // make sure the handler is started from new.
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

                // Color map determines which sprites render to the map.
                if (red == 255 && green == 0 && blue == 0) // pure red
                    handler.addObject(new Block(xx * 32, yy * 32, ID.Block, ss, this));

                if (red == 0 && green == 0 && blue == 255) // pure blue
                    handler.addObject(new Wizard(xx * 32, yy * 32, ID.Player, handler, game, this, cs));

                if (red == 0 && green == 255 && blue == 0) // pure green
                    handler.addObject(new Minion(xx * 32, yy * 32, ID.Minion, handler, cs));

                if (red == 0 && green == 255 && blue == 255) // pure cyan
                    handler.addObject(new Crate(xx * 32, yy * 32, ID.Crate, ss));

                if (red == 255 && green == 255 && blue == 0) // pure yellow
                    handler.addObject(new Totem(xx * 32, yy * 32, ID.Totem, ss));

                if (red == 255 && green == 0 && blue == 255) //pure magenta
                    handler.addObject(new Knight(xx * 32, yy * 32, ID.Knight, handler, this, cs));

                if (red == 0 && green == 153 && blue == 102) // # 009966 green
                    handler.addObject(new Ent(xx * 32, yy * 32, ID.Ent, handler, this, cs)); //

                if (red == 255 && green == 153 && blue == 51) // # ff9933 vivid orange
                    handler.addObject(new Hound(xx * 32, yy * 32, ID.Hound, handler, cs));
            }

        }
    }

    public void respawn() {
        // Respawn the wizard after death.

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
                g.drawImage(floor, xx, yy, null);
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
        g.drawString("Level: " + levelNumb, 5, 70); // Change to level numb variable

        // Creating escape button.
        g.setColor(Color.gray);
        g.fillRect(250, 5, 75, 25);
        g.setColor(Color.gray);
        g.fillRect(250, 5, 75, 25);
        g.setColor(Color.gray);
        g.drawRect(250, 5, 75, 25);
        g.setColor(Color.black);
        g.drawString("|| PAUSE", 265, 25);


        // Creating lives HUD.
        // for the amount of lives render an image.
        int x = 100; // Create x coordinate
        for (int i = this.getLives(); i > 0; i--) {
            g.drawImage(livesImage, x, 35, null);
            x += 20;
        }

        // Handle player death event. (See wizard class)
        if(this.getHp() <= 0) {
            // The player has died, loses a life and the player is removed from game, then....
            g.setColor(Color.white);
            g.drawString("You have died!", 400, 281);
            g.drawString("Click to respawn", 810, 150);


            Rectangle resButton = new Rectangle(810, 150, 150, 75); //reset button.
            g.draw(resButton);

            camera.setX(0); //reset camera so button coordinates don't glitch.
            camera.setY(0);
        }

        if(this.getLives() <= 0) {
            // Handle game over event. (Also done through wizard class)
            g.setColor(Color.white);
            g.drawString("Game Over!", 400, 281);

            game.setGameState(Game.STATE.MENU); // this is how it's done.
            gamePanel.resetGame(); // call reset level
            // Go back to the menu.
        }
    } // end render

    public void resetLevel() {
        // Resets hp, lives and resets entire game back to level One. Really should be called resetGame()?
        game.setHp(1); // debug
        game.setLives(3);
        gamePanel.setLevel(1);
        handler.clearHandler();
        loadLevel(currentMap);
        gamePanel.update();
    }

    /*getters and setters.*/
    public int getHp(){
        return game.getHp();
    }

    public void decHp(){
        game.decHp();
    }

    public int getLives() {
        return game.getLives();
    }

    public void decLives() {
        game.decLives();
    }

    public int getAmmo() {
        return game.getAmmo();
    }
    public void incAmmo(int inc) {
        game.incAmmo(inc);
    }

    public Handler getHandler(){
        return handler;
    }

    public Camera getCamera() {
        return camera;
    }
}
