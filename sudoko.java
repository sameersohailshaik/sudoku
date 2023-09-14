import java.util.Random;

public class SudokuGenerator {
    private static final int SIZE = 9;
    private static final int SUBGRID_SIZE = 3;
    private static final int EMPTY_CELL = 0;

    public static void main(String[] args) {
        int[][] board = generateSudoku();
        printSudoku(board);
    }

    public static int[][] generateSudoku() {
        int[][] board = new int[SIZE][SIZE];
        Random random = new Random();

        // Fill the diagonal subgrids (initial pattern)
        fillDiagonalSubgrids(board, random);

        // Solve the puzzle using backtracking (this is where a complete game would start)
        solveSudoku(board);

        // Remove some numbers to create a playable puzzle (for simplicity, we remove a fixed number of cells)
        removeNumbers(board, 30); // Adjust the number of removed cells as desired

        return board;
    }

    public static void fillDiagonalSubgrids(int[][] board, Random random) {
        for (int row = 0; row < SIZE; row += SUBGRID_SIZE) {
            for (int col = 0; col < SIZE; col += SUBGRID_SIZE) {
                fillSubgrid(board, row, col, random);
            }
        }
    }

    public static void fillSubgrid(int[][] board, int startRow, int startCol, Random random) {
        int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        shuffleArray(values, random);

        int index = 0;
        for (int row = startRow; row < startRow + SUBGRID_SIZE; row++) {
            for (int col = startCol; col < startCol + SUBGRID_SIZE; col++) {
                board[row][col] = values[index];
                index++;
            }
        }
    }

    public static void shuffleArray(int[] array, Random random) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static boolean solveSudoku(int[][] board) {
        // TODO: Implement a Sudoku solving algorithm (e.g., backtracking)
        return false;
    }

    public static void removeNumbers(int[][] board, int numToRemove) {
        Random random = new Random();

        while (numToRemove > 0) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);

            if (board[row][col] != EMPTY_CELL) {
                board[row][col] = EMPTY_CELL;
                numToRemove--;
            }
        }
    }

    public static void printSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] + " ");
                if ((col + 1) % SUBGRID_SIZE == 0 && col < SIZE - 1) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if ((row + 1) % SUBGRID_SIZE == 0 && row < SIZE - 1) {
                System.out.println("---------------------");
            }
        }
    }
}
