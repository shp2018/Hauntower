package org.object;

import org.Entities.Player;
import org.MainGame.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

/**
 * RatStaticPunishment class creates a static punishment that reduces the score of the player and will set the thread to null if the player has 0 coins
 * or fewer.
 * @version 1.0
 * @author andrewkim
 * @since November 12, 2023
 */

public class RatStaticPunishment extends SuperObject{
    Random randomCoordinates=new Random();
    /**
     * RatStaticPunishment constructor names the object as "Rat" and gets the sprite for the object
     */
    public RatStaticPunishment(){
        name="Rat";
        try{
            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/RatStaticPunishment.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * objectBehaviour method defines the behaviours of the RatStaticPunishment. The defined behaviour is the removal
     * of one coin when the player enters a rat tile and if the player has less then one coin then it is a game over
     * Now when the player touches a rat with coins a hit sound effect plays and a loss sound effect is played when the player
     * has 0 coins.
     * @param player object will be used to check the number of coins the player currently has
     * @param gp object will now set the gameState to gameOverState when player enters punishment with 0 coins.
     * @param i variable will be used for the SuperObjects array to find the RatStaticPunishment and set it to null
     *          when the player enters the tile.
     */
    @Override
    public void objectBehaviour(Player player, GamePanel gp, int i) {
        player.coins--;
        if (player.coins<0){
            gp.stopMusic();
            gp.playSE(2);
            gp.GameState=gp.gameOverState;
        }
        gp.playSE(6);
        RatStaticPunishment.super.worldX= randomCoordinates.nextInt(2, 49);
        RatStaticPunishment.super.worldY=randomCoordinates.nextInt(2, 49);
    }
}
