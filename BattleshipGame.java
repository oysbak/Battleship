package battleship;

public class BattleshipGame {
    BattleshipGame() {
        final Player player = new Player("Player 1");
        final Player opponent = new Player("Player 2");
        player.setUpGame();
        //player.doQuickInitializeNo1();
        opponent.setUpGame();
        //opponent.doQuickInitializeNo2();
        play(player, opponent);
    }

    public void play(Player player, Player opponent) {
        System.out.println("The game starts!");
        boolean doContinue = true;
        while (doContinue) {
            player.drawGameBoard();
            player.requestCannonShot(opponent);
            doContinue = !player.areAllShipsDestroyed();
            if (doContinue) {
                opponent.drawGameBoard();
                opponent.requestCannonShot(player);
                doContinue = !opponent.areAllShipsDestroyed();
            }
        }
        while (doContinue);
        player.drawGameBoard();
        opponent.drawGameBoard();
        System.out.println("You sank the last ship. You won. Congratulations!");
    }
}
