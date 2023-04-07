package com.questforholygrail.game.ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    private GamePanel gp;
    private Tile[] tile;
    private int mapTileNumber[][];


    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[30];
        mapTileNumber = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];

        getTileImage();
        loadMap();
    }

    public void loadMap() {
        try{
            InputStream is = getClass().getResourceAsStream("/maps/worldMap.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            int col = 0;
            int row = 0;

            while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
                String line = br.readLine();
                int lineLength = 0;
                while (row < gp.getMaxWorldRow()) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                    lineLength = numbers.length;

                    if (lineLength != 0 && col == lineLength) {
                        col = 0;
                        row++;
                        line = br.readLine();
                    }
                }
            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/EmptyTile(White).png"))));
            tile[0].setCollision(true);

            tile[1] = new Tile();
            tile[1].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneBottomLeft.png"))));
            tile[1].setCollision(true);

            tile[2] = new Tile();
            tile[2].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneRightTopCornerWall.png"))));
            tile[2].setCollision(true);

            tile[3] = new Tile();
            tile[3].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneBottomLeftWall.png"))));
            tile[3].setCollision(true);

            tile[4] = new Tile();
            tile[4].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneBottomWall.png"))));
            tile[4].setCollision(true);

            tile[5] = new Tile();
            tile[5].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneBottomMiddleWall.png"))));
            tile[5].setCollision(true);

            tile[6] = new Tile();
            tile[6].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneBottomRight.png"))));
            tile[6].setCollision(true);

            tile[7] = new Tile();
            tile[7].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneBottomRightCorner.png"))));
            tile[7].setCollision(true);

            tile[8] = new Tile();
            tile[8].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneBottomRightWall.png"))));
           tile[8].setCollision(true);

            tile[9] = new Tile();
            tile[9].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneFloorTile.png"))));

            tile[10] = new Tile();
            tile[10].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneLeftSideFloorWall.png"))));
            tile[10].setCollision(true);

            tile[11] = new Tile();
            tile[11].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneRightFloorWall.png"))));
            tile[11].setCollision(true);

            tile[12] = new Tile();
            tile[12].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneTopLeft.png"))));
           tile[12].setCollision(true);

            tile[13] = new Tile();
            tile[13].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneTopLeftCornerWall.png"))));
           tile[13].setCollision(true);

            tile[14] = new Tile();
            tile[14].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneTopLeftWall.png"))));
            tile[14].setCollision(true);

            tile[15] = new Tile();
            tile[15].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneTopMiddle.png"))));
            tile[15].setCollision(true);

            tile[16] = new Tile();
            tile[16].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneTopMiddleWall.png"))));
            tile[16].setCollision(true);

            tile[17] = new Tile();
            tile[17].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneTopRight.png"))));
            tile[17].setCollision(true);

            tile[18] = new Tile();
            tile[18].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneTopRightCorner.png"))));
            tile[18].setCollision(true);

            tile[19] = new Tile();
            tile[19].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneTopRightWall.png"))));
            tile[19].setCollision(true);

            tile[20] = new Tile();
            tile[20].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneBottomRightCorner.png"))));
            tile[20].setCollision(true);

            tile[21] = new Tile();
            tile[21].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneBottomLeftCorner.png"))));
            tile[21].setCollision(true);

            tile[22] = new Tile();
            tile[22].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneLeftSideWall.png"))));
            tile[22].setCollision(true);

            tile[23] = new Tile();
            tile[23].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/grayStoneRightSideWall.png"))));
            tile[23].setCollision(true);

            tile[24] = new Tile();
            tile[24].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/doorTopLeftWithTile.png"))));
            tile[24].setCollision(false);

            tile[25] = new Tile();
            tile[25].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/doorTopRightWithTile.png"))));
            tile[25].setCollision(false);

            tile[26] = new Tile();
            tile[26].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/doorBottomLeft.png"))));
            tile[26].setCollision(false);

            tile[27] = new Tile();
            tile[27].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/doorBottomRight.png"))));
            tile[27].setCollision(false);

            tile[28] = new Tile();
            tile[28].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/torchOnWall.png"))));
            tile[28].setCollision(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2, boolean isMiniMap) {
        int tileSize = (isMiniMap ? gp.getOriginalTileSize()/2 : gp.getTileSize());

        int worldCol = 0;
        int worldRow = 0;

        if (isMiniMap) {
            while (worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {
                int tileNumber = mapTileNumber[worldCol][worldRow];
                int worldX = worldCol * tileSize;
                int worldY = worldRow * tileSize;
                g2.drawImage(tile[tileNumber].getImage(), worldX, worldY, tileSize, tileSize, null);

                worldCol++;

                if(worldCol == gp.getMaxWorldCol()) {
                    worldCol = 0;
                    worldRow++;
                }
            }
        } else {
            while (worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {

                int tileNumber = mapTileNumber[worldCol][worldRow];
                int worldX = worldCol * tileSize;
                int worldY = worldRow * tileSize;
                int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
                int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

                if(worldX + tileSize> gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                        worldX - tileSize< gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                        worldY + tileSize> gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                        worldY - tileSize< gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()){
                    g2.drawImage(tile[tileNumber].getImage(), screenX, screenY, tileSize, tileSize, null);
                }
                worldCol++;

                if(worldCol == gp.getMaxWorldCol()) {
                    worldCol = 0;
                    worldRow++;
                }
            }
        }
    }

    public Tile[] getTile() {
        return tile;
    }

    public int[][] getMapTileNumber() {
        return mapTileNumber;
    }

}
