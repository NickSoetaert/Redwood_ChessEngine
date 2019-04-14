import java.util.ArrayList;

public class Play{
    public static void main(String[] args){

        System.out.println("Hello World!");

        Board board = BoardGenerator.initStandardBoard();


        //MoveGenerator.generateVerticalHorizontalMoves(board, 43);
        //MoveGenerator.generateDiagonalMoves(board, 43);

        ArrayList<String> x = MoveGenerator.possibleWhiteMoves(board, "");
        //ArrayList<String> y = MoveGenerator.possibleBlackMoves(board, "");
        System.out.println("~~~" + x);
        

    }
}