package com.questforholygrail.game.UI;

import com.questforholygrail.game.Entity;

public class CollisionChecker {

  GamePanel gp;

  public CollisionChecker(GamePanel gp){
      this.gp = gp;
  }

  public void checkTile(Entity entity){
    int entityLeftWorldX = entity.getWorldX() + (int)entity.getSolidArea().getX();
    int entityRightWorldX = entity.getWorldX() + (int)(entity.getSolidArea().getX() + entity.getSolidArea().getWidth());
    int entityTopWorldY = entity.getWorldY() + (int)entity.getSolidArea().getY();
    int entityButtonWorldY = entity.getWorldY() + (int)(entity.getSolidArea().getY() + entity.getSolidArea().getHeight());

    int entityLeftCol = entityLeftWorldX / gp.getTileSize();
    int entityRightCol = entityRightWorldX / gp.getTileSize();
    int entityTopRow = entityTopWorldY / gp.getTileSize();
    int entityBottomRow = entityButtonWorldY / gp.getTileSize();

    int tileNum1, tileNum2;

    switch (entity.getDirection()){
      case "up":
        entityTopRow = (entityTopWorldY - entity.getSpeed()) / gp.getTileSize();
        tileNum1 = gp.getTileManager().getMapTileNumber()[entityLeftCol][entityTopRow];
        tileNum2 = gp.getTileManager().getMapTileNumber()[entityRightCol][entityTopRow];
        if(gp.getTileManager().getTile()[tileNum1].isCollision() == true ||
           gp.getTileManager().getTile()[tileNum2].isCollision()) {
          entity.setCollisionOn(true);
        }
        break;
      case "down":
        entityBottomRow = (entityButtonWorldY + entity.getSpeed()) / gp.getTileSize();
        tileNum1 = gp.getTileManager().getMapTileNumber()[entityLeftCol][entityBottomRow];
        tileNum2 = gp.getTileManager().getMapTileNumber()[entityRightCol][entityBottomRow];
        if(gp.getTileManager().getTile()[tileNum1].isCollision() == true ||
            gp.getTileManager().getTile()[tileNum2].isCollision()) {
          entity.setCollisionOn(true);
        }
        break;
      case "left":
        entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gp.getTileSize();
        tileNum1 = gp.getTileManager().getMapTileNumber()[entityLeftCol][entityTopRow];
        tileNum2 = gp.getTileManager().getMapTileNumber()[entityLeftCol][entityBottomRow];
        if(gp.getTileManager().getTile()[tileNum1].isCollision() == true ||
            gp.getTileManager().getTile()[tileNum2].isCollision()) {
          entity.setCollisionOn(true);
        }
        break;
      case "right":
        entityRightCol = (entityRightWorldX + entity.getSpeed()) / gp.getTileSize();
        tileNum1 = gp.getTileManager().getMapTileNumber()[entityRightCol][entityTopRow];
        tileNum2 = gp.getTileManager().getMapTileNumber()[entityRightCol][entityBottomRow];
        if(gp.getTileManager().getTile()[tileNum1].isCollision() == true ||
            gp.getTileManager().getTile()[tileNum2].isCollision()) {
          entity.setCollisionOn(true);
        }
        break;

    }
  }

}
