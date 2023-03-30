package com.questforholygrail.game.UI;

import java.util.Scanner;

public class UserInput {

  private static final Scanner scanner = new Scanner(System.in);


  public static String getInput(){
    return scanner.nextLine();
  }

}
