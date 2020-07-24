package edu.sdccd.cisc191.wizardGame.gui.screen;

import edu.sdccd.cisc191.wizardGame.Game;


public class LevelOne extends AbstractLevel {

    public LevelOne(Game game, GamePanel gamePanel) { // Level will take the game controller, then pass it to the abstract super. Pretty confusing
        // Call abstract parent constructor.
        super(game, gamePanel);

        // Set floor type.
        floor = floorOne;
        // Set level number.
        levelNumb = 1;
        // Load level.
        loadLevel(levelOneImage);

    }
}

