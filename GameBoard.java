package battleship;

public class GameBoard {
    private final BoardCell[][] gameBoard;
    private String message = "";

    GameBoard() {
        gameBoard = new BoardCell[10][10];
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                gameBoard[row][column] = new BoardCell(row, column, Mark.FREE);
            }
        }
    }

    void draw(boolean isFogOfWar) {
        final String[] rowNotation = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String header = "  1 2 3 4 5 6 7 8 9 10";
        System.out.println(header);
        for (int row = 0; row < 10; row++) {
            System.out.print(rowNotation[row]);
            for (int column = 0; column < 10; column++) {
                System.out.print(" " + gameBoard[row][column].getMark(isFogOfWar));
            }
            System.out.println();
        }
        System.out.println(message);
        message = "";
    }

    public BoardCell getBoardCell(String coordinate) {
        int row = coordinate.charAt(0) - 65;
        int column = Integer.parseInt(coordinate.substring(1)) - 1;
        return this.gameBoard[row][column];
    }

    BoardCell[] getBoardCells() {
        BoardCell[] cells = new BoardCell[100];
        int i = 0;
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                cells[i++] = gameBoard[row][column];
            }
        }
        return cells;
        //return returnValue.toArray(new BoardCell[returnValue.size()]);
    }

    boolean isValidBoardCell(BoardCell boardCell) {
        return 0 <= boardCell.getRow() && boardCell.getRow() <= 9 && 0 <= boardCell.getColumn() &&
                boardCell.getColumn() <= 9;
    }

    boolean isOccupiedBoardCell(BoardCell cell) {
        return cell.isOccupied();
    }

    private boolean isNeighbourOccupied(BoardCell cell) {
        for (BoardCell boardCell : this.getBoardCells()) {
            if (boardCell.isANeighbour(cell) && boardCell.isOccupied()) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return true;
            }
        }
        return false;
    }

    boolean isValidPlacementOrder(PlacementOrder placementOrder) {
        // Check for valid, occupied and neighbouring cells
        for (BoardCell cell : placementOrder.getOccupiedBoardCells()) {
            if (!this.isValidBoardCell(cell) || this.isOccupiedBoardCell(cell) || this.isNeighbourOccupied(cell)) {
                return false;
            }
        }
        return true;
    }

    public void placeShip(PlacementOrder placementOrder) {
        for (BoardCell boardCell : placementOrder.getOccupiedBoardCells()) {
            boardCell.setShip(placementOrder.getShip());
            boardCell.setMark(Mark.OCCUPIED);
        }

    }

    public boolean isValidCannonShot(CannonShot cannonShot) {
        if (this.isValidBoardCell(getBoardCell(cannonShot.getCoordinate()))) {
            return true;
        } else {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            return false;
        }
    }

    public void placeCannonShot(CannonShot cannonShot) {
        message = getBoardCell(cannonShot.getCoordinate()).setCannonShot();

    }

    private int getLength(BoardCell fromBoardCell, BoardCell toBoardCell) {
        return 1 + Math.max(Math.abs(fromBoardCell.getRow() - toBoardCell.getRow()),
                Math.abs(fromBoardCell.getColumn() - toBoardCell.getColumn()));
    }

    public BoardCell[] getBoardCells(BoardCell fromBoardCell, BoardCell toBoardCell) {
        BoardCell[] boardCells = new BoardCell[this.getLength(fromBoardCell, toBoardCell)];
        int index = 0;
        if (fromBoardCell.getRow() == toBoardCell.getRow()) {
            // All cells on same row horizontally
            for (int column = fromBoardCell.getColumn(); column <= toBoardCell.getColumn(); column++) {
                boardCells[index++] = gameBoard[fromBoardCell.getRow()][column];
            }
        } else {
            // All cells on same column vertically
            for (int row = fromBoardCell.getRow(); row <= toBoardCell.getRow(); row++) {
                boardCells[index++] = gameBoard[row][fromBoardCell.getColumn()];
            }
        }
        return boardCells;
    }
}