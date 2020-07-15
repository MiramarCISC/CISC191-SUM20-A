import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/* Note about whats happening here as it's extremely convoluted - Jordan,
so we notice that in the Menu buttons are drawn as seen below playButton etc, we now check to see if the points of the mouse click event
denoted with variables mx and my are within the boundaries of that button, if that is true and we are in the menu state, then switch to game.
The reason to check if we are in menu state first, is otherwise a mouse click will restart the game during the actual game, not good since we click to
shoot ammo. This is not a good technique simply to create a menu button but it does work. The use of states is definately the way to go, but it will require refactoring.
 */

//n the mouse pressed event, put all the button checks within a if statement that checks if Game.State == Game.STATE.MENU

public class MouseInput extends MouseAdapter {

    // These buttons are here so we can check for their size values and calculate clicks. Why are these public...good question.
    public Rectangle resButton = new Rectangle(810, 150, 150, 75);
    public Rectangle playButton = new Rectangle(810, 150, 150, 75);
    public Rectangle helpButton = new Rectangle(810, 450, 150, 75);
    public Rectangle quitButton = new Rectangle(810, 750, 150, 75);
    public Rectangle escButton = new Rectangle(250, 5, 75, 25);

    private Handler handler;
    private Camera camera;
    private Game game;
    private SpriteSheet ss;
    private SpriteSheet cs; //Needs two sprite sheets, that's the reason I would like to just have one big sprite sheet.

    public MouseInput (Handler handler, Camera camera, Game game, SpriteSheet ss, SpriteSheet cs) {
        this.handler = handler;
        this.camera = camera;
        this.game = game;
        this.ss = ss;
        this.cs = cs;
    }

    public void mousePressed(MouseEvent e) {
        int mx = (int) (e.getX() + camera.getX());
        int my = (int) (e.getY() + camera.getY());

        int x = e.getX();
        int y = e.getY();

        //Remember camera origins also control button clicks, but camera is mapped to player. Always reset.
        if (Game.State == Game.STATE.GAME) {

            for(int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if(tempObject.getId() == ID.Player && game.ammo >= 1) {
                    handler.addObject(new Bullet(tempObject.getX()+16, tempObject.getY()+24, ID.Bullet, handler, mx, my, ss));
                    game.ammo--;
                }
            } // end object for loop.

            if(resButton.contains(mx, my) && game.hp <= 0) {
                // Res button if player has died but still has lives > 0.
                handler.addObject(new Wizard(50, 50, ID.Player, handler, game, cs));
            }

            if(escButton.contains(x, y)) {
                // Res button if player has died but still has lives > 0.
                Game.State = Game.STATE.PAUSE;
            }

        }

        else if (Game.State == Game.STATE.MENU) {
            if(playButton.contains(x, y)) {
                Game.State = Game.STATE.GAME;
            }

            else if(helpButton.contains(x, y)) {
                Game.State = Game.STATE.HELP;
            }

            else if(quitButton.contains(x,y)) {
                Window.quitGame();
            }
        }

        else if (Game.State == Game.STATE.PAUSE) {
            if(playButton.contains(x, y)) {
                Game.State = Game.STATE.GAME;
            }

            else if(quitButton.contains(x,y)) {
                Window.quitGame();
            }
        }// end PAUSE else if (needs refactoring). (possibly a switch statement)

        else if (Game.State == Game.STATE.HELP) {
            if(quitButton.contains(x,y)) {
                Game.State = Game.STATE.MENU;
            }
        } // End HELP else if
    }
} // end class
