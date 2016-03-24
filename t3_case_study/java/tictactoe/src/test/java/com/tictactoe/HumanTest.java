import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Hiram Abraham on 1/30/2016.
 */
public class HumanTest {
    @Test
    public void testIDChange(){
        Player testPlayer = new Human();
        assertEquals(1, testPlayer.getID());
        testPlayer.setID(4);
        assertEquals(4, testPlayer.getID());
    }

    @Test
    public void testIDChangeToInvalidUponInputNotGreaterThanZero(){
        Player testPlayer = new Human();
        assertEquals(1, testPlayer.getID());
        testPlayer.setID(4);
        assertEquals(4, testPlayer.getID());
        testPlayer.setID(0);
        assertEquals(Player.INVALID_ID, testPlayer.getID());
        testPlayer.setID(1);
        assertEquals(1, testPlayer.getID());
    }

    @Test
    public void testMovePlacesProperCharacter(){
        Player testPlayer = new Human();
        TicTacToeGame testGame = new TicTacToeGame();
        testPlayer.primeRow(1);
        testPlayer.primeColumn(1);
        testPlayer.move(testGame);
        assertEquals(testPlayer.getID(), testGame.getToeBoard().checkElementAt(1, 1));
    }

}
