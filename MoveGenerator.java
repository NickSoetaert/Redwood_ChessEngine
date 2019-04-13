public class MoveGenerator
{



    public static long generateKnightMoves(Board board){

        long WN = 4294967296L;
        //22136263676928

        long dest;

        dest = (WN << 17) | (WN >> 17) | (WN >> 15) | (WN << 15) | (WN >> 10) | (WN << 10) | (WN >> 6) | (WN << 6); 
        
        BoardGenerator.drawPiece(dest);



        return dest;
    }
}