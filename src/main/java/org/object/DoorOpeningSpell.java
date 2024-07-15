package org.object;

import org.Entities.Player;
import org.MainGame.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * This class creates an object that will open a door when acquired.
 * @author andrewkim other guys add names here
 * @since November 5th, 2023
 * @version 1.0
 */

public class DoorOpeningSpell extends SuperObject{
    /**
     * DoorOpeningSpell constructor sets the name to "Door Opening Spell" and gets the DoorOpeningSpell sprite.
     */
   public DoorOpeningSpell(){
       name="Door Opening Spell";
       try{
           image=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/DoorSpell.png")));
       }
       catch (IOException e){
           e.printStackTrace();
       }
   }

    /**
     * objectBehaviour method defines the behaviours for the DoorOpeningSpell when the player enters the tile
     * that it resides in. When the player enters the tile the players hasDoorOpeningSpell counter will increase
     * and a message saying the number of door opening spells is displayed on the screen along with a message saying that
     * you picked up a potion to unlock the door.
     * @param player is the object where it updates the hasDoorOpeningSpell
     * @param gp is the GamePanel where it gets the objects array to be able to set to null and display messages
     * @param i is the int location in the objects array that this object resides int
     */
    @Override
    public void objectBehaviour(Player player, GamePanel gp, int i) {
        player.hasDoorOpeningSpell++;
        gp.objects[i]=null;
        System.out.println("Door opening Spells: "+ player.hasDoorOpeningSpell);
        gp.playSE(7);
        gp.ui.showMsg("You picked up a potion to unlock the door!");
    }
}
