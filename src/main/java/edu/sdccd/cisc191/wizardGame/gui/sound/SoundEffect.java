package edu.sdccd.cisc191.wizardGame.gui.sound;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * Enum to separate sound effects from game function
 *
 * 1. Define sound effect name and associated wave file.
 * 2. To play an effect, invoke SoundEffect.SOUND_NAME.play().
 * 3. You can use the static variable SoundEffect.volume to mute the sound.
 *
 */
public enum SoundEffect {
    // Different effects
    THEME("theme.wav"),
    LEVEL("level.wav"),
    BULLET("bullet.wav"),
    CRATE("crate.wav"),
    LOSE("lose_life.wav");


    // Nested class for specifying volume
    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.LOW;

    // Each sound effect has its own clip, loaded with its own sound file.
    private Clip clip;

    // Constructor to construct each element of the enum with its own sound file.
    SoundEffect(String soundFileName) {
        try {
            // Use URL (instead of File) to read from disk and JAR.
            URL url = this.getClass().getClassLoader().getResource(soundFileName);
            // Set up an audio input stream piped from the sound file.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            // Get a clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Play or Re-play the sound effect from the beginning, by rewinding.
    public void play() {
        if (volume != Volume.MUTE) {
            if (clip.isRunning())
                clip.stop();   // Stop the player if it is still running
            clip.setFramePosition(0); // rewind to the beginning
            clip.start();     // Start playing
        }
    }

    public void stop() {

        clip.stop();
    }


    // Optional static method to pre-load all the sound files.
    public static void init() {
        values(); // calls the constructor for all the elements
    }
}