package edu.sdccd.cisc191.wizardGame.gui.Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.screen.*;
import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;

public class RespawnAction extends WizardGameAction implements ActionListener {

    GamePanel panel;

    public static final String id = "respawnAction";

    public RespawnAction(Window frame){
        super(frame, id);
        this.panel = (GamePanel)frame.allPanels.get("game");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.respawn();
        frame.getGame().wizardRespawn();
        panel.getRespawnBtn().setVisible(false);

    }

}
