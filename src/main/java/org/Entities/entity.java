package org.Entities;

import org.MainGame.GamePanel;
import org.object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * entity class will be extended by all other entities to get their direction sprite counters, coordinates of the entity
 * and load the sprites. All these are behaviours shared by all entities.
 * @author andrewkim/ adheeshk and others
 * @version 1.0
 * @since November 2nd, 2023
 */
public class entity {
    public GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public int actionCounter = 0;
    public String name;
    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3, right4;
    public String direction = "";
    public int counter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;

    public entity(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        SuperObject.screenSet(g2, gp, worldX, worldY, null);
    }

    public void setAction() {
        actionCounter +=1;
    }
    protected void collisionFalseMove(){
        if (!collision) {
            switch (direction) {
                case ("up"):
                    worldY -= speed;
                    break;
                case ("down"):
                    worldY += speed;
                    break;
                case ("right"):
                    worldX += speed;
                    break;
                case ("left"):
                    worldX -= speed;
                    break;
            }
        }
        counter++;
        if (counter > 8) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            counter = 0;
        }
    }


    public void update() {
        setAction();
        collision = false;
        gp.cChecker.checkTile(this);
        collisionFalseMove();
    }


}


