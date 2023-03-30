package com.questforholygrail.game;

import com.google.gson.Gson;
import com.questforholygrail.game.UI.Display;
import com.questforholygrail.game.UI.MainGameWindow;
import com.questforholygrail.game.UI.UserInput;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public class Main {

    private static Location[] locations;
    private static Sound sound = new Sound();


    public static void main(String[] args) throws IOException {

        MainGameWindow mgw = new MainGameWindow(1100, 800);

        try(Reader reader = new InputStreamReader(
            Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("rooms.json")))) {

            Gson gson = new Gson();
            locations = gson.fromJson(reader, Location[].class);
        }

        // set current location to the first location in the array
        Location currentLocation = locations[0];

        sound.soundLoad();


        // create a new player with starting values
        Player player = new Player(100, 10, currentLocation, false);

        Commands parser = new Commands(player, currentLocation);

        // Display game title
        Commands.gameTitle();

        // Wait for player to press enter
        Display.printScreenLn("Press enter to start the game...");
        UserInput.getInput();

        // Ask player if they want to start a new game
        String choice;
        do {
            Display.printScreenLn("Would you like to start a new game? (y/n)");
            choice = UserInput.getInput();
        } while (!choice.equals("y") && !choice.equals("n"));

        if (choice.equals("y")) {

            // print out intro
            Commands.gameIntro();

            // Game loop
            while (true) {

                // displays location, player health and inventory, updates accordingly.
                Commands.showStatus();

                // Riddle game
                Commands.playRiddle();

                Commands.battle();

                // displays room description
                Commands.roomDescription();

                // displays npc in room
                Commands.showNPC();

                // display items in room
                Commands.showItem();

                // Ask player to input something
                Display.printScreenLn("What would you like to do?");
                Display.printScreen("> ");
                String command = UserInput.getInput();

                parser.parseCommand(command);

                if (command.equals("quit")) {
                    Display.printScreenLn("Quitting game...");
                    break;
                }

                if (player.isWin()) {
                    Display.printScreenLn("The End...");
                    break;
                }
            }
        }
        // If player chooses not to start a new game, end the program
        else {
            Display.printScreenLn("Quitting game...");
        }
    }

    public static Location[] getLocations() {
        return locations;
    }

    public static void setLocations(Location[] locations) {
        Main.locations = locations;
    }

    public static Sound getSound() {
        return sound;
    }

    public static void setSound(Sound sound) {
        Main.sound = sound;
    }
}
