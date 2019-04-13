//Nick Soetaert

import java.util.*;

public class BoardGenerator{

    //For debug stuff
    public static long getBoardKey(){
        String boardString[][] = {
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "}
            };
            /*
            {" "," "," "," "," "," "," "," "},
            {" "," ","x"," ","x"," "," "," "},
            {" ","x"," "," "," ","x"," "," "},
            {" "," "," ","o"," "," "," "," "},
            {" ","x"," "," "," ","x"," "," "},
            {" "," ","x"," ","x"," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "}
            };
            */
        long result = 0;
        String binaryStr;
        for(int i = 0; i < 64; i++){
            binaryStr = "0000000000000000000000000000000000000000000000000000000000000000";
            binaryStr = binaryStr.substring(i+1) + "1" + binaryStr.substring(0,i);

            switch(boardString[i/8][i%8]){
                case "x": result += stringToBinary(binaryStr);
                break;
            }
        }
        
        System.out.println(result);
        return result;
    }

    public static Board initStandardBoard(){

        long WP=0L, WN=0L, WB=0L, WR=0L, WQ=0L, WK=0L, BP=0L, BN=0L, BB=0L, BR=0L, BQ=0L, BK=0L;

        //Black pieces are lowercase, a1 square is bottom left corner.
        String boardString[][] = {
        {"r","n","b","q","k","b","n","r"},
        {"p","p","p","p","p","p","p","p"},
        {" ","P"," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {"p"," "," ","p"," "," "," "," "},
        {"P","P","P","P","P","P","P","P"},
        {"R","N","B","Q","K","B","N","R"}
        };
        
        String binaryStr;
        //For every square on the chess board
        for(int i = 0; i < 64; i++){
            binaryStr = "0000000000000000000000000000000000000000000000000000000000000000";

            binaryStr = binaryStr.substring(i+1) + "1" + binaryStr.substring(0,i);
           
            //cycles through board, top left to bottom right
            switch(boardString[i/8][i%8]){
                case "P": WP += stringToBinary(binaryStr);
                    break;
                case "N": WN += stringToBinary(binaryStr);
                    break;
                case "B": WB += stringToBinary(binaryStr);
                    break;
                case "R": WR += stringToBinary(binaryStr);
                    break;
                case "Q": WQ += stringToBinary(binaryStr);
                    break;
                case "K": WK += stringToBinary(binaryStr);
                    break;
                case "p": BP += stringToBinary(binaryStr);
                    break;
                case "n": BN += stringToBinary(binaryStr);
                    break;
                case "b": BB += stringToBinary(binaryStr);
                    break;
                case "r": BR += stringToBinary(binaryStr);
                    break;
                case "q": BQ += stringToBinary(binaryStr);
                    break;
                case "k": BK += stringToBinary(binaryStr);
                    break;
                default:
                    break;
            }

        }

        drawBoard(WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK);
        return new Board(WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK);

    }

    public static void drawBoard(long WP, long WN, long WB, long WR, long WQ, long WK,
                                 long BP, long BN, long BB, long BR, long BQ, long BK){

        String chessBoard[][] = new String[8][8];
        
        for(int i = 0; i < 64; i++){
            chessBoard[i/8][i%8] = " ";
        }

        for(int i = 0; i < 64; i++){
            /*
             * Shift bits i bits to the right
             * After shift, if leading bit is a 1, there is a piece on the current square
             */
            if(((WP>>i) & 1) == 1) {chessBoard[i/8][i%8] = "P";}
            if(((WN>>i) & 1) == 1) {chessBoard[i/8][i%8] = "N";}
            if(((WB>>i) & 1) == 1) {chessBoard[i/8][i%8] = "B";}
            if(((WR>>i) & 1) == 1) {chessBoard[i/8][i%8] = "R";}
            if(((WQ>>i) & 1) == 1) {chessBoard[i/8][i%8] = "Q";}
            if(((WK>>i) & 1) == 1) {chessBoard[i/8][i%8] = "K";}
            if(((BP>>i) & 1) == 1) {chessBoard[i/8][i%8] = "p";}
            if(((BN>>i) & 1) == 1) {chessBoard[i/8][i%8] = "n";}
            if(((BB>>i) & 1) == 1) {chessBoard[i/8][i%8] = "b";}
            if(((BR>>i) & 1) == 1) {chessBoard[i/8][i%8] = "r";}
            if(((BQ>>i) & 1) == 1) {chessBoard[i/8][i%8] = "q";}
            if(((BK>>i) & 1) == 1) {chessBoard[i/8][i%8] = "k";}
        }

        for(int i = 0; i < 8; i++){
            System.out.println(8-i + " " + Arrays.toString(chessBoard[i]));
        }
        System.out.println("   A  B  C  D  E  F  G  H\n");
    }

    //Takes a 64 character string of 1s and 0s and converts that into a 64 bit binary representation
    private static long stringToBinary(String binaryStr){
        /*
           1000000000000000000000000000000000000000000000000000000000000000
           is too large for parseLong.
           The else statement fixes this, converting it to -9223372036854775808,
           which is 1000000... in signed 2's compliment binary.
       */       
       
       if(binaryStr.charAt(0) == '0'){
           return Long.parseLong(binaryStr, 2);
       }
       else {
           return Long.parseLong("1"+binaryStr.substring(2), 2) * 2;
       }
  
    }

   public static void drawPiece(long bb){
        String chessBoard[][] = new String[8][8];
            
        for(int i = 0; i < 64; i++){
            chessBoard[i/8][i%8] = " ";
        }

        for(int i = 0; i < 64; i++){
            /*
            * Shift bits i bits to the right
            * After shift, if leading bit is a 1, there is a piece on the current square
            */
            if(((bb>>i) & 1) == 1) {chessBoard[i/8][i%8] = "x";}
        } 

        for(int i = 0; i < 8; i++){
            System.out.println(8-i + " " + Arrays.toString(chessBoard[i]));
        }
        System.out.println("   A  B  C  D  E  F  G  H\n");
    }


}