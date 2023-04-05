package com.questforholygrail.game.UI;

import com.questforholygrail.game.Commands;
import com.questforholygrail.game.Location;
import com.questforholygrail.game.Main;
import com.questforholygrail.game.NPC;

import com.questforholygrail.game.object.OBJ_BattleAxe;
import com.questforholygrail.game.object.OBJ_Compass;
import com.questforholygrail.game.object.OBJ_LifeToken;
import com.questforholygrail.game.object.OBJ_MagicSword;

public class AssetSetter {
  GamePanel gp;

  public AssetSetter(GamePanel gp){
    this.gp = gp;
  }

  public void setObject() {
    gp.getObj()[0] = new OBJ_Compass();
    gp.getObj()[0].setWorldX(5 * gp.getTileSize());
    gp.getObj()[0].setWorldY(10 * gp.getTileSize());

    gp.getObj()[1] = new OBJ_BattleAxe();
    gp.getObj()[1].setWorldX(10 * gp.getTileSize());
    gp.getObj()[1].setWorldY(12 * gp.getTileSize());

    gp.getObj()[2] = new OBJ_LifeToken();
    gp.getObj()[2].setWorldX(5 * gp.getTileSize());
    gp.getObj()[2].setWorldY(25 * gp.getTileSize());

    gp.getObj()[3] = new OBJ_MagicSword();
    gp.getObj()[3].setWorldX(10 * gp.getTileSize());
    gp.getObj()[3].setWorldY(27 * gp.getTileSize());
  }

  public static void setNPC(GamePanel gp){
    for(Location area : Main.getLocations()) {
      for(NPC npc : area.getNpc()) {
        npc.setWorldX(npc.getInitialX() * gp.getTileSize());
        npc.setWorldY(npc.getInitialY() * gp.getTileSize());
        npc.setSpeed(2);
        npc.setDirection("right");
        npc.setSpriteNum(1);
        npc.getImages();
      }
    }

  }

}
