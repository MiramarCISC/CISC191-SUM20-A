package edu.sdccd.cisc191.wizardGame.gui.Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.sdccd.cisc191.wizardGame.gui.screen.*;
import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;

public class RespawnAction extends WizardGameAction implements ActionListener {

    public static final String id = "respawnAction";

    public RespawnAction(Window frame){
        super(frame);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gamePanel.respawn();
        frame.getGame().wizardRespawn();
        gamePanel.getRespawnBtn().setVisible(false);

    }

}
