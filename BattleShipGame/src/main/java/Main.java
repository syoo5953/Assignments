import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board(10, 10);
        board.addExtendedShips(); // change to addNormalShips() for non-extended version

        board.init();
        board.populateBattleship(board.getShipList().size());

        System.out.print("Enter a player1 name: ");
        String player_name = sc.next();
        Player player1 = new Player(player_name, board);
        System.out.print("Enter a player2 name: ");
        player_name = sc.next();
        Player player2 = new Player(player_name, board);
        System.out.println("\n\n =============== The Game Starts ===============\n");

        int playerTurn = new Random().nextInt() % 2; // set the first player to start the game
        board.printBoard();
        while(!takeTurn(board)) {
            int x, y;
            Player currentPlayer = null;
            if(playerTurn == 1) {
                System.out.println("Player [" + player2.getName() + "]'s Turn\n");
                currentPlayer = player2;
                playerTurn = 0;
            } else if(playerTurn == 0) {
                System.out.println("Player [" + player1.getName() + "]'s Turn\n");
                currentPlayer = player1;
                playerTurn = 1;
            }

            System.out.print("Enter x coordinate: ");
            x = sc.nextInt();
            System.out.print("Enter y coordinate: ");
            y = sc.nextInt();

            board.fireTheBattleship(y, x, currentPlayer);
            board.printBoard();
        }
        printWinner(player1, player2);
    }

    public static boolean takeTurn(Board board){
        if(board.getShipList().size() == 0) {
            return true;
        }
        return false;
    }

    public static void printWinner(Player player1, Player player2) {
        System.out.println("\n =============== The Game has finished ===============\n");
        System.out.println("Player [" + player1.getName() + "]'s Score: " + player1.getScore());
        System.out.println("Player [" + player2.getName() + "]'s Score: " + player2.getScore());
        if(player1.getScore() > player2.getScore()) {
            System.out.println("The WINNER is [" + player1.getName() + "]");
        } else if(player1.getScore() < player2.getScore()) {
            System.out.println("The WINNER is [" + player2.getName() + "]");
        } else if(player1.getScore() == player2.getScore()) {
            System.out.println("!!!DRAW!!!");
        }
    }
}
