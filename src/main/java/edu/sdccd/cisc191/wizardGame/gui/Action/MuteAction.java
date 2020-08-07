package edu.sdccd.cisc191.wizardGame.gui.Action;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;
import edu.sdccd.cisc191.wizardGame.gui.screen.Window;
import edu.sdccd.cisc191.wizardGame.gui.sound.SoundEffect;

import java.awt.event.ActionEvent;

public class MuteAction extends WizardGameAction {

    Game game;
    public static final String id = "muteAction";

    public MuteAction(Window frame){
        super(frame);
    }


    public void actionPerformed(ActionEvent event){
        game = gamePanel.getGame();

        if (!game.gameMuted()) {
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

    @Override
    public String getId() {
        return id;
    }

}
