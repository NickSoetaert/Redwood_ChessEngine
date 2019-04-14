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

    public String Think(ArrayList<String> moves,String Color)
    {
        //Random rand = new Random();

        System.out.println(moves.size());
        System.out.println("wait up");

        /*if (moves.size() > 1)
        {
            return moves.get(rand.nextInt(moves.size()));
        }
        else 
        {
            return moves.get(0);
        }*/
        return FindBestMove(currentGame, 11,new ArrayList<String>(),Color);
    }
    public void act(String action)
    {
        currentGame.move(action);
    }

    public int minmaxBlack(Board board, int depth, int maxDepth, ArrayList<String> history)
    {

        //System.out.println("Depth at : "+ depth + " of max " + maxDepth);
        //System.out.println(depth >= maxDepth);
        if (depth >= maxDepth)
        {
            //System.out.println("Returning!@!@!@!@!");
            return board.eval();
        }

        

        if (depth % 2 != 0)
        {
            int best = 1000;
            ArrayList<String> childMoves = MoveGenerator.possibleBlackMoves(board, history);

            for (int i = 0; i < childMoves.size(); ++i)
            {
                // In an ideal world we would make an undo fucntion that can undo a move 

                depth += 1;
                best = Math.min(best, minmaxBlack(board.newBoardMove(childMoves.get(i)), depth, maxDepth, history));
            }
            return best;
        }
        else
        {
            int best = -1000;
            ArrayList<String> childMoves = MoveGenerator.possibleWhiteMoves(board, history);

            for (int i = 0; i < childMoves.size(); ++i)
            {
                // In an ideal world we would make an undo fucntion that can undo a move 

                depth += 1;
                best = Math.max(best, minmaxBlack(board.newBoardMove(childMoves.get(i)), depth, maxDepth, history));
            }
            return best;
        }
    }


    public int minmaxWhite(Board board, int depth, int maxDepth, ArrayList<String> history)
    {

        //System.out.println("Depth at : "+ depth + " of max " + maxDepth);
        //System.out.println(depth >= maxDepth);
        if (depth >= maxDepth)
        {
            //System.out.println("deepest score of : " + board.eval());
            return board.eval();
        }

        

        if (depth % 2 == 0)
        {
            int best = -1000;
            ArrayList<String> childMoves = MoveGenerator.possibleWhiteMoves(board, history);

            for (int i = 0; i < childMoves.size(); ++i)
            {
                // In an ideal world we would make an undo fucntion that can undo a move 

                depth += 1;
                best = Math.max(best, minmaxWhite(board.newBoardMove(childMoves.get(i)), depth, maxDepth, history));
            }
            return best;
        }
        else
        {
            int best = 1000;
            ArrayList<String> childMoves = MoveGenerator.possibleBlackMoves(board, history);

            for (int i = 0; i < childMoves.size(); ++i)
            {
                // In an ideal world we would make an undo fucntion that can undo a move 

                depth += 1;
                best = Math.min(best, minmaxWhite(board.newBoardMove(childMoves.get(i)), depth, maxDepth, history));
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
            childMoves = MoveGenerator.possibleWhiteMoves(board, history);
        }
        else
        {
            BestScore = 2000;
            childMoves = MoveGenerator.possibleBlackMoves(board, history);
        }

        for (int i = 0; i < childMoves.size(); ++i)
        {
            // In an ideal world we would make an undo fucntion that can undo a move 

            if (color == "White")
            {
                temp_score = minmaxWhite(board.newBoardMove(childMoves.get(i)), 0, depthMax, history);
                //System.out.println(childMoves.get(i) + " has a final score of : " + temp_score);
                if (temp_score > BestScore)
                {
                    BestScore = temp_score;
                    best_move = childMoves.get(i);
                }
            }
            else
            {
                temp_score = minmaxBlack(board.newBoardMove(childMoves.get(i)), 0, depthMax, history);
                if (temp_score < BestScore)
                {
                    BestScore = temp_score;
                    best_move = childMoves.get(i);
                }
            }
        }

        BoardGenerator.drawBoard(currentGame);
        System.out.println("I think the best move is " + best_move + " it has a score of  : " + BestScore);
        return best_move;
    }
}