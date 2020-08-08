package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.Window;

import java.awt.event.ActionEvent;

/**
 * Action that is triggered from pressing the resumeBtn in <a href="#{@link}">{@link edu.sdccd.cisc191.wizardGame.gui.screen.PausePanel}</a>.
 *
 */
public class ResumeAction extends WizardGameAction{

    public static final String id = "resumeAction";

    /**
     * Instantiate the resume action.
     * @param frame The window that contains panel with referenced JButton
     */
    public ResumeAction(Window frame){
        super(frame);
    }


    /**
     * This action triggers game resume.
     * @param e Resume button action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.changePanel("game", false);
        frame.getGame().resumeGame();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }


}
