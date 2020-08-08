package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
import edu.sdccd.cisc191.wizardGame.gui.screen.Window;

import java.awt.event.ActionEvent;

public class LoadAction extends WizardGameAction {

    /**
     * String identifier that will be used to reference this class.
     */
    public static final String id = "loadAction";

    /**
     * Create this action.
     * @param frame Window which holds panel that instantiates button.
     */
    public LoadAction(Window frame){
        super(frame);
        this.gamePanel = (GamePanel)frame.allPanels.get("game");
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Action to trigger game start via load functionality.
     * @param e Load button event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // Run load game method from GamePanel class.
        gamePanel.loadGame();

        // Change panels to game.
        frame.changePanel("game", true);
    }
}
