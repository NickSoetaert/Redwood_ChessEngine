public class MoveGenerator
{
    //A, B file: 217020518514230019L
    //G, H file: -4557430888798830400L

    //1st to 8th rank
    static long ranks[] = {
        -72057594037927936L,
        71776119061217280L,
        280375465082880L,
        1095216660480L,
        4278190080L,
        16711680L,
        65280L,
        255L

        
    };


    static long files[] = {
        72340172838076673L,
        144680345676153346L,
        289360691352306692L,
        578721382704613384L,
        1157442765409226768L,
        2314885530818453536L,
        4629771061636907072L,
        -9187201950435737472L
    };



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


        int last_Move_Start = 57, last_Move_End = 39;
        long moves = 0L;
        long WP = board.get(BitBoardEnum.WP);
        long blackPieces = board.GetBlackPieces();
        long whitePieces = board.GetWhitePieces();
        long empty = ~(blackPieces | whitePieces);

        //Capture left
        moves = (WP >> 9)&~ranks[7]&~files[7]&blackPieces;

        //Capture right
        moves = moves | (WP >> 7)&~ranks[7]&~files[0]&blackPieces;

        //Forward 1 square
        moves = moves | (WP >> 8)&~ranks[7]&empty;

        //Forward 2 squares
        moves = moves | (WP >> 16)&empty&(empty >> 8);

        BoardGenerator.drawPiece(moves);

        return 0;
    }

    public static long generateWhiteRookMoves(Board board, int startSquare){

        long occupied = board.GetBlackPieces() | board.GetWhitePieces();

        long binaryStartSquare = 1L << startSquare;
        BoardGenerator.drawPiece(startSquare);
        int rank = startSquare / 8;
        int file = startSquare % 8;

        //g5
        System.out.println("~!~!~!~!~");

        System.out.println("Rank: " + rank); //rank is horizontal
        System.out.println("File: " + file); //file is vertical

        //BoardGenerator.drawPiece(ranks[rank]);
        //BoardGenerator.drawPiece(files[file]);

        long verticalMoves = (occupied - (2 * binaryStartSquare)) ^ 
                              Long.reverse(Long.reverse(occupied) - 2 * Long.reverse(binaryStartSquare));
        verticalMoves = verticalMoves&files[file];


        long horizontalMoves = ((occupied & ranks[rank]) - (2 * binaryStartSquare)) ^ 
                                Long.reverse(Long.reverse(occupied&ranks[rank]) - (2 * Long.reverse(startSquare)));
        
        horizontalMoves = horizontalMoves&ranks[rank];

        BoardGenerator.drawPiece(ranks[0]);

        BoardGenerator.drawPiece(horizontalMoves);// ^ verticalMoves);

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