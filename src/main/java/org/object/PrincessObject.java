package org.object;

import org.Entities.Player;
import org.MainGame.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**PrincessObject class sets the PrincessObject behaviours as a win condition for the game and sets the sprite for the
 * Princess
 * @author andrewkim
 * @version 1.1
 * @since November 12th, 2023
 */

public class PrincessObject extends SuperObject{
    /**
     * PrincessObject constructor sets the name to "Princess" and sets the sprite
     */
    public PrincessObject(){
        name="Princess";
        try {
            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/Princess1.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * objectBehaviour method defines the behaviour of the Princess Object. For now, it will set the thread to null
     * to end the game loop (temporary). Will also display the # of coins the player has
     * @param player object used to display the # of coins to the terminal
     * @param gp GamePanel object used to set the gameState to gameOverState (temporary)
     * @param i variable not used in this method
     */
    @Override
    public void objectBehaviour(Player player, GamePanel gp, int i) {
        System.out.println("Coins: "+ player.coins);
        gp.GameState=gp.gameWinState;
        gp.stopMusic();
        gp.playSE(3);
    }
}
