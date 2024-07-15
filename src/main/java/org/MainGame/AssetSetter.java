package org.MainGame;

import org.Entities.Ghost;
import org.object.*;

import java.io.IOException;

/**
 * AssetSetter class will set the objects onto the map
 * @author andrewkim/ other guys put names here
 * @since November 5th, 2023
 * @version 1.0
 */

public class AssetSetter {
    GamePanel gp;

    /**
     * AssetSetter constructor takes in a GamePanel object for all future operations occurring within
     * AssetSetter
     * @param gp GamePanel object used for all operations withing AssetSetter.
     */
    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }

    /**
     * setObject method sets the objects from the superObject array from GamePanel onto the map in specific locations
     * on the map.
     */
    public void setObject(){
        gp.objects[0]=new DoorOpeningSpell();
        gp.objects[0].worldX=23*gp.tileSize;
        gp.objects[0].worldY=5*gp.tileSize;

        gp.objects[1]=new DoorOpeningSpell();
        gp.objects[1].worldX=45*gp.tileSize;
        gp.objects[1].worldY=20*gp.tileSize;

        gp.objects[2]=new Door();
        gp.objects[2].worldX=25*gp.tileSize;
        gp.objects[2].worldY=8*gp.tileSize;

        gp.objects[3]=new Door();
        gp.objects[3].worldX=38*gp.tileSize;
        gp.objects[3].worldY=21*gp.tileSize;

        gp.objects[4]=new Coin();
        gp.objects[4].worldX=22*gp.tileSize;
        gp.objects[4].worldY=14*gp.tileSize;

        gp.objects[5]=new PotionSpeed();
        gp.objects[5].worldX=5*gp.tileSize;
        gp.objects[5].worldY=5*gp.tileSize;

        gp.objects[6]=new Door();
        gp.objects[6].worldX=25*gp.tileSize;
        gp.objects[6].worldY=34*gp.tileSize;

        gp.objects[7]=new DoorOpeningSpell();
        gp.objects[7].worldX=30*gp.tileSize;
        gp.objects[7].worldY=45*gp.tileSize;

        gp.objects[8]=new PrincessObject();
        gp.objects[8].worldX=10*gp.tileSize;
        gp.objects[8].worldY=45*gp.tileSize;

        gp.objects[9]=new RatStaticPunishment();
        gp.objects[9].worldX=10*gp.tileSize;
        gp.objects[9].worldY=40*gp.tileSize;

        gp.objects[10]=new RatStaticPunishment();
        gp.objects[10].worldX=10*gp.tileSize;
        gp.objects[10].worldY=15*gp.tileSize;

        gp.objects[11]=new RatStaticPunishment();
        gp.objects[11].worldX=10*gp.tileSize;
        gp.objects[11].worldY=35*gp.tileSize;

        gp.objects[12]=new RatStaticPunishment();
        gp.objects[12].worldX=40*gp.tileSize;
        gp.objects[12].worldY=10*gp.tileSize;

        gp.objects[13]=new Coin();
        gp.objects[13].worldX=10*gp.tileSize;
        gp.objects[13].worldY=14*gp.tileSize;

        gp.objects[14]=new Coin();
        gp.objects[14].worldX=22*gp.tileSize;
        gp.objects[14].worldY=34*gp.tileSize;

        gp.objects[15]=new Coin();
        gp.objects[15].worldX=35*gp.tileSize;
        gp.objects[15].worldY=45*gp.tileSize;

        gp.objects[15]=new Coin();
        gp.objects[15].worldX=35*gp.tileSize;
        gp.objects[15].worldY=45*gp.tileSize;
        gp.objects[16]=new Coin();
        gp.objects[16].worldX=38*gp.tileSize;
        gp.objects[16].worldY=48*gp.tileSize;
        gp.objects[17]=new Coin();
        gp.objects[17].worldX=40*gp.tileSize;
        gp.objects[17].worldY=45*gp.tileSize;
        gp.objects[18]=new Coin();
        gp.objects[18].worldX=45*gp.tileSize;
        gp.objects[18].worldY=45*gp.tileSize;
        gp.objects[19]=new Coin();
        gp.objects[19].worldX=50*gp.tileSize;
        gp.objects[19].worldY=45*gp.tileSize;

    }

    /**
     * Spawns the monster on the map with different coordinates using the monster array from GamePanel
     */
    public void setMonster() throws IOException {
        gp.monster[0] = new Ghost(gp);
        gp.monster[0].worldX=22*gp.tileSize;
        gp.monster[0].worldY=5*gp.tileSize;

        gp.monster[1] = new Ghost(gp);
        gp.monster[1].worldX=40*gp.tileSize;
        gp.monster[1].worldY=5*gp.tileSize;

        gp.monster[2] = new Ghost(gp);
        gp.monster[2].worldX=2*gp.tileSize;
        gp.monster[2].worldY=5*gp.tileSize;

        gp.monster[3] = new Ghost(gp);
        gp.monster[3].worldX=45*gp.tileSize;
        gp.monster[3].worldY=45*gp.tileSize;

        gp.monster[4] = new Ghost(gp);
        gp.monster[4].worldX=3*gp.tileSize;
        gp.monster[4].worldY=35*gp.tileSize;
    }
}
