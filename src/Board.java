import java.util.Arrays;

// Extract Class + Encapsulate Field
public class Board {
    private final String[] cells;
    private final int size;

    public Board(int size) {
        this.size = size;
        this.cells = new String[size];
        reset();
    }

    public void reset() {
        for (int i = 0; i < size; i++) {
            cells[i] = String.valueOf(i + 1);
        }
    }

    public boolean isAvailable(int index) {
        return cells[index].equals(String.valueOf(index + 1));
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            if (cells[i].equals(String.valueOf(i + 1))) return false;
        }
        return true;
    }

    public void setCell(int index, String symbol) {
        cells[index] = symbol;
    }

    public String[] getCells() {
        return cells.clone();
    }

    public void printBoard() {
        System.out.println("|---|---|---|");
        for (int i = 0; i < size; i += 3) {
            System.out.println("| " + cells[i] + " | " + cells[i + 1] + " | " + cells[i + 2] + " |");
            if (i < 6) System.out.println("|-----------|");
        }
        System.out.println("|---|---|---|");
    }
}