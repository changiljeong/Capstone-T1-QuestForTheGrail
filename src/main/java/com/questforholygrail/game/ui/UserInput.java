package com.questforholygrail.game.ui;

import java.util.Scanner;

public class UserInput {

    private static final Scanner scanner = new Scanner(System.in);


    public static String getInput() {
        return scanner.nextLine();
    }

}
