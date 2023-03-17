import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Commands parser = new Commands();

        // Display game title
        System.out.println("Welcome!");

        // Wait for player to press enter
        System.out.println("Press enter to start the game...");
        scanner.nextLine();

        // Ask player if they want to start a new game
        System.out.println("Would you like to start a new game? (y/n)");
        String choice = scanner.nextLine();

        // If player chooses to start a new game, begin the game
        if (choice.equals("y")) {
            System.out.println("Let the adventure begin!");

            // Display basic information about the game
            System.out.println(
                "You walk into a dark, damp dungeon. You are in search of the holy grail.");
            System.out.println(
                "To win the game, you must find and use the 3 statues to unlock the passage to the holy grail!");

            // Game loop
            while (true) {

                // Display status (player name, health, lives, inventory)


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
}
