public class Play{
    public static void main(String[] args){

        System.out.println("Hello World!");

        Board board = BoardGenerator.initStandardBoard();


        MoveGenerator.generateVerticalHorizontalMoves(board, 42);
        MoveGenerator. generateDiagonalMoves(board, 42);



    }
}