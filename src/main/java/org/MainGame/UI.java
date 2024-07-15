package org.MainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import org.object.*;

/**
 * UI Class draws the menus, the timer, # Of items, Game Over and Victory Screens.
 * @author lucaschen/ sanghyunpark
 * @since November 8th 2023
 * @version 1.0
 */
public class UI {
    public boolean messageCondition = false;
    int MessageTimer = 0;

    public int commandNum = 0;
    double Time = 0;
    DecimalFormat format = new DecimalFormat("#0.00");
    public String message = "";
    GamePanel gp;
    Font arial_40;

    Font arial_80;

    BufferedImage SpellImage;
    BufferedImage CoinImage;

    /**
     * Method shows message onto the screen
     * @param text is the String message that is displayed on the screen
     */
    public void showMsg(String text){
        message = text;
        messageCondition = true;
    }

    /**
     * Constructor sets base parameters of the UI such as fonts and size, and gets the images for the objects
     * @param gp is the panel that is used for drawing.
     */
    public UI(GamePanel gp){
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN,20);
        arial_80 = new Font("Arial", Font.PLAIN,80);
        DoorOpeningSpell Spell = new DoorOpeningSpell();
        SpellImage = Spell.image;
        Coin Coin = new Coin();
        CoinImage = Coin.image;
    }
    /**
     * Constructor creates Graphics2D on screen, and sets the font attributes on screen.
     * @param g2 The font and colour of the word being drawn on screen
     */
    public void draw(Graphics2D g2){

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        menuDraw(g2);

        if(messageCondition){
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
            MessageTimer++;
            if(MessageTimer>120){
                MessageTimer = 0;
                messageCondition = false;
            }
        }
    }

    /**
     * Will set the background color to black for the various menu screens.
     * @param g2 is the 2d graphics drawer object
     */
    private void SetBlack(Graphics2D g2) {
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0, gp.width, gp.height);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));
    }

    /**
     * arrowDraw is for drawing the arrow indicator on the screen for the menus
     * @param g2 is the 2d graphics drawer object
     * @param commandNum is for dictating where the arrow should be drawn
     */
    private void arrowDraw(Graphics2D g2, int commandNum) {
        if (commandNum == 0){
            g2.drawString(">",gp.width/2-250,gp.height/2-100);
        }
        if(commandNum == 1){
            g2.drawString(">",gp.width/2-250,gp.height/2);
        }
        if(commandNum == 2){
            g2.drawString(">",gp.width/2-250,gp.height/2+100);
        }
    }

    /**
     * Will draw the menus depending on the gameState the game is in.
     * @param g2 is the graphics2D object used to draw onto the screen
     */
    private void menuDraw(Graphics2D g2){
        if(gp.GameState == gp.playState){
            Time += (double)1/60;
//            g2.drawString("Timer:"+format.format(Time),gp.tileSize*13,30);
//            g2.drawImage(SpellImage,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
//            g2.drawImage(CoinImage,gp.tileSize/2,90,gp.tileSize,gp.tileSize,null);
//            g2.drawString("x " + gp.player.hasDoorOpeningSpell,70,65);
//            g2.drawString("x " + gp.player.coins,70,130);

        }
        if(gp.GameState == gp.pauseState){
            g2.setFont(arial_80);
            g2.setColor(Color.white);
            String text = "PAUSED";
            g2.drawString(text,gp.width/2-150,gp.height/2);
        }
        if(gp.GameState == gp.titleState){
            Time = 0;
            g2.setFont(arial_80);

            g2.setColor(Color.white);
            String text = "HaunTower";
            g2.drawString(text,gp.width/2-200,gp.height/2-200);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
            g2.drawString("NEW GAME",gp.width/2-200,gp.height/2-100);
            g2.drawString("SETTINGS",gp.width/2-200,gp.height/2);
            g2.drawString("QUIT",gp.width/2-200,gp.height/2+100);
            arrowDraw(g2, commandNum);
        }
        if(gp.GameState == gp.gameOverState){
            Time = 0;
            SetBlack(g2);
            String text;
            int x;
            int y;

            text = "Game Over";
            g2.setColor(Color.black);
            x = gp.width/2-200;
            y = gp.height/2-200;
            g2.drawString(text,x,y);

            g2.setColor(Color.white);
            g2.drawString(text, x-100,y-4);
            g2.setFont(g2.getFont().deriveFont(50f));
            text = "Retry";
            x = gp.width/2-60;
            y += 100;
            g2.drawString(text,x,y);
            text = "Quit";
            x = gp.width/2-55;
            y+= 150;
            g2.drawString(text,x,y);
            if (commandNum == 0){
                g2.drawString(">",gp.width/2-100,gp.height/2-100);
            }
            if (commandNum == 1){
                g2.drawString(">",gp.width/2-100,y);
            }



        }

        if(gp.GameState == gp.gameWinState){
            String text;
            int x;
            int y;
            SetBlack(g2);


            text = "Victory";
            g2.setColor(Color.black);
            x = gp.width/2-170;
            y = gp.height/2-200;


            g2.setColor(Color.white);
            g2.drawString(text, x,y-4);
            g2.setFont(g2.getFont().deriveFont(50f));
            text = "Main Menu";
            y += 100;
            g2.drawString(text,x+50,y);
            text = "Quit";
            x = gp.width/2-55;
            y+= 150;
            g2.drawString(text,x,y);
            if (commandNum == 0){
                g2.drawString(">",gp.width/2-200,gp.height/2-100);
            }
            if (commandNum == 1){
                g2.drawString(">",gp.width/2-100,y);
            }

            text="Coins: " + gp.player.coins;
            g2.setColor(Color.white);
            g2.drawString(text, x-400,y-150);

            text="Timer: "+format.format(Time);
            g2.setColor(Color.white);
            g2.drawString(text, x-400,y-50);

        }

        if (gp.GameState == GamePanel.settingsState) {
            g2.setColor(Color.BLACK); // Set the background color to black
            g2.fillRect(0, 0, gp.width, gp.height); // Fill the entire panel with black

            g2.setFont(arial_80);
            g2.setColor(Color.WHITE);
            String text = "SETTINGS";
            g2.drawString(text, gp.width/2-200, gp.height/2-200);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            g2.drawString("General", gp.width/2-200, gp.height/2-100);
            g2.drawString("Controls", gp.width/2-200, gp.height/2);
            g2.drawString("Audio", gp.width/2-200, gp.height/2+100);
            g2.drawString("Back", gp.width/2-200, gp.height/2+200);

            arrowDraw(g2, commandNum);
            if(commandNum == 3){
                g2.drawString(">",gp.width/2-250,gp.height/2+200);
            }
        }
    }

}
