package battleship;

public class BattleshipGame {
    private final GameBoard gameBoard = new GameBoard();
    private final Ship[] ships = {
            new Ship("Aircraft Carrier", 5),
            new Ship("Battleship", 4),
            new Ship("Submarine", 3),
            new Ship("Cruiser", 3),
            new Ship("Destroyer", 2)
    };
    private final Player player = new Player("Player 1");
    private final Player opponent = new Player("Player 2");

    BattleshipGame() {
        //quickInitializeNo1();
        setup();
        play();
    }

    void setup() {
        gameBoard.draw(false);
        for (Ship ship : ships) {
            requestCellsForShip(ship);
            gameBoard.draw(false);
        }
    }

    public void play() {
        System.out.println("The game starts!");
        gameBoard.draw(true);
        do {
            requestShot();
            gameBoard.draw(true);
        } while (!allShipsDestroyed());
        gameBoard.draw(false);
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    private void requestCellsForShip(Ship ship) {
        boolean approvedPlacement = false;
        System.out.println("Enter the coordinates of the " + ship.getType() + " (" + ship.getLength() + ") cells: ");
        do {
            PlacementOrder placementOrder = player.getPlacementOrder(gameBoard, ship);
            if (placementOrder.isValid() && gameBoard.isValidPlacementOrder(placementOrder)) {
                //System.out.println(placementOrder);
                gameBoard.placeShip(placementOrder);
                approvedPlacement = true;
            }
        } while (!approvedPlacement);
    }

    private void requestShot() {
        boolean approvedShot = false;
        System.out.println("Take a shot!");
        do {
            CannonShot cannonShot = player.getCannonShot();
            if (gameBoard.isValidCannonShot(cannonShot)) {
                approvedShot = true;
                gameBoard.placeCannonShot(cannonShot);
            }
        } while (!approvedShot);
    }

    private boolean allShipsDestroyed() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    private void quickInitializeNo1() {
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[0], "F3", "F7"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[1], "A1", "D1"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[2], "J10", "J8"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[3], "B9", "D9"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[4], "I2", "J2"));
        gameBoard.draw(false);
    }

    private void quickInitializeNo2() {
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[0], "J3", "J7"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[1], "C8", "F8"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[2], "A1", "C1"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[3], "H1", "H3"));
        gameBoard.placeShip(new PlacementOrder(gameBoard, ships[4], "B5", "C5"));
        gameBoard.draw(false);
    }
}
