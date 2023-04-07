package com.questforholygrail.game.ui;

import com.questforholygrail.game.Main;

public class Display {

  public static void printScreenLn(String message){
    if(!Main.isGui()) {
      System.out.println(message);
    }
  }

  public static void printScreen(String message){
    if(!Main.isGui()) {
      System.out.print(message);
    }
  }
}
