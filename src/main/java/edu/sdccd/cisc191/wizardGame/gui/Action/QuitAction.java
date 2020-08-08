package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.Window;

import java.awt.event.ActionEvent;

public class QuitAction extends WizardGameAction {

    /**
     * String identifier that will be used to reference this class.
     */
    public static final String id = "quitAction";

    /**
     * Create this action.
     * @param frame Window which holds panel that instantiates button.
     */
    public QuitAction(Window frame){
        super(frame);
    }


    /**
     * This action triggers game quit.
     * @param e Quit button event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.changePanel("quit", false);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }



}
