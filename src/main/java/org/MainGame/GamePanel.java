package org.MainGame;

import org.Entities.Player;

import org.object.SuperObject;
import org.tile.TileSetter;
import org.Entities.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;


/**
 *GamePanel class sets up the tileSize, scale, screen size, and max world size as well as calling the keyHandler
 * the player, creating gameObjects, setting the tiles onto the screen, collision checker, assetSetter to set the
 * assets onto the board, creating the UI, and creating game states.
 * Also sets up the basic game loop.
 * @author andrewkim
 * @version 1.0
 * @since November 2nd, 2023
 */
public class GamePanel extends JPanel implements Runnable {

    public final int tileSize=48; //tileSize scales the tile from 16 pixels to 48
    public final int width=tileSize*20;
    public final int height=tileSize*16;
    public final int maxWorldCol=50;
    public final int maxWorldRow=50;


    //SYSTEM STUFF for the future we may want to refactor each of these into different classes just to make this more
    //clean because this is getting a bit difficult to navigate.
    Sound Music=new Sound();
    public Sound SE=new Sound();
    public Sound deathSound=new Sound();
    public Thread thread;
    Random randomCoordinates=new Random();
    public UI ui = new UI(this);
    int FPS=60;
    KeyHandler keyW=new KeyHandler(this);
    public Player player=new Player(this, keyW);
    public SuperObject[] objects=new SuperObject[20];
    public entity[] monster = new entity[20];
    TileSetter tileM=new TileSetter(this);
    public CollisionChecker cChecker=new CollisionChecker(this);
    public AssetSetter aSetter=new AssetSetter(this);
    public long currentTime;


    // PAUSE MENU
    //Maybe create a separate class to put all the menu declarations in a place that is not GamePanel
    //to clean up the GamePanel class
    public int GameState;
    public final int pauseState = 0;
    public final int playState = 1;
    public final int titleState = 2;
    public static final int settingsState = 3;

    public final int gameOverState = 4;

    public final int gameWinState = 5;


    /**
     * GamePanel constructor sets some basic parameters surrounding the panel that is visible to the user. Dimensions
     * background colour, a keyHandler,
     */
    public GamePanel() throws Exception {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyW);
        this.setFocusable(true);
    }

    /**
     * setUpGame method sets the objects from the assetSetter class onto the map and sets the GameState to the
     * titleState.
     */
    public void setUpGame() throws IOException {
        aSetter.setObject();
        GameState = titleState;
        aSetter.setMonster();
        playMusic(1);
    }

    /**
     * startGame method starts the thread.
     */
    public void startGame(){
        thread=new Thread(this);
        thread.start();
    }

    /**
     * Run method creates the game loop for the thread
     */
    @Override
    public void run() {//game loop
        double drawInterval= (double) 1000000000 /FPS; //0.01666 seconds for screen draw

        double delta=0;
        long lastTime=System.nanoTime();
        long Timer=0;
        int drawCount=0;

        while (thread!=null){
            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            Timer+=(currentTime-lastTime);
            lastTime=currentTime;
            coinMove(currentTime);

            if(delta>=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (Timer>=1000000000){
                drawCount=0;
                Timer=0;
            }
        }
    }

    /**
     * Update method changes player coordinates everytime there is a user input using the arrow keys
     * or the wasd
     */
    public void update(){
        if(GameState == playState) {
            player.update();

            for (org.Entities.entity entity : monster) {
                if (entity != null) {
                    entity.update();
                }
            }
        }
    }


    /**
     * paint component draws the sprites onto the GamePanel and the UI elements
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        if(GameState == titleState){
            ui.draw(g2);
        }
        else {
            tileM.draw(g2);

            for (SuperObject object : objects) {
                if (object != null) {
                    object.draw(g2, this);
                }
            }
            for (org.Entities.entity entity : monster) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
            player.draw(g2);
            ui.draw(g2);
            g2.dispose();
        }

    }

    /**
     * Runs the background music and sets it to a loop to play continuously in the background.
     * @param i is the index for choosing which background music to play
     */
    public void playMusic(int i){
        Music.setFile(i);
        Music.play();
        Music.loop();
    }

    /**
     * stopMusic will stop the music.
     */
    public void stopMusic(){
        Music.stop();
    }

    /**
     * playSE will play a sound effect when called. If the sound effect is the death sound effect it will use a separate
     * Sound object to handle it.
     * @param i is the index for choosing which sound effect is played
     */
    public void playSE(int i){
        if (i==2){
            deathSound.setFile(i);
            deathSound.play();
        }
        else {
            SE.setFile(i);
            SE.play();
        }
    }

    /**
     * stopSE will stop the sound effect
     */
    public void stopSE(){
        SE.stop();
    }

    /**
     * This method resets the game to its original state from before any gameplay has occurred.
     * @throws IOException for catching any IOExceptions from monsters, players, and objects
     */
    public void resetGame() throws IOException {
        stopMusic();
        stopSE();
        deathSound.stop();
        aSetter.setMonster();
        aSetter.setObject();
        player.reset();
        playMusic(1);
    }

    /**
     * coinMove method will move the coins every time 2.1 seconds have passed by generating a random number between the bounds
     * of the upper rooms and the others within the bounds of the lower rooms
     * @param time is the variable used to track how much time has passed.
     */
    private void coinMove(long time){
//        if (time%2100000000==0) {
//            objects[4].worldX = randomCoordinates.nextInt(2, 49) * tileSize;
//            objects[4].worldY = randomCoordinates.nextInt(2, 25) * tileSize;
//
//            objects[13].worldX = randomCoordinates.nextInt(2, 25) * tileSize;
//            objects[13].worldY = randomCoordinates.nextInt(2, 49) * tileSize;
//
//            objects[14].worldX = randomCoordinates.nextInt(2, 49) * tileSize;
//            objects[14].worldY = randomCoordinates.nextInt(2, 35) * tileSize;
//
//            objects[15].worldX = randomCoordinates.nextInt(2, 25) * tileSize;
//            objects[15].worldY = randomCoordinates.nextInt(2, 49) * tileSize;
//            System.out.println("coin has moved");
//        }
    }
}