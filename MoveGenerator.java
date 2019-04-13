public class MoveGenerator
{

    //A, B file: 217020518514230019L
    //G, H file: -4557430888798830400L

    static long eighthRank  = 255L;
    static long seventhRank = 65280L;
    static long sixthRank   = 16711680L;
    static long fifthRank   = 4278190080L;
    static long fourthRank  = 1095216660480L;
    static long thirdRank   = 280375465082880L;
    static long secondRank  = 71776119061217280L;
    static long firstRank   = -72057594037927936L;

    static long aFile = 72340172838076673L;
    static long bFile = 144680345676153346L;
    static long cFile = 289360691352306692L;
    static long dFile = 578721382704613384L;
    static long eFile = 1157442765409226768L;
    static long fFile = 2314885530818453536L;
    static long gFile = 4629771061636907072L;
    static long hFile = -9187201950435737472L;






    public static long generateWhitePawnMoves(Board board, String pastMove){


        long moves = 0L;
        long WP = board.get(BitBoardEnum.WP);
        long blackPieces = board.GetBlackPieces();
        System.out.println("~~~~~~~~~~~~~~~~~~~~" + WP);

        //d6 = 524288
        //Capture left
        moves = (WP >> 9)&~eighthRank&~hFile&blackPieces;

        //Capture right
        moves = moves | (WP >> 7)&~eighthRank&~aFile&blackPieces;


        BoardGenerator.drawPiece(moves);

        return 0;
    }


    public static long generateWhiteKnightMoves(Board board){

        long WN = 4294967296L;

        long dest;

        dest = (WN << 17) | (WN >> 17) | (WN >> 15) | (WN << 15) | (WN >> 10) | (WN << 10) | (WN >> 6) | (WN << 6); 
        
        BoardGenerator.drawPiece(dest);



        return dest;
    
    }
}