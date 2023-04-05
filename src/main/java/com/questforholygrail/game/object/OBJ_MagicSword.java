package com.questforholygrail.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_MagicSword extends SuperObject {

  public OBJ_MagicSword() {
    setName("Magic Sword");
    try {
      setImage(ImageIO.read(getClass().getResourceAsStream("/objects/magical_sword.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
