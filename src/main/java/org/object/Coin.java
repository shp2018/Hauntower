package org.object;

import org.Entities.Player;
import org.MainGame.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

/**
 * Coin class creates a coin that increases score
 *
 * @author andrewkim /other guys put names here
 * @since November 5th, 2023
 * @version 1.1
 */

public class Coin extends SuperObject{
    /**
     * Coin constructor makes a coin that increases score
     */
    Random randomCoordinates=new Random();
    public Coin(){
        name="Coin";
        try {
            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/Coin.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * objectBehaviour method defines the coin behaviour. When the player enters the tile with the coin
     * it increases the players coin counter and displays that you have a certain # of coins.
     * @param player object is used to edit and keep track of the coins
     * @param gp GamePanel used for displaying ui elements
     * @param i variable not used here
     */

    @Override
    public void objectBehaviour(Player player, GamePanel gp, int i) {
        player.coins++;
        Coin.super.worldX=randomCoordinates.nextInt(40, 49)*gp.tileSize;
        Coin.super.worldY=randomCoordinates.nextInt(40, 49)*gp.tileSize;
        gp.playSE(4);
//        gp.ui.showMsg("You have $" + player.coins + " in your wallet!");
    }
}
