/**
 * Created by Hiram Abraham on 1/30/2016.
 */
public class TicTacToeBoard {

    private int[][] board;
    private int columns = 3;
    private int rows = 3;
    public final int EMPTY = 0;

    //Creates a standard, empty 3x3 TicTacToe board.
    //Empty spaces are delineated with a 0, non-zero values indicate occupied spaces.
    public TicTacToeBoard() {board = new int[rows][columns];}

    public TicTacToeBoard(int squareSize) {
        rows = squareSize;
        columns = squareSize;
        board = new int[rows][columns];
    }

    public int checkElementAt(int rowIndex, int columnIndex) {
        return board[rowIndex][columnIndex];
    }

    public void placeElementAt(int identifier, int rowIndex, int columnIndex) {
        if (!isValidIndex(rowIndex, columnIndex)||(checkElementAt(rowIndex, columnIndex) != 0 && identifier != EMPTY)) {
            TicLog.invalidInput();
        } else {
            board[rowIndex][columnIndex] = identifier;
        }
    }

    public boolean isValidIndex(int rowIndex, int colIndex){
        boolean valid = false;
        try{
            valid = (rowIndex >= 0 && rowIndex < board.length) && (colIndex >= 0 && colIndex < board[0].length);
        }catch(Exception e){
            if (e instanceof ArrayIndexOutOfBoundsException){
                System.err.print("Out of range!");
            }
        }
        return valid;
    }

    public int getNumInstancesInRow(int identifier, int row) {
        int instances = 0;
        for (int i = 0; i < getColumns(); i++) {
            if (checkElementAt(row, i) == identifier) instances++;
        }
        return instances;
    }

    public int getNumInstancesInColumn(int identifier, int column) {
        int instances = 0;
        for (int i = 0; i < getRows(); i++) {
            if (checkElementAt(i, column) == identifier) instances++;
        }
        return instances;
    }

    public int getNumInstancesInForDiag(int identifier) {
        int instances = 0;
        for (int i = 0; i < getRows(); i++) {
            if (checkElementAt(i, i) == identifier) instances++;
        }
        return instances;
    }

    public int getNumInstancesInInvDiag(int identifier) {
        int instances = 0;
        for (int i = 0; i < getRows(); i++) {
            if (checkElementAt(i, (getColumns() - 1) - i) == identifier) instances++;
        }
        return instances;
    }

    public boolean isSpaceEmpty(int row, int column) {
        return checkElementAt(row, column) == EMPTY;
    }

    public int getRows() {
        return board.length;
    }

    public int getColumns() {
        return board[0].length;
    }
}

