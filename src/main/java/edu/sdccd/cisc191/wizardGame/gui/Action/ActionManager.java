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
 * within the map extended from {@link OptionGroup}.
 *
 * @author Seth Steen-Fuentes
 *
 * 8/4/2020
 */

public class ActionManager extends OptionGroup {

    public ActionManager() {
    }

    /**
     * Method to add all game actions to map for access via JButton ActionEvents within panels.
     * @param frame The GUI instance that requires response to actions.
     */
    public void initializeActions(Window frame){
        add(new BackAction(frame));
        add(new HelpAction(frame));
        add(new LoadAction(frame));
        add(new PauseAction(frame));
        add(new PlayAction(frame));
        add(new QuitAction(frame));
        add(new RespawnAction(frame));
        add(new ResumeAction(frame));
        add(new SaveAction(frame));
        add(new MuteAction(frame));
    }

    /**
     * Used to add correct Action to addActionListener call.
     * @param id Reference string for class
     * @return The class that handles the actionPerformed method for that JButton's functionality.
     */
    public WizardGameAction getWizardGameAction(String id){
        return getOption(id, WizardGameAction.class);
    }

}
