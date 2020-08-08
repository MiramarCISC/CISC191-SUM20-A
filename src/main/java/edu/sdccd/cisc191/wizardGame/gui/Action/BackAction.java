package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.Window;

import java.awt.event.ActionEvent;

/**
 * A class that handles backBtn functionality via actionListener.
 */

public class BackAction extends WizardGameAction {

    /**
     * String identifier that will be used to reference this class.
     */
    public static final String id = "backAction";

    /**
     * Create this action.
     * @param frame Window which holds panel that instantiates button.
     */
    public BackAction(Window frame){
        super(frame);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     *  Action to change panel to last open panel.
     * @param e Back button event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.changePanel(frame.getLastOpenPanel(), false);
    }


}
