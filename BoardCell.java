package battleship;

public class BoardCell {
    private final int row, column;
    private Mark mark;
    private Ship ship;

    BoardCell(int row, int column, Mark mark) {
        this.row = row;
        this.column = column;
        this.mark = mark;
    }

    Mark getMark(boolean isFogOfWar) {
        if (isFogOfWar && mark == Mark.OCCUPIED) {
            return Mark.FREE;
        } else {
            return mark;
        }
    }

    void setMark(Mark mark) {
        this.mark = mark;
    }

    String setCannonShot() {
        String message;
        if (isOccupied()) {
            message = "You hit a ship!";
            if (this.mark == Mark.OCCUPIED) {
                this.ship.incrementDamage();
                setMark(Mark.HIT);
            }
            if (this.getShip().isSunk()) {
                message = "You sank a ship! Specify a new target:";
            }
        } else {
            setMark(Mark.MISS);
            message = "You missed!";
        }
        return message;
    }

    int getRow() {
        return row;
    }

    int getColumn() {
        return column;
    }

    boolean isANeighbour(BoardCell boardCell) {
        return row == boardCell.row && Math.abs(column - boardCell.getColumn()) == 1 ||
                Math.abs(row - boardCell.row) == 1 && column == boardCell.column;
    }

    int getLength(BoardCell toBoardCell) {
        return 1 + Math.max(Math.abs(this.getRow() - toBoardCell.getRow()),
                Math.abs(this.getColumn() - toBoardCell.getColumn()));
    }

    public boolean isOccupied() {
        return ship != null;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
