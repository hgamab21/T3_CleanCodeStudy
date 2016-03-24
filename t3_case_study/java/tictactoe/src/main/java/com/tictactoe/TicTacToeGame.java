import java.util.ArrayList;
import java.util.Collections;

/**
 * TicTacToeGame class:
 * Defines the gameplay of TicTacToe, including definitions for win and draw conditions.
 */
public class TicTacToeGame {

    private Player[] players;
    private TicTacToeBoard toeBoard;
    private boolean gameOver;
    private ArrayList<Integer> remainingSpaces;
    private int currentPlayer = 1;
    private Player winningPlayer;
    private int lastRow;
    private int lastColumn;

    public TicTacToeGame() {
        toeBoard = new TicTacToeBoard();
        players = new Player[2];
        gameOver = false;
        remainingSpaces = new ArrayList<>();
        for (int i = 0; i < toeBoard.getRows()*toeBoard.getColumns(); i++) {
            remainingSpaces.add(i);
        }
    }

    public TicTacToeGame(int boardSize) {
        toeBoard = new TicTacToeBoard(boardSize);
        players = new Player[2];
        gameOver = false;
        remainingSpaces = new ArrayList<>();
        for (int i = 0; i < toeBoard.getRows()*toeBoard.getColumns(); i++) {
            remainingSpaces.add(i);
        }
    }

    public TicTacToeGame(int boardSize, int numPlayers) {
        toeBoard = new TicTacToeBoard(boardSize);
        players = new Player[numPlayers];
        gameOver = false;
        remainingSpaces = new ArrayList<>();
        for (int i = 0; i < toeBoard.getRows()*toeBoard.getColumns(); i++) {
            remainingSpaces.add(i);
        }
    }

    public TicTacToeBoard getToeBoard(){return toeBoard;}

    public void evaluateGame(){
        if (hasWin(getPlayer(currentPlayer))){
            winningPlayer = getPlayer(currentPlayer);
            setGameOver(true);
        }
        else if (isBoardFull()) setGameOver(true);
        else if (!hasWin(getPlayer(currentPlayer)) && isGameOver()) setGameOver(false);
        else{progressPlayer();}
    }

    public void setMoveAt(Player mover, int row, int column) {
        if(!toeBoard.isValidIndex(row, column)){
            System.err.println("Out of range! Try again.");
        }
        else if(isSpaceTaken(row, column)){
            System.err.println("That space is taken! Try again.");
        }
        else {
            toeBoard.placeElementAt(mover.getID(), row, column);
            lastRow = row;
            lastColumn = column;
            removeMove(row, column);
        }
    }

    private void removeMove(int row, int column) {
        if (remainingSpaces.contains((row * toeBoard.getColumns()) + column))
            remainingSpaces.remove(remainingSpaces.indexOf((row * toeBoard.getColumns()) + column));
    }

    public boolean isSpaceTaken(int row, int column){
        return !toeBoard.isSpaceEmpty(row, column);
    }

    public ArrayList<Integer> getRemainingSpaces() {
        return remainingSpaces;
    }

    public int getNumPlayers() {
        return players.length;
    }

    public boolean hasRowsWin(Player p){
        return getToeBoard().getNumInstancesInRow(p.getID(), lastRow) == getToeBoard().getColumns();
    }

    public boolean hasColumnsWin(Player p){
        return getToeBoard().getNumInstancesInColumn(p.getID(), lastColumn) == getToeBoard().getRows();
    }

    public boolean hasCrossesWin(Player p){
        return hasForwardDiagonalWin(p) || hasInverseDiagonalWin(p);
    }

    public boolean hasForwardDiagonalWin(Player p){
        return getToeBoard().getNumInstancesInForDiag(p.getID()) == getToeBoard().getRows();
    }

    public boolean hasInverseDiagonalWin(Player p){
        return getToeBoard().getNumInstancesInInvDiag(p.getID()) == getToeBoard().getRows();
    }

    public boolean hasWin(Player possible){
        return hasRowsWin(possible) || hasColumnsWin(possible) || hasCrossesWin(possible);
    }

    public void setPlayerAt(int index, Player player) {
        players[index - 1] = player;
    }

    public Player getPlayer(int index) {
        return players[index - 1];
    }

    public void undoMove(int row, int column) {
        if (!getRemainingSpaces().contains((row * getToeBoard().getColumns()) + column)) {
            getToeBoard().placeElementAt(getToeBoard().EMPTY, row, column);
            if(!isGameOver())revertPlayer();
            else{
                setGameOver(false);
                setWinningPlayer(null);
            }
            getRemainingSpaces().add((row * getToeBoard().getColumns()) + column);
            Collections.sort(remainingSpaces);
        }
        else System.err.println("This space is already empty!");
    }

    public boolean isGameOver() {return gameOver;}

    public void setGameOver(boolean gameOver) {this.gameOver = gameOver;}

    public boolean isBoardFull(){return getRemainingSpaces().size() == 0;}

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinningPlayer() {return winningPlayer;}

    public void setWinningPlayer(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public void revertPlayer(){
        currentPlayer = (currentPlayer > 1) ? currentPlayer - 1 : players.length;
    }

    public void progressPlayer(){
        currentPlayer = (currentPlayer < players.length) ? currentPlayer + 1 : 1;
    }

    public int getLastRow() {return lastRow;}

    public int getLastColumn(){return lastColumn;}

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
