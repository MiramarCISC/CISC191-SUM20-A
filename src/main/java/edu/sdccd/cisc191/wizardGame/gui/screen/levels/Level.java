package edu.sdccd.cisc191.wizardGame.gui.screen.levels;

import edu.sdccd.cisc191.wizardGame.Game;
import edu.sdccd.cisc191.wizardGame.gui.screen.GamePanel;


public class Level extends AbstractLevel {

    public Level(Game game, GamePanel gamePanel) {
        // Call the AbstractLevel parent constructor.
        super(game, gamePanel);

        // Obtain the level number from game class.
        levelNumber = game.getLevelNumber();

        // Use current level to obtain appropriate floor tile.
        currentFloor = getFloorImage(levelNumber);

        // Load the current level map using similar technique.
        loadLevel(getLevelMap(levelNumber));
    }
}
