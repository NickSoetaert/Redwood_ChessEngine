import java.util.Scanner;

public class Game
{
    public AI OurGuy;
    public enum Turn {BLACK, WHITE}
    

    public void PlayDumbieGame()
    {
        Scanner scanner = new Scanner(System.in);
        Board board = BoardGenerator.initStandardBoard();
        OurGuy = new AI(board);
        boolean CheckMate = false;
        Turn curTurn = Turn.WHITE;
        String UCIinput;
        Turn AIturn = Turn.WHITE;
        Turn userTurn = Turn.BLACK;
        String advisedAction = "noneTaken";


        // while (!CheckMate)
        while(true)
        {
            if (AIturn == curTurn)
            {
                BoardGenerator.drawBoard(board);
                advisedAction = OurGuy.Think(MoveGenerator.possibleWhiteMoves(board, "junk for now"));
                OurGuy.act(advisedAction);
                curTurn = userTurn;
            }
            else
            {
                BoardGenerator.drawBoard(board);
                System.out.println("AI action : ".concat(advisedAction));
                curTurn = AIturn;
                System.out.println(curTurn.toString() + "User's turn please type a move : ");
                UCIinput = scanner.nextLine();
                board.move(UCIinput);
            }
        }
    }

    public void PlayAIDumbieGame()
    {
        Scanner scanner = new Scanner(System.in);
        Board board = BoardGenerator.initStandardBoard();
        OurGuy = new AI(board);
        boolean CheckMate = false;
        Turn curTurn = Turn.WHITE;
        String UCIinput;
        Turn AIturn = Turn.WHITE;
        Turn userTurn = Turn.BLACK;
        boolean Watchable = true;
        int Sleep_Time = 2500;
        String advisedAction = "noneTaken";


        // while (!CheckMate)
        while(true)
        {
            if (AIturn == curTurn)
            {
                BoardGenerator.drawBoard(board);
                advisedAction = OurGuy.Think(MoveGenerator.possibleWhiteMoves(board, "junk for now"));
                OurGuy.act(advisedAction);
                curTurn = userTurn;
                BoardGenerator.drawBoard(board);
                if (Watchable)
                {
                    try
                    {
                        Thread.sleep(Sleep_Time);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            else
            {
                BoardGenerator.drawBoard(board);
                advisedAction = OurGuy.Think(MoveGenerator.possibleBlackMoves(board, "junk for now"));
                OurGuy.act(advisedAction);
                curTurn = AIturn;
                BoardGenerator.drawBoard(board);
                if (Watchable)
                {
                    try
                    {
                        Thread.sleep(Sleep_Time);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}