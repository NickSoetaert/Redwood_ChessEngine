import java.util.ArrayList;

public class Play{
    public static void main(String[] args){

        System.out.println("Hello World!");

        Board board = BoardGenerator.initStandardBoard();

        ArrayList<String> pastMoves = new ArrayList<>();
        ArrayList<String> whiteMoves = MoveGenerator.possibleWhiteMoves(board, pastMoves);
        ArrayList<String> blackMoves = MoveGenerator.possibleBlackMoves(board, pastMoves);

        System.out.println("~~~" + whiteMoves);
        System.out.println("~~~" + blackMoves);

        //Game g = new Game();

        //g.PlayDumbieGame();
    
        Game g = new Game();
        g.PlayDumbieGame();
        
    }
}