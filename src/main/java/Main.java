import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

public class Main {
    private static Player player;
    private static Location currentLocation;
    static Location[] locations;
    static Items[] items;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        try(Reader reader = new InputStreamReader(Main.class.getClassLoader().getResourceAsStream("rooms.json"))) {

        Gson gson = new Gson();
        locations = gson.fromJson(reader, Location[].class);
        }

        try(Reader reader = new InputStreamReader(Main.class.getClassLoader().getResourceAsStream("items.json"))) {
            Gson gson1 = new Gson();
            items = gson1.fromJson(reader, Items[].class);
        }


        // set current location to the first location in the array
        currentLocation = locations[0];

        // create a new player with starting values
        player = new Player("Player", 100, currentLocation);

        Commands parser = new Commands(player, currentLocation);

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

            //Display commands
            System.out.println("Commands:"
                + "\n" + "Go - move around"
                + "\n" + "Look - look at something"
                + "\n" + "Get - pick up stuff"
                + "\n" + "Help - see commands again");
            // Display basic information about the game
            System.out.println(
                "You walk into a dark, damp dungeon. You are in search of the holy grail.");
            System.out.println(
                "To win the game, you must find and use the 3 statues to unlock the passage to the holy grail!");

            // Game loop
            while (true) {

//                // Display status (player name, health, lives, inventory)
//                System.out.println("Location: " + currentLocation.getName());
//                System.out.println();

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
            "Main locations" + Main.locations +
            '}';
    }
}
