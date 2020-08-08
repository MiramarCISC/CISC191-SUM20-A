package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.Window;
import java.awt.event.ActionEvent;

public class PauseAction extends WizardGameAction {

    public static final String id = "pauseAction";

    /**
     * Create this action.
     * @param frame Window which holds panel that instantiates button.
     */
    public PauseAction(Window frame){
        super(frame);
    }


    /**
     * This action triggers game pause and shows pause panel.
     * @param e Pause button event.
     */
    public void actionPerformed(ActionEvent e){
        frame.changePanel("pause", false);
        frame.getGame().pauseGame();
        gamePanel.getLevel().releaseKeys();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

}
