import java.util.ArrayList;

public class Play{
    public static void main(String[] args){

        System.out.println("Hello World!");
        System.out.println("Redwood ChessEngine 1.2.");


        
        Board board = BoardGenerator.initStandardBoard();
        BoardGenerator.drawBoard(board);
        board.eval();
        

        ArrayList<String> pastMoves = new ArrayList<>();
        ArrayList<String> whiteMoves = MoveGenerator.possibleWhiteMoves(board, pastMoves).getAlg();
        ArrayList<String> blackMoves = MoveGenerator.possibleBlackMoves(board, pastMoves).getAlg();

        //System.out.println("~~~" + whiteMoves);
        //System.out.println("~~~" + blackMoves);


        //BoardGenerator.getBoardKey();

        //BoardGenerator.drawBoard(board);

        Game g = new Game();

        
        System.out.println("Would you like to:");
        System.out.println("[1] Play vs AI?");
        System.out.println("[2] Watch robots duel?");
        System.out.println("[3] Debug the board?\n");

        
        g.RobotDuel();


        
    }
}