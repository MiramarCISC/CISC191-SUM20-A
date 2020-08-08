package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
import edu.sdccd.cisc191.wizardGame.gui.screen.Window;
import edu.sdccd.cisc191.wizardGame.gui.sound.SoundEffect;

import java.awt.event.ActionEvent;

public class PlayAction extends WizardGameAction {

    GamePanel panel;

    public static final String id = "playAction";

    public PlayAction(Window frame){
        super(frame);
        this.panel = (GamePanel) frame.allPanels.get("game");
        SoundEffect.THEME.play();
    }

    public void actionPerformed(ActionEvent event){
        frame.changePanel("game", true);
        // Resume game if paused
        if (frame.getGame().isGamePaused())
            frame.getGame().resumeGame();
    }

    @Override
    public String getId() {
        return id;
    }
}
