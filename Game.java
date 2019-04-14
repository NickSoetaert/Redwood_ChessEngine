import java.util.Scanner;

public class Game
{
    public enum Turn {BLACK, WHITE}
    

    public void PlayDumbieGame()
    {
        Scanner scanner = new Scanner(System.in);
        Board board = BoardGenerator.initStandardBoard();
        boolean CheckMate = false;
        Turn curTurn = Turn.WHITE;
        String UCIinput;

        // while (!CheckMate)
        while(true)
        {
            System.out.println("is this working");
            BoardGenerator.drawBoard(board);
            System.out.println(curTurn.toString() + "'s turn please type a move : ");

            UCIinput = scanner.nextLine();
            int test[] = Board.UCItoIndex(UCIinput);
            System.out.println(test[0] + ", " + test[1] );
            board.move(UCIinput);

            if (curTurn == Turn.BLACK)
            {
                curTurn = Turn.WHITE;
            }
            else
            {
                curTurn = Turn.BLACK;
            }

        }
    }
}