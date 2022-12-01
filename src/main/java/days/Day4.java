package days;

import classes.BingoGame;
import classes.BingoSheet;
import java.util.ArrayList;
import java.util.LinkedList;

public class Day4 {

    public int bingo1(ArrayList<String> strings) {
        BingoGame bingoGame = new BingoGame().create(strings);
        for (int nr : bingoGame.bingoNr) {
            for (int i = 0; i < bingoGame.bingoSheet.size(); i++) {
                BingoSheet bs = bingoGame.bingoSheet.get(i);
                for (int j = 0; j < bs.nr.length; j++) {
                    int[] rowInt = bs.nr[j];
                    boolean[] rowBool = bs.markedNr[j];
                    boolean bingo = false;
                    if (markNr(nr, rowInt, rowBool)) {
                        bingo = validate1(bingoGame);
                    }
                    if (bingo) {
                        System.err.println("bingo: " + nr);
                        int sum = sumAllUnmarked(bs);
                        return nr * sum;
                    }
                }
            }
        }

        return 0;
    }

    public int bingo2(ArrayList<String> strings) {
        BingoGame bingoGame = new BingoGame().create(strings);
        LinkedList<BingoSheet> arraysBS = new LinkedList<>();
        bingoLingo(bingoGame, arraysBS, -1);
        BingoSheet bs = arraysBS.getLast();
        int nr = bs.winningNr;
        arraysBS = new LinkedList<>();
        bingoLingo(new BingoGame().create(strings), arraysBS, nr);
        int sum = sumAllUnmarked(arraysBS.getLast());
        return nr * sum;
    }

    private void bingoLingo(BingoGame bingoGame, LinkedList<BingoSheet> arraysBS, int nrStop) {
        for (int nr : bingoGame.bingoNr) {
            for (int i = 0; i < bingoGame.bingoSheet.size(); i++) {
                BingoSheet bs = bingoGame.bingoSheet.get(i);
                for (int j = 0; j < bs.nr.length; j++) {
                    int[] rowInt = bs.nr[j];
                    boolean[] rowBool = bs.markedNr[j];
                    boolean bingo = false;
                    if (!bs.bingo && markNr(nr, rowInt, rowBool)) {
                        bingo = validateHor(bingoGame) || validateVert(bingoGame);
                    }
                    if (!bs.bingo && bingo) {
                        bs.bingo = true;
                        bs.winningNr = nr;
                        arraysBS.addLast(bs);
                    }
                }
            }

            if (nr == nrStop) {
                return;
            }
        }
    }

    private int sumAllUnmarked(BingoSheet bs) {
        int sum = 0;
        for (int i = 0; i < bs.markedNr.length; i++) {
            for (int j = 0; j < bs.markedNr.length; j++) {
                if (!bs.markedNr[i][j]) {
                    sum += bs.nr[i][j];
                }
            }
        }

        return sum;
    }

    private boolean markNr(int nr, int[] rowInt, boolean[] rowBool) {
        for (int i = 0; i < rowInt.length; i++) {
            if (rowInt[i] == nr) {
                rowBool[i] = true;
                return true;
            }
        }
        return false;
    }

    private boolean validate1(BingoGame bingoGame) {
        for (BingoSheet bingoSheet : bingoGame.bingoSheet) {
            for (int i = 0; i < 5; i++) {
                boolean bingo = true;
                for (int j = 0; j < 5; j++) {
                    bingo = bingo && bingoSheet.markedNr[i][j];
                }
                if (bingo) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean validateHor(BingoGame bingoGame) {
        for (BingoSheet bingoSheet : bingoGame.bingoSheet) {
            if (!bingoSheet.bingo) {
                for (int i = 0; i < 5; i++) {
                    boolean bingo = true;
                    for (int j = 0; j < 5; j++) {
                        bingo = bingo && bingoSheet.markedNr[i][j];
                    }
                    if (bingo) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean validateVert(BingoGame bingoGame) {
        for (BingoSheet bingoSheet : bingoGame.bingoSheet) {
            if (!bingoSheet.bingo) {
                for (int i = 0; i < 5; i++) {
                    boolean bingo = true;
                    for (int j = 0; j < 5; j++) {
                        bingo = bingo && bingoSheet.markedNr[j][i];
                    }
                    if (bingo) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
