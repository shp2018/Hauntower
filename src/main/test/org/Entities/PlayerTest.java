package org.Entities;

import org.MainGame.GamePanel;
import org.MainGame.KeyHandler;
import org.junit.Assert;
import org.junit.Before;

import java.awt.*;
import java.io.IOException;


import org.junit.Test;



public class PlayerTest {

    GamePanel gamePanel=new GamePanel();
    KeyHandler keyH;
    private Player player;

    public PlayerTest() throws Exception {

    }

    @Before
    public void setUp() throws Exception {
        keyH=new KeyHandler(gamePanel);
        player = new Player(gamePanel, keyH);
    }

    @Test
    public void testPlayerDefault() {
        Assert.assertNotNull(player);

        Assert.assertEquals(new Rectangle(0, 0, 32, 32), player.solidArea);
        Assert.assertEquals(4, player.speed);


    }

    @Test
    public void testReset() throws IOException {
        player.reset();
        Assert.assertEquals(gamePanel.tileSize*3, player.worldX);
        Assert.assertEquals(gamePanel.tileSize*3, player.worldY);
        Assert.assertEquals(4, player.speed);
        Assert.assertEquals("down", player.direction);
        Assert.assertEquals(0, player.hasDoorOpeningSpell);
        Assert.assertEquals(0, player.coins);
    }
}