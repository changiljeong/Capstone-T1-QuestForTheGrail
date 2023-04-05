package com.questforholygrail.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Compass extends SuperObject{

  public OBJ_Compass(){
    setName("Compass");
    try {
      setImage(ImageIO.read(getClass().getResourceAsStream("/objects/compass.png")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
