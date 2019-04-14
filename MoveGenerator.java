import java.util.ArrayList;

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

    //a to h file
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
        1L,                     //a8-a8
        258L,                   //a7-b8
        66052L,                 //a6-c8
        16909320L,              //a5-d8
        4328785936L,            //a4-e8
        1108169199648L,         //a3-f8
        283691315109952L,       //a2-g8
        72624976668147840L,     //a1-h8
        145249953336295424L,    //b1-h7
        290499906672525312L,    //c1-h6
        580999813328273408L,    //d1-h5
        1161999622361579520L,   //e1-h4
        2323998145211531264L,   //f1-h3
        4647714815446351872L,   //g1-h2
        -9223372036854775808L   //h1-h1
    };

    // top right to bottom left
    static long antiDiaginalMasks[] = {

        128L,                   //h8-h8
        32832L,                 //h7-g8
        8405024L,               //h6-f8
        2151686160L,            //h5-e8
        550831656968L,          //h4-d8
        141012904183812L,       //h3-c8
        36099303471055874L,     //h2-b8
        -9205322385119247871L,  //h1-a8
        4620710844295151872L,   //g1-a7
        2310355422147575808L,   //f1-a6
        1155177711073755136L,   //e1-a5
        577588855528488960L,    //d1-a4
        288794425616760832L,    //c1-a3
        144396663052566528L,    //b1-a2
        72057594037927936L      //a1-a1
    };

    private static char fileToChar(int file){
        switch(file){
            case 0: return 'a';
            case 1: return 'b';
            case 2: return 'c';
            case 3: return 'd';
            case 4: return 'e';
            case 5: return 'f';
            case 6: return 'g';
            case 7: return 'h';
            default: return ' ';
        }
    }

    public static ArrayList<String> possibleWhiteMoves(Board board, ArrayList<String> pastMoves){

        ArrayList<String> legalMoves = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        long uncapturablePieces = board.GetWhitePieces() | board.get(BitBoardEnum.BK);

        temp = possibleWhitePawnMoves(board, pastMoves);
        for(String s : temp){
            legalMoves.add(s);
        }

        temp = possibleKnightMoves(board, uncapturablePieces, board.get(BitBoardEnum.WN));
        for(String s : temp){
            legalMoves.add(s);
        }

        temp = possibleBishopMoves(board, uncapturablePieces, board.get(BitBoardEnum.WB));
        for(String s : temp){
            legalMoves.add(s);
        }
        
        temp = possibleRookMoves(board, uncapturablePieces, board.get(BitBoardEnum.WR));
        for(String s : temp){
            legalMoves.add(s);
        }

       temp = possibleQueenMoves(board, uncapturablePieces, board.get(BitBoardEnum.WQ)); 
        for(String s : temp){
           legalMoves.add(s);
        }

        temp = possibleKingMoves(board, uncapturablePieces, board.get(BitBoardEnum.WK));
        for(String s : temp){
            legalMoves.add(s);
        }
        return legalMoves;
    }

    public static ArrayList<String> possibleBlackMoves(Board board, ArrayList<String> pastMoves){

        ArrayList<String> legalMoves = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        long uncapturablePieces = board.GetBlackPieces() | board.get(BitBoardEnum.WK);

        temp = possibleBlackPawnMoves(board, pastMoves);
        for(String s : temp){
            legalMoves.add(s);
        }

        temp = possibleKnightMoves(board, uncapturablePieces, board.get(BitBoardEnum.BN));
        for(String s : temp){
            legalMoves.add(s);
        }

        temp = possibleBishopMoves(board, uncapturablePieces, board.get(BitBoardEnum.BB));
        for(String s : temp){
            legalMoves.add(s);
        }
        
        temp = possibleRookMoves(board, uncapturablePieces, board.get(BitBoardEnum.BR));
        for(String s : temp){
            legalMoves.add(s);
        }

        temp = possibleQueenMoves(board, uncapturablePieces, board.get(BitBoardEnum.BQ));
        for(String s : temp){
           legalMoves.add(s);
        }

        temp = possibleKingMoves(board, uncapturablePieces, board.get(BitBoardEnum.BK));
        for(String s : temp){
            legalMoves.add(s);
        }

        System.out.println("Legal black moves: " + legalMoves);
        return legalMoves;
    }

    public static ArrayList<String> possibleKnightMoves(Board b, long uncapturablePieces, long N){

        ArrayList<String> algebraicLegalMoves = new ArrayList<String>();
        String singleAlgebraicMove = "";

        long knightMoveShape = 43234889994L;
        long possibleMoves;
        long currKnight = N;
        currKnight=N&~(N-1); //Gets FIRST occurance of a knight

        while(currKnight != 0)
        {
            int knightLocation = Long.numberOfTrailingZeros(currKnight);

            //If the knight is in the upper left corner, but far over enough to not go off the board
            //18 represents the c6 square, furthest in the corner the knight can move safely up and to the left
            if (knightLocation>18)
            {
                //we are safe to shift right(and down)
                possibleMoves = knightMoveShape<<(knightLocation-18);
            }
            else {
                possibleMoves = knightMoveShape>>(18-knightLocation);
            }

            //If the knight is on the left side of the board,
            //  then we must eliminate any squares on the g or h files (because of wrapping)
            if(knightLocation % 8 < 4){
                //Take away moves that wrapped around the board, or land on uncapable pieces
                possibleMoves = possibleMoves & ~(files[6] | files[7]);
                possibleMoves = possibleMoves & ~uncapturablePieces;
            }
            //If knight is on right side of board,
            //  eliminate any squares on a or b files
            else {
                //Take away moves that wrapped around the board, or land on uncapable pieces
                possibleMoves = possibleMoves & ~(files[0] | files[1]);
                possibleMoves = possibleMoves & ~uncapturablePieces;
            }

            //Gets a SINGLE possible move
            long singlePossibleMove = possibleMoves & ~(possibleMoves-1);
            //Note this is a nested loop.
            //While there are still moves left for this specific knight...
            while (singlePossibleMove != 0)
            {
                int index = Long.numberOfTrailingZeros(singlePossibleMove);

                singleAlgebraicMove = "" + fileToChar(knightLocation%8) + (8 - knightLocation/8)
                                         + fileToChar(index%8) + (8 - index/8);
                algebraicLegalMoves.add(singleAlgebraicMove);

                //We processed a possible move, so remove a single possible move from possible moves
                possibleMoves = possibleMoves & ~singlePossibleMove;
                //and set single possible move to next possible move
                singlePossibleMove = possibleMoves & ~(possibleMoves-1);
            }
            //update current knight to the next knight
            N&=~currKnight;
            currKnight=N&~(N-1);
        }
        return algebraicLegalMoves; 
        
    }

    public static ArrayList<String> possibleBishopMoves(Board b, long uncapturablePieces, long B){
        ArrayList<String> algebraicLegalMoves = new ArrayList<String>();
        String singleAlgebraicMove = "";
        long possibleMoves;
        long currBishop;
        currBishop=B&~(B-1); //Gets FIRST occurance of a Bishop

        while(currBishop != 0){
            int bishopLocation = Long.numberOfTrailingZeros(currBishop);

            possibleMoves = generateDiagonalMoves(b, bishopLocation) & ~uncapturablePieces;

            //Gets a SINGLE possible move
            long singlePossibleMove = possibleMoves & ~(possibleMoves-1);

            //Note this is a nested loop.
            //While there are still moves left for this specific bishop...
            while (singlePossibleMove != 0)
            {
                int index = Long.numberOfTrailingZeros(singlePossibleMove);

                singleAlgebraicMove = "" + fileToChar(bishopLocation%8) + (8 - bishopLocation/8)
                                         + fileToChar(index%8) + (8 - index/8);
                algebraicLegalMoves.add(singleAlgebraicMove);

                //We processed a possible move, so remove a single possible move from possible moves
                possibleMoves = possibleMoves & ~singlePossibleMove;
                //and set single possible move to next possible move
                singlePossibleMove = possibleMoves & ~(possibleMoves-1);
            }

            //update current bishop to the next bishop
            B &= ~currBishop;
            currBishop=B&~(B-1);
        }
        return algebraicLegalMoves;
    }

    public static ArrayList<String> possibleRookMoves(Board b, long uncapturablePieces, long R){
        ArrayList<String> algebraicLegalMoves = new ArrayList<String>();
        String singleAlgebraicMove = "";
        long possibleMoves;
        long currRook;
        currRook=R&~(R-1); //Gets FIRST occurance of a rook

        while(currRook != 0){
            int rookLocation = Long.numberOfTrailingZeros(currRook);

            possibleMoves = generateVerticalHorizontalMoves(b, rookLocation) & ~uncapturablePieces;

            //Gets a SINGLE possible move
            long singlePossibleMove = possibleMoves & ~(possibleMoves-1);

            //Note this is a nested loop.
            //While there are still moves left for this specific bishop...
            while (singlePossibleMove != 0)
            {
                int index = Long.numberOfTrailingZeros(singlePossibleMove);

                singleAlgebraicMove = "" + fileToChar(rookLocation%8) + (8 - rookLocation/8)
                                         + fileToChar(index%8) + (8 - index/8);
                algebraicLegalMoves.add(singleAlgebraicMove);

                //We processed a possible move, so remove a single possible move from possible moves
                possibleMoves = possibleMoves & ~singlePossibleMove;
                //and set single possible move to next possible move
                singlePossibleMove = possibleMoves & ~(possibleMoves-1);
            }

            //update current rook to the next rook
            R &= ~currRook;
            currRook=R&~(R-1);
        }
        return algebraicLegalMoves;
    }

    public static ArrayList<String> possibleQueenMoves(Board b, long uncapturablePieces, long Q){
        ArrayList<String> algebraicLegalMoves = new ArrayList<String>();
        String singleAlgebraicMove = "";
        long possibleMoves;
        long currQueen;
        currQueen=Q&~(Q-1); //Gets FIRST occurance of a queen

        while(currQueen != 0){
            int queenLocation = Long.numberOfTrailingZeros(currQueen);

            possibleMoves = (generateVerticalHorizontalMoves(b, queenLocation) | 
                             generateDiagonalMoves(b, queenLocation)) & 
                             ~uncapturablePieces;

            //Gets a SINGLE possible move
            long singlePossibleMove = possibleMoves & ~(possibleMoves-1);

            //Note this is a nested loop.
            //While there are still moves left for this specific bishop...
            while (singlePossibleMove != 0)
            {
                int index = Long.numberOfTrailingZeros(singlePossibleMove);

                singleAlgebraicMove = "" + fileToChar(queenLocation%8) + (8 - queenLocation/8)
                                         + fileToChar(index%8) + (8 - index/8);
                algebraicLegalMoves.add(singleAlgebraicMove);

                //We processed a possible move, so remove a single possible move from possible moves
                possibleMoves = possibleMoves & ~singlePossibleMove;
                //and set single possible move to next possible move
                singlePossibleMove = possibleMoves & ~(possibleMoves-1);
            }

            //update current queen to the next queen
            Q &= ~currQueen;
            currQueen=Q&~(Q-1);
        }
        return algebraicLegalMoves;
    }

    public static ArrayList<String> possibleKingMoves(Board b, long uncapturablePieces, long K){
    
        ArrayList<String> algebraicLegalMoves = new ArrayList<String>();
        String singleAlgebraicMove = "";

        long kingMoveShape = 460039L;
        long possibleMoves;
        long currKing = K;
        currKing=K&~(K-1); //Gets FIRST occurance of a king

        int kingLocation = Long.numberOfTrailingZeros(currKing);

        //9 represents the c6 square, furthest in the corner the king can move safely up and to the left
        if (kingLocation>9)
        {
            //we are safe to shift right(and down)
            possibleMoves = kingMoveShape<<(kingLocation-9);
        }
        else {
            possibleMoves = kingMoveShape>>(9-kingLocation);
        }

        if(kingLocation % 8 < 4){
            //Take away moves that wrapped around the board, or land on uncapable pieces
            possibleMoves = possibleMoves & ~(files[7]);
            possibleMoves = possibleMoves & ~uncapturablePieces;
        }
        else {
            //Take away moves that wrapped around the board, or land on uncapable pieces
            possibleMoves = possibleMoves & ~(files[0]);
            possibleMoves = possibleMoves & ~uncapturablePieces;
        }

        long singlePossibleMove = possibleMoves & ~(possibleMoves-1);
        while (singlePossibleMove != 0)
        {
            int index = Long.numberOfTrailingZeros(singlePossibleMove);

            singleAlgebraicMove = "" + fileToChar(kingLocation%8) + (8 - kingLocation/8)
                                        + fileToChar(index%8) + (8 - index/8);
            algebraicLegalMoves.add(singleAlgebraicMove);

            //We processed a possible move, so remove a single possible move from possible moves
            possibleMoves = possibleMoves & ~singlePossibleMove;
            //and set single possible move to next possible move
            singlePossibleMove = possibleMoves & ~(possibleMoves-1);
        }
        //update current king to the next king
        K&=~currKing;
        currKing=K&~(K-1);
        
        return algebraicLegalMoves; 
    }



    public static ArrayList<String> possibleWhitePawnMoves(Board b, ArrayList<String> pastMoves){

        // These should be actually given rather then hardcoded
        int last_Move_Start = 57, last_Move_End = 39;

        long WP = b.get(BitBoardEnum.WP);
        long capturablePieces = b.GetBlackPieces() ^ b.get(BitBoardEnum.BK);
        long whitePieces = b.GetWhitePieces();
        long blackPieces = b.GetBlackPieces();
        long empty = ~(blackPieces | whitePieces);

        ArrayList<String> algebraicLegalMoves = new ArrayList<String>();
        String singleAlgebraicMove = "";
        long possibleMoves;
        long currPawn = b.get(BitBoardEnum.WP);

        currPawn=WP&~(WP-1); //Gets FIRST occurance of a pawn

        while(currPawn != 0){
            int pawnLocation = Long.numberOfTrailingZeros(currPawn);

            //Capture left
            possibleMoves = (currPawn >> 9)&~files[7]&capturablePieces;

            //Capture right
            possibleMoves = possibleMoves | (currPawn >> 7)&~files[0]&capturablePieces;

            //Forward 1 square
            possibleMoves = possibleMoves | (currPawn >> 8)&~ranks[7]&empty;

            //Forward 2 squares
            possibleMoves = possibleMoves | (currPawn >> 16)&empty&(empty >> 8) & ranks[3];

            //En passant
            if(pastMoves.size() >= 1){

                String prevMove = pastMoves.get(pastMoves.size()-1);
                System.out.println("prev[0] = " + prevMove);
                int[] prev = Board.UCItoIndex(prevMove);
                if(Math.abs(prev[0] - prev[1]) == 16){
                    System.out.println(prev[0] + " " + prev[1]);
                    
                    //en passant from right
                    possibleMoves = possibleMoves | ((((currPawn>>1)&b.get(BitBoardEnum.BP)&ranks[4]&(files[Board.getFile(prev[0])])))>>8);
                    //BoardGenerator.drawPiece(files[Board.getFile(prev[0])]);
                    //System.out.println(files[0]);
                    
                    //en passant from left

                    possibleMoves = possibleMoves | ((((currPawn<<1)&b.get(BitBoardEnum.BP)&ranks[4]&files[Board.getFile(prev[0])]))>>8);
                    //BoardGenerator.drawPiece(possibleMoves);
                }
            }
            //Gets a SINGLE possible move
            long singlePossibleMove = possibleMoves & ~(possibleMoves-1);

            //Note this is a nested loop.
            //While there are still moves left for this specific pawn...
            while (singlePossibleMove != 0)
            {
                int index = Long.numberOfTrailingZeros(singlePossibleMove);

                singleAlgebraicMove = "" + fileToChar(pawnLocation%8) + (8 - pawnLocation/8)
                                         + fileToChar(index%8) + (8 - index/8);
                algebraicLegalMoves.add(singleAlgebraicMove);

                //We processed a possible move, so remove a single possible move from possible moves
                possibleMoves = possibleMoves & ~singlePossibleMove;
                //and set single possible move to next possible move
                singlePossibleMove = possibleMoves & ~(possibleMoves-1);
            }

            //update current pawn to the next pawn
            WP &= ~currPawn;
            currPawn = WP & ~(WP-1);
        }
        return algebraicLegalMoves;
    }

    public static ArrayList<String> possibleBlackPawnMoves(Board b, ArrayList<String> pastMoves){

        // These should be actually given rather then hardcoded
        int last_Move_Start = 57, last_Move_End = 39;

        long BP = b.get(BitBoardEnum.BP);
        long capturablePieces = b.GetWhitePieces() ^ b.get(BitBoardEnum.WK);
        long whitePieces = b.GetWhitePieces();
        long blackPieces = b.GetBlackPieces();
        long empty = ~(blackPieces | whitePieces);

        ArrayList<String> algebraicLegalMoves = new ArrayList<String>();
        String singleAlgebraicMove = "";
        long possibleMoves;
        long currPawn = b.get(BitBoardEnum.BP);

        currPawn=BP&~(BP-1); //Gets FIRST occurance of a pawn

        while(currPawn != 0){
            int pawnLocation = Long.numberOfTrailingZeros(currPawn);

            //Capture left
            possibleMoves = (currPawn << 9)&~files[7]&capturablePieces;

            //Capture right
            possibleMoves = possibleMoves | (currPawn << 7)&~files[0]&capturablePieces;

            //Forward 1 square
            possibleMoves = possibleMoves | (currPawn << 8)&~ranks[7]&empty;

            //Forward 2 squares
            possibleMoves = possibleMoves | (currPawn << 16)&empty&(empty >> 8) & ranks[4];

            if (Math.abs(last_Move_Start - last_Move_End) == 18)
            {
                // En Passant from right
                possibleMoves = possibleMoves | (((currPawn<<1)&b.get(BitBoardEnum.WP)&ranks[5]&files[last_Move_Start%8])>>8);
                
                // En Passant from left 
                possibleMoves = possibleMoves | (((currPawn<<1)&b.get(BitBoardEnum.WP)&ranks[5]&files[last_Move_Start%8])>>8);
            }

            //Gets a SINGLE possible move
            long singlePossibleMove = possibleMoves & ~(possibleMoves-1);

            //Note this is a nested loop.
            //While there are still moves left for this specific pawn...
            while (singlePossibleMove != 0)
            {
                int index = Long.numberOfTrailingZeros(singlePossibleMove);

                singleAlgebraicMove = "" + fileToChar(pawnLocation%8) + (8 - pawnLocation/8)
                                         + fileToChar(index%8) + (8 - index/8);
                algebraicLegalMoves.add(singleAlgebraicMove);

                //We processed a possible move, so remove a single possible move from possible moves
                possibleMoves = possibleMoves & ~singlePossibleMove;
                //and set single possible move to next possible move
                singlePossibleMove = possibleMoves & ~(possibleMoves-1);
            }

            //update current pawn to the next pawn
            BP &= ~currPawn;
            currPawn = BP & ~(BP-1);
        }
        return algebraicLegalMoves;
    }

    private static long generateVerticalHorizontalMoves(Board board, int startSquare){

        long occupied = board.GetBlackPieces() | board.GetWhitePieces();
        long binaryStartSquare = 1L << startSquare;

        int rank = 7 - startSquare / 8;
        int file = startSquare % 8;

        long horizontalMoves = ((occupied - 2 * binaryStartSquare) ^
                              Long.reverse(Long.reverse(occupied) - 2 * Long.reverse(binaryStartSquare)));
        horizontalMoves = horizontalMoves & ranks[rank];
        
        long verticalMoves = ((occupied & files[file]) - (2 *binaryStartSquare)) ^
                               Long.reverse(Long.reverse(occupied&files[file]) - (2 * Long.reverse(binaryStartSquare)));
        verticalMoves = verticalMoves & files[file];

        return(horizontalMoves ^ verticalMoves);
    }

    private static long generateDiagonalMoves(Board board, int startSquare){

        long occupied = board.GetBlackPieces() | board.GetWhitePieces();
        int rank = startSquare / 8;
        int file = startSquare % 8;

        long binaryStartSquare = 1L << startSquare;

        long diagonalMoves = ((occupied & diaginalMasks[rank + file]) - (2 * binaryStartSquare)) ^
                                Long.reverse(Long.reverse(occupied&diaginalMasks[rank + file]) - (2 * Long.reverse(binaryStartSquare)));
        diagonalMoves = diagonalMoves & diaginalMasks[rank + file];

        long antiDiagonalMoves = ((occupied & antiDiaginalMasks[rank + 7 - file]) - (2 * binaryStartSquare)) ^ 
                                Long.reverse(Long.reverse(occupied&antiDiaginalMasks[rank + 7 - file]) - (2 * Long.reverse(binaryStartSquare)));
        antiDiagonalMoves = antiDiagonalMoves & antiDiaginalMasks[rank + 7 - file];

        return(diagonalMoves ^ antiDiagonalMoves);
    }
}