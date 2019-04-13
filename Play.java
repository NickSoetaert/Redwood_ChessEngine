public class Play{
    public static void main(String[] args){

        System.out.println("Hello World!");

        BoardGenerator.initStandardBoard();

        long tmp = BoardGenerator.getBoardKey();
        BoardGenerator.drawPiece(tmp);

        Board b = new Board(4563419136L,0,0,0,0,0,0,524288,0,0,0,0);

        String history = "";

        MoveGenerator.generateWhitePawnMoves(b, history);

    }
}