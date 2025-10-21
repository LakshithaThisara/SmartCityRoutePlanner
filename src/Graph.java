// ===== Member 1: Graph Developer =====
import java.util.*;

public class Graph {
    private Map<String, List<String>> adjacencyList = new HashMap<>();

    // Add a location (node)
    public void addLocation(String location) {
        adjacencyList.putIfAbsent(location, new ArrayList<>());
    }

    // Remove a location (and connected roads)
    public void removeLocation(String location) {
        adjacencyList.remove(location);
        for (List<String> neighbours : adjacencyList.values()) {
            neighbours.remove(location);
        }
    }

    // Add a road (edge)
    public boolean addRoad(String from, String to) {
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) return false;
        if (adjacencyList.get(from).contains(to)) return false; // already exists
        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from); // undirected graph
        return true;
    }

    // Remove a road
    public boolean removeRoad(String from, String to) {
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) return false;
        adjacencyList.get(from).remove(to);
        adjacencyList.get(to).remove(from);
        return true;
    }

    // Display all connections
    public void displayConnections() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No connections available.");
            return;
        }
        System.out.println("Connections (Undirected):");
        for (String loc : adjacencyList.keySet()) {
            System.out.println(" - " + loc + " : " + adjacencyList.get(loc));
        }
    }

    public Map<String, List<String>> getAdjacencyList() {
        return adjacencyList;
    }
}
