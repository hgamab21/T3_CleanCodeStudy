import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;


/**
 * Created by Hiram Abraham on 2/1/2016.
 */
public class AITest {

    //helper methods
    public static void printBoardTest(TicTacToeGame game){
        int gameBoardRows = game.getToeBoard().getRows();
        int gameBoardColumns = game.getToeBoard().getColumns();

        TicLog.borderLine();
        for (int i = 0; i < gameBoardRows; i++) {
            System.out.print(" ");
            for (int j = 0; j < gameBoardColumns; j++) {
                if (game.getToeBoard().checkElementAt(i, j)== 0){System.out.print(" ");}
                else {System.out.print(game.getToeBoard().checkElementAt(i, j));}

                if (j < gameBoardColumns - 1) System.out.print(" | ");
            }
            System.out.println();

            if(i < gameBoardRows - 1) {
                for (int j = 0; j < gameBoardColumns; j++) {
                    System.out.print("===");
                    if (j < gameBoardColumns - 1) System.out.print("+");
                }
            }
            System.out.println();
        }
        TicLog.borderLine();
    }

    //Reintroduce commented out code if you wish to diagnose internals in detail.
    public boolean hasLoopholesInAILogic(TicTacToeGame game, int humanIndex, int AIIndex){
        game.setPlayerAt(humanIndex, new Human(humanIndex));
        game.setPlayerAt(AIIndex, new AI(AIIndex));

        ArrayList<Integer> moveSet = game.getRemainingSpaces();
        boolean hasPassed = false;
        int rowToBeRemoved;
        int colToBeRemoved;
        if(game.isGameOver()){
            if (game.getWinningPlayer() == null){}//System.out.println("Game ends in a draw.");
            else if (game.getWinningPlayer() != null && game.getWinningPlayer().getID() == humanIndex) {
                //System.out.println("Game results in a loss for the AI");
                return true;
            }
            else{}//System.out.println("AI wins, as expected.");
        }
        else{
            for (int i = 0; i < moveSet.size(); i++) {
                int currMove = moveSet.get(i);
                //System.out.println("Show current index:" + currMove);
                if (game.getPlayer(game.getCurrentPlayer()) instanceof Human){
                    game.getPlayer(game.getCurrentPlayer()).setPrimedRow(currMove / game.getToeBoard().getColumns());
                    game.getPlayer(game.getCurrentPlayer()).setPrimedColumn(currMove % game.getToeBoard().getColumns());
                    game.getPlayer(game.getCurrentPlayer()).move(game);
                    rowToBeRemoved = game.getPlayer(game.getCurrentPlayer()).getPrimedRow();
                    colToBeRemoved = game.getPlayer(game.getCurrentPlayer()).getPrimedColumn();
                    //System.out.println("Move made by p"+ humanIndex + ": " + rowToBeRemoved + ", " + colToBeRemoved);
                    game.evaluateGame();
                    hasPassed = hasLoopholesInAILogic(game, humanIndex, AIIndex);
                }
                else {
                    ((AI)game.getPlayer(game.getCurrentPlayer())).move(game);
                    rowToBeRemoved = game.getPlayer(game.getCurrentPlayer()).getPrimedRow();
                    colToBeRemoved = game.getPlayer(game.getCurrentPlayer()).getPrimedColumn();
                    //System.out.println("Move made by p" + AIIndex + ": " + rowToBeRemoved + ", " + colToBeRemoved);
                    game.evaluateGame();
                    hasPassed = hasLoopholesInAILogic(game, humanIndex, AIIndex);
                }

                game.undoMove(rowToBeRemoved, colToBeRemoved);
                if (hasPassed)break;
            }
            return hasPassed;
        }
        return hasPassed;
    }


    @Test
    public void testForAlphaBetaFunctionalityWhenPlayerTwo() {
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new AI(2));

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(1);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);

        assertEquals(1, testGame.getToeBoard().checkElementAt(1, 0));

        testGame.evaluateGame();

        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(2, testGame.getCurrentPlayer());

        AI trueTestPlayer = (AI) testGame.getPlayer(testGame.getCurrentPlayer());
        int[] minimaxResults = trueTestPlayer.alphaBetaMini(testGame, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("Score: " + minimaxResults[0]);
        System.out.println(minimaxResults[1]);
        System.out.println(minimaxResults[2]);
        trueTestPlayer.move(testGame);

        printBoardTest(testGame);

        testGame.evaluateGame();
        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(1, testGame.getCurrentPlayer());

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(2);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(1);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);

        assertEquals(1, testGame.getToeBoard().checkElementAt(2, 1));

        testGame.evaluateGame();

        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(2, testGame.getCurrentPlayer());

        trueTestPlayer = (AI)testGame.getPlayer(testGame.getCurrentPlayer());
        minimaxResults = trueTestPlayer.alphaBetaMini(testGame, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("Score: " + minimaxResults[0]);
        System.out.println(minimaxResults[1]);
        System.out.println(minimaxResults[2]);
        trueTestPlayer.primeRow(minimaxResults[1]);
        trueTestPlayer.primeColumn(minimaxResults[2]);
        trueTestPlayer.move(testGame);

        printBoardTest(testGame);

        testGame.evaluateGame();
        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(1, testGame.getCurrentPlayer());

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(1);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);

        assertEquals(1, testGame.getToeBoard().checkElementAt(0, 1));

        testGame.evaluateGame();

        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(2, testGame.getCurrentPlayer());

        trueTestPlayer = (AI)testGame.getPlayer(testGame.getCurrentPlayer());
        minimaxResults = trueTestPlayer.alphaBetaMini(testGame, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("Score: " + minimaxResults[0]);
        System.out.println(minimaxResults[1]);
        System.out.println(minimaxResults[2]);
        trueTestPlayer.primeRow(minimaxResults[1]);
        trueTestPlayer.primeColumn(minimaxResults[2]);
        trueTestPlayer.move(testGame);

        printBoardTest(testGame);

        testGame.evaluateGame();
        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(1, testGame.getCurrentPlayer());

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(1);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(2);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);

        assertEquals(1, testGame.getToeBoard().checkElementAt(1, 2));

        testGame.evaluateGame();

        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(2, testGame.getCurrentPlayer());

        trueTestPlayer = (AI)testGame.getPlayer(testGame.getCurrentPlayer());
        minimaxResults = trueTestPlayer.alphaBetaMini(testGame, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("Score: " + minimaxResults[0]);
        System.out.println(minimaxResults[1]);
        System.out.println(minimaxResults[2]);
        trueTestPlayer.primeRow(minimaxResults[1]);
        trueTestPlayer.primeColumn(minimaxResults[2]);
        trueTestPlayer.move(testGame);

        printBoardTest(testGame);

        testGame.evaluateGame();

        assert(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(2, testGame.getWinningPlayer().getID());
    }

    @Test
    public void testForAlphaBetaFunctionalityWhenPlayerOne() {
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(2, new Human(2));
        testGame.setPlayerAt(1, new AI(1));

        AI trueTestPlayer = (AI) testGame.getPlayer(testGame.getCurrentPlayer());
        int[] minimaxResults = trueTestPlayer.alphaBetaMini(testGame, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("Score: " + minimaxResults[0]);
        System.out.println(minimaxResults[1]);
        System.out.println(minimaxResults[2]);
        trueTestPlayer.move(testGame);

        printBoardTest(testGame);

        testGame.evaluateGame();
        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(2, testGame.getCurrentPlayer());

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(2);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);

        assertEquals(2, testGame.getToeBoard().checkElementAt(0, 2));

        testGame.evaluateGame();

        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(1, testGame.getCurrentPlayer());

        trueTestPlayer = (AI) testGame.getPlayer(testGame.getCurrentPlayer());
        minimaxResults = trueTestPlayer.alphaBetaMini(testGame, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("Score: " + minimaxResults[0]);
        System.out.println(minimaxResults[1]);
        System.out.println(minimaxResults[2]);
        trueTestPlayer.move(testGame);

        printBoardTest(testGame);

        testGame.evaluateGame();
        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(2, testGame.getCurrentPlayer());

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(2);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);

        assertEquals(2, testGame.getToeBoard().checkElementAt(2, 0));

        testGame.evaluateGame();

        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(1, testGame.getCurrentPlayer());

        trueTestPlayer = (AI)testGame.getPlayer(testGame.getCurrentPlayer());
        minimaxResults = trueTestPlayer.alphaBetaMini(testGame, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("Score: " + minimaxResults[0]);
        System.out.println(minimaxResults[1]);
        System.out.println(minimaxResults[2]);
        trueTestPlayer.primeRow(minimaxResults[1]);
        trueTestPlayer.primeColumn(minimaxResults[2]);
        trueTestPlayer.move(testGame);

        printBoardTest(testGame);

        testGame.evaluateGame();
        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(2, testGame.getCurrentPlayer());

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(1);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);

        assertEquals(2, testGame.getToeBoard().checkElementAt(0, 1));

        testGame.evaluateGame();

        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(1, testGame.getCurrentPlayer());

        trueTestPlayer = (AI)testGame.getPlayer(testGame.getCurrentPlayer());
        minimaxResults = trueTestPlayer.alphaBetaMini(testGame, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("Score: " + minimaxResults[0]);
        System.out.println(minimaxResults[1]);
        System.out.println(minimaxResults[2]);
        trueTestPlayer.primeRow(minimaxResults[1]);
        trueTestPlayer.primeColumn(minimaxResults[2]);
        trueTestPlayer.move(testGame);

        printBoardTest(testGame);

        testGame.evaluateGame();
        assert (testGame.isGameOver());
        assert (testGame.getWinningPlayer().getID() == 1);

    }

    @Test
    public void testAllPossibleMovesForHumanNoWinEndgame(){
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human(1));
        testGame.setPlayerAt(2, new AI(2));

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(1);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);

        assertEquals(1, testGame.getToeBoard().checkElementAt(1, 0));

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);

        assertEquals(1, testGame.getToeBoard().checkElementAt(0, 0));

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(2);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);

        assertEquals(1, testGame.getToeBoard().checkElementAt(2, 0));

        testGame.evaluateGame();

        assert(hasLoopholesInAILogic(testGame, 1, 2));

        assertFalse(hasLoopholesInAILogic(new TicTacToeGame(), 1, 2));
        assertFalse(hasLoopholesInAILogic(new TicTacToeGame(), 2, 1));
    }


}


