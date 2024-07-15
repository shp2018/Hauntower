package org.MainGame;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.*;


public class GamePanelTest {

    private GamePanel gamePanel;

    @Before
    public void setUp() throws Exception {
        gamePanel = new GamePanel();

    }

    @Test
    public void testGamePanelConstructor() {
        assertNotNull(gamePanel);
        assertEquals(new Dimension(960, 768), gamePanel.getPreferredSize());
        assertEquals(Color.BLACK, gamePanel.getBackground());
        assertTrue(gamePanel.isDoubleBuffered());
        assertNotNull(gamePanel.getKeyListeners());
        assertTrue(gamePanel.isFocusable());
    }

    @Test
    public void testSetUpGame() throws IOException {
        gamePanel.setUpGame();
        assertEquals(2, gamePanel.GameState);
    }

    @Test
    public void testStartGame() {
        gamePanel.startGame();
        assertNotNull(gamePanel.thread);
    }

    @Test
    public void testRun() {
        gamePanel.startGame();
        assertNotNull(gamePanel.thread);
        assertTrue(gamePanel.thread.isAlive());
        assertEquals(1, gamePanel.playState);

    }
    @Test
    public void testPause() {
        gamePanel.startGame();
        assertTrue(gamePanel.thread.isAlive());
        assertEquals(0, gamePanel.pauseState);

    }
    @Test
    public void testSettings() {
        gamePanel.startGame();
        assertTrue(gamePanel.thread.isAlive());
        assertEquals(3, GamePanel.settingsState);

    }
    @Test
    public void testGameOver() {
        gamePanel.startGame();
        assertTrue(gamePanel.thread.isAlive());
        assertEquals(4, gamePanel.gameOverState);

    }
    @Test
    public void testGameWin() {
        gamePanel.startGame();
        assertTrue(gamePanel.thread.isAlive());
        assertEquals(5, gamePanel.gameWinState);

    }
}