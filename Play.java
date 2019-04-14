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
    
        //ystem.out.println("am i chaning");
        Game g = new Game();
        g.PlayAIDumbieGame();
        //int ints[] = Board.UCItoIndex("c7c5");

        //System.out.println(ints[0] + " , " + ints[1]);
        //PlayAIDumbieGame
    }
}