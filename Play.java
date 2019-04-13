public class Play{
    public static void main(String[] args){

        System.out.println("Hello World!");

        BoardGenerator.initStandardBoard();

        long tmp = BoardGenerator.getBoardKey();
        BoardGenerator.drawPiece(tmp);

        Board b = new Board();

        MoveGenerator.generateKnightMoves(b);

    }
}