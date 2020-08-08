package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.Window;

import java.awt.event.ActionEvent;

public class QuitAction extends WizardGameAction {

    public static final String id = "quitAction";

    public QuitAction(Window frame){
        super(frame);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.changePanel("quit", false);
    }


}