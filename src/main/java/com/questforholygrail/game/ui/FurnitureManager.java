package com.questforholygrail.game.ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class FurnitureManager {

    private Furniture[] furniture;

    private GamePanel gp;

    private int[] xOffsetValues;
    private int[] yOffsetValues;

    public FurnitureManager(GamePanel gp) {

        furniture = new Furniture[30];
        this.gp = gp;
        xOffsetValues = new int[]{0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        yOffsetValues = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 20, 20, 20, 20, 20, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public void loadFurniture() {
        try {
            furniture[0] = new Furniture();
            furniture[0].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/bookShelfOne.png"))));
            furniture[0].setxScaleFactor(1);
            furniture[0].setyScaleFactor(1);
            furniture[0].setCollision(true);
            furniture[0].setInitialX(18);
            furniture[0].setInitialY(28);


            furniture[1] = new Furniture();
            furniture[1].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/bookShelfOne.png"))));
            furniture[1].setxScaleFactor(1);
            furniture[1].setyScaleFactor(1);
            furniture[1].setCollision(true);
            furniture[1].setInitialX(20);
            furniture[1].setInitialY(28);

            furniture[2] = new Furniture();
            furniture[2].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/bookShelfOne.png"))));
            furniture[2].setxScaleFactor(1);
            furniture[2].setyScaleFactor(1);
            furniture[2].setCollision(true);
            furniture[2].setInitialX(22);
            furniture[2].setInitialY(28);

            furniture[3] = new Furniture();
            furniture[3].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/bookShelfOne.png"))));
            furniture[3].setxScaleFactor(1);
            furniture[3].setyScaleFactor(1);
            furniture[3].setCollision(true);
            furniture[3].setInitialX(24);
            furniture[3].setInitialY(28);

            furniture[4] = new Furniture();
            furniture[4].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/bookShelfOne.png"))));
            furniture[4].setxScaleFactor(1);
            furniture[4].setyScaleFactor(1);
            furniture[4].setCollision(true);
            furniture[4].setInitialX(26);
            furniture[4].setInitialY(28);

            furniture[5] = new Furniture();
            furniture[5].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/bookShelfOne.png"))));
            furniture[5].setxScaleFactor(1);
            furniture[5].setyScaleFactor(1);
            furniture[5].setCollision(true);
            furniture[5].setInitialX(28);
            furniture[5].setInitialY(28);

            furniture[6] = new Furniture();
            furniture[6].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/bookShelfTwo.png"))));
            furniture[6].setxScaleFactor(1);
            furniture[6].setyScaleFactor(1);
            furniture[6].setCollision(true);
            furniture[6].setInitialX(19);
            furniture[6].setInitialY(28);

            furniture[7] = new Furniture();
            furniture[7].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/bookShelfTwo.png"))));
            furniture[7].setxScaleFactor(1);
            furniture[7].setyScaleFactor(1);
            furniture[7].setCollision(true);
            furniture[7].setInitialX(21);
            furniture[7].setInitialY(28);

            furniture[8] = new Furniture();
            furniture[8].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/bookShelfTwo.png"))));
            furniture[8].setxScaleFactor(1);
            furniture[8].setyScaleFactor(1);
            furniture[8].setCollision(true);
            furniture[8].setInitialX(25);
            furniture[8].setInitialY(28);

            furniture[9] = new Furniture();
            furniture[9].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/bookShelfTwo.png"))));
            furniture[9].setxScaleFactor(1);
            furniture[9].setyScaleFactor(1);
            furniture[9].setCollision(true);
            furniture[9].setInitialX(27);
            furniture[9].setInitialY(28);

            furniture[10] = new Furniture();
            furniture[10].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/boxOne.png"))));
            furniture[10].setxScaleFactor(1);
            furniture[10].setyScaleFactor(1);
            furniture[10].setCollision(true);
            furniture[10].setInitialX(21);
            furniture[10].setInitialY(33);

            furniture[11] = new Furniture();
            furniture[11].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/boxOne.png"))));
            furniture[11].setxScaleFactor(1);
            furniture[11].setyScaleFactor(1);
            furniture[11].setCollision(true);
            furniture[11].setInitialX(23);
            furniture[11].setInitialY(33);

            furniture[12] = new Furniture();
            furniture[12].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/boxTwo.png"))));
            furniture[12].setxScaleFactor(1);
            furniture[12].setyScaleFactor(1);
            furniture[12].setCollision(true);
            furniture[12].setInitialX(22);
            furniture[12].setInitialY(33);

            furniture[13] = new Furniture();
            furniture[13].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/boxTwo.png"))));
            furniture[13].setxScaleFactor(1);
            furniture[13].setyScaleFactor(1);
            furniture[13].setCollision(true);
            furniture[13].setInitialX(24);
            furniture[13].setInitialY(33);

            furniture[14] = new Furniture();
            furniture[14].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/boxThree.png"))));
            furniture[14].setxScaleFactor(1);
            furniture[14].setyScaleFactor(1);
            furniture[14].setCollision(true);
            furniture[14].setInitialX(25);
            furniture[14].setInitialY(33);

            furniture[15] = new Furniture();
            furniture[15].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/knight.png"))));
            furniture[15].setxScaleFactor(1);
            furniture[15].setyScaleFactor(1);
            furniture[15].setCollision(true);
            furniture[15].setInitialX(26);
            furniture[15].setInitialY(33);

            furniture[16] = new Furniture();
            furniture[16].setImage(
                    ImageIO.read(Objects.requireNonNull(
                            getClass().getResourceAsStream("/tiles/piconia_dungeon_tiles/knightWithBanner.png"))));
            furniture[16].setxScaleFactor(1);
            furniture[16].setyScaleFactor(1);
            furniture[16].setCollision(true);
            furniture[16].setInitialX(20);
            furniture[16].setInitialY(33);


            for (int i = 0; i < furniture.length; i++) {
                if (furniture[i] != null) {
                    furniture[i].setSolidArea(
                            new Rectangle(
                                    furniture[i].getInitialX() * gp.getTileSize() + xOffsetValues[i],
                                    furniture[i].getInitialY() * gp.getTileSize() + yOffsetValues[i],
                                    (i > 9 ? gp.getTileSize() / 3 : gp.getTileSize()),
                                    (i > 9 ? gp.getTileSize() / 3 : gp.getTileSize())));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, boolean onUtilWidget, GamePanel gp) {
        for (Furniture piece : furniture) {
            if (piece != null) {
                int screenX = piece.getInitialX() * gp.getTileSize() - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
                int screenY = piece.getInitialY() * gp.getTileSize() - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

                g2.drawImage(piece.getImage(), screenX, screenY, gp.getTileSize() * piece.getxScaleFactor(), gp.getTileSize() * piece.getyScaleFactor(), null);

            }
        }
    }

    public Furniture[] getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture[] furniture) {
        this.furniture = furniture;
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }
}


