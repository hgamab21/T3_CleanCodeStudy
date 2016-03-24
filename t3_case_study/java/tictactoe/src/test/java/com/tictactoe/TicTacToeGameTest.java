import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * Created by Hiram Abraham on 1/31/2016.
 */
public class TicTacToeGameTest {
    @Test
    public void testForIsSpaceTakenReturnsFalseOnEmptySpace(){
        TicTacToeGame game = new TicTacToeGame();
        assert(!game.isSpaceTaken(0,0));
        assert(!game.isSpaceTaken(1,2));
        assert(!game.isSpaceTaken(0,1));
        assert(!game.isSpaceTaken(1,1));
    }

    @Test
    public void testForProperSpacesRemainingUponInstantiation() {
        TicTacToeGame game = new TicTacToeGame();
        assert (game.getRemainingSpaces().size() == 9);
    }

    @Test
    public void testForExistenceOfTwoPlayers(){
        TicTacToeGame testGame = new TicTacToeGame();
        assertEquals(2, testGame.getNumPlayers());
    }

    @Test
    public void testForWinCheckReturnsFalseInInitialState(){
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        //testGame.getPlayer(1).setID(1);
        testGame.setPlayerAt(2, new Human(2));
        //testGame.getPlayer(2).setID(2);
        assertFalse(testGame.hasWin(testGame.getPlayer(1)));
    }

    @Test
    public void testForAbilityToCheckWinByRowsPlayerOne(){
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));
        testGame.getPlayer(1).primeRow(0);
        testGame.getPlayer(1).primeColumn(0);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(0);
        testGame.getPlayer(1).primeColumn(1);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(0);
        testGame.getPlayer(1).primeColumn(2);
        testGame.getPlayer(1).move(testGame);

        assert(testGame.hasRowsWin(testGame.getPlayer(1)));
    }

    @Test
    public void testForAbilityToCheckWinByRowsPlayerTwo() {
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));
        testGame.getPlayer(2).primeRow(0);
        testGame.getPlayer(2).primeColumn(0);
        testGame.getPlayer(2).move(testGame);

        testGame.getPlayer(2).primeRow(0);
        testGame.getPlayer(2).primeColumn(1);
        testGame.getPlayer(2).move(testGame);

        testGame.getPlayer(2).primeRow(0);
        testGame.getPlayer(2).primeColumn(2);
        testGame.getPlayer(2).move(testGame);

        assertEquals(2, testGame.getToeBoard().checkElementAt(0, 0));
        assertEquals(2, testGame.getToeBoard().checkElementAt(0, 1));
        assertEquals(2, testGame.getToeBoard().checkElementAt(0, 2));
        assert (testGame.hasRowsWin(testGame.getPlayer(2)));

    }

    @Test
    public void testForAbilityToCheckWinByRowsNotTheFirst() {
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));
        testGame.getPlayer(1).primeRow(1);
        testGame.getPlayer(1).primeColumn(0);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(1);
        testGame.getPlayer(1).primeColumn(1);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(1);
        testGame.getPlayer(1).primeColumn(2);
        testGame.getPlayer(1).move(testGame);

        assertEquals(1, testGame.getToeBoard().checkElementAt(1, 0));
        assertEquals(1, testGame.getToeBoard().checkElementAt(1, 1));
        assertEquals(1, testGame.getToeBoard().checkElementAt(1, 2));
        assert (testGame.hasRowsWin(testGame.getPlayer(1)));
    }

    @Test
    public void testForAbilityToCheckWinByColumnsPlayerOne() {
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));
        testGame.getPlayer(1).primeRow(0);
        testGame.getPlayer(1).primeColumn(0);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(1);
        testGame.getPlayer(1).primeColumn(0);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(2);
        testGame.getPlayer(1).primeColumn(0);
        testGame.getPlayer(1).move(testGame);
        assertEquals(1, testGame.getToeBoard().checkElementAt(0, 0));
        assertEquals(1, testGame.getToeBoard().checkElementAt(1, 0));
        assertEquals(1, testGame.getToeBoard().checkElementAt(2, 0));
        assert (testGame.hasColumnsWin(testGame.getPlayer(1)));
    }

    @Test
    public void testForAbilityToCheckWinByColumnsPlayerTwo() {
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));
        testGame.getPlayer(2).primeRow(0);
        testGame.getPlayer(2).primeColumn(0);
        testGame.getPlayer(2).move(testGame);

        testGame.getPlayer(2).primeRow(1);
        testGame.getPlayer(2).primeColumn(0);
        testGame.getPlayer(2).move(testGame);

        testGame.getPlayer(2).primeRow(2);
        testGame.getPlayer(2).primeColumn(0);
        testGame.getPlayer(2).move(testGame);

        assertEquals(2, testGame.getToeBoard().checkElementAt(0, 0));
        assertEquals(2, testGame.getToeBoard().checkElementAt(1, 0));
        assertEquals(2, testGame.getToeBoard().checkElementAt(2, 0));
        assert (testGame.hasColumnsWin(testGame.getPlayer(2)));
    }

    @Test
    public void testForAbilityToCheckWinByColumnsNotTheFirst() {
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));
        testGame.getPlayer(1).primeRow(0);
        testGame.getPlayer(1).primeColumn(0);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(1);
        testGame.getPlayer(1).primeColumn(0);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(2);
        testGame.getPlayer(1).primeColumn(0);
        testGame.getPlayer(1).move(testGame);

        assert (testGame.hasColumnsWin(testGame.getPlayer(1)));
    }

    @Test
    public void testForAbilityToDiscernWhichColumnIsWinning() {
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));
        testGame.getPlayer(1).primeRow(0);
        testGame.getPlayer(1).primeColumn(1);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(1);
        testGame.getPlayer(1).primeColumn(2);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(2);
        testGame.getPlayer(1).primeColumn(1);
        testGame.getPlayer(1).move(testGame);
        assertFalse(testGame.hasColumnsWin(testGame.getPlayer(1)));
    }

    @Test
    public void testForAbilityToCheckWinByCrossPlayerOne() {
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));
        testGame.getPlayer(1).primeRow(0);
        testGame.getPlayer(1).primeColumn(0);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(1);
        testGame.getPlayer(1).primeColumn(1);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(2);
        testGame.getPlayer(1).primeColumn(2);
        testGame.getPlayer(1).move(testGame);
        assert (testGame.hasCrossesWin(testGame.getPlayer(1)));
    }

    @Test
    public void testForAbilityToCheckWinByInverseCrossPlayerOne() {
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));
        testGame.getPlayer(1).primeRow(2);
        testGame.getPlayer(1).primeColumn(0);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(1);
        testGame.getPlayer(1).primeColumn(1);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(0);
        testGame.getPlayer(1).primeColumn(2);
        testGame.getPlayer(1).move(testGame);
        assert (testGame.hasCrossesWin(testGame.getPlayer(1)));
        assertFalse (testGame.hasCrossesWin(testGame.getPlayer(2)));

    }

    @Test
    public void testForAbilityToDiscernValidWinByCross() {
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));
        testGame.getPlayer(1).primeRow(0);
        testGame.getPlayer(1).primeColumn(0);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(1);
        testGame.getPlayer(1).primeColumn(1);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(1).primeRow(2);
        testGame.getPlayer(1).primeColumn(0);
        testGame.getPlayer(1).move(testGame);
        assertFalse(testGame.hasCrossesWin(testGame.getPlayer(1)));
    }

    @Test
    public void testForAbilityToDiscernValidWinByCrossDifferentPlayerInput() {
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));
        testGame.getPlayer(1).primeRow(0);
        testGame.getPlayer(1).primeColumn(0);
        testGame.getPlayer(1).move(testGame);

        testGame.getPlayer(2).primeRow(1);
        testGame.getPlayer(2).primeColumn(1);
        testGame.getPlayer(2).move(testGame);

        testGame.getPlayer(1).primeRow(2);
        testGame.getPlayer(1).primeColumn(2);
        testGame.getPlayer(1).move(testGame);

        assertFalse(testGame.hasCrossesWin(testGame.getPlayer(1)));
        assertFalse(testGame.hasCrossesWin(testGame.getPlayer(2)));
    }

    @Test
    public void testRemovalOfMoveWhenHumanMoves(){
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));

        assertEquals(9, testGame.getRemainingSpaces().size());

        testGame.getPlayer(1).primeRow(1);
        testGame.getPlayer(1).primeColumn(2);
        testGame.getPlayer(1).move(testGame);

        assertEquals(8, testGame.getRemainingSpaces().size());
        assertFalse(testGame.getRemainingSpaces().contains(5));
    }

    @Test
    public void testForAddOfMoveWhenMoveUndone(){
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));

        assertEquals(9, testGame.getRemainingSpaces().size());

        testGame.getPlayer(1).primeRow(1);
        testGame.getPlayer(1).primeColumn(2);
        testGame.getPlayer(1).move(testGame);

        assertEquals(8, testGame.getRemainingSpaces().size());
        assertFalse(testGame.getRemainingSpaces().contains(5));

        testGame.undoMove(1, 2);

        assertEquals(9, testGame.getRemainingSpaces().size());
        assert(testGame.getRemainingSpaces().contains(5));
        assertEquals(0, testGame.getToeBoard().checkElementAt(1, 2));
    }

    @Test
    public void testForProperAddDeleteDynamicInRemainingSpaces(){
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));

        assertEquals(9, testGame.getRemainingSpaces().size());

        testGame.getPlayer(1).primeRow(1);
        testGame.getPlayer(1).primeColumn(2);
        testGame.getPlayer(1).move(testGame);

        assertEquals(8, testGame.getRemainingSpaces().size());
        assertFalse(testGame.getRemainingSpaces().contains(5));

        testGame.undoMove(1, 2);

        assertEquals(9, testGame.getRemainingSpaces().size());
        assert(testGame.getRemainingSpaces().contains(5));
        assertEquals(0, testGame.getToeBoard().checkElementAt(1, 2));

        testGame.getPlayer(1).primeRow(2);
        testGame.getPlayer(1).primeColumn(0);
        testGame.getPlayer(1).move(testGame);

        assertEquals(8, testGame.getRemainingSpaces().size());
        assert(testGame.getRemainingSpaces().contains(5));
        assertFalse(testGame.getRemainingSpaces().contains(6));

    }

    @Test
    public void testForAbilityForEvaluateGameToDecideNextGameStateVersionWin() {
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(1);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(1);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);

        assertEquals(1, testGame.getToeBoard().checkElementAt(1, 1));

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

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);

        assertEquals(1, testGame.getToeBoard().checkElementAt(0, 0));

        testGame.evaluateGame();

        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(2, testGame.getCurrentPlayer());

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(2);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(1);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);

        assertEquals(2, testGame.getToeBoard().checkElementAt(2, 1));

        testGame.evaluateGame();

        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(1, testGame.getCurrentPlayer());

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(2);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(2);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);

        assertEquals(1, testGame.getToeBoard().checkElementAt(2, 2));

        testGame.evaluateGame();

        assert(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());
        assertEquals(1, testGame.getCurrentPlayer());

        assert (testGame.hasWin(testGame.getPlayer(1)));

        testGame.undoMove(2, 2);

        assertEquals(1, testGame.getCurrentPlayer());

        assertEquals(1, testGame.getCurrentPlayer());
        assertFalse(testGame.isGameOver());
        assertFalse(testGame.isBoardFull());

        testGame.undoMove(2, 1);

        assertFalse(testGame.getCurrentPlayer() == 1);

    }

    @Test
    public void testForAbilityForEvaluateGameToDecideNextGameStateVersionDraw() {
        TicTacToeGame testGame = new TicTacToeGame();
        testGame.setPlayerAt(1, new Human());
        testGame.setPlayerAt(2, new Human(2));

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(1);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(1);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);
        testGame.evaluateGame();

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);
        testGame.evaluateGame();

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(1);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);
        testGame.evaluateGame();

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(2);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(1);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);
        testGame.evaluateGame();

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(2);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);
        testGame.evaluateGame();

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(2);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);
        testGame.evaluateGame();

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(1);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(2);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);
        testGame.evaluateGame();

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(1);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(0);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);
        testGame.evaluateGame();

        testGame.getPlayer(testGame.getCurrentPlayer()).primeRow(2);
        testGame.getPlayer(testGame.getCurrentPlayer()).primeColumn(2);
        testGame.getPlayer(testGame.getCurrentPlayer()).move(testGame);
        testGame.evaluateGame();

        assert(testGame.isGameOver());
        assert(testGame.isBoardFull());
        assertNull(testGame.getWinningPlayer());

        assertFalse(testGame.hasWin(testGame.getPlayer(1)));
        assertFalse(testGame.hasWin(testGame.getPlayer(2)));

    }

}
