package classes;

import java.util.ArrayList;


public class BingoGame {
    public ArrayList<BingoSheet> bingoSheet;
    public int[] bingoNr;
    public BingoGame create(ArrayList<String> strings) {
        bingoNr = parseBingoNr(strings.get(0));
        bingoSheet = new ArrayList<>();
        for (int i = 1, bIndex = -1, row = 0; i < strings.size(); i++) {
            if ("".equals(strings.get(i))) {
                bingoSheet.add(new BingoSheet());
                row = 0;
                bIndex++;
            } else if (row < 5) {
                BingoSheet bs = bingoSheet.get(bIndex);
                bs.nr[row] =(parseBingoRow(strings.get(i)));
                row++;
            }
        }
        return this;
    }

    private int[] parseBingoRow(String s) {
        s = s.trim();
        String[] nrs = s.split(" +");
        int[] row = new int[5];
        for (int i = 0; i < nrs.length; i++) {
            row[i] =Integer.parseInt(nrs[i]);
        }
        return row;
    }

    private int[] parseBingoNr(String s) {
        String[] nrs = s.split(",");
        int[] bingoNr = new int[nrs.length];
        for (int i = 0, nrsLength = nrs.length; i < nrsLength; i++) {
            bingoNr[i] = Integer.parseInt(nrs[i]);
        }
        return bingoNr;
    }
}
