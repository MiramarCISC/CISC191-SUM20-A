package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
import edu.sdccd.cisc191.wizardGame.gui.screen.Window;
import edu.sdccd.cisc191.wizardGame.save.DataManager;
import edu.sdccd.cisc191.wizardGame.save.SaveData;

import java.awt.event.ActionEvent;

public class SaveAction extends WizardGameAction {

    /**
     * String identifier that will be used to reference this class.
     */
    public static final String id = "saveAction";

    /**
     * Create this action.
     * @param frame Window which holds panel that instantiates button.
     */
    public SaveAction(Window frame){
        super(frame);
    }


    /**
     * This action triggers saving of game attributes.
     * @param e Save button event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        gamePanel.getLevel().releaseKeys();

        SaveData data = new SaveData();
        data.level = gamePanel.getGame().getLevelNumber();
        data.lives = gamePanel.getGame().getLives();
        data.ammo = gamePanel.getGame().getAmmo();
        data.hp = gamePanel.getGame().getHp();
        try {
            DataManager.save(data, "1.save");
            System.out.println("Data saved");
        }
        catch (Exception error) {
            System.out.println("Couldn't save: " + error.getMessage());
        }
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

}
