package edu.sdccd.cisc191.wizardGame.gui.screen.levels;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;


public class LevelOne extends AbstractLevel {

    public LevelOne(Game game, GamePanel gamePanel) { // Level will take the game controller, then pass it to the abstract super. Pretty confusing
        super(game, gamePanel);

        level = floorOne; // Set floor type.
        levelNum = 1; // Set level number.
        loadLevel(getLevel(levelNum)); // Load level.
    }
}

