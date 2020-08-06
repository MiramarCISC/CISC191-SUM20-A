package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
import edu.sdccd.cisc191.wizardGame.gui.screen.Window;

import java.awt.event.ActionEvent;

/**
 * The
 */

public class BackAction extends WizardGameAction {

    GamePanel panel;

    public static final String id = "backAction";

    public BackAction(Window frame){
        super(frame, id);
        this.panel = (GamePanel)frame.allPanels.get("game");
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
