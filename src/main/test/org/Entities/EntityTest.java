package org.Entities;

import org.MainGame.CollisionChecker;
import org.MainGame.GamePanel;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EntityTest {
    private GamePanel gamePanel;
    private entity testEntity;

    @Before
    public void setUp() throws Exception {
        gamePanel = new GamePanel();
        testEntity = new entity(gamePanel);
    }
    @Test
    public void testDraw() {
        BufferedImage mockImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D mockGraphics = mockImage.createGraphics();

    }

    @Test
    public void testSetAction() {
        int initialActionCounter = testEntity.actionCounter;
        System.out.println(initialActionCounter);
        testEntity.setAction();
        System.out.println(testEntity.actionCounter);
        assertTrue(testEntity.actionCounter > initialActionCounter);
    }


    @Test
    public void testCollisionFalseMove() {
        testEntity.direction = "up";
        testEntity.worldX = 0;
        testEntity.worldY = 48;
        testEntity.speed = 5;
        testEntity.spriteNum = 1;
        testEntity.counter = 3;

        testEntity.collisionFalseMove();

        assertEquals(0, testEntity.worldX);
        assertEquals(43, testEntity.worldY);

        assertEquals(1, testEntity.spriteNum);
        assertEquals(4, testEntity.counter);

    }

    @Test
    public void testUpdate() {
        gamePanel.cChecker = new CollisionChecker(gamePanel) {
            @Override
            public void checkTile(entity entity) {
                entity.collision = true;
            }
        };

        testEntity.direction = "up";
        testEntity.worldX = 0;
        testEntity.worldY = 48;
        testEntity.speed = 5;
        testEntity.spriteNum = 1;
        testEntity.counter = 3;

        testEntity.update();
        assertTrue(testEntity.collision);

    }

}