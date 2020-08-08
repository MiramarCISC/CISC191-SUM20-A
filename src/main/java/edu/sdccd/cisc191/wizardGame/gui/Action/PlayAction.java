package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
import edu.sdccd.cisc191.wizardGame.gui.screen.Window;
import edu.sdccd.cisc191.wizardGame.gui.sound.SoundEffect;

import java.awt.event.ActionEvent;

public class PlayAction extends WizardGameAction {

    /**
     * JPanel that contains button that will reference this class.
     */
    GamePanel panel;

    /**
     * String identifier that will be used to reference this class.
     */
    public static final String id = "playAction";

    /**
     * Create this action.
     * @param frame Window which holds panel that instantiates button.
     */
    public PlayAction(Window frame){
        super(frame);
        this.panel = (GamePanel) frame.allPanels.get("game");
        SoundEffect.THEME.play();
    }

    /**
     * This action triggers game panel to become visible and gameplay to resume.
     * @param e Play button event.
     */
    public void actionPerformed(ActionEvent e){
        frame.changePanel("game", true);
        // Resume game if paused
        if (frame.getGame().isGamePaused())
            frame.getGame().resumeGame();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }
}
