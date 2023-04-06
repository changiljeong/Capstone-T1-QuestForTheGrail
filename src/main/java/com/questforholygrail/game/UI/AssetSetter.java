package com.questforholygrail.game.UI;

import com.google.gson.Gson;
import com.questforholygrail.game.Item;
import com.questforholygrail.game.Main;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

import com.questforholygrail.game.Location;
import com.questforholygrail.game.NPC;

public class AssetSetter {
  GamePanel gp;
  private Item[] items;
  private Location[] location;


  public AssetSetter(GamePanel gp){
    this.gp = gp;
    location = Main.getLocations();
  }

  public void setObject() {

    gp.getObj()[0] = new Item(location[0].getItems().get(0).getName(), location[0].getItems().get(0).getFilePath());
    gp.getObj()[0].setWorldX(25 * gp.getTileSize());
    gp.getObj()[0].setWorldY(30 * gp.getTileSize());

    gp.getObj()[1] = new Item(location[3].getItems().get(0).getName(), location[3].getItems().get(0).getFilePath());;
    gp.getObj()[1].setWorldX(10 * gp.getTileSize());
    gp.getObj()[1].setWorldY(12 * gp.getTileSize());

    gp.getObj()[2] = new Item(location[4].getItems().get(0).getName(), location[4].getItems().get(0).getFilePath());;
    gp.getObj()[2].setWorldX(5 * gp.getTileSize());
    gp.getObj()[2].setWorldY(28 * gp.getTileSize());

    gp.getObj()[3] = new Item(location[12].getItems().get(0).getName(), location[12].getItems().get(0).getFilePath());;
    gp.getObj()[3].setWorldX(10 * gp.getTileSize());
    gp.getObj()[3].setWorldY(30 * gp.getTileSize());
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
