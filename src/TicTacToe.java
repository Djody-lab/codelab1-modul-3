// Tic Tac Toe Game - After Refactoring
import java.util.*;

public class TicTacToe implements Game {
    private static final int BOARD_SIZE = 9; // Replace Magic Number
    private final Board board;                // Extract Class
    private String currentPlayer;             // Rename Variable
    private final Scanner scanner;

    public TicTacToe() {
        board = new Board(BOARD_SIZE);
        scanner = new Scanner(System.in);
        currentPlayer = "X";
    }

    @Override
    public void play() {
        String winner = null;

        System.out.println("Selamat Datang.");
        board.printBoard();
        System.out.println("X will play first. Enter a slot number to place X in:");

        while (winner == null) {
            int move = getPlayerInput();
            board.setCell(move, currentPlayer);
            board.printBoard();
            winner = checkWinner();
            if (winner == null) switchPlayer();
        }

        announceResult(winner);
    }

    @Override
    public void reset() {
        board.reset();
        currentPlayer = "X";
    }

    // Extract Method 1: Input handling
    private int getPlayerInput() {
        while (true) {
            System.out.print(currentPlayer + "'s turn; enter a slot number: ");
            try {
                int input = scanner.nextInt();
                if (input < 1 || input > BOARD_SIZE) {
                    System.out.println("Invalid input; re-enter slot number:");
                    continue;
                }
                if (!board.isAvailable(input - 1)) {
                    System.out.println("Slot already taken; re-enter slot number:");
                    continue;
                }
                return input - 1;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; please enter a number 1-9:");
                scanner.nextLine();
            }
        }
    }

    // Extract Method 2: Switch player
    private void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    // Extract Method 3: Check winner
    private String checkWinner() {
        String[] cells = board.getCells();
        int[][] winPatterns = {
                {0, 1, 5}, {3, 4, 5}, {6, 7, 8}, // rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
                {0, 4, 8}, {2, 4, 6}              // diagonals
        };

        for (int[] pattern : winPatterns) {
            String line = cells[pattern[0]] + cells[pattern[1]] + cells[pattern[2]];
            if (line.equals("XXX")) return "X";
            if (line.equals("OOO")) return "O";
        }

        if (board.isFull()) return "draw";
        return null;
    }

    // Extract Method 4: Announce result
    private void announceResult(String winner) {
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        } else {
            System.out.println("Congratulations! " + winner + " wins!");
        }
    }

    public static void main(String[] args) {
        new TicTacToe().play();
    }
}