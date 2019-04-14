import java.util.ArrayList;
import java.util.Random;




public class AI
{
    public Board currentGame;
    public float WhiteTime,BlackTime;
    public Color ourColor;
    
    

    public AI(Board currentGame, Color ourColor)
    {
        this.currentGame = currentGame;
        this.ourColor = ourColor;
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





    public static int minmaxWhiteCalc(Board board, Color colorCheck, int depth, int MaxDepth, ArrayList<String> History)
    {
        int score = board.eval();

        if (depth >= MaxDepth)
        {
            return score;
        }

        if (depth % 2 == 0)
        {
            int best = -1000;
            ArrayList<String> childrenBoard = MoveGenerator.possibleWhiteMoves(board, "Replace me with real data");

        }
        else
        {
            int best = 1000;

        }


        ArrayList<String> children = MoveGenerator.possibleWhiteMoves(board, pastMove)
    }



}