package com.questforholygrail.game.ui;


import com.questforholygrail.game.Item;
import com.questforholygrail.game.Main;

import java.awt.Rectangle;


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
    gp.getObj()[0].setWorldX(63 * gp.getTileSize());
    gp.getObj()[0].setWorldY(43 * gp.getTileSize());
    location[0].getItems().get(0).setWorldX(63 * gp.getTileSize());
    location[0].getItems().get(0).setWorldY(43 * gp.getTileSize());


    gp.getObj()[1] = new Item(location[3].getItems().get(0).getName(), location[3].getItems().get(0).getFilePath());;
    gp.getObj()[1].setWorldX(79 * gp.getTileSize());
    gp.getObj()[1].setWorldY(12 * gp.getTileSize());
    location[3].getItems().get(0).setWorldX(79 * gp.getTileSize());
    location[3].getItems().get(0).setWorldY(12 * gp.getTileSize());

    gp.getObj()[3] = new Item(location[4].getItems().get(0).getName(), location[4].getItems().get(0).getFilePath());;
    gp.getObj()[3].setWorldX(79 * gp.getTileSize());
    gp.getObj()[3].setWorldY(50 * gp.getTileSize());
    location[4].getItems().get(0).setWorldX(79 * gp.getTileSize());
    location[4].getItems().get(0).setWorldY(50 * gp.getTileSize());

    gp.getObj()[4] = new Item(location[5].getItems().get(0).getName(), location[5].getItems().get(0).getFilePath());;
    gp.getObj()[4].setWorldX(95 * gp.getTileSize());
    gp.getObj()[4].setWorldY(37 * gp.getTileSize());
    location[5].getItems().get(0).setWorldX(95 * gp.getTileSize());
    location[5].getItems().get(0).setWorldY(37 * gp.getTileSize());


    gp.getObj()[6] = new Item(location[8].getItems().get(0).getName(), location[8].getItems().get(0).getFilePath());;
    gp.getObj()[6].setWorldX(42 * gp.getTileSize());
    gp.getObj()[6].setWorldY(47 * gp.getTileSize());
    location[8].getItems().get(0).setWorldX(42 * gp.getTileSize());
    location[8].getItems().get(0).setWorldY(47 * gp.getTileSize());

    gp.getObj()[7] = new Item(location[9].getItems().get(0).getName(), location[9].getItems().get(0).getFilePath());;
    gp.getObj()[7].setWorldX(47 * gp.getTileSize());
    gp.getObj()[7].setWorldY(11 * gp.getTileSize());
    location[9].getItems().get(0).setWorldX(47 * gp.getTileSize());
    location[9].getItems().get(0).setWorldY(11 * gp.getTileSize());

    gp.getObj()[8] = new Item(location[11].getItems().get(0).getName(), location[11].getItems().get(0).getFilePath());;
    gp.getObj()[8].setWorldX(62 * gp.getTileSize());
    gp.getObj()[8].setWorldY(19 * gp.getTileSize());
    location[11].getItems().get(0).setWorldX(62 * gp.getTileSize());
    location[11].getItems().get(0).setWorldY(19 * gp.getTileSize());

    gp.getObj()[9] = new Item(location[12].getItems().get(0).getName(), location[12].getItems().get(0).getFilePath());;
    gp.getObj()[9].setWorldX(63 * gp.getTileSize());
    gp.getObj()[9].setWorldY(4 * gp.getTileSize());
    location[12].getItems().get(0).setWorldX(63 * gp.getTileSize());
    location[12].getItems().get(0).setWorldY(4 * gp.getTileSize());
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
        npc.setSolidArea(new Rectangle(0, 0, (int)(npc.getImageScaleX()>2? npc.getImageScaleX()-1: npc.getImageScaleX()) * gp.getTileSize()/2, (int)(npc.getImageScaleY()>2? npc.getImageScaleY()-1: npc.getImageScaleY())* gp.getTileSize()/2));
      }
    }
  }

}
