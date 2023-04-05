package com.questforholygrail.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_LifeToken extends SuperObject {

  public OBJ_LifeToken() {
    setName("Life Token");
    try {
      setImage(ImageIO.read(getClass().getResourceAsStream("/objects/life_token.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
