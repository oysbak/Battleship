package battleship;

import java.util.Scanner;

public class Player {
    private final Scanner scanner = new Scanner(System.in);
    private final String name;
    private final GameBoard gameBoard = new GameBoard();

    Player(String name) { this.name = name; }

    public PlacementOrder getPlacementOrder(GameBoard gameBoard, Ship ship) {
        String[] coordinates = scanner.nextLine().split(" ");
        return new PlacementOrder(gameBoard, ship, coordinates[0], coordinates[1]);
    }

    public CannonShot getCannonShot() {
        return new CannonShot(scanner.nextLine());
    }
}
