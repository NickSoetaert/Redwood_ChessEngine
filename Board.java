public class Board
{   
    private long WP=0L, WN=0L, WB=0L, WR=0L, WQ=0L, WK=0L, BP=0L, BN=0L, BB=0L, BR=0L, BQ=0L, BK=0L;

    // constructor
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
    public void move(int startIndex,int finishIndex)
    {
        // flipps bits on origin and destination bits
        switch(getPeiceType(startIndex))
        {
            case WP:
                WP = WP ^ (1<<startIndex);
                WP = WP ^ (1<<finishIndex);
                break;
            case WN:
                WN = WN ^ (1<<startIndex);
                WN = WN ^ (1<<finishIndex);
                break;
            case WB:
                WB = WB ^ (1<<startIndex);
                WB = WB ^ (1<<finishIndex);
                break;
            case WR:
                WR = WR ^ (1<<startIndex);
                WR = WR ^ (1<<finishIndex);                
                break;
            case WQ:
                WQ = WQ ^ (1<<startIndex);
                WQ = WQ ^ (1<<finishIndex);                
                break;
            case WK:
                WK = WK ^ (1<<startIndex);
                WK = WK ^ (1<<finishIndex);                
                break;
            case BP:
                BP = BP ^ (1<<startIndex);
                BP = BP ^ (1<<finishIndex);                
                break;
            case BN:
                BN = BN ^ (1<<startIndex);
                BN = BN ^ (1<<finishIndex);                
                break;
            case BB:
                BB = BB ^ (1<<startIndex);
                BB = BB ^ (1<<finishIndex);                
                break;
            case BR:
                BR = BR ^ (1<<startIndex);
                BR = BR ^ (1<<finishIndex);                
                break;
            case BQ:
                BQ = BQ ^ (1<<startIndex);
                BQ = BQ ^ (1<<finishIndex);                
                break;
            case BK:
                BK = BK ^ (1<<startIndex);
                BK = BK ^ (1<<finishIndex);                
                break;
            default:
                break;  
        }

        // If a peice is captured by this move it is removed from its board
        BitBoardEnum taken = getPeiceType(finishIndex);
        if (taken != null)
        {
            switch(getPeiceType(finishIndex))
            {
                case WP:
                    if (((WP>>finishIndex)&1) == 1)
                        WP = WP ^ (1<<finishIndex);
                    break;
                case WN:
                    if (((WN>>finishIndex)&1) == 1)
                        WN = WN ^ (1<<finishIndex);
                    break;
                case WB:
                    if (((WB>>finishIndex)&1) == 1)
                        WB = WB ^ (1<<finishIndex);
                    break;
                case WR:
                    if (((WR>>finishIndex)&1) == 1)
                        WR = WR ^ (1<<finishIndex);                
                    break;
                case WQ:
                    if (((WQ>>finishIndex)&1) == 1)
                        WQ = WQ ^ (1<<finishIndex);                
                    break;
                case WK:
                    if (((WK>>finishIndex)&1) == 1)
                        WK = WK ^ (1<<finishIndex);                
                    break;
                case BP:
                    if (((BP>>finishIndex)&1) == 1)
                        BP = BP ^ (1<<finishIndex);                
                    break;
                case BN:
                    if (((BN>>finishIndex)&1) == 1)
                        BN = BN ^ (1<<finishIndex);                
                    break;
                case BB:
                    if (((BB>>finishIndex)&1) == 1)
                        BB = BB ^ (1<<finishIndex);                
                    break;
                case BR:
                    if (((BR>>finishIndex)&1) == 1)
                        BR = BR ^ (1<<finishIndex);                
                    break;
                case BQ:
                    if (((BQ>>finishIndex)&1) == 1)
                        BQ = BQ ^ (1<<finishIndex);                
                    break;
                case BK:
                    if (((BK>>finishIndex)&1) == 1)
                        BK = BK ^ (1<<finishIndex);                
                    break;
                default:
                    break;  
            }
        }
    }
    
    // creates a new board that is a copy of current board but with move made
    public Board newBoardMove(int startIndex,int finishIndex)
    {
        Board newBoard = new Board(WP, WN, WB, WR, WQ, WK,BP, BN, BB, BR, BQ, BK);
        newBoard.move(startIndex, finishIndex);  
        return newBoard;
    }


    public long GetWhitePieces()
    {
        return (WP|WN|WB|WR|WQ|WK);
    }

    public long GetBlackPieces()
    {
        return (BP|BN|BB|BR|BQ|BK);
    }


    public BitBoardEnum getPeiceType(int index)
    {
        if(((WP>>i) & 1) == 1) {return BitBoardEnum.WP;}
        if(((WN>>i) & 1) == 1) {return BitBoardEnum.WN;}
        if(((WB>>i) & 1) == 1) {return BitBoardEnum.WB;}
        if(((WR>>i) & 1) == 1) {return BitBoardEnum.WR;}
        if(((WQ>>i) & 1) == 1) {return BitBoardEnum.WQ;}
        if(((WK>>i) & 1) == 1) {return BitBoardEnum.WK;}
        if(((BP>>i) & 1) == 1) {return BitBoardEnum.BP;}
        if(((BN>>i) & 1) == 1) {return BitBoardEnum.BN;}
        if(((BB>>i) & 1) == 1) {return BitBoardEnum.BB;}
        if(((BR>>i) & 1) == 1) {return BitBoardEnum.BR;}
        if(((BQ>>i) & 1) == 1) {return BitBoardEnum.BQ;}
        if(((BK>>i) & 1) == 1) {return BitBoardEnum.BK;}
        return null;
    }

}