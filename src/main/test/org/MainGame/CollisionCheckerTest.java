package org.MainGame;
import org.object.SuperObject;
import org.Entities.entity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CollisionCheckerTest {

    private GamePanel gamePanel;
    private CollisionChecker collisionChecker;

    @Before
    public void setup() {
        try {
            gamePanel = new GamePanel();
        } catch (Exception e) {
            Assert.fail("Exception during GamePanel creation: " + e.getMessage());
        }
        collisionChecker = new CollisionChecker(gamePanel);
    }

    @Test
    public void testCheckTileCollision() {
        entity mockEntity = new entity(gamePanel);
        mockEntity.worldX = 48;
        mockEntity.worldY = 48;
        mockEntity.direction = "up";
        gamePanel.tileM.mapTileNum[3][3] = 1;

        collisionChecker.checkTile(mockEntity);
        Assert.assertFalse( mockEntity.collision);
    }

    @Test
    public void testCheckTileNoCollision() {
        entity mockEntity = new entity(gamePanel);
        mockEntity.worldX = 48;
        mockEntity.worldY = 48;
        mockEntity.direction = "up";
        gamePanel.tileM.mapTileNum[3][3] = 0;

        collisionChecker.checkTile(mockEntity);
        Assert.assertFalse(mockEntity.collision);
    }

    @Test
    public void testCheckObjectCollision() {
        entity mockEntity = new entity(gamePanel);
        mockEntity.worldX = 48;
        mockEntity.worldY = 48;
        mockEntity.direction = "up";

        SuperObject mockObject = new SuperObject();
        mockObject.worldX = 48;
        mockObject.worldY = 48;
        mockObject.collision = true;

        gamePanel.objects[0] = mockObject;
        int result = collisionChecker.checkObject(mockEntity, true);

        Assert.assertTrue(mockEntity.collision);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testCheckObjectNoCollision() {
        entity mockEntity = new entity(gamePanel);
        mockEntity.worldX = 48;
        mockEntity.worldY = 48;
        mockEntity.direction = "up";

        SuperObject mockObject = new SuperObject();
        mockObject.worldX = 100;
        mockObject.worldY = 100;
        mockObject.collision = false;

        gamePanel.objects[0] = mockObject;

        int result = collisionChecker.checkObject(mockEntity, false);

        Assert.assertFalse(mockEntity.collision);
        Assert.assertEquals(999, result);
    }

    @Test
    public void testCheckEntityCollision() {
        entity mockEntity = new entity(gamePanel);
        mockEntity.worldX = 48;
        mockEntity.worldY = 48;
        mockEntity.direction = "up";

        entity mockMonster = new entity(gamePanel);
        mockMonster.worldX = 48;
        mockMonster.worldY = 48;
        mockMonster.collision = true;

        gamePanel.monster[0] = mockMonster;
        int result = collisionChecker.checkEntity(mockEntity);

        Assert.assertTrue(mockEntity.collision);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testCheckEntityNoCollision() {
        entity mockEntity = new entity(gamePanel);
        mockEntity.worldX = 48;
        mockEntity.worldY = 48;
        mockEntity.direction = "up";

        entity mockMonster = new entity(gamePanel);
        mockMonster.worldX = 100;
        mockMonster.worldY = 100;
        mockMonster.collision = false;

        gamePanel.monster[0] = mockMonster;

        int result = collisionChecker.checkEntity(mockEntity);

        Assert.assertFalse(mockEntity.collision);
        Assert.assertEquals(999, result);
    }
}
