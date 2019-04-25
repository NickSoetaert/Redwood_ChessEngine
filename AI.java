import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class AI
{
    public Board currentGame;
    public ColorBool color;
    //private float WhiteTime,BlackTime;
    //UCI takes care of time I think
    
    public String ConfidenceinLastMove; //unclear name

    public ColorBool getColor(){
        return this.color; 
    }

    public void flipColor(){
        if(this.color == ColorBool.WHITE){
            this.color = ColorBool.BLACK;
        } else {
            this.color = ColorBool.WHITE;
        }
    }
    
    public AI(ColorBool c, Board currentGame)
    {
        //set this.color to c
        this.color = c;
        this.currentGame = currentGame;
    }

    public void act(String action)
    {
        currentGame.move(action);
    }

    public int minMax(Board board, int depth, ColorBool playerColor, int maxDepth){
        ArrayList<String> pastMoves = new ArrayList<>();
        if(depth == 0){
            return board.eval();
        } 
        if(playerColor == ColorBool.WHITE){
            int maxEval = -10000;
            Move possibleMoves = new Move(); 
            possibleMoves = MoveGenerator.possibleWhiteMoves(board, pastMoves);
            for(String m : possibleMoves.getAlg()){
                maxEval = minMax(board, depth-1, ColorBool.BLACK, maxDepth);
                board.move(m);
                maxEval = Math.max(maxEval, board.eval());
            }
            return maxEval;

        }
        else{
            int minEval = 10000;
            Move possibleMoves = new Move();
            possibleMoves = MoveGenerator.possibleBlackMoves(board, pastMoves);
            for(String m : possibleMoves.getAlg()){
                minEval = minMax(board, depth-1, ColorBool.WHITE, maxDepth);
                board.move(m);
                minEval = Math.min(minEval, board.eval());
            }
            return minEval;

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

    public String findBestMove(Board board,int depthMax, ArrayList<String> history, String color)
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