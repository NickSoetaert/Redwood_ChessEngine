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

    static long whiteSide = firstRank | secondRank | thirdRank | fourthRank;
    static long blackSide = fifthRank | sixthRank | seventhRank | eighthRank;

    static long aFile = 72340172838076673L;
    static long bFile = 144680345676153346L;
    static long cFile = 289360691352306692L;
    static long dFile = 578721382704613384L;
    static long eFile = 1157442765409226768L;
    static long fFile = 2314885530818453536L;
    static long gFile = 4629771061636907072L;
    static long hFile = -9187201950435737472L;


    // diaginal masks from top left to bottom right
    static long diaginalMasks[] = {
        1L,
        258L,
        66052L,
        16909320L,
        4328785936L,
        1108169199648L,
        283691315109952L,
        72624976668147840L,
        145249953336295424L,
        290499906672525312L,
        580999813328273408L,
        1161999622361579520L,
        2323998145211531264L,
        4647714815446351872L,
        -9223372036854775808L
    };

    // top right to bottom left
    static long antiDiaginalMasks[] = {

        128L,
        32832L,
        8405024L,
        2151686160L,
        550831656968L,
        141012904183812L,
        36099303471055874L,
        -9205322385119247871L,
        4620710844295151872L,
        2310355422147575808L,
        1155177711073755136L,
        577588855528488960L,
        288794425616760832L,
        144396663052566528L,
        72057594037927936L
    };




    public static long generateWhitePawnMoves(Board board, String pastMove){


        long moves = 0L;
        long WP = board.get(BitBoardEnum.WP);
        long blackPieces = board.GetBlackPieces();
        long whitePieces = board.GetWhitePieces();
        long empty = ~(blackPieces | whitePieces);

        //Capture left
        moves = (WP >> 9)&~eighthRank&~hFile&blackPieces;

        //Capture right
        moves = moves | (WP >> 7)&~eighthRank&~aFile&blackPieces;

        //Forward 1 square
        moves = moves | (WP >> 8)&~eighthRank&empty;

        //Forward 2 squares
        moves = moves | (WP >> 16)&empty&(empty >> 8);

        BoardGenerator.drawPiece(moves);

        return 0;
    }

    public static long generateWhiteRookMoves(Board board, int startSquare){

        long binaryStartSquare = 1L << startSquare;
        int rank = 8 - startSquare / 8;
        int file = startSquare % 8;

         

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