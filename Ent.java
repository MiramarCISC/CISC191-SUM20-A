import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ent extends GameObject {

    private Handler handler;
    private Game game;
    private BufferedImage[] ent_image = new BufferedImage[8];
    private SpriteSheet cs; // Used for spawning new minions.
    Animation anim;

    // Import random as enemies will roam around randomly.
    Random r = new Random();
    int choose = 0;
    int hp = 100;

    public Ent(int x, int y, ID id, Handler handler, Game game, SpriteSheet cs) {
        super(x, y, id, cs);
        this.handler = handler;
        this.game = game;
        this.cs = cs;

        // Loading animations
        ent_image[0] = cs.grabImage(1, 8, 32, 32);
        ent_image[1] = cs.grabImage(2, 8, 32, 32);
        ent_image[2] = cs.grabImage(3, 8, 32, 32);
        ent_image[3] = cs.grabImage(4, 8, 32, 32);
        ent_image[4] = cs.grabImage(5, 8, 32, 32);
        ent_image[5] = cs.grabImage(6, 8, 32, 32);
        ent_image[6] = cs.grabImage(7, 8, 32, 32);
        ent_image[7] = cs.grabImage(8, 8, 32, 32);


        anim = new Animation(ent_image, 150);
        }

    public void tick() {
        x += velX;
        y += velY;

        /* Constantly 'choose' is a random variable from 0-9.
        If choose == 0, enemy moves in a different direction. */
        choose = r.nextInt(10);

        // If enemy collides, they automatically pick a new direction.
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Block) {
                // Play around with this to get a better enemy AI. Lil' glitchy...
                if(getBoundsBig().intersects(tempObject.getBounds())) {
                    x += (velX*5) * -1;
                    y += (velY*5) * -1; //Invert velocity and shoot it back (ricochet)
                    velX *= -1;
                    velY *= -1;
                } else if (choose == 0) {
                    // Random between -4 and 4 algorithm.
                    velX = (r.nextInt(2 - -2) + -2);
                    velY = (r.nextInt(2 - -2) + -2);
                }
            }

            if(tempObject.getId() == ID.Bullet) {
                // If bullet hits minion...
                if(getBounds().intersects(tempObject.getBounds())) {
                    hp -= 50;
                    handler.removeObject(tempObject);
                }
            }
        }
        anim.tick();

        if(hp <= 0) {
            // HP handling.
            handler.removeObject(this);

            // Upon death spawns 3 minions.
            handler.addObject(new Minion(this.getX(), this.getY(), ID.Minion, handler, cs));
            handler.addObject(new Minion(this.getX(), this.getY(), ID.Minion, handler, cs));
            handler.addObject(new Minion(this.getX(), this.getY(), ID.Minion, handler, cs));
        }

    } //End tick method





        public void render (Graphics g){
            anim.render(g, x, y, 32, 32);
        }

        public Rectangle getBounds () {
            return new Rectangle(x, y, 32, 32);
        }

        public Rectangle getBoundsBig () {
            // Makes bounding box slightly bigger for colliding with walls as opposed to bullets (not so tight).
            return new Rectangle(x - 16, y - 16, 64, 64);
        }
    }

