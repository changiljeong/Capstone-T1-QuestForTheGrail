package com.questforholygrail.game.ui;

import com.questforholygrail.game.Commands;
import com.questforholygrail.game.Entity;
import com.questforholygrail.game.NPC;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.getWorldX() + (int) entity.getSolidArea().getX();
        int entityRightWorldX = entity.getWorldX() + (int) (entity.getSolidArea().getX() + entity.getSolidArea().getWidth());
        int entityTopWorldY = entity.getWorldY() + (int) entity.getSolidArea().getY();
        int entityButtonWorldY = entity.getWorldY() + (int) (entity.getSolidArea().getY() + entity.getSolidArea().getHeight());


        int entityLeftCol = entityLeftWorldX / gp.getTileSize();
        int entityRightCol = entityRightWorldX / gp.getTileSize();
        int entityTopRow = entityTopWorldY / gp.getTileSize();
        int entityBottomRow = entityButtonWorldY / gp.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileManager().getMapTileNumber()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNumber()[entityRightCol][entityTopRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() == true ||
                        gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityButtonWorldY + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileManager().getMapTileNumber()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileManager().getMapTileNumber()[entityRightCol][entityBottomRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() == true ||
                        gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileManager().getMapTileNumber()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNumber()[entityLeftCol][entityBottomRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() == true ||
                        gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileManager().getMapTileNumber()[entityRightCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNumber()[entityRightCol][entityBottomRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() == true ||
                        gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }

    public void checkNPC(Entity entity) {
        if (Commands.getCurrentLocation().getNpc().size() > 0) {
            entity.getSolidArea().setLocation((int) (entity.getWorldX() + entity.getSolidArea().getX()), (int) (entity.getWorldY() + entity.getSolidArea().getY()));
            NPC npc = Commands.getCurrentLocation().getNpc().get(0);
            npc.getSolidArea().setLocation(npc.getWorldX() + npc.getxOffset(), npc.getWorldY() + npc.getyOffset());
            switch (entity.getDirection()) {
                case "up":
                    entity.getSolidArea().setLocation((int) entity.getSolidArea().getX(), (int) (entity.getSolidArea().getY() - entity.getSpeed()));
                    if (entity.getSolidArea().intersects(npc.getSolidArea())) {
                        entity.setCollisionOn(true);
                    }
                    break;
                case "down":
                    entity.getSolidArea().setLocation((int) entity.getSolidArea().getX(), (int) (entity.getSolidArea().getY() + entity.getSpeed()));
                    if (entity.getSolidArea().intersects(npc.getSolidArea())) {
                        entity.setCollisionOn(true);
                    }
                    break;
                case "left":
                    entity.getSolidArea().setLocation((int) (entity.getSolidArea().getX() - entity.getSpeed()), (int) entity.getSolidArea().getY());
                    if (entity.getSolidArea().intersects(npc.getSolidArea())) {
                        entity.setCollisionOn(true);
                    }
                    break;
                case "right":
                    entity.getSolidArea().setLocation((int) (entity.getSolidArea().getX() + entity.getSpeed()), (int) entity.getSolidArea().getY());
                    if (entity.getSolidArea().intersects(npc.getSolidArea())) {
                        entity.setCollisionOn(true);
                    }
                    break;
            }
            entity.getSolidArea().setLocation(entity.getSolidAreaDefaultX(), entity.getSolidAreaDefaultY());
        }

    }

    public void checkFurniture(Entity entity) {
        Furniture[] furniture = gp.getFurnitureManager().getFurniture();
        entity.getSolidArea().setLocation((int) (entity.getWorldX() + entity.getSolidArea().getX()), (int) (entity.getWorldY() + entity.getSolidArea().getY()));
        for (Furniture piece : furniture) {
            if (piece == null) {
                continue;
            }
            switch (entity.getDirection()) {
                case "up":
                    entity.getSolidArea().setLocation((int) entity.getSolidArea().getX(), (int) (entity.getSolidArea().getY() - entity.getSpeed()));
                    if (entity.getSolidArea().intersects(piece.getSolidArea())) {
                        entity.setCollisionOn(true);
                    }
                    break;
                case "down":
                    entity.getSolidArea().setLocation((int) entity.getSolidArea().getX(), (int) (entity.getSolidArea().getY() + entity.getSpeed()));
                    if (entity.getSolidArea().intersects(piece.getSolidArea())) {
                        entity.setCollisionOn(true);
                    }
                    break;
                case "left":
                    entity.getSolidArea().setLocation((int) (entity.getSolidArea().getX() - entity.getSpeed()), (int) entity.getSolidArea().getY());
                    if (entity.getSolidArea().intersects(piece.getSolidArea())) {
                        entity.setCollisionOn(true);
                    }
                    break;
                case "right":
                    entity.getSolidArea().setLocation((int) (entity.getSolidArea().getX() + entity.getSpeed()), (int) entity.getSolidArea().getY());
                    if (entity.getSolidArea().intersects(piece.getSolidArea())) {
                        entity.setCollisionOn(true);
                    }
                    break;
            }
        }
        entity.getSolidArea().setLocation(entity.getSolidAreaDefaultX(), entity.getSolidAreaDefaultY());
    }


    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        for (int i = 0; i < gp.getObj().length; i++) {
            if (gp.getObj()[i] != null) {
                //Get entity's solid area position
                entity.getSolidArea().setLocation((int) (entity.getWorldX() + entity.getSolidArea().getX()), (int) (entity.getWorldY() + entity.getSolidArea().getY()));
                //Get object's solid area position
                gp.getObj()[i].getSolidArea().setLocation((int) (gp.getObj()[i].getWorldX() + gp.getObj()[i].getSolidArea().getX()), (int) (gp.getObj()[i].getWorldY() + gp.getObj()[i].getSolidArea().getY()));
                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().setLocation((int) entity.getSolidArea().getX(), (int) (entity.getSolidArea().getY() - entity.getSpeed()));
                        if (entity.getSolidArea().intersects(gp.getObj()[i].getSolidArea())) {
                            if (gp.getObj()[i].isCollision() == true)
                                entity.setCollisionOn(true);
                            if (player == true)
                                index = i;
                        }
                        break;
                    case "down":
                        entity.getSolidArea().setLocation((int) entity.getSolidArea().getX(), (int) (entity.getSolidArea().getY() + entity.getSpeed()));
                        if (entity.getSolidArea().intersects(gp.getObj()[i].getSolidArea())) {
                            if (gp.getObj()[i].isCollision() == true)
                                entity.setCollisionOn(true);
                            if (player == true)
                                index = i;
                        }
                        break;
                    case "left":
                        entity.getSolidArea().setLocation((int) (entity.getSolidArea().getX() - entity.getSpeed()), (int) entity.getSolidArea().getY());
                        if (entity.getSolidArea().intersects(gp.getObj()[i].getSolidArea())) {
                            if (gp.getObj()[i].isCollision() == true)
                                entity.setCollisionOn(true);
                            if (player == true)
                                index = i;
                        }
                        break;
                    case "right":
                        entity.getSolidArea().setLocation((int) (entity.getSolidArea().getX() + entity.getSpeed()), (int) entity.getSolidArea().getY());
                        if (entity.getSolidArea().intersects(gp.getObj()[i].getSolidArea())) {
                            if (gp.getObj()[i].isCollision() == true)
                                entity.setCollisionOn(true);
                            if (player == true)
                                index = i;
                        }
                        break;
                }
                entity.getSolidArea().setLocation(entity.getSolidAreaDefaultX(), entity.getSolidAreaDefaultY());
                gp.getObj()[i].getSolidArea().setLocation(gp.getObj()[i].getSolidAreaDefaultX(), gp.getObj()[i].getSolidAreaDefaultY());
            }
        }
        return index;
    }

}
