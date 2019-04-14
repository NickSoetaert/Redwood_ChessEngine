//Nick Soetaert

import java.util.*;

public class BoardGenerator{

    //For debug stuff
    public static long getBoardKey(){
            String boardString[][] = {
            {"x","x","x"," "," "," "," "," "},
            {"x"," ","x"," "," "," "," "," "},
            {"x","x","x"," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "}
            };
            
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
        String boardString[][] =
        
        {
            {"r","n","b","q","k","b","n","r"},
            {"z","z","z","z","z","z","z","z"},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {"P","P","P","P","P","P","P","P"},
            {"R","N","B","Q","K","B","N","R"}
        };
       /* 
        {
            {" "," "," "," "," "," "," ","k"},
            {" "," "," "," "," "," ","p","p"},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," ","P"," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {"K"," "," "," "," "," "," "," "}
         };
        */
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
                case "z": BP += stringToBinary(binaryStr);
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
        Board curBoard = new Board(WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK);
        //drawBoard(curBoard);
        return curBoard;

    }

    public static void drawBoard(Board curBoard){

        String chessBoard[][] = new String[8][8];
        
        for(int i = 0; i < 64; i++){
            chessBoard[i/8][i%8] = "   ";
        }

        for(int i = 0; i < 64; i++){
            /*
             * Shift bits i bits to the right
             * After shift, if leading bit is a 1, there is a piece on the current square
             */
            if(((curBoard.get(BitBoardEnum.WP)>>i) & 1) == 1) {chessBoard[i/8][i%8] = " P ";}
            if(((curBoard.get(BitBoardEnum.WN)>>i) & 1) == 1) {chessBoard[i/8][i%8] = " N ";}
            if(((curBoard.get(BitBoardEnum.WB)>>i) & 1) == 1) {chessBoard[i/8][i%8] = " B ";}
            if(((curBoard.get(BitBoardEnum.WR)>>i) & 1) == 1) {chessBoard[i/8][i%8] = " R ";}
            if(((curBoard.get(BitBoardEnum.WQ)>>i) & 1) == 1) {chessBoard[i/8][i%8] = " Q ";}
            if(((curBoard.get(BitBoardEnum.WK)>>i) & 1) == 1) {chessBoard[i/8][i%8] = " K ";}
            if(((curBoard.get(BitBoardEnum.BP)>>i) & 1) == 1) {chessBoard[i/8][i%8] = " z ";}
            if(((curBoard.get(BitBoardEnum.BN)>>i) & 1) == 1) {chessBoard[i/8][i%8] = " n ";}
            if(((curBoard.get(BitBoardEnum.BB)>>i) & 1) == 1) {chessBoard[i/8][i%8] = " b ";}
            if(((curBoard.get(BitBoardEnum.BR)>>i) & 1) == 1) {chessBoard[i/8][i%8] = " r ";}
            if(((curBoard.get(BitBoardEnum.BQ)>>i) & 1) == 1) {chessBoard[i/8][i%8] = " q ";}
            if(((curBoard.get(BitBoardEnum.BK)>>i) & 1) == 1) {chessBoard[i/8][i%8] = " k ";}
        }

        int j = 0;
        for(String[] arr : chessBoard){
            System.out.println("   ________________________________________");
            System.out.print(8-j + " |");
            j++;
            for(String c : arr){
                System.out.print(c + " |");
            }
            System.out.println("");
        }

        System.out.println("   ========================================");
        System.out.println("    A  | B  | C  | D  | E  | F  | G  | H  |\n");
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