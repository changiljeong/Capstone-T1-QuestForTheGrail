import java.util.List;
import java.util.Map;

public class Location {
    private String name;
    private String description;
    private Map<String, String>items;
    private Map<String, String> directions;

    public Location(String name, String description, Map<String, String> items, Map<String, String> directions) {
        this.name = name;
        this.description = description;
        this.items = items;
        this.directions = directions;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getItems() {
        return items;
    }


    public Map<String, String> getDirections() {
        return directions;
    }



}

