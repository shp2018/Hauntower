package org.MainGame;

import org.Entities.entity;


/**
 * Collision checker class takes the game panel and an entity and makes it so when the top side, bottom side, right
 * side, or left side of their hit box hits a tile with collision set to true then the collision is set to true
 * making it so that the entity can not move past the hittable entity.
 * @author andrewkim/ Other guys add names here
 * @since November 5th, 2023
 * @version 1.0
 */

public class CollisionChecker {
    GamePanel gp;

    /**
     * Takes a GamePanel object to be used in all its checks
     * @param gp is the given GamePanel object from the main client code used to interact with the elements in the
     *           GamePanel.
     */
    public CollisionChecker(GamePanel gp){
        this.gp=gp;
    }

    /**
     * Method takes an entity and sets the hit boxes for that entity and when it is given an entity and that entity moves
     * it will check if an entity has any of its sides hit a tile that is set to collision = true and if so the entities
     * collision is set to true where in their respective update blocks it won't allow them to move.
     * @param entity is the given object that is able to move around.
     */
    public void checkTile(entity entity){
        if(entity == null){
            return;
        }
        int entityLeftWorldX=entity.worldX + entity.solidArea.x + 12;
        int entityRightWorldX=entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY= entity.worldY + entity.solidArea.y + 12;
        int entityBottomWorldY=entity.worldY + entity.solidArea.y+entity.solidArea.height;

        int entityLeftCol=entityLeftWorldX/gp.tileSize;
        int entityRightCol=entityRightWorldX/gp.tileSize;
        int entityTopRow=entityTopWorldY/gp.tileSize;
        int entityBottomRow=entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case ("up"):
                entityTopRow=(entityTopWorldY-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collision=true;
                }
                break;
            case ("down"):
                entityBottomRow=(entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2=gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collision=true;
                }
                break;
            case ("left"):
                entityLeftCol=(entityLeftWorldX-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collision=true;
                }
                break;
            case ("right"):
                entityRightCol=(entityRightWorldX+entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collision=true;
                }
                break;
        }
    }

    /**
     * checkObject method checks the collision of the entity and if it's a player returns i to index to be used for
     * checking the superObject
     * @param entity used for checking the entity collision
     * @param player used for checking the player collision
     * @return index is returned to be used for the superObject
     */
    public int checkObject(entity entity, boolean player){
        int index = 999;

        for (int i=0; i<gp.objects.length; i++){
            if(gp.objects[i]!=null){
                entity.solidArea.x=entity.worldX+entity.solidArea.x;
                entity.solidArea.y=entity.worldY+entity.solidArea.y;
                gp.objects[i].solidArea.x=gp.objects[i].worldX+gp.objects[i].solidArea.x;
                gp.objects[i].solidArea.y=gp.objects[i].worldY+gp.objects[i].solidArea.y;

                switch (entity.direction){
                    case ("up"):
                        entity.solidArea.y-=entity.speed;
                        if(entity.solidArea.intersects(gp.objects[i].solidArea)){
                            if(gp.objects[i].collision){
                                entity.collision=true;
                            }
                            if(player){
                                index=i;
                            }
                        }
                        break;
                    case ("down"):
                        entity.solidArea.y+=entity.speed;
                        if(entity.solidArea.intersects(gp.objects[i].solidArea)){
                            if(gp.objects[i].collision){
                                entity.collision=true;
                            }
                            if(player){
                                index=i;
                            }
                        }
                        break;
                    case ("left"):
                        entity.solidArea.x-=entity.speed;
                        if(entity.solidArea.intersects(gp.objects[i].solidArea)){
                            if(gp.objects[i].collision){
                                entity.collision=true;
                            }
                            if(player){
                                index=i;
                            }
                        }
                        break;
                    case ("right"):
                        entity.solidArea.x+=entity.speed;
                        if(entity.solidArea.intersects(gp.objects[i].solidArea)){
                            if(gp.objects[i].collision){
                                entity.collision=true;
                            }
                            if(player){
                                index=i;
                            }
                        }
                        break;
                }
                entity.solidArea.x=entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
                gp.objects[i].solidArea.x=gp.objects[i].solidAreaDefaultX;
                gp.objects[i].solidArea.y=gp.objects[i].solidAreaDefaultY;
            }
        }
        return index;
    }
     //Monster Collision
    public int checkEntity(entity entity){
        int index = 999;

        for (int i=0; i<gp.monster.length; i++){
            if(gp.monster[i]!=null){
                entity.solidArea.x=entity.worldX+entity.solidArea.x;
                entity.solidArea.y=entity.worldY+entity.solidArea.y;
                gp.monster[i].solidArea.x=gp.monster[i].worldX+gp.monster[i].solidArea.x;
                gp.monster[i].solidArea.y=gp.monster[i].worldY+gp.monster[i].solidArea.y;

                switch (entity.direction){
                    case ("up"):
                        entity.solidArea.y-=entity.speed;
                        if(entity.solidArea.intersects(gp.monster[i].solidArea)){
                                entity.collision=true;
                                index = i;
                            }
                        break;

                    case ("down"):
                        entity.solidArea.y+=entity.speed;
                        if(entity.solidArea.intersects(gp.monster[i].solidArea)){
                                entity.collision=true;
                                index=i;
                        }
                        break;
                    case ("left"):
                        entity.solidArea.x-=entity.speed;
                        if(entity.solidArea.intersects(gp.monster[i].solidArea)){
                                entity.collision=true;
                                index=i;
                        }
                        break;
                    case ("right"):
                        entity.solidArea.x+=entity.speed;
                        if(entity.solidArea.intersects(gp.monster[i].solidArea)){
                                entity.collision=true;
                                index=i;
                        }
                        break;
                }
                entity.solidArea.x=entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
                gp.monster[i].solidArea.x=gp.monster[i].solidAreaDefaultX;
                gp.monster[i].solidArea.y=gp.monster[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
