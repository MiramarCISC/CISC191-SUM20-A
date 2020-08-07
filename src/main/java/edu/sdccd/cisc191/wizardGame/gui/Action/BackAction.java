package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.Window;

import java.awt.event.ActionEvent;

/**
 * The
 */

public class BackAction extends WizardGameAction {

    public static final String id = "backAction";

    public BackAction(Window frame){
        super(frame);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.changePanel(frame.getLastOpenPanel(), false);
    }


}
