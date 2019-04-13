public class Play{
    public static void main(String[] args){

        System.out.println("Hello World!");

        Board board = BoardGenerator.initStandardBoard();



        MoveGenerator.generateWhiteRookMoves(board, 40);


    }
}