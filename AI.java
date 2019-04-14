import java.util.ArrayList;
import java.util.Random;

public class AI
{
    public Board currentGame;
    public float WhiteTime,BlackTime;
    public String ConfidenceinLastMove;
    
    public AI(Board currentGame)
    {
        this.currentGame = currentGame;
    }

    public String Think(ArrayList<String> moves,String Color,int depth)
    {
        return FindBestMove(currentGame, depth ,new ArrayList<String>(),Color);
    }
    public void act(String action)
    {
        currentGame.move(action);
    }

    public int minmaxBlack(Board board, int depth, int maxDepth, ArrayList<String> history,Boolean ismax)
    {
        //System.out.println("Depth at : "+ depth + " of max " + maxDepth);
        //System.out.println(depth >= maxDepth);
        if (depth >= maxDepth)
        {
            //System.out.println("Returning!@!@!@!@!");
            return board.eval();
        }

        if (ismax)
        {

            int best = -1000;
            ArrayList<String> childMoves = MoveGenerator.possibleBlackMoves(board, history).getAlg();

            for (int i = 0; i < childMoves.size(); ++i)
            {
                // In an ideal world we would make an undo fucntion that can undo a move 

                depth += 1;
                best = Math.max(best, minmaxBlack(board.newBoardMove(childMoves.get(i)), depth, maxDepth, history,ismax));
            }
            return best;
        }
        else
        {
            int best = 1000;
            ArrayList<String> childMoves = MoveGenerator.possibleWhiteMoves(board, history).getAlg();

            for (int i = 0; i < childMoves.size(); ++i)
            {
                // In an ideal world we would make an undo fucntion that can undo a move 

                depth += 1;
                best = Math.min(best, minmaxBlack(board.newBoardMove(childMoves.get(i)), depth, maxDepth, history,ismax));
            }
            return best;
        }
    }

    public int minmaxWhite(Board board, int depth, int maxDepth, ArrayList<String> history, boolean ismax)
    {

        //System.out.println("Depth at : "+ depth + " of max " + maxDepth);
        //System.out.println(depth >= maxDepth);
        if (depth >= maxDepth)
        {
            //System.out.println("deepest score of : " + board.eval());
            return board.eval();
        }

        

        if (ismax)
        {
            int best = -1000;
            ArrayList<String> childMoves = MoveGenerator.possibleWhiteMoves(board, history).getAlg();

            for (int i = 0; i < childMoves.size(); ++i)
            {
                // In an ideal world we would make an undo fucntion that can undo a move 
                //depth += 1;
                best = Math.max(best, minmaxWhite(board.newBoardMove(childMoves.get(i)), depth+1, maxDepth, history,!ismax));
            }
            return best;
        }
        else
        {
            int best = 1000;
            ArrayList<String> childMoves = MoveGenerator.possibleBlackMoves(board, history).getAlg();

            for (int i = 0; i < childMoves.size(); ++i)
            {
                // In an ideal world we would make an undo fucntion that can undo a move 

                //depth += 1;
                best = Math.min(best, minmaxWhite(board.newBoardMove(childMoves.get(i)), depth+1, maxDepth, history,!ismax));
            }
            return best;
        }
    }

    public String FindBestMove(Board board,int depthMax, ArrayList<String> history, String color)
    {
        String best_move = "There is None";
        int BestScore;        
        int temp_score;
        ArrayList<String> childMoves;

        if (color == "White")
        {
            BestScore = -2000;
            childMoves = MoveGenerator.possibleWhiteMoves(board, history).getAlg();
        }
        else
        {
            BestScore = 2000;
            childMoves = MoveGenerator.possibleBlackMoves(board, history).getAlg();
        }

        for (int i = 0; i < childMoves.size(); ++i)
        {

            if (color == "White")
            {
                temp_score = minmaxWhite(board.newBoardMove(childMoves.get(i)), 0, depthMax, history,true);
                //System.out.println(childMoves.get(i) + " has a final score of : " + temp_score);
                if (temp_score > BestScore)
                {
                    BestScore = temp_score;
                    best_move = childMoves.get(i);
                }
            }
            else
            {
                temp_score = minmaxBlack(board.newBoardMove(childMoves.get(i)), 0, depthMax, history,false);
                if (temp_score < BestScore)
                {
                    BestScore = temp_score;
                    best_move = childMoves.get(i);
                }
            }
            //System.out.println("most choice " + i + " of  " + childMoves.size() + "current best evaluated to be : " + BestScore);

        }

        //BoardGenerator.drawBoard(currentGame);
        //System.out.println("I think the best move is " + best_move + " it has a score of  : " + BestScore);
        ConfidenceinLastMove = "I think the best move is " + best_move + " it has a score of  : " + BestScore;
        return best_move;
    }
}