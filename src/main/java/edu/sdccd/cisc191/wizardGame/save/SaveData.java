package edu.sdccd.cisc191.wizardGame.save;

import java.util.Arrays;


public class SaveData implements java.io.Serializable {
    // Stores the current level and lives into a file.
    private static final long serialVersionUID = 1L;

    public int level;
    public int lives;

    public SaveData(int level){
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    @Override
    public String toString() {
        return "SavedGame{" +
                "level=" + Arrays.toString(new int[]{level});
    }

}
