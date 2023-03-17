package com.questforholygrail.game;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

public class Main {
    private static Player player;
    static Location[] locations;


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        try(Reader reader = new InputStreamReader(Main.class.getClassLoader().getResourceAsStream("rooms.json"))) {

            Gson gson = new Gson();
            locations = gson.fromJson(reader, Location[].class);
        }


        // set current location to the first location in the array
        Location currentLocation = locations[0];

        // create a new player with starting values
        player = new Player("Player", 100, currentLocation);

        Commands parser = new Commands(player, currentLocation);

        // Display game title
        Commands.gameTitle();

        // Wait for player to press enter
        System.out.println("Press enter to start the game...");
        scanner.nextLine();

        // Ask player if they want to start a new game
        String choice;
        do {
            System.out.println("Would you like to start a new game? (y/n)");
            choice = scanner.nextLine();
        } while (!choice.equals("y") && !choice.equals("n"));
        if (choice.equals("y")) {

            // print out intro
            Commands.gameIntro();

            // Game loop
            while (true) {

                // displays location, player health and inventory, updates accordingly.
                Commands.showStatus();

                // displays room description
                Commands.roomDescription();

                // display items in room
                Commands.showItem();

                // Ask player to input something
                System.out.println("What would you like to do?");
                System.out.print("> ");
                String command = scanner.nextLine();

                parser.parseCommand(command);

                if (command.equals("quit")) {
                    System.out.println("Quitting game...");
                    break;

                }
            }
        }
        // If player chooses not to start a new game, end the program
        else {
            System.out.println("Quitting game...");
            return;
        }
    }

    @Override
    public String toString() {
        return "Commands{" +
            "player=" + player +
            "locations" + locations +
            "locations" + Main.locations +
            '}';
    }
}
