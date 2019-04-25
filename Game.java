import java.util.Scanner;
import java.util.ArrayList;

public class Game
{
    //public AI OurGuy;
    public ArrayList<String> pastMoves = new ArrayList<>();

    //robot vs robot
    public void RobotDuel(){

        Scanner scanner = new Scanner(System.in);
        Board board = BoardGenerator.initStandardBoard();
        AI robot = new AI(ColorBool.WHITE, board); //changes its own color move on move to move
        ColorBool whoseTurn = ColorBool.WHITE;

        boolean slowDownMoves = false; //set to true to use sleep for some debug purposes
        int Sleep_Time = 2000;
        String advisedAction = "NOT INITIATED";


        // while (!CheckMate)
        int i = 0;
        while(i < 1000000){

            //advisedAction = robot.Think(MoveGenerator.possibleWhiteMoves(board, pastMoves).getAlg(), robot.getColor(),6);
            //robot.act(advisedAction);
            robot.flipColor();
            System.out.println(robot.ConfidenceinLastMove);

            BoardGenerator.drawBoard(board);

            if (slowDownMoves)
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
    /*
    public void PlayGame()
    {
        Scanner scanner = new Scanner(System.in);
        Board board = BoardGenerator.initStandardBoard();
        OurGuy = new AI(board);
        ColorBool curTurn = ColorBool.WHITE;
        String UCIinput;
        ColorBool AIturn;// = Turn.WHITE;
        ColorBool userTurn;// = Turn.BLACK;
        String advisedAction = "noneTaken";//rename

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
                System.out.println(MoveGenerator.possibleWhiteMoves(board, pastMoves));
                BoardGenerator.drawBoard(board);

                //System.out.println(MoveGenerator.possibleWhiteMoves(board, pastMoves));
                advisedAction = OurGuy.Think(MoveGenerator.possibleWhiteMoves(board, pastMoves).getAlg(), "White", 3);

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
    */
    /*
    public void PlayAIDumbieGame()
    {
        Scanner scanner = new Scanner(System.in);
        Board board = BoardGenerator.initStandardBoard();
        OurGuy = new AI(board);
        ColorBool curTurn = Turn.WHITE;
        ColorBool AIturn = Turn.WHITE;
        ColorBool userTurn = Turn.BLACK;
        boolean Watchable = false;
        int sleepTime = 2000;
        String advisedAction = "noneTaken";


        // while (!CheckMate)
        int i = 0;
        while(i < 1000000)
        {

            if (AIturn == curTurn)
            {

                advisedAction = OurGuy.Think(MoveGenerator.possibleWhiteMoves(board, pastMoves).getAlg(),"White",6);
                OurGuy.act(advisedAction);
                curTurn = userTurn;
                System.out.println(OurGuy.ConfidenceinLastMove);
                System.out.println("WHITE AI action : ".concat(advisedAction));
                BoardGenerator.drawBoard(board);
                if (Watchable)
                {
                    try
                    {
                        Thread.sleep(sleepTime);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            else
            {

                advisedAction = OurGuy.Think(MoveGenerator.possibleBlackMoves(board, pastMoves).getAlg(),"Black",6);
                OurGuy.act(advisedAction);
                curTurn = AIturn;
                System.out.println(OurGuy.ConfidenceinLastMove);
                System.out.println("BLACK AI action : ".concat(advisedAction));
                BoardGenerator.drawBoard(board);
                if (Watchable)
                {
                    try
                    {
                        Thread.sleep(sleepTime);
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
    */

}