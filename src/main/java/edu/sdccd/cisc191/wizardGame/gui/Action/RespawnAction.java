package edu.sdccd.cisc191.wizardGame.gui.Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.sdccd.cisc191.wizardGame.gui.screen.*;

public class RespawnAction extends WizardGameAction implements ActionListener {

    /**
     * String identifier that will be used to reference this class.
     */
    public static final String id = "respawnAction";

    /**
     * Create this action.
     * @param frame Window which holds panel that instantiates button.
     */
    public RespawnAction(Window frame){
        super(frame);
    }


    /**
     * This action triggers {@link edu.sdccd.cisc191.wizardGame.objects.Wizard} respawn using
     * {@code wizardRespawn()} method from {@link GamePanel}.
     * @param e Respawn button event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gamePanel.respawn();
        frame.getGame().wizardRespawn();
        gamePanel.getRespawnBtn().setVisible(false);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

}
