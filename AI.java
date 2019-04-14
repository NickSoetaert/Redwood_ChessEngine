import java.util.ArrayList;
import java.util.Random;

public class AI
{
    public Board currentGame;
    public float WhiteTime,BlackTime;
    
    public AI(Board currentGame)
    {
        this.currentGame = currentGame;
    }

    public String Think(ArrayList<String> moves)
    {
        Random rand = new Random();

        String bestMatch = moves.get(rand.nextInt(moves.size() - 1));

        return bestMatch;
    }
    public void act(String action)
    {
        currentGame.move(action);
    }


    public int minmax(Board board, int depth, int maxDepth, ArrayList<String> history)
    {

        if (depth >= maxDepth)
        {
            return board.eval();
        }
        

        if (depth % 2 != 0)
        {
            int best = -1000;
            ArrayList<String> childMoves = MoveGenerator.possibleWhiteMoves(board, history);

            for (int i = 0; i <= childMoves.size(); i++)
            {
                // In an ideal world we would make an undo fucntion that can undo a move 

                best = Math.max(best, minmax(board.newBoardMove(childMoves.get(i)), depth+1, maxDepth, history));
            }
            return best;
        }
        else
        {
            int best = 1000;
            ArrayList<String> childMoves = MoveGenerator.possibleWhiteMoves(board, history);

            for (int i = 0; i <= childMoves.size(); i++)
            {
                // In an ideal world we would make an undo fucntion that can undo a move 

                best = Math.max(best, minmax(board.newBoardMove(childMoves.get(i)), depth+1, maxDepth, history));
            }
            return best;
        }
    }

    public String FindBestMove(Board board,int depthMax, ArrayList<String> history)
    {
        String best_move = "There is None";
        int BestScore = -2000;        
        
        ArrayList<String> childMoves = MoveGenerator.possibleWhiteMoves(board, history);

        for (int i = 0; i <= childMoves.size(); i++)
        {
            // In an ideal world we would make an undo fucntion that can undo a move 

            int temp_score= minmax(board.newBoardMove(childMoves.get(i)), 0, depthMax, history);

            if (temp_score > BestScore)
            {
                best_move = childMoves.get(i);
            }
        }

        System.out.println("I think the best move is " + best_move + " it has a score of  : " + BestScore);
        return best_move;
    }
}