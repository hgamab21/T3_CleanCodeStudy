/**
 * All player types will inherit this class.
 */
public abstract class Player {

    private int id;
    private int primedRow = -1;
    private int primedColumn = -1;
    public static final int INVALID_ID = -1;

    public Player(){
        id = 1;
    }

    public Player(int ID){
        id = (isValidID(ID)) ? ID : INVALID_ID;
    }

    public boolean isValidID(int idAttempt){
        return idAttempt > 0;
    }

    public abstract void move(TicTacToeGame game);

    public abstract void primeRow(int rowIndex);

    public abstract void primeColumn(int colIndex);

    public int getPrimedRow(){return primedRow;}

    public int getPrimedColumn(){return primedColumn;}

    public void setPrimedRow(int rowIndex){primedRow = rowIndex;}

    public void setPrimedColumn(int colIndex){primedColumn = colIndex;}

    public int getID() {
        return id;
    }

    public void setID(int newID) {
        id = (isValidID(newID)) ? newID : INVALID_ID;
    }
}
