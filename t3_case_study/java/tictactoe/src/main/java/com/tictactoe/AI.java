
import java.util.ArrayList;

/**
 * Created by Hiram Abraham on 2/1/2016.
 */
public class AI extends Player{

    public AI(){super();}

    public AI(int ID){super(ID);}

    @Override
    public void setID(int newID) {
        super.setID(newID);
    }

    @Override
    public int getID() {
        return super.getID();
    }

    @Override
    public void move(TicTacToeGame game){
        int[] miniResults = alphaBetaMini(game, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        setPrimedRow(miniResults[1]);
        setPrimedColumn(miniResults[2]);
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

    public int minimaxScore(TicTacToeGame game, int depth){
	int maxBoardScore = (game.getToeBoard().getRows() * game.getToeBoard().getColumns()) + 1;
        if(game.getWinningPlayer() == this)return maxBoardScore - depth;
        else if(game.getWinningPlayer() != null) return depth - maxBoardScore;
        else return 0;
    }

    public int[] alphaBetaMini(TicTacToeGame game, int depth, int alpha, int beta){
        ArrayList<Integer> subsetMoveList = game.getRemainingSpaces();

        int bestScore;
        int finalRow = -1, finalColumn = -1;
        int currentDepth = depth;

        if (game.isGameOver()){
            bestScore = minimaxScore(game, depth);
            return new int[]{bestScore, finalRow, finalColumn};
        }
        else{
            for (int i = 0; i < subsetMoveList.size(); i++) {
                Integer move = subsetMoveList.get(i);
                game.setMoveAt(game.getPlayer(game.getCurrentPlayer()),
                        move / game.getToeBoard().getColumns(),
                        move % game.getToeBoard().getColumns());
                if (game.getCurrentPlayer() == this.getID()){
                    game.evaluateGame();
                    bestScore = alphaBetaMini(game, depth + 1, alpha, beta)[0];
                    if (bestScore > alpha){
                        finalRow = move / game.getToeBoard().getColumns();
                        finalColumn = move % game.getToeBoard().getColumns();
                        alpha = bestScore;
                    }
                }
                else{
                    game.evaluateGame();
                    bestScore = alphaBetaMini(game, depth + 1, alpha, beta)[0];
                    if (bestScore < beta){
                        finalRow = move / game.getToeBoard().getColumns();
                        finalColumn = move % game.getToeBoard().getColumns();
                        beta = bestScore;
                    }
                }
                game.undoMove(move / game.getToeBoard().getColumns(), move % game.getToeBoard().getColumns());
                if (alpha >= beta) {
                    break;
                }

            }
            return new int[]{(game.getCurrentPlayer() == this.getID()) ? alpha : beta, finalRow, finalColumn};
        }


    }
}
