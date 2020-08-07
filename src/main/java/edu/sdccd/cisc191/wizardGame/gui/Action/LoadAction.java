package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
import edu.sdccd.cisc191.wizardGame.gui.screen.Window;

import java.awt.event.ActionEvent;

public class LoadAction extends WizardGameAction {

    GamePanel panel;
    public static final String id = "loadAction";

    public LoadAction(Window frame){
        super(frame);
        this.panel = (GamePanel)frame.allPanels.get("game");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Run load game method from GamePanel class.
        gamePanel.loadGame();

        // Change panels to game.
        frame.changePanel("game", true);


    }
}
