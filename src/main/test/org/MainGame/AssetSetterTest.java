package org.MainGame;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AssetSetterTest {
    GamePanel gp=new GamePanel();
    AssetSetter aSetter;

    public AssetSetterTest() throws Exception {
    }

    @Before
            public void setUp(){
        aSetter=new AssetSetter(gp);
    }
    int[] monsterX={
        22*gp.tileSize,
        40*gp.tileSize,
        2*gp.tileSize,
        45*gp.tileSize,
        3*gp.tileSize
    };
    int[] monsterY={
    5*gp.tileSize,
    5*gp.tileSize,
   5*gp.tileSize,
    45*gp.tileSize,
    35*gp.tileSize
    };
    int[] objectX={
    23*gp.tileSize,
    45*gp.tileSize,
    25*gp.tileSize,
    38*gp.tileSize,
    22*gp.tileSize,
    5*gp.tileSize,
    25*gp.tileSize,
    30*gp.tileSize,
    10*gp.tileSize,
    10*gp.tileSize,
    10*gp.tileSize,
    10*gp.tileSize,
    40*gp.tileSize,
    0,
    22*gp.tileSize,
    35*gp.tileSize
   };
    int[] objectY={
    5*gp.tileSize,
    20*gp.tileSize,
    8*gp.tileSize,
    21*gp.tileSize,
   14*gp.tileSize,
   5*gp.tileSize,
    34*gp.tileSize,
    45*gp.tileSize,
    45*gp.tileSize,
    40*gp.tileSize,
    5*gp.tileSize,
    35*gp.tileSize,
    10*gp.tileSize,
    4*gp.tileSize,
    34*gp.tileSize,
    45*gp.tileSize,
    };

    @Test
    public void testSetObject() {
        aSetter.setObject();
        for (int i=0; i<5; i++){
            assert gp.objects[i].worldX == objectX[i] && gp.objects[i].worldY == objectY[i];
        }
        assert true;
    }

    @Test
    public void testSetMonster() throws IOException {
        aSetter.setMonster();

        for (int i=0; i<5; i++){
            assert gp.monster[i].worldX == monsterX[i] && gp.monster[i].worldY == monsterY[i];
        }
        assert true;
    }
}