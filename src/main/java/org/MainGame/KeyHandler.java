package org.MainGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**keyHandler class watches the keyboard and will change 4 boolean variables to true as a way to denote that they have
 * pressed for the program and will reset them to false when the key is released.
 * @author andrewkim / lucaschen / sanghyunpark
 * @version 1.0
 * @since November 12th, 2023
 */

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, rightPressed, leftPressed,escPressed;
    private static final int menuOptions = 3;
    private static final int gameOverOptions = 2;
    private static final int settingsOptions = 4;

    /**
     * KeyHandler constructor used for creating a KeyHandler object for GamePanel
     * @param gp object used for interacting with the GamePanel for UI
     */
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    /**
     * keyTyped Method detects a key typed. Empty implementation because it needs to be implemented from the KeyListener
     * interface.
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e){
    }

    /**
     * keyPressed method detects the KeyEvent for interacting with the title screen and gameState and direction for the
     * player
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e){
        int code=e.getKeyCode();
//
        if(gp.GameState == gp.titleState){
            if (handleMenuInput(code, menuOptions)) {
                return;
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.GameState = gp.playState;
                }
                else if (gp.ui.commandNum == 1){
                    gp.GameState = GamePanel.settingsState;
                    gp.ui.commandNum = 0;
                }
                else if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        }
        if(gp.GameState == gp.gameOverState){
            gameOverMenuInput(code);
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.GameState = gp.playState;
                    try {
                        gp.resetGame();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if(gp.ui.commandNum == 1){
                    System.exit(0);
                }
            }}
//
        if(gp.GameState == gp.gameWinState){
            gameOverMenuInput(code);
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.GameState = gp.titleState;
                    try {
                        gp.resetGame();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                if(gp.ui.commandNum == 1){
                    System.exit(0);
                }
            }}


        if(gp.GameState == GamePanel.settingsState){
            if (handleMenuInput(code, settingsOptions)) {
                return;
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 3){
                    gp.GameState = gp.titleState;
                    gp.ui.commandNum = 0;
                }
            }
        }

        if (code==KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed=true;
        }
        if (code==KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed=true;
        }
        if (code==KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed=true;
        }
        if (code==KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed=true;
        }
        if (code==KeyEvent.VK_ESCAPE){
            escPressed = true;
            if(gp.GameState == gp.playState){
                gp.GameState = gp.pauseState;
            }
            else if(gp.GameState == gp.pauseState){
                gp.GameState = gp.playState;
            }
        }
    }

    /**
     * gameOverMenuInput method deals with inputs in the game over menu
     * @param code is the number related to the keyInput
     */
    private void gameOverMenuInput(int code) {
        if (handleMenuInput(code, gameOverOptions)) {
            return;
        }
    }

    /**
     * keyReleased method makes it so when the key is released it unsets the direction from true to false.
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if (code==KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed=false;
        }
        if (code==KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed=false;
        }
        if (code==KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed=false;
        }
        if (code==KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed=false;
        }
        if (code==KeyEvent.VK_ESCAPE){
            escPressed = false;
        }
    }
    /**
     * handleMenuInput used for setting and changing cursor locations on different pages
     * @param options used to identify the size of the menu
     */
    private boolean handleMenuInput(int code, int options) {
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum = (gp.ui.commandNum - 1 + options) % options;
            return true;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum = (gp.ui.commandNum + 1) % options;
            return true;
        }
        return false;
    }
}
