
/**
 * Created by Hiram Abraham on 1/30/2016.
 */
public class Human extends Player {

    public Human(){super();}

    public Human(int id){super(id);}

    @Override
    public void move(TicTacToeGame game) {
        game.setMoveAt(this, getPrimedRow(), getPrimedColumn());
    }

    @Override
    public void primeRow(int rowIndex) {
        super.setPrimedRow(rowIndex);
    }

    @Override
    public void primeColumn(int colIndex) {
        super.setPrimedColumn(colIndex);
    }

    @Override
    public void setID(int newID) {
        super.setID(newID);
    }

    @Override
    public int getID() {
        return super.getID();
    }

}
