package battleship;

public class Main {
    public static void main(String[] args) {
        // Write your code here
        //new Debug();
        new BattleshipGame();
    }
}

class Debug {
    Debug() {
        //System.out.println("Hello Scratch!");
        Mark mark = Mark.MISS;
        System.out.println(mark);
        System.out.println(mark.name());
        System.out.println(mark);
        System.out.println(mark.ordinal());
    }
}
