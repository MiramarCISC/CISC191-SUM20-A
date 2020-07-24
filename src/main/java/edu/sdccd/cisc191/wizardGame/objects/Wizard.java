package edu.sdccd.cisc191.wizardGame.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.anim.Animation;
import edu.sdccd.cisc191.wizardGame.gui.screen.AbstractLevel;
import edu.sdccd.cisc191.wizardGame.gui.screen.LevelOne;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

public class Wizard extends GameObject {

    Handler handler;
    AbstractLevel level;
    Game game;

    private BufferedImage[] wizard_image = new BufferedImage[4];

    Animation anim;

    public Wizard(int x, int y, ID id, Handler handler, Game game, AbstractLevel level, SpriteSheet cs) {
        super(x, y, id, cs);
        this.handler = handler;
        this.level = level;
        this.game = game;

        wizard_image[0] = cs.grabImage(13, 8, 32, 32);
        wizard_image[1] = cs.grabImage(14, 8, 32, 32);
        wizard_image[2] = cs.grabImage(15, 8, 32, 32);
        wizard_image[3] = cs.grabImage(16, 8, 32, 32);

        anim = new Animation(wizard_image, 200); // Animation speed

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
        else if(!handler.isDown()) velY = 0; // If down is being pushed and velocity gets set to zero. Lag may result

        if(handler.isDown()) velY = 5;
        else if(!handler.isUp()) velY = 0;

        if (handler.isRight()) velX = 5;
        else if(!handler.isLeft()) velX = 0;

        if(handler.isLeft()) velX = -5;
        else if(!handler.isRight()) velX = 0;

        anim.tick();
    }

    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Block) {

                if(!place_free((int) (x+velX), y, getBounds(), tempObject.getBounds())) {
                    velX = 0;
                }

                if(!place_free(x, (int) (y+velY), getBounds(), tempObject.getBounds())) {
                    velY = 0;
                }
            }

            if(tempObject.getId() == ID.Crate) {

                if(getBounds().intersects(tempObject.getBounds())) {
                    level.incAmmo(10);
                    handler.removeObject(tempObject);
                }
            }

            if(tempObject.getId() == ID.Totem) {

                if(getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(tempObject);
                    game.setLevel(2);
                }
            }

            if(tempObject.getId() == ID.Minion || tempObject.getId() == ID.Ent) {
                if(getBounds().intersects(tempObject.getBounds())) {
                   level.decHp(); //Debug line
                }
            }
        }
    }

    public boolean place_free(int x, int y, Rectangle myRect, Rectangle otherRect) {

        myRect.x = x;
        myRect.y = y;
        if(myRect.intersects(otherRect)) {
            return false;
        }
        else {
            return true;
        }
    }

    public void render(Graphics g) {
        if(velX == 0 && velY == 0)
            g.drawImage(wizard_image[0], x, y, 62, 62, null);
        else
            anim.render(g, x, y, 62, 62);
    }

    public Rectangle getBounds() {
        return new Rectangle (x, y, 62, 62);
    }
}

