import java.util.Scanner;

public class TicTacToeUI {

    public static char[] playerIDChar;
    public static TicTacToeGame activeGame;
    public static Scanner input = new Scanner(System.in);
    public static boolean wrongInputFlag = false;


    public static void main(String[] a)  throws InterruptedException{
        boolean replay;
        do {
            introSelect();
            initGame();
            System.out.println("Would you like to replay? (enter y for yes, n or any key for no)");
            String entry = input.nextLine();
            replay = entry.equalsIgnoreCase("y");
        }while(replay);
        TicLog.endMsg();
        Thread.sleep(1000);
        System.exit(0);
    }

    public static boolean isIntegerInput(String attempt){
        try {return ((Integer)Integer.parseInt(attempt) instanceof Integer);}
        catch (Exception e) {
            TicLog.nanError();
            return false;}
    }

    public static boolean isCharInput(String attempt){
        if (attempt.length() != 1) TicLog.notChar();
        return (attempt.length() == 1);}

    private static void introSelect() throws InterruptedException{
        do {
            TicLog.intro();
            String select = input.nextLine();
            wrongInputFlag = !isIntegerInput(select);
            if (wrongInputFlag)continue;
            else{introSelectionInput(Integer.parseInt(select));}
        }while(wrongInputFlag);
    }

    private static void introSelectionInput(int selection) throws InterruptedException{
        if (selection == 1) Thread.sleep(1000);
        else if (selection == 2) {
            showRules();
            introSelect();
        }
        else if (selection == 3) System.exit(0);
        else{
            TicLog.stdInvalidInput();
            wrongInputFlag = true;
        }
    }

    private static void showRules() throws InterruptedException{
        Thread.sleep(1000);
        TicLog.rulesIntro();
        Thread.sleep(4000);
        System.out.println("Enter any key to continue:");
        input.nextLine();
    }

    private static void initGame() throws InterruptedException {
        activeGame = new TicTacToeGame();
        playerIDChar = new char[activeGame.getNumPlayers()];
        selectType();
        Thread.sleep(1000);
        chooseMarks();
        printBoard(activeGame);
        int playerOffset = getFirstPlayer();
        gameFlow(playerOffset);
    }

    private static void selectType() {
        do {
            TicLog.gameType();
            String type = input.nextLine();
            wrongInputFlag = !isIntegerInput(type);
            if (wrongInputFlag)continue;
            else gameType(Integer.parseInt(type));
        }while (wrongInputFlag);
    }

    private static void gameType(int type) {
        if (type == 1){
            activeGame.setPlayerAt(1, new Human(1));
            activeGame.setPlayerAt(2, new Human(2));
        }
        else if (type == 2){
            activeGame.setPlayerAt(1, new Human(1));
            activeGame.setPlayerAt(2, new AI(2));
        }
        else if (type == 3){
            activeGame.setPlayerAt(1, new AI(1));
            activeGame.setPlayerAt(2, new AI(2));
        }
        else{
            TicLog.stdInvalidInput();
            wrongInputFlag = true;
        }
    }

    private static void chooseMarks() {
        for (int i = 0; i < playerIDChar.length; i++) {
            System.out.println("Choose a mark for Player "+ (i+1) +"(any ONE letter, number, etc.): ");
            setPlayerIDChar(i);
        }
    }

    public static void setPlayerIDChar(int index){
    do {
        String mark = input.nextLine();
        wrongInputFlag = !isCharInput(mark);
        if(wrongInputFlag) continue;
        else{
            for (int i = 0; i < playerIDChar.length; i++) {
                if (mark.charAt(0) == playerIDChar[i]){
                    wrongInputFlag = true;
                    TicLog.charTaken();
                    break;
                }
            }
            if (wrongInputFlag)continue;
            else playerIDChar[index] = mark.charAt(0);
        }
    }while(wrongInputFlag);
    }

    private static int getFirstPlayer() {
        int numOfFirstPlayer = 0;
        do {
            TicLog.goesFirst(activeGame.getNumPlayers());
            String firstPlay = input.nextLine();
            wrongInputFlag = !isIntegerInput(firstPlay);
            if (wrongInputFlag)continue;
            else{
                if (Integer.parseInt(firstPlay) > activeGame.getNumPlayers() || Integer.parseInt(firstPlay) < 1) {
                    wrongInputFlag = true;
                    TicLog.outOfRange();
                }
                else numOfFirstPlayer = Integer.parseInt(firstPlay);
            }
        }while(wrongInputFlag);
        return numOfFirstPlayer;
    }

    private static void gameFlow(int offset) throws InterruptedException{
        activeGame.setCurrentPlayer(offset);
        while (!activeGame.isGameOver()) {
            System.out.println("Your turn, Player " + activeGame.getCurrentPlayer());
            Thread.sleep(500);
            if (activeGame.getPlayer(activeGame.getCurrentPlayer()) instanceof AI) {
                aiTurn(activeGame.getPlayer(activeGame.getCurrentPlayer()));
            }
            else humanTurn(activeGame.getPlayer(activeGame.getCurrentPlayer()));

            printBoard(activeGame);
            activeGame.evaluateGame();
            if (activeGame.isGameOver()) {
                if(activeGame.getWinningPlayer() != null)
                    System.out.println("Winner is Player " + activeGame.getWinningPlayer().getID() + "!");
                else TicLog.drawGame();
            }

        }
    }

    private static void humanTurn(Player humanPlay) throws InterruptedException{
        int rowPrimed = -1;
        int columnPrimed = -1;
        Thread.sleep(250);
        do {
            TicLog.enterRow();
            String row = input.nextLine();
            wrongInputFlag = !isIntegerInput(row);
            if (wrongInputFlag)continue;
            else{
                rowPrimed = Integer.parseInt(row) - 1;
                wrongInputFlag = (rowPrimed >= activeGame.getToeBoard().getRows() || rowPrimed < 0);
                if (wrongInputFlag){
                    TicLog.outOfRange();
                    continue;
                }
            }

            TicLog.enterColumn();
            String column = input.nextLine();
            wrongInputFlag = !isIntegerInput(column);
            if (wrongInputFlag)continue;
            else{
                columnPrimed = Integer.parseInt(column) - 1;
                wrongInputFlag = (columnPrimed >= activeGame.getToeBoard().getColumns() || columnPrimed < 0);
                if (wrongInputFlag){
                    TicLog.outOfRange();
                    continue;
                }
            }
            wrongInputFlag = (activeGame.isSpaceTaken(rowPrimed, columnPrimed));
            if (!wrongInputFlag) {
                humanPlay.setPrimedRow(rowPrimed);
                humanPlay.setPrimedColumn(columnPrimed);
                humanPlay.move(activeGame);
            }
            else TicLog.occupied();
        }while (wrongInputFlag);
    }

    private static void aiTurn(Player player) throws InterruptedException{
        Thread.sleep(250);
        TicLog.computerResponse();
        player.move(activeGame);
        Thread.sleep(250);
        System.out.println("Row " + (activeGame.getLastRow() + 1) + ", " + "Column " + (activeGame.getLastColumn() + 1));
        Thread.sleep(750);
    }

    public static void printBoard(TicTacToeGame game) {
        int gameBoardRows = game.getToeBoard().getRows();
        int gameBoardColumns = game.getToeBoard().getColumns();

        TicLog.borderLine();
        for (int i = 0; i < gameBoardRows; i++) {
            System.out.print(" ");
            for (int j = 0; j < gameBoardColumns; j++) {
                if (game.getToeBoard().checkElementAt(i, j)== game.getToeBoard().EMPTY){System.out.print(" ");}
                else {System.out.print(playerIDChar[game.getToeBoard().checkElementAt(i, j) - 1]);}

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

}
