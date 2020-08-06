/**
 * Inspiration for Action implementation came from the open-source, Java-based game, FreeCol:
 * Resources: https://github.com/FreeCol/freecol
 *
 */

package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.option.OptionGroup;
import edu.sdccd.cisc191.wizardGame.gui.screen.Window;

/**
 * Action manager for all of the button functionality of the GUI. Panel button trigger action events stored
 * within the map extended from {@code OptionGroup}.
 *
 * @author Seth Steen-Fuentes
 *
 * 8/4/2020
 */

public class ActionManager extends OptionGroup {

    private final Window frame;

    public ActionManager(Window frame) {
        this.frame = frame;
    }

    /**
     *
     * @param frame The GUI instance that requires response to actions.
     */
    public void initializeActions(Window frame){
        add(new BackAction(frame));
        add(new HelpAction(frame));
        add(new PauseAction(frame));
        add(new PlayAction(frame));
        add(new QuitAction(frame));
        add(new RespawnAction(frame));
        add(new ResumeAction(frame));

    }

    public WizardGameAction getWizardGameAction(String id){
        return getOption(id, WizardGameAction.class);
    }
}
