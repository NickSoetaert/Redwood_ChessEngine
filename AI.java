import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class AI
{
    public Board currentGame;
    public ColorBool color;
    //private float WhiteTime,BlackTime;
    //UCI takes care of time I think, could be wrong
    
    private int boardEval = 0;

    public String ConfidenceinLastMove; //unclear name
    
    public AI(ColorBool c, Board currentGame)
    {
        //set this.color to c
        this.color = c;
        this.currentGame = currentGame;
    }

    public String Think(ArrayList<String> moves, ColorBool WhoIsThinking, int depth)
    {
        return FindBestMove(currentGame, depth,new ArrayList<String>(), WhoIsThinking.color);
    }
    public void act(String action)
    {
        currentGame.move(action);
    }

    public int minMax(Board board, int depth, ColorBool playerColor, int maxDepth){
        if(depth == 0){
            return boardEval;
        } else {
            //negative infinity
            boardEval = -999999;
            for(String m : moves){
                maxEval = max(boardEval, m.eval);
            }

            return 0;
        }
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

            int best = 1000;
            ArrayList<String> childMoves = MoveGenerator.possibleBlackMoves(board, history).getAlg();

            for (int i = 0; i < childMoves.size(); ++i)
            {
                //Find a way to not need to init new board each time. "Undo" function?
                //Should be very easy to find parent node in a tree...
                //Probably better style than referencing a master list of moves
                //(Only have to read 1 value instead of n values)
                
                best = Math.max(best, minmaxBlack(board.newBoardMove(childMoves.get(i)), depth, maxDepth, history,ismax));
                depth += 1; //depth should equal zero first pass through
            }
            return best;
        }
        else
        {
            int best = -1000;
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
            // In an ideal world we would make an undo fucntion that can undo a move 

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