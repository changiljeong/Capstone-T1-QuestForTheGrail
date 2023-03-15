public class Commands {
    public void parseCommand(String command) {
        String[] words = command.split(" ");
        String verb = words[0].toLowerCase();
        String noun = "";

        if (words.length > 1) {
            noun = words[1].toLowerCase();
        }

        switch (verb) {
            case "look":
                // handle "look" command
                if (noun.equals("")) {
                    System.out.println("You look around but see nothing of interest.");
                } else {
                    System.out.println("You look at the " + noun + ".");
                    // handle looking at specific object
                }
                break;

            case "go":
                // handle "go" command
                if (noun.equals("")) {
                    System.out.println("Go where?");
                } else {
                    System.out.println("You go to the " + noun + ".");
                    // handle going to specific location

                }
                break;

            default:
                System.out.println("I don't understand that command.");
                break;
        }
    }

}
