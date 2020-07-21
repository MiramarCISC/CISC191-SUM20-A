package edu.sdccd.cisc191.wizardGame.save;

public class SaveData implements java.io.Serializable {
    // Stores the current level and lives into a file.

    private static final long serialVersionUID = 1L;

    public int level;
    public int lives;
}
