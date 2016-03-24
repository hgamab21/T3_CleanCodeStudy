import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
public class TicTacToeBoardTest {

    @Test
    public void testForSquareBoard(){
        TicTacToeBoard testBoard = new TicTacToeBoard();
        assert(testBoard.getRows() == testBoard.getColumns());
    }

    @Test
    public void testForEmptyBoardUponInstantiation(){
        TicTacToeBoard testBoard = new TicTacToeBoard();
        boolean isEmpty = true;
        for (int i = 0; i < testBoard.getRows(); i++) {
            for (int j = 0; j <testBoard.getColumns() ; j++) {
                if(testBoard.checkElementAt(i, j) != 0) isEmpty = false;
            }
        }
        assert(isEmpty);
    }

    @Test
    public void testForPlacementOfValueAtIndex(){
        TicTacToeBoard testBoard = new TicTacToeBoard();
        boolean isEmpty = true;
        testBoard.placeElementAt(2, 1, 1);
        for (int i = 0; i < testBoard.getRows(); i++) {
            for (int j = 0; j <testBoard.getColumns() ; j++) {
                if(testBoard.checkElementAt(i, j) != 0) isEmpty = false;
            }
        }
        assertFalse(isEmpty);
    }

    @Test
    public void testForNoPlacementOfValueAtIndexInCaseBothCoordinatesAreTooHigh(){
        TicTacToeBoard testBoard = new TicTacToeBoard();
        boolean isEmpty = true;
        testBoard.placeElementAt(2, 3, 3);
        for (int i = 0; i < testBoard.getRows(); i++) {
            for (int j = 0; j <testBoard.getColumns() ; j++) {
                if(testBoard.checkElementAt(i, j) != 0) isEmpty = false;
            }
        }
        assert(isEmpty);
    }

    @Test
    public void testForNoPlacementOfValueAtIndexInCaseRowCoordinatesAreTooHigh(){
        TicTacToeBoard testBoard = new TicTacToeBoard();
        boolean isEmpty = true;
        testBoard.placeElementAt(2, 4, 2);
        for (int i = 0; i < testBoard.getRows(); i++) {
            for (int j = 0; j <testBoard.getColumns() ; j++) {
                if(testBoard.checkElementAt(i, j) != 0) isEmpty = false;
            }
        }
        assert(isEmpty);
    }

    @Test
    public void testForNoPlacementOfValueAtIndexInCaseColumnCoordinatesAreTooHigh(){
        TicTacToeBoard testBoard = new TicTacToeBoard();
        boolean isEmpty = true;
        testBoard.placeElementAt(2, 2, 8);
        for (int i = 0; i < testBoard.getRows(); i++) {
            for (int j = 0; j <testBoard.getColumns() ; j++) {
                if(testBoard.checkElementAt(i, j) != 0) isEmpty = false;
            }
        }
        assert(isEmpty);
    }

    @Test
    public void testForNoPlacementOfValueAtIndexInCaseBothCoordinatesAreNegative(){
        TicTacToeBoard testBoard = new TicTacToeBoard();
        boolean isEmpty = true;
        testBoard.placeElementAt(2, -3, -5);
        for (int i = 0; i < testBoard.getRows(); i++) {
            for (int j = 0; j <testBoard.getColumns() ; j++) {
                if(testBoard.checkElementAt(i, j) != 0) isEmpty = false;
            }
        }
        assert(isEmpty);
    }

    @Test
    public void testForNoPlacementOfValueAtIndexInCaseRowCoordinatesAreNegative(){
        TicTacToeBoard testBoard = new TicTacToeBoard();
        boolean isEmpty = true;
        testBoard.placeElementAt(2, -1, 0);
        for (int i = 0; i < testBoard.getRows(); i++) {
            for (int j = 0; j <testBoard.getColumns() ; j++) {
                if(testBoard.checkElementAt(i, j) != 0) isEmpty = false;
            }
        }
        assert(isEmpty);
    }

    @Test
    public void testForNullPlacementOfValueAtIndexInCaseColumnCoordinatesAreNegative(){
        TicTacToeBoard testBoard = new TicTacToeBoard();
        boolean isEmpty = true;
        testBoard.placeElementAt(2, -3, -5);
        for (int i = 0; i < testBoard.getRows(); i++) {
            for (int j = 0; j <testBoard.getColumns() ; j++) {
                if(testBoard.checkElementAt(i, j) != testBoard.EMPTY) isEmpty = false;
            }
        }
        assert(isEmpty);
    }

    @Test
    public void testForProperPlacementOfValueAtIndex(){
        TicTacToeBoard testBoard = new TicTacToeBoard();
        testBoard.placeElementAt(2, 1, 2);
        assert(testBoard.checkElementAt(1, 2) == 2);
    }

    @Test
    public void testForProperFunctionOfIsEmpty(){
        TicTacToeBoard testBoard = new TicTacToeBoard();
        testBoard.placeElementAt(2, 1, 2);
        assert(testBoard.checkElementAt(1, 2) == 2);
        assert(testBoard.isSpaceEmpty(2, 2));
    }

}
