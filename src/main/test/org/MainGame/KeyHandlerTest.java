package org.MainGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KeyHandlerTest {

    private KeyHandler keyHandler;
    private KeyEvent keyEvent;
    private GamePanel gamePanel;
    @BeforeEach
    public void setUp() throws Exception {
        gamePanel = new GamePanel();
        keyHandler = new KeyHandler(gamePanel);
    }

    @Test
    public void testKeyPressedWhenWThenUpPressedTrue() {
        keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.upPressed, "The upPressed variable should be true when 'W' key is pressed.");
    }

    @Test
    public void testKeyPressedWhenSThenDownPressedTrue() {
        keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.downPressed, "The downPressed variable should be true when 'S' key is pressed.");
    }

    @Test
    public void testKeyPressedWhenDThenRightPressedTrue() {
        keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.rightPressed, "The rightPressed variable should be true when 'D' key is pressed.");
    }

    @Test
    public void testKeyPressedWhenAThenLeftPressedTrue() {
        keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.leftPressed, "The leftPressed variable should be true when 'A' key is pressed.");
    }

    @Test
    public void testKeyPressedWhenEscapeThenEscPressedTrue() {
        keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, ' ');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.escPressed, "The escPressed variable should be true when 'Escape' key is pressed.");
    }

    @Test
    public void testKeyReleasedWhenWThenUpPressedFalse() {
        keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyHandler.keyReleased(keyEvent);
        assertFalse(keyHandler.upPressed, "The upPressed variable should be false when 'W' key is released.");
    }

    @Test
    public void testKeyReleasedWhenSThenDownPressedFalse() {
        keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        keyHandler.keyReleased(keyEvent);
        assertFalse(keyHandler.downPressed, "The downPressed variable should be false when 'S' key is released.");
    }

    @Test
    public void testKeyReleasedWhenDThenRightPressedFalse() {
        keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyHandler.keyReleased(keyEvent);
        assertFalse(keyHandler.rightPressed, "The rightPressed variable should be false when 'D' key is released.");
    }

    @Test
    public void testKeyReleasedWhenAThenLeftPressedFalse() {
        keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyHandler.keyReleased(keyEvent);
        assertFalse(keyHandler.leftPressed, "The leftPressed variable should be false when 'A' key is released.");
    }

    @Test
    public void testKeyReleasedWhenEscapeThenEscPressedFalse() {
        keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, ' ');
        keyHandler.keyReleased(keyEvent);
        assertFalse(keyHandler.escPressed, "The escPressed variable should be false when 'Escape' key is released.");
    }
}