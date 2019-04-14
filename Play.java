import java.util.ArrayList;

public class Play{
    public static void main(String[] args){

        System.out.println("Hello World!");

        Board board = BoardGenerator.initStandardBoard();


        board.eval();

        ArrayList<String> pastMoves = new ArrayList<>();
        ArrayList<String> whiteMoves = MoveGenerator.possibleWhiteMoves(board, pastMoves);
        ArrayList<String> blackMoves = MoveGenerator.possibleBlackMoves(board, pastMoves);

        System.out.println("~~~" + whiteMoves);
        System.out.println("~~~" + blackMoves);


        BoardGenerator.getBoardKey();

        //Game g = new Game();

        //g.PlayDumbieGame();
    
        //Game g = new Game();
        //g.PlayDumbieGame();
        
    }
}