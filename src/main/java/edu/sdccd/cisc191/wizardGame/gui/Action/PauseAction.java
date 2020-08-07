package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.Window;
import java.awt.event.ActionEvent;

public class PauseAction extends WizardGameAction {

    public static final String id = "pauseAction";

    public PauseAction(Window frame){
        super(frame);
    }


    public void actionPerformed(ActionEvent event){
        frame.changePanel("pause", false);
        frame.getGame().pauseGame();
        gamePanel.getLevel().releaseKeys();

    }

    @Override
    public String getId() {
        return id;
    }

}
