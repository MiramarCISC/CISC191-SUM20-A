package edu.sdccd.cisc191.wizardGame.gui.screen.levels;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;


public class LevelTwo extends AbstractLevel {

    public LevelTwo(Game game, GamePanel gamePanel) {
        super(game, gamePanel);

        level = floorTwo; // Set floor type.
        levelNum = 2; // Set level number.
        loadLevel(getLevel(levelNum)); // Load level.
    }
}
