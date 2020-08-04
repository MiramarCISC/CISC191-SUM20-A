package edu.sdccd.cisc191.wizardGame.gui.Action;

import java.awt.*;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.option.OptionGroup;
import edu.sdccd.cisc191.wizardGame.gui.screen.Window;

public class ActionManager extends OptionGroup {

    private final Window frame;

    public ActionManager(Window frame) {
        this.frame = frame;
    }

    public void initializeActions(Window frame){
        add(new PauseAction(frame));
        add(new RespawnAction(frame));
    }

    public WizardGameAction getWizardGameAction(String id){
        return getOption(id, WizardGameAction.class);
    }
}
