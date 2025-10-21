// ===== Member 4: Interface & Integration Lead =====
import java.util.*;

public class SmartCityRoutePlanner {
    private static AVLTree avl = new AVLTree();
    private static Graph graph = new Graph();
    private static LocationRoadManager manager = new LocationRoadManager(graph, avl);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            System.out.println();
            switch (choice) {
                case 1:
                    manager.addLocation(readNonEmptyString("Enter location name to add: "));
                    break;
                case 2:
                    manager.removeLocation(readNonEmptyString("Enter location name to remove: "));
                    break;
                case 3:
                    manager.addRoad(
                        readNonEmptyString("Enter starting location: "),
                        readNonEmptyString("Enter destination location: "));
                    break;
                case 4:
                    manager.removeRoad(
                        readNonEmptyString("Enter starting location: "),
                        readNonEmptyString("Enter destination location: "));
                    break;
                case 5:
                    graph.displayConnections();
                    break;
                case 6:
                    displayAllLocations();
                    break;
                case 7:
                    System.out.println("Exiting... Thank you for using Smart City Route Planner!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("--- Smart City Route Planner ---");
        System.out.println("1. Add a new location");
        System.out.println("2. Remove a location");
        System.out.println("3. Add a road between locations");
        System.out.println("4. Remove a road");
        System.out.println("5. Display all connections");
        System.out.println("6. Display all locations (using a tree)");
        System.out.println("7. Exit");
    }

    private static void displayAllLocations() {
        List<String> locs = avl.inorder();
        if (locs.isEmpty()) {
            System.out.println("No locations stored yet.");
        } else {
            System.out.println("Locations (stored using AVL tree):");
            for (String loc : locs) {
                System.out.println(" - " + loc);
            }
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input cannot be empty.");
        }
    }
}
