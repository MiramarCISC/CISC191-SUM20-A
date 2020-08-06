package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
import edu.sdccd.cisc191.wizardGame.gui.screen.Window;

import java.awt.event.ActionEvent;

public class ResumeAction extends WizardGameAction{

    GamePanel panel;

    public static final String id = "resumeAction";

    public ResumeAction(Window frame){
        super(frame, id);
        this.panel = (GamePanel)frame.allPanels.get("game");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.changePanel("game", false);
        frame.getGame().resumeGame();
    }

}
