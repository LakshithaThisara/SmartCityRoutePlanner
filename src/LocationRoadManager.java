// ===== Member 3: Location & Road Manager =====
import java.util.*;

public class LocationRoadManager {
    private Graph graph;
    private AVLTree avl;

    public LocationRoadManager(Graph graph, AVLTree avl) {
        this.graph = graph;
        this.avl = avl;
    }

    public void addLocation(String name) {
        if (avl.insert(name)) {
            graph.addLocation(name);
            System.out.println("Location '" + name + "' added successfully.");
        } else {
            System.out.println("Location '" + name + "' already exists.");
        }
    }

    public void removeLocation(String name) {
        if (avl.delete(name)) {
            graph.removeLocation(name);
            System.out.println("Location '" + name + "' removed successfully.");
        } else {
            System.out.println("Location '" + name + "' not found.");
        }
    }

    public void addRoad(String from, String to) {
        if (!graph.getAdjacencyList().containsKey(from) || !graph.getAdjacencyList().containsKey(to)) {
            System.out.println("Both locations must exist before adding a road.");
            return;
        }
        if (from.equalsIgnoreCase(to)) {
            System.out.println("Cannot add a road from a location to itself.");
            return;
        }
        if (graph.addRoad(from, to)) {
            System.out.println("Road added between " + from + " and " + to + ".");
        } else {
            System.out.println("Road already exists or invalid.");
        }
    }

    public void removeRoad(String from, String to) {
        if (graph.removeRoad(from, to)) {
            System.out.println("Road removed between " + from + " and " + to + ".");
        } else {
            System.out.println("No such road found.");
        }
    }
