package org.object;

import org.Entities.Player;
import org.MainGame.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * Door class creates a door object
 * @author andrewkim/ other guys put names here
 * @since November 5th, 2023
 * @version 1.0
 */

public class Door extends SuperObject{
    /**
     * Door constructor makes a door
     */
    public Door(){
        name="Door";
        try {
            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/ClosedDoor.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        collision=true;
    }

    /**
     * method defines the behaviours for the Door. When the player has a doorOpeningSpell the door object is set to null
     * and removed from the map. If the player does not have a doorOpeningSpell a message is shown saying that you need
     * a door unlocking potion.
     * @param player object used to check the number of doorOpeningSpells and remove one from the players doorOpeningSpell
     *               Counter if they enter a tile with a door.
     * @param gp object used to access the superObjects array and set the door to null if the player opens a door
     *           and used to show the UI messages
     * @param i variable used to be able to set object to null when then player enters a door tile with a doorOpeningSpell
     */
    @Override
    public void objectBehaviour(Player player, GamePanel gp, int i) {
        if (player.hasDoorOpeningSpell>0){
            gp.objects[i]=null;
            player.hasDoorOpeningSpell--;
            gp.playSE(4);//Place holder for now
        }
        else if(player.hasDoorOpeningSpell==0){
            gp.ui.showMsg("You need a door unlocking potion!");
        }
    }
}
