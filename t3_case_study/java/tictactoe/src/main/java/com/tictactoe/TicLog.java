import java.io.PrintStream;
import java.util.Random;

public final class TicLog {

    private static PrintStream output = System.out;
    private static PrintStream error = System.err;

    public static void occupied(){
        error.println("That space is already taken!!");
    }

    public static void enterRow(){
        output.print("Enter a row: ");
    }

    public static void enterColumn(){
        output.print("Enter a column: ");
    }

    public static void drawGame(){
        output.print("It's a tie!");
    }

    public static void charTaken(){ error.println("That character is already taken! Try again.");}

    public static void invalidInput(){
        error.println("Invalid input! Choose a space on the board - Remember, space must be unoccupied!");
    }

    public static void stdInvalidInput(){
        error.println("Invalid input! Try again.");
    }

    public static void nanError(){
        error.println("Not a number! Try again.");
    }

    public static void intro(){
        output.println("Welcome to Tic Tac Toe! Select from one of the following:");
        output.println("(1) Play!");
        output.println("(2) Instructions");
        output.println("(3) Exit");
    }

    public static void borderLine(){
        output.println("*-----------------------------------------------------------------------------------------*");
    }

    public static void rulesIntro(){
        output.println("Welcome to Tic Tac Toe! The rules are simple: get three across, three in a column,");
        output.println("or three on either diagonal.");
        output.println("Inputs are taken in a form of a row, followed by a column - for example: If Player 1(X)");
        output.println("entered row 1, column 3, it would look like this:");
        output.println("   " + "|" + "   " + "|" + " X");
        output.println("===+===+===");
        output.println("   " + "|" + "   " + "|" + " ");
        output.println("===+===+===");
        output.println("   " + "|" + "   " + "|" + " ");
        output.println();
    }

    public static void gameType(){
        output.println("Choose from any one of the following:");
        output.println("(1) Player vs Player");
        output.println("(2) Player vs Computer");
        output.println("(3) Computer vs Computer (Observe mode)");
    }

    public static void goesFirst(int players){
        output.println("Who should go first?");
        for (int i = 0; i < players; i++) {
            output.println("(" + (i+1) + ") Player " + (i+1));
        }
    }

    public static void computerResponse(){
        Random messageGen = new Random();
        int msgSelect = messageGen.nextInt(8);
        switch(msgSelect){
            case 1: System.out.println("Hmm... not bad. I think I'll pick...");
                break;
            case 2: System.out.println("Nice. Let's see - I'll take...");
                break;
            case 3: System.out.println("Hmm...how about I pick...");
                break;
            case 4: System.out.println("Let's go with...");
                break;
            case 5: System.out.println("My turn? In that case, I'm gonna pick...");
                break;
            case 6: System.out.println("I don't know if I have an answer for that - Let's try...");
                break;
            case 7: System.out.println("Next, I'll pick...");
                break;
            default: System.out.println("*bleep bloop bleep* Computer noises *bleep bloop bleep*");
                break;
        }
    }

    public static void endMsg(){
        output.println("Thanks for playing!");
    }

    public static void outOfRange(){error.println("Out of range. Try again.");}

    public static void notChar() {error.println("Choose only ONE character for your mark! Try again.");}
}
