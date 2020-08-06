package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
import edu.sdccd.cisc191.wizardGame.gui.screen.Window;

import java.awt.event.ActionEvent;

public class PlayAction extends WizardGameAction {

    GamePanel panel;

    public static final String id = "playAction";

    public PlayAction(Window frame){
        super(frame, id);
        this.panel = (GamePanel)frame.allPanels.get("game");
    }

    public void actionPerformed(ActionEvent event){
        frame.changePanel("game", true);
    }

    @Override
    public String getId() {
        return id;
    }
}
