package org.object;

import org.Entities.Player;
import org.MainGame.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * PotionSpeed class increases player speed when aquired
 * @author andrewkim /other guys put names here
 * @since November 5th, 2023
 * @version 1.0
 */

public class PotionSpeed extends SuperObject{
    /**
     * PotionSpeed constructor creates a potion that increases speed
     */
    public PotionSpeed(){
        name="Potion Of Speed";
        try {
            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/PotionSpeed.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * objectBehaviour method defines the behaviour of the PotionSpeed object which means that it increases the player
     * objects speed variable by a certain amount
     * @param player where the method gets the speed variable
     * @param gp where the method displays the messages and gets the SuperObject array
     * @param i where the PotionSpeed object is located within the SuperObject array
     */
    @Override
    public void objectBehaviour(Player player, GamePanel gp, int i) {
        player.speed=player.speed+2;
        gp.objects[i]=null;
        gp.playSE(5);
        gp.ui.showMsg("SPEEEEED!");
    }
}
