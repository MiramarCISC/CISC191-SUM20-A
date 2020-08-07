package edu.sdccd.cisc191.wizardGame.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.anim.Animation;
import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
import edu.sdccd.cisc191.wizardGame.gui.screen.levels.AbstractLevel;
import edu.sdccd.cisc191.wizardGame.gui.sound.SoundEffect;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

public class Wizard extends GameObject {

    private Handler handler;
    private AbstractLevel level;
    private Game game;

    private BufferedImage[] wizardImage = new BufferedImage[4];

    private Animation anim;

    /**
     * No args Wizard constructor.
     */
    public Wizard() { super(); }

    public Wizard(int x, int y, ID id, Handler handler, Game game, AbstractLevel level, SpriteSheet cs) {
        super(x, y, id, cs);
        this.handler = handler;
        this.level = level;
        this.game = game;

        wizardImage[0] = cs.grabImage(13, 8, 32, 32);
        wizardImage[1] = cs.grabImage(14, 8, 32, 32);
        wizardImage[2] = cs.grabImage(15, 8, 32, 32);
        wizardImage[3] = cs.grabImage(16, 8, 32, 32);

        anim = new Animation(wizardImage, 200); // Animation speed
    }

    public void tick() {

        collision();

        x += velX;
        y += velY;

        // Check for game over
        if(level.getLives() <= 0) {
            handler.removeObject(this);
        }

        if(level.getHp() <= 0) {
            level.decLives();
            handler.removeObject(this);
        }

        // Movement handling
        if (handler.isUp()) velY = -5;
        else if (!handler.isDown()) velY = 0; // If down is being pushed and velocity gets set to zero. Lag may result

        if (handler.isDown()) velY = 5;
        else if (!handler.isUp()) velY = 0;

        if (handler.isRight()) velX = 5;
        else if (!handler.isLeft()) velX = 0;

        if (handler.isLeft()) velX = -5;
        else if (!handler.isRight()) velX = 0;

        anim.tick();
    }

    /**
     * Wizard collision detection.
     * Prevents from running over Blocks, picks up Crates, take hits from
     * monsters and enters next level colliding with Totem.
     */
    public void collision() {
        for (int i = 0; i < handler.getObject().size(); i++) {

            GameObject tempObject = handler.getObject().get(i);

            if(tempObject.getId() == ID.Block) {

                if(!placeFree((int) (x + velX), y, getBounds(), tempObject.getBounds())) {
                    velX = 0;
                }

                if(!placeFree(x, (int) (y + velY), getBounds(), tempObject.getBounds())) {
                    velY = 0;
                }
            }

            if(tempObject.getId() == ID.Crate) {

                if(getBounds().intersects(tempObject.getBounds())) {
                    level.incAmmo(10);
                    handler.removeObject(tempObject);
                    SoundEffect.CRATE.play();
                }
            }

            if(tempObject.getId() == ID.Totem) {

                if(getBounds().intersects(tempObject.getBounds())) {
                    // Remove totem and wizard from play.
                    handler.removeObject(tempObject);
                    handler.removeObject(this);
                    // Iterate to next level and play sound effect.
                    SoundEffect.LEVEL.play();
                    GamePanel gamePanel = (GamePanel) game.getFrame().getPanel("game");
                    game.incLevelNumber();
                    // Show load screen then build next level
                    game.pauseGame();
                    game.getFrame().showLoadScreen(2000);
                    game.resumeGame();
                    gamePanel.changeLevel();
                }
            }

            if(tempObject.getId() == ID.Minion || tempObject.getId() == ID.Ent || tempObject.getId() == ID.Horizontal) {
                if(getBounds().intersects(tempObject.getBounds())) {
                   level.decHp(); //Debug line
                }
            }
        }
    }

    public boolean placeFree(int x, int y, Rectangle myRect, Rectangle otherRect) {

        myRect.x = x;
        myRect.y = y;
        if (myRect.intersects(otherRect)) {
            return false;
        } else {
            return true;
        }
    }

    public void render(Graphics g) {
        if (velX == 0 && velY == 0)
            g.drawImage(wizardImage[0], x, y, 62, 62, null);
        else
            anim.render(g, x, y, 62, 62);
    }

    public Rectangle getBounds() {
        return new Rectangle (x, y, 62, 62);
    }

    /** Accessor methods */
    public AbstractLevel getLevel()           { return this.level; }
    public Handler getHandler()               { return this.handler; }
    public Animation getAnimation()           { return this.anim; }

    /** Modifier methods */
    public void setLevel(AbstractLevel level) { this.level = level; }
    public void setHandler(Handler handler)   { this.handler = handler; }
    public void setAnimation(Animation anim)  { this.anim = anim; }
}

