package org.object;

import org.Entities.Player;
import org.MainGame.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * SuperObject class is the parent class to all future objects and the behaviours common to all objects will be defined
 * here
 * @author andrewkim other guys write name here
 * @since November 5th, 2023
 * @version 1.0
 */
public class SuperObject {
    public String name;
    public BufferedImage image;
    public boolean collision=false;
    public int worldX, worldY;
    public Rectangle solidArea=new Rectangle(0,0, 48, 48);
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;

    /**
     * objectBehaviour method is overridden by the other objects to set the behaviours of each object
     * @param player used for accessing player objects variables
     * @param gp object used to display messages and access the superObject array
     * @param i used to access the object index in the superObject to be able to set it to null
     */
    public void objectBehaviour(Player player, GamePanel gp, int i){
    }

    /**
     * draw method is used for drawing the sprites onto the map
     * @param g2 object used for drawing onto GamePanel
     * @param gp object to be used for being drawn on
     */
    public void draw(Graphics2D g2, GamePanel gp){
        screenSet(g2, gp, worldX, worldY, image);
    }

    /**
     * draws the object onto the screen when the object coordinates enter the screen.
     * @param g2 2d graphics object
     * @param gp object is a GamePanel object that is used to access the player
     * @param worldX size of the world in X space
     * @param worldY size of the world in Y space
     * @param image is the object image to be drawn
     */
    public static void screenSet(Graphics2D g2, GamePanel gp, int worldX, int worldY, BufferedImage image) {
        int screenX= worldX -gp.player.worldX+gp.player.screenX;
        int screenY= worldY -gp.player.worldY+gp.player.screenY;

        if(worldX +gp.tileSize > gp.player.worldX - gp.player.screenX && worldX -gp.tileSize<gp.player.worldX + gp.player.screenX && worldY +gp.tileSize >
                gp.player.worldY-gp.player.screenY
                && worldY -gp.tileSize< gp.player.worldY+gp.player.screenY){
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
