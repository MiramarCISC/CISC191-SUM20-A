package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
import edu.sdccd.cisc191.wizardGame.gui.screen.Window;
import edu.sdccd.cisc191.wizardGame.gui.sound.SoundEffect;

import java.awt.event.ActionEvent;

public class MuteAction extends WizardGameAction {

    /**
     *  Current game controller.
     */
    Game game;

    /**
     * String identifier that will be used to reference this class.
     */
    public static final String id = "muteAction";

    /**
     * Create this action.
     * @param frame Window which holds panel that instantiates button.
     */
    public MuteAction(Window frame){
        super(frame);

    }


    /**
     * Button to trigger sound on/off.
     * @param e Mute button event.
     */
    public void actionPerformed(ActionEvent e){
        game = gamePanel.getGame();

        if (!game.isGameSoundMuted()) {
            SoundEffect.THEME.stop();
            SoundEffect.volume = SoundEffect.Volume.MUTE;
            gamePanel.getLevel().releaseKeys();
        }

        else{
            SoundEffect.volume = SoundEffect.Volume.LOW;
            SoundEffect.THEME.play();
        }

        game.muteGame();

    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

}
