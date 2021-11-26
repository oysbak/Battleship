package battleship;

import java.util.Scanner;

public class Player {
    private final Scanner scanner = new Scanner(System.in);
    private final String name;
    private final GameBoard gameBoard;
    private final Ship[] ships;

    Player(String name) {
        this.name = name;
        gameBoard = new GameBoard();
        ships = new Ship[]{
                new Ship("Aircraft Carrier", 5),
                new Ship("Battleship", 4),
                new Ship("Submarine", 3),
                new Ship("Cruiser", 3),
                new Ship("Destroyer", 2)
        };
    }

    void drawGameBoard() {
        gameBoard.draw(true);
    }

    public void requestCannonShot(Player opponent) {
        boolean approvedShot = false;
        System.out.println("Take a shot!");
        do {
            CannonShot cannonShot = opponent.getCannonShot();
            if (gameBoard.isValidCoordinate(cannonShot.getCoordinate())) {
                approvedShot = true;
                gameBoard.markCannonShot(cannonShot);
            }
        } while (!approvedShot);
    }


    public void setUpGame() {
        gameBoard.draw(false);
        for (Ship ship : ships) {
            requestCellsForShip(ship);
            gameBoard.draw(false);
        }
    }

    private void requestCellsForShip(Ship ship) {
        boolean approvedPlacement = false;
        System.out.println("Enter the coordinates of the " + ship.getType() + " (" + ship.getLength() + ") cells: ");
        do {
            PlacementOrder placementOrder = getPlacementOrder(gameBoard, ship);
            if (placementOrder.isValid() && gameBoard.isValidPlacementOrder(placementOrder)) {
                //System.out.println(placementOrder);
                gameBoard.placeShip(placementOrder);
                approvedPlacement = true;
            }
        } while (!approvedPlacement);
    }

    private PlacementOrder getPlacementOrder(GameBoard gameBoard, Ship ship) {
        return new PlacementOrder(gameBoard, ship, scanner.nextLine());
    }

    public CannonShot getCannonShot() {
        return new CannonShot(scanner.nextLine());
    }

    public boolean areAllShipsDestroyed() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    void doQuickInitializeNo1() {
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[0], "F3 F7"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[1], "A1 D1"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[2], "J10 J8"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[3], "B9 D9"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[4], "I2 J2"));
        gameBoard.draw(false);
    }

    void doQuickInitializeNo2() {
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[0], "J3 J7"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[1], "C8 F8"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[2], "A1 C1"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[3], "H1 H3"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[4], "B5 C5"));
        gameBoard.draw(false);
    }
}
