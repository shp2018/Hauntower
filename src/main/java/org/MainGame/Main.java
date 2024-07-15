package org.MainGame;
import javax.swing.*;


/**Main Method that will call the JFrame to create a GameWindow while also making it resizable and makes the
 * Window visible for the user and will create the maze and start the game thread.
 *
 * @author andrewkim/ sanghyunpark/ Other guys add your names here
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) throws Exception {
        JFrame GameWindow=new JFrame();
        GameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        GameWindow.setResizable(false);
        GameWindow.setTitle("HaunTower");

        GamePanel gamePanel=new GamePanel();
        GameWindow.add(gamePanel);

        GameWindow.pack();

        GameWindow.setLocationRelativeTo(null);
        GameWindow.setVisible(true);

        gamePanel.setUpGame();

        gamePanel.startGame();
    }
}
