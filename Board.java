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

    public void move(String UCIin)
    {
        int movevals[] = Board.UCItoIndex(UCIin);
        this.move(movevals[0], movevals[1]);
    }

    // moves a peice on local board
    public void move(int startIndex,int finishIndex)
    {
        BitBoardEnum taken = getPeiceType(finishIndex);
        BitBoardEnum moved = getPeiceType(startIndex);
        
        // flipps bits on origin and destination bits
        switch(moved)
        {
            case WP:
                WP = WP ^ (1L <<(startIndex));
                WP = WP ^ (1L <<(finishIndex));
                break;
            case WN:
                WN = WN ^ (1L<<(startIndex));
                WN = WN ^ (1L<<(finishIndex));
                break;
            case WB:
                WB = WB ^ (1L<<(startIndex));
                WB = WB ^ (1L<<(finishIndex));
                break;
            case WR:
                WR = WR ^ (1L<<(startIndex));
                WR = WR ^ (1L<<(finishIndex));                
                break;
            case WQ:
                WQ = WQ ^ (1L<<(startIndex));
                WQ = WQ ^ (1L<<(finishIndex));                
                break;
            case WK:
                WK = WK ^ (1L<<(startIndex));
                WK = WK ^ (1L<<(finishIndex));                
                break;
            case BP:
                BP = BP ^ (1L<<(startIndex));
                BP = BP ^ (1L<<(finishIndex));                
                break;
            case BN:
                BN = BN ^ (1L<<(startIndex));
                BN = BN ^ (1L<<(finishIndex));                
                break;
            case BB:
                BB = BB ^ (1L<<(startIndex));
                BB = BB ^ (1L<<(finishIndex));                
                break;
            case BR:
                BR = BR ^ (1L<<(startIndex));
                BR = BR ^ (1L<<(finishIndex));                
                break;
            case BQ:
                BQ = BQ ^ (1L<<(startIndex));
                BQ = BQ ^ (1L<<(finishIndex));                
                break;
            case BK:
                BK = BK ^ (1L<<(startIndex));
                BK = BK ^ (1L<<(finishIndex));                
                break;
            default:
                break;  
        }

        // If a peice is captured by this move it is removed from its board
        if (taken != null)
        {
            switch(taken)
            {
                case WP:
                    if (((WP>>finishIndex)&1) == 1)
                        WP = WP ^ (1L<<(finishIndex));
                    break;
                case WN:
                    if (((WN>>finishIndex)&1) == 1)
                        WN = WN ^ (1L<<(finishIndex));
                    break;
                case WB:
                    if (((WB>>finishIndex)&1) == 1)
                        WB = WB ^ (1L<<(finishIndex));
                    break;
                case WR:
                    if (((WR>>finishIndex)&1) == 1)
                        WR = WR ^ (1L<<(finishIndex));                
                    break;
                case WQ:
                    if (((WQ>>finishIndex)&1) == 1)
                        WQ = WQ ^ (1L<<(finishIndex));                
                    break;
                case WK:
                    if (((WK>>finishIndex)&1) == 1)
                        WK = WK ^ (1L<<(finishIndex));                
                    break;
                case BP:
                    if (((BP>>finishIndex)&1) == 1)
                        BP = BP ^ (1L<<(finishIndex));                
                    break;
                case BN:
                    if (((BN>>finishIndex)&1) == 1)
                        BN = BN ^ (1L<<(finishIndex));                
                    break;
                case BB:
                    if (((BB>>finishIndex)&1) == 1)
                        BB = BB ^ (1L<<(finishIndex));                
                    break;
                case BR:
                    if (((BR>>finishIndex)&1) == 1)
                        BR = BR ^ (1L<<(finishIndex));                
                    break;
                case BQ:
                    if (((BQ>>finishIndex)&1) == 1)
                        BQ = BQ ^ (1L<<(finishIndex));                
                    break;
                case BK:
                    if (((BK>>finishIndex)&1) == 1)
                        BK = BK ^ (1L<<(finishIndex));                
                    break;
                default:
                    break;  
            }
        }

        //UCI_LastMove = +(String)((int)"a"+startIndex/8)+ 
    }
    
    // creates a new board that is a copy of current board but with move made
    public Board newBoardMove(int startIndex,int finishIndex)
    {
        Board newBoard = new Board(WP, WN, WB, WR, WQ, WK,BP, BN, BB, BR, BQ, BK);
        newBoard.move(startIndex, finishIndex);  
        return newBoard;
    }

    public Board newBoardMove(String UCIin)
    {

        Board newBoard = new Board(WP, WN, WB, WR, WQ, WK,BP, BN, BB, BR, BQ, BK);
        newBoard.move(UCIin);  
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

    public BitBoardEnum getPeiceType(int i)
    {
        if(((WP>>(i)) & 1) == 1) {return BitBoardEnum.WP;}
        if(((WN>>(i)) & 1) == 1) {return BitBoardEnum.WN;}
        if(((WB>>(i)) & 1) == 1) {return BitBoardEnum.WB;}
        if(((WR>>(i)) & 1) == 1) {return BitBoardEnum.WR;}
        if(((WQ>>(i)) & 1) == 1) {return BitBoardEnum.WQ;}
        if(((WK>>(i)) & 1) == 1) {return BitBoardEnum.WK;}
        if(((BP>>(i)) & 1) == 1) {return BitBoardEnum.BP;}
        if(((BN>>(i)) & 1) == 1) {return BitBoardEnum.BN;}
        if(((BB>>(i)) & 1) == 1) {return BitBoardEnum.BB;}
        if(((BR>>(i)) & 1) == 1) {return BitBoardEnum.BR;}
        if(((BQ>>(i)) & 1) == 1) {return BitBoardEnum.BQ;}
        if(((BK>>(i)) & 1) == 1) {return BitBoardEnum.BK;}
        return null;
    }

    public static int[] UCItoIndex(String UCI)
    {
        int returnArray[] = new int[2];

        returnArray[0] = 8*(UCI.charAt(1)-'1');
        returnArray[0] += 'h' - (UCI.charAt(0));
        returnArray[0] = 63 - returnArray[0];

        returnArray[1] = 8*(UCI.charAt(3)-'1');
        returnArray[1] += 'h' - (UCI.charAt(2));
        returnArray[1] = 63 - returnArray[1];

        return returnArray;
    }

    public static String IndexToUCI(int firstIndex,int LastIndex)
    {
        String UCIout = "";
        UCIout = UCIout.concat(String.valueOf((char)( (int)'a' + getFile(firstIndex))));
        UCIout = UCIout.concat(String.valueOf((getRank(firstIndex)+1)));
        UCIout = UCIout.concat(String.valueOf((char)( (int)'a' + getFile(LastIndex))));
        UCIout = UCIout.concat(String.valueOf((getRank(LastIndex)+1)));

        return UCIout;
    }

    public static int getRank(int Index)
    {
        return 7 - (Index / 8);
    }
    public static int getFile(int Index)
    {
        return Index % 8;
    }

    //Will return centipawn evaluation
    // A positive score favors white, negative favors black
    public int eval()
    {
        return 0;
    }

    

}