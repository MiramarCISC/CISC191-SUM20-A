package edu.sdccd.cisc191.wizardGame.gui.Action;

import javax.swing.*;
import java.awt.event.ActionListener;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
import edu.sdccd.cisc191.wizardGame.gui.screen.Window;
import edu.sdccd.cisc191.wizardGame.option.Option;

/**
 * Super class of actions triggered by buttons within the WizardGame GUI. Subclasses
 * that inherit this class are stored within a map in {@link ActionManager}.
 *
 * @author Seth Steen-Fuentes
 *
 * 08/05/2020
 */

public abstract class WizardGameAction extends AbstractAction implements Option<WizardGameAction>, ActionListener {

    protected final Window frame;
    public GamePanel gamePanel;

    public WizardGameAction(Window frame) {
        this.frame = frame;
    }

    protected Game getGame()                { return frame.getGame(); }

    /**
     * Method that would serve a role in a more complex object ID system,
     * possibly for serialization.
     *</p>
     * This method is not used in current project structure.
     *
     * @return
     */
    public abstract String getId();
}
