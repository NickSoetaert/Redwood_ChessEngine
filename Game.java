import java.util.Scanner;
import java.util.ArrayList;

public class Game
{
    public AI OurGuy;


    //~!~!~!~!~!~!~!~!~!~!~!~
    public ArrayList<String> pastMoves = new ArrayList<>();



    public void PlayDumbieGame()
    {
        Scanner scanner = new Scanner(System.in);
        Board board = BoardGenerator.initStandardBoard();
        OurGuy = new AI(board);
        Turn curTurn = Turn.WHITE;
        String UCIinput;
        Turn AIturn = Turn.WHITE;
        Turn userTurn = Turn.BLACK;
        String advisedAction = "noneTaken";


        // while (!CheckMate)
        int i = 0;
        while(i < 100000)
        {
            int z = 0;
            for (String s : pastMoves){
                System.out.println(z + " " + s);
                z++;
            }
            if (AIturn == curTurn)
            {
                BoardGenerator.drawBoard(board);
                advisedAction = OurGuy.Think(MoveGenerator.possibleWhiteMoves(board, pastMoves).getAlg(),"White",4);
                OurGuy.act(advisedAction);
                pastMoves.add(advisedAction);
                curTurn = userTurn;
            }
            else
            {
                BoardGenerator.drawBoard(board);
                System.out.println(OurGuy.ConfidenceinLastMove);
                System.out.println("WHITE AI action : ".concat(advisedAction));
    
                curTurn = AIturn;
                System.out.println(curTurn.toString() + " user's turn, please type a move : ");
                UCIinput = scanner.nextLine();
                board.move(UCIinput);
                pastMoves.add(UCIinput);
                
            }
            i++;
        }
        scanner.close();
    }

    public void PlayAIDumbieGame()
    {
        Scanner scanner = new Scanner(System.in);
        Board board = BoardGenerator.initStandardBoard();
        OurGuy = new AI(board);
        Turn curTurn = Turn.WHITE;
        Turn AIturn = Turn.WHITE;
        Turn userTurn = Turn.BLACK;
        boolean Watchable = true;
        int Sleep_Time = 1000;
        String advisedAction = "noneTaken";


        // while (!CheckMate)
        int i = 0;
        while(i < 1000000)
        {

            if (AIturn == curTurn)
            {
                BoardGenerator.drawBoard(board);
                advisedAction = OurGuy.Think(MoveGenerator.possibleWhiteMoves(board, pastMoves).getAlg(),"White",4);
                OurGuy.act(advisedAction);
                curTurn = userTurn;
                System.out.println(OurGuy.ConfidenceinLastMove);
                System.out.println("WHITE AI action : ".concat(advisedAction));
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
                advisedAction = OurGuy.Think(MoveGenerator.possibleBlackMoves(board, pastMoves).getAlg(),"Black",5);
                OurGuy.act(advisedAction);
                curTurn = AIturn;
                System.out.println(OurGuy.ConfidenceinLastMove);
                System.out.println("BLACK AI action : ".concat(advisedAction));
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
            i++;
        }
        scanner.close();
    }
}