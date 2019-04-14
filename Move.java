import java.util.ArrayList;

public class Move {
    private ArrayList<String> algebraic;
    private long bitBoard;

    Move(){
        algebraic = new ArrayList<>();
        bitBoard = 0L;
    }

    public void combine(Move move){
        for(String s : move.getAlg()){
            this.addAlg(s);
        }
        this.bitBoard = this.bitBoard | move.bitBoard;

    }

    public void addAlg(String move){
        algebraic.add(move);
    }

    public void setBitBoard(long bb){
        bitBoard = bb;
    }

    public ArrayList<String> getAlg(){
        return algebraic;
    }

    public long getBitBoard(){
        return bitBoard;
    }
}