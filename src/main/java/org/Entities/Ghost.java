package org.Entities;

import org.MainGame.GamePanel;
import org.object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;


public class Ghost extends entity {

    public Ghost(GamePanel gp) throws IOException {
        super(gp);
        name = "Ghost";
        speed = 4;
        direction = "down";
        solidArea=new Rectangle(0, 0, 32, 32);
        solidArea.width = 32;
        solidArea.height = 22;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        GetImage();
    }
    private void GetImage() throws IOException{
        up1 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/monster/ghost_down1.png"))));
        up2 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/monster/ghost_down2.png"))));
    }

    /**
     * Simple AI function. It calculates the distance between the player and the ghost. If the distance is too
     * big between the both, the ghost automatically changes direction
     */
    public void setAction(){
        actionCounter++;

        if(actionCounter == 10){
            Random random = new Random();
            int differenceX = this.worldX - this.gp.player.worldX;
            int differenceY = this.worldY - this.gp.player.worldY;
            int distance = random.nextInt(2);

            if (distance == 0) {

                if (differenceX < 0) {
                    this.direction = "right";
                }
                else  if (differenceX > 0) {
                    this.direction = "left";
                }
            }
            else {

                if (differenceY < 0) {
                    this.direction = "down";
                }
                else if (differenceY > 0) {
                    this.direction = "up";
                }
            }
            actionCounter = 0;

        }

    }

    public void draw(Graphics2D g2){
        BufferedImage image = up1;
        SuperObject.screenSet(g2, gp, worldX, worldY, image);
    }

}
