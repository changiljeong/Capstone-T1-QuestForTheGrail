package com.questforholygrail.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_BattleAxe extends SuperObject{

  public OBJ_BattleAxe(){
    setName("Battle Axe");
    try {
      setImage(ImageIO.read(getClass().getResourceAsStream("/objects/battle_axe.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
