package battleship;

public enum Mark {

    FREE('~'), OCCUPIED('O'), HIT('X'), MISS('M');

    private final char symbol;

    Mark(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
