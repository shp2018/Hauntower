package org.tile;

import org.MainGame.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * TileSetter file creates an array of tiles intended for the background of the screen for the game screen
 * and will draw those tiles into specific locations.
 * @author andrewkim
 * @version 1.0
 * @since November 3rd, 2023
 */

public class TileSetter {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    /**
     * TileSetter constructor creates an array of tiles that are used to load the tile png files
     * @param gp is the game panel object used for the main thread
     */
    public TileSetter(GamePanel gp) throws Exception {
        this.gp=gp;

        tile=new Tile[10];
        mapTileNum=new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world02.txt");

    }

    /**
     * getTileImage Method gets the sprites for the tiles and inputs them into the array
     */
    private void getTileImage() throws IOException{
            tile[0]=new Tile();
            tile[0].tileImage= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/DungeonFloor2.png")));

            tile[1]=new Tile();
            tile[1].tileImage= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/DungeonTile.png")));

            tile[2]=new Tile();
            tile[2].tileImage= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/DungeonWall.png")));
            tile[2].collision=true;

            tile[3]=new Tile();
            tile[3].tileImage=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/GrassWithFlower1.png")));

            tile[4]=new Tile();
            tile[4].tileImage=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/GrassWithFlower2.png")));

            tile[5]=new Tile();
            tile[5].tileImage=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Water1.png")));
            tile[5].collision=true;

            tile[6]=new Tile();
            tile[6].tileImage=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/OpenDoor.png")));


    }

    /**
     * loadMap method loads map data from the txt file to be put into the mapTileNum array
     * which will be used to decide what will be drawn on the map.
     */
    private void loadMap(String map) throws Exception{
            InputStream is = getClass().getResourceAsStream(map);
            assert is != null;
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;

            while(col<gp.maxWorldCol && row<gp.maxWorldRow){
                String line=br.readLine();
                while(col<gp.maxWorldCol){
                    String[] numbers =line.split(" ");
                    int num=Integer.parseInt(numbers[col]);

                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col==gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();

    }

    /**
     * draw method iterates through tbe mapTileNum array and draws the corresponding tile onto each map location
     * @param g2 object used for drawing to the game panel
     */
    public void draw(Graphics2D g2){
        int worldCol=0;
        int worldRow=0;

        while(worldCol< gp.maxWorldCol && worldRow<gp.maxWorldRow){
            int tileNum=mapTileNum[worldCol][worldRow];

            int worldX=worldCol*gp.tileSize;
            int worldY=worldRow*gp.tileSize;
            int screenX=worldX-gp.player.worldX+gp.player.screenX;
            int screenY=worldY-gp.player.worldY+gp.player.screenY;

            if(worldX+gp.tileSize > gp.player.worldX - gp.player.screenX && worldX-gp.tileSize<gp.player.worldX + gp.player.screenX && worldY+gp.tileSize >
                    gp.player.worldY-gp.player.screenY
                    && worldY-gp.tileSize< gp.player.worldY+gp.player.screenY){
                g2.drawImage(tile[tileNum].tileImage, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

                if (worldCol==gp.maxWorldCol){
                    worldCol=0;
                    worldRow++;
                }
        }
    }

}
