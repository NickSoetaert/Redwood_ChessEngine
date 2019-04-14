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



}