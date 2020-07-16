import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    protected static STATE State = STATE.MENU; // Protected static as all classes need this variable

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private Menu menu;
    private Help help;
    private Pause pause;
    private Camera camera;
    private SpriteSheet ss; // spritesheet
    private SpriteSheet cs; // character sheet

    private BufferedImage level = null;
    private BufferedImage sprite_sheet = null;
    private BufferedImage char_sheet = null; //char sheet
    private BufferedImage floor = null;
    private BufferedImage lives_image;

    // Various public variables that are critical for display in the HUD, ammo, hp etc.
    public int ammo = 50;
    public int hp = 0; // Wizard fills the hp upon construction.
    public int lives = 3;
    public int level_numb = 1;
    public boolean totem_flag = false;

    // Modifying state debug
    public enum STATE{
        MENU,
        GAME,
        HELP,
        PAUSE,
    };

    public Game(int current_level, int current_life) {

        //new Window(1980,1080,"Wizard Game", this);

        Window.changeLevel(this); // Level one

        // Don't create a new instance of this handler again, could cause problems.
        // Instead swap it into new classes, since the objects are inside this handler.
        handler = new Handler();
        menu = new Menu();
        help = new Help();
        pause = new Pause();
        camera = new Camera(0, 0);
        this.addKeyListener(new KeyInput(handler));
        lives = current_life;


        BufferedImageLoader loader = new BufferedImageLoader();

        sprite_sheet = loader.loadImage("/main_sheet.png");
        char_sheet = loader.loadImage("/wizard_sheet.png"); //char sheet

        ss = new SpriteSheet(sprite_sheet);

        cs = new SpriteSheet(char_sheet); // character sheet

        lives_image = cs.grabImage(13, 8, 32, 32);

        // level_numb parameter determines which level is loaded.
        if (current_level == 1) {
            level = loader.loadImage("/level_one.png"); // load level
            floor = ss.grabImage(6, 6, 32, 32); // load floor tiles
            level_numb = current_level;
        }

        else if (current_level > 1) {
            level = loader.loadImage("/level_two.png"); // load level 2
            floor = ss.grabImage(7, 2, 32, 32); // load different floor tiles
            level_numb = current_level;
        }

        this.addMouseListener(new MouseInput(handler, camera, this, ss, cs));

        loadLevel(level);

        start(); // Everything is loaded in, now begin rendering.
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        isRunning = false;
        /*try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }No need for thread join, rely on boolean*/
    }

    public void run() {
        // Main gameloop thread.


        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    public void tick() {

        if(State == STATE.GAME) {
            for(int i = 0; i < handler.object.size(); i++) {
                if(handler.object.get(i).getId() == ID.Player) {
                    camera.tick(handler.object.get(i));
                }
            }

            handler.tick();
        }
    } // end tick method

    public void render() {
        // Renders everything in the game.
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            // Three in the chamber strat, preloads 3 frames for each render.
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        if(State == STATE.GAME) {
            ///////////this is where we actually draw stuff to game///////////////////////


            // Create background.
            g.setColor(Color.black);
            g.fillRect(0, 0, 1920, 1080);

            // Translate location of camera.
            g2d.translate(-camera.getX(), -camera.getY());

            for(int xx = 0; xx < 30*92; xx += 32) { //debug
                for(int yy = 0; yy < 30*72; yy += 32) {
                    g.drawImage(floor, xx, yy, null);
                }
            }

            // Render all GameObjects
            handler.render(g);

            // Keep translating camera as coords change. (Follow player)
            g2d.translate(camera.getX(), camera.getY());

            // HUD: Creating health bar.
            g.setColor(Color.gray);
            g.fillRect(5, 5, 200, 32);
            g.setColor(Color.red);
            g.fillRect(5, 5, hp*2, 32);
            g.setColor(Color.black);
            g.drawRect(5, 5, 200, 32);
            g.setColor(Color.white);
            g.drawString("HEALTH", 5, 25);

            // Creating ammo HUD.
            g.setColor(Color.white);
            g.drawString("Ammo: " + ammo, 5, 50);

            // Creating level HUD.
            g.setColor(Color.white);
            g.drawString("Level: " + level_numb, 5, 70);

            // Creating escape button.
            g.setColor(Color.gray);
            g.fillRect(250, 5, 75, 25);
            g.setColor(Color.gray);
            g.fillRect(250, 5, 75, 25);
            g.setColor(Color.gray);
            g2d.drawRect(250, 5, 75, 25);
            g.setColor(Color.black);
            g.drawString("|| PAUSE", 265, 25);


            // Creating lives HUD.
            // for the amount of lives render an image.
            int x = 100; // Create x coordinate
            for (int i = lives; i > 0; i--) {
                g.drawImage(lives_image, x, 35, null);
                x += 20;
            }

            // Handle player death event. (See wizard class)
            if(hp <= 0) {
                // The player has died, loses a life and the player is removed from game, then....
                g.setColor(Color.white);
                g.drawString("You have died!", 400, 281);
                g.drawString("Click to respawn", 810, 150);


                Rectangle resButton = new Rectangle(810, 150, 150, 75); //reset button.
                g2d.draw(resButton);

                camera.setX(0); //reset camera so button coordinates don't glitch.
                camera.setY(0);
            }

            if(lives <= 0) {
                // Handle game over event. (Also done through wizard class)
                g.setColor(Color.white);
                g.drawString("Game Over!", 400, 281);

                Game.State = Game.STATE.MENU;

                camera.setX(0); //If you forget to reset the camera, you're gonna have a bad time...
                camera.setY(0);

                // Brief explanation, basically this is refreshing lives so we don't end up back here,
                // Starting a new game, then killing this thread. If you don't stop this thread.
                // You're gonna have a bad time....
                stop();
                lives = 3;
                new Game(1, lives);
            }

            // Level functionality (possibly refactor into new function)
            if(totem_flag == true) {
                /* If the public level_numb variable has incremented
                Then kill the thread and start a new game loading a new level.
                If you don't kill the thread with stop(); You're not going to like what happens...
                Every level will be a new thread. Layering on top of the window.
                 */
                stop();
               new Game(level_numb, lives);
            }
            //////////////////////////////////
        } else if(State == STATE.MENU) {
            menu.render(g);
        } else if (State == STATE.HELP) {
            help.render(g);
        } else if(State == STATE.PAUSE) {
            pause.render(g);
        }//end if state
        g.dispose();
        bs.show();
    } // end render method

    // Loading the level.
    private void loadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        for(int xx = 0; xx < w; xx++) {
            for(int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 255 && green == 0 && blue == 0) // pure red
                    handler.addObject(new Block(xx*32, yy*32, ID.Block, ss, this));

                if(red == 0 && green == 0 && blue == 255) // pure blue
                    handler.addObject(new Wizard(xx*32, yy*32, ID.Player, handler, this, cs));

                if(red == 0 && green == 255 && blue == 0) // pure green
                    handler.addObject(new Minion(xx*32, yy*32, ID.Minion, handler, cs));

                if(red == 0 && green == 255 && blue == 255) // pure cyan
                    handler.addObject(new Crate(xx*32, yy*32, ID.Crate, ss));

                if(red == 255 && green == 255 && blue == 0) // pure yellow
                    handler.addObject(new Totem(xx*32, yy*32, ID.Totem, ss));

                if(red == 255 && green == 0 && blue == 255) //pure magenta
                    handler.addObject(new Knight(xx*32, yy*32, ID.Knight, handler, this, cs));
            }
        }
    }

    public static void main(String[] args) {
        new Window(1980,1080,"Wizard Game"); // This window will never be created again. This is the main game window.
        new Game(1, 3); // Represents the first level thread.
    }
}