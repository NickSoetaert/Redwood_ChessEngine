public class Board
{   
    private long WP=0L, WN=0L, WB=0L, WR=0L, WQ=0L, WK=0L, BP=0L, BN=0L, BB=0L, BR=0L, BQ=0L, BK=0L;

    // this is pretty st
    public Board(long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK)
    {
        this.WP = WP;
        this.WN = WN;
        this.WB = WB;
        this.WR = WR;
        this.WQ = WQ;
        this.WK = WK;
        this.BP = BP;
        this.BR = BR;
        this.BN = BN;
        this.BB = BB;
        this.BQ = BQ;
        this.BK = BK;
    }



    public long get(BitBoardEnum bitBoard)
    {
        switch(bitBoard)
        {
            case WP:
                return WP;
            case WN:
                return WN;
            case WB:
                return WB;
            case WR:
                return WR;
            case WQ:
                return WQ;
            case WK:
                return WK;
            case BP:
                return BP;
            case BN:
                return BN;
            case BB:
                return BB;
            case BR:
                return BR;
            case BQ:
                return BQ;
            case BK:
                return BK;
            default:
                return 0L; 
        }
    }

    public void set(BitBoardEnum board, long val)
    {
        switch(board)
        {
            case WP:
                WP = val;
                break;
            case WN:
                WN = val;
                break;
            case WB:
                WB = val;
                break;
            case WR:
                WR = val;
                break;
            case WQ:
                WQ = val;
                break;
            case WK:
                WK = val;
                break;
            case BP:
                BP = val;
                break;
            case BN:
                BN = val;
                break;
            case BB:
                BB = val;
                break;
            case BR:
                BR = val;
                break;
            case BQ:
                BQ = val;
                break;
            case BK:
                BK = val;
                break;
            default:
                break; 
        }
    }


    // moves a peice on local board
    public void move(long rowStart,long colStart, long rowEnd, long colEnd)
    {
        // does nothing at this time
    }
    
    // creates a new board that is a copy of current board but with move made
    public Board newBoardMove(long rowStart,long colStart, long rowEnd, long colEnd)
    {
        Board newBoard = new Board();
    }


    public long GetWhitePieces()
    {
        return (WP|WN|WB|WR|WQ|WK);
    }

    public long GetBlackPieces()
    {
        return (BP|BN|BB|BR|BQ|BK);
    }


    /*public BitBoardEnum getPeiceType(int index)
    {
        if(((WP>>i) & 1) == 1)
    }*/

}