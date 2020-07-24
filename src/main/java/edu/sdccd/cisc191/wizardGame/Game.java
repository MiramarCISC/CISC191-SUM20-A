package edu.sdccd.cisc191.wizardGame;

import edu.sdccd.cisc191.wizardGame.events.KeyInput;
import edu.sdccd.cisc191.wizardGame.events.MouseInput;
import edu.sdccd.cisc191.wizardGame.gui.anim.Camera;
import edu.sdccd.cisc191.wizardGame.gui.screen.*;
import edu.sdccd.cisc191.wizardGame.objects.Bullet;
import edu.sdccd.cisc191.wizardGame.objects.GameObject;
import edu.sdccd.cisc191.wizardGame.objects.Handler;
import edu.sdccd.cisc191.wizardGame.objects.ID;
import edu.sdccd.cisc191.wizardGame.utils.images.BufferedImageLoader;
import edu.sdccd.cisc191.wizardGame.utils.images.SpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game {

    // Set first state to the main menu.
    private static STATE State = STATE.MENU;

    // Variables that are critical for controlling the Game.
    private int ammo = 50;
    private int hp = 100;
    private int lives = 3;

    // List of all states in the game.
    public enum STATE{
        MAIN_MENU,
        MENU,
        GAME,
        HELP,
        PAUSE,
    };

    public Game() {}

    /* Setters and getters for private variables. */
    public STATE getGameState(){
        return State;
    }

    public void setGameState(STATE State){
        this.State = State;
    }
    public int getAmmo(){
        return ammo;
    }

    public void setAmmo (int ammo){
        this.ammo = ammo;
    }

    public void incAmmo(int inc){
        ammo += inc;
    }

    public void decAmmo() {
        ammo-= 1;
    }

    public int getHp(){
        return hp;
    }

    public void setHp(int hp){
        this.hp = hp;
    }

    public void decHp(){
        hp--;
    }

    public int getLives(){
        return lives;
    }

    public void decLives(){
        lives--;
    }

    public void setLives(int lives){
        this.lives = lives;
    }

    // // Main method starts one Game instance and thus one GameController thread.
    // public static void main(String[] args) {
    //     // TODO: Run game
    //     new Game();
    // }
}
