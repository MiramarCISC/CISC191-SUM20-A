package edu.sdccd.cisc191.wizardGame.gui.screen;

import edu.sdccd.cisc191.wizardGame.Game;


public class LevelTwo extends AbstractLevel {

    public LevelTwo(Game game) {
        super(game);

        // Set floor type.
        floor = floorTwo;
        // Set level number.
        levelNumb = 2;
        // Load level.
        loadLevel(levelTwoImage);
        // Update all game handlers.
        game.Update();





    }
}
