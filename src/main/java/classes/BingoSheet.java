package classes;

public class BingoSheet {
    public int[][] nr;
    public boolean[][] markedNr;
    public boolean bingo;
    public int winningNr;

    public BingoSheet() {
        this.nr = new int[5][5];
        this.markedNr = new boolean[5][5];
        this.bingo = false;
    }
}
