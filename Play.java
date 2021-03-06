import java.util.ArrayList;

public class Play{
    public static void main(String[] args){

        System.out.println("Hello World!");

        BoardGenerator.getBoardKey();

        BoardGenerator.drawPiece(1L);

        
        Board board = BoardGenerator.initStandardBoard();


        board.eval();

        ArrayList<String> pastMoves = new ArrayList<>();
        ArrayList<String> whiteMoves = MoveGenerator.possibleWhiteMoves(board, pastMoves).getAlg();
        ArrayList<String> blackMoves = MoveGenerator.possibleBlackMoves(board, pastMoves).getAlg();

        System.out.println("~~~" + whiteMoves);
        System.out.println("~~~" + blackMoves);


        BoardGenerator.getBoardKey();

        BoardGenerator.drawBoard(board);

        
        
        Game g = new Game();
        g.PlayDumbieGame();
    }
}