package org.Entities;

import org.MainGame.GamePanel;
import org.MainGame.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


/**
 * player class holds the sprites and player speed and coordinates as well as starting coordinates. The player class
 * also defines the behaviours of the objects when they collide as other entities do not interact with objects.
 * Player class also moves the coin to different coordinates when collected by the player and does not remove it from
 * the board.
 *
 * @author andrewkim
 * @version 1.0
 * @since November 2nd, 2023
 */
public class Player extends entity{
    GamePanel gp;
    KeyHandler keyH;


    public final int screenX;
    public final int screenY;
    public int hasDoorOpeningSpell=0;
    public int coins=0;

    /**
     * Constructor takes GamePanel and KeyHandler objects from main class to set default values
     * and location for the player object
     *
     * @param gp is the GamePanel object from the main class to create the game instance.
     * @param keyH handles all valid user inputs.
     */
    public Player(GamePanel gp, KeyHandler keyH) throws IOException {
        super(gp);
        this.gp=gp;
        this.keyH=keyH;

        screenX=gp.width/2 - (gp.tileSize/2);
        screenY=gp.height/2 - (gp.tileSize/2);

        solidArea=new Rectangle(0, 0, 32, 32);
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;

        playerDefault();
        getPlayerSprite();
        direction="down";
    }

    /**
     * Method sets basic values for player object. The x coordinates are set to 100 and
     * y coordinates are set to 100 and speed set to 4.
     */
    private void playerDefault(){
        worldX=gp.tileSize*3;
        worldY=gp.tileSize*3;
        speed=4;
    }

    /**
     * reset will reset the players location, coins, position, and spells and speed back to 4 when called.
     */
    public void reset(){
        worldX=gp.tileSize*3;
        worldY=gp.tileSize*3;
        direction = "down";
        hasDoorOpeningSpell=0;
        coins=0;
        speed = 4;
    }

    /**
     * getPlayerSprite gets the sprites from the resources folder and loads them into the directional buffered image
     * variables
     * 16 sprites
     */
    private void getPlayerSprite() throws IOException{
            up1 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Up1.png"))));
            up2 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Up2.png"))));
            up3 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Up3.png"))));
            up4 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Up4.png"))));
            down1 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Down1.png"))));
            down2 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Down2.png"))));
            down3 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Down3.png"))));
            down4 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Down4.png"))));
            left1 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Left1.png"))));
            left2 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Left2.png"))));
            left3 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Left3.png"))));
            left4 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Left4.png"))));
            right1 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Right1.png"))));
            right2 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Right2.png"))));
            right3 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Right3.png"))));
            right4 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/Right4.png"))));
    }


    /**
     * update method is called 60 frames per second and will check if the user has inputted valid
     * key inputs to interact with the sprite and update it
     * top left corner of the JFrame is 0,0 coordinate so left is -
     * right is +
     * up is Y -
     * down is Y +
     * Also checks for collision and only allows movement if the collision==false
     */
    public void update(){
        if(keyH.upPressed || keyH.rightPressed || keyH.downPressed || keyH.leftPressed) {

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.rightPressed) {
                direction = "right";

            } else {
                direction = "left";
            }
            //check tile collision
            collision=false;
            gp.cChecker.checkTile(this);
            int npcIndex = gp.cChecker.checkEntity(this);
            interactNPC(npcIndex);
            //check Object Collision
            int objIndex=gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //if collision is false player can move
            collisionFalseMove();
        }

    }

    /**
     * Calls the objectBehaviour method for each object
     * @param i is the index of the object in the array
     */
    private void pickUpObject(int i){
        if (i!=999){
            gp.objects[i].objectBehaviour(this, gp, i);
        }
    }

    /**
     * This method dictates how the game reacts when the player interacts with an NPC but more specifically an enemy.
     * Will set the gameState to the game over state and will stop the music and then play the loss sound effect
     * @param i will be dictating which NPC the interaction is with.
     */
    private void interactNPC(int i){
        if (i!=999) {
            gp.GameState = gp.gameOverState;
            gp.stopMusic();
            gp.playSE(2);
        }
    }


    /**
     * draw method gets the sprite image for the corresponding direction and depending on the counter
     * will draw a different sprite for the animation.
     * @param sprite object allows us to draw to the JFrame
     */
    public void draw(Graphics2D sprite){
        BufferedImage image= null;
        switch (direction){
            case("down"):
                if(spriteNum==1){
                    image=down1;
                }
                if (spriteNum==2){
                    image=down2;
                }
                if (spriteNum==3){
                    image=down3;
                }
                if (spriteNum==4){
                    image=down4;
                }
                break;
            case ("up"):
                if(spriteNum==1){
                    image=up1;
                }
                if (spriteNum==2){
                    image=up2;
                }
                if (spriteNum==3){
                    image=up3;
                }
                if (spriteNum==4){
                    image=up4;
                }
                break;
            case ("left"):
                if(spriteNum==1){
                    image=left1;
                }
                if (spriteNum==2){
                    image=left2;
                }
                if (spriteNum==3){
                    image=left3;
                }
                if (spriteNum==4){
                    image=left4;
                }
                break;
            case("right"):
                if(spriteNum==1){
                    image=right1;
                }
                if (spriteNum==2){
                    image=right2;
                }
                if (spriteNum==3){
                    image=right3;
                }
                if (spriteNum==4){
                    image=right4;
                }
                break;
        }
        sprite.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
