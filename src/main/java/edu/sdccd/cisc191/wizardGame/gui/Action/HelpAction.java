package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.Window;

import java.awt.event.ActionEvent;

public class HelpAction extends WizardGameAction{

    /**
     * String identifier that will be used to reference this class.
     */
    public static final String id = "helpAction";

    /**
     * Create this action.
     * @param frame Window which holds panel that instantiates button.
     */
    public HelpAction(Window frame){
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
     * Action to trigger help panel visibility.
     * @param e Help button event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.changePanel("help", false);
    }
}
