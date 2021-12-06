package days;

import classes.Lines;
import java.util.ArrayList;

public class Day5 {
    public static int part1(ArrayList<String> readFromFile) {
        ArrayList<Lines> linesArr = new ArrayList<>(readFromFile.size());
        int[][] oeanFloor = makeOceanFloor(readFromFile, linesArr);

        for (Lines lines : linesArr) {

            if (lines.getX1() == lines.getX2() || lines.getY1() == lines.getY2()) {

                for (int i = 0; i < lines.getxPoints().size(); i++) {
                    for (int j = 0; j < lines.getyPoints().size(); j++) {
                        oeanFloor[lines.getxPoints().get(i)][lines.getyPoints().get(j)]++;
                    }
                }
            }
        }

        return countOverlap(oeanFloor);
    }

    private static int[][] makeOceanFloor(ArrayList<String> readFromFile, ArrayList<Lines> linesArr) {
        int max = 0;
        for (String s : readFromFile) {
            Lines lines = new Lines(s);
            linesArr.add(lines);
            max = Math.max(max, lines.getX1());
            max = Math.max(max, lines.getX2());
            max = Math.max(max, lines.getY1());
            max = Math.max(max, lines.getY2());
        }
        int[][] oeanFloor = new int[max + 1][max + 1];
        return oeanFloor;
    }

    public static int level2(ArrayList<String> readFromFile) {
        ArrayList<Lines> linesArr = new ArrayList<>(readFromFile.size());
        int[][] oeanFloor = makeOceanFloor(readFromFile, linesArr);
        for (Lines lines : linesArr) {
            if (lines.getX1() == lines.getX2()
                    || lines.getY1() == lines.getY2()) {
                for (int i = 0; i < lines.getxPoints().size(); i++) {
                    for (int j = 0; j < lines.getyPoints().size(); j++) {
                        oeanFloor[lines.getyPoints().get(j)][lines.getxPoints().get(i)]++;
                    }
                }
            }
            for (int i = 0; i < lines.getVerticalPoints().size(); i++) {
                oeanFloor[lines.getVerticalPoints().get(i).y][lines.getVerticalPoints().get(i).x]++;
            }
        }
        return countOverlap(oeanFloor);
    }

    private static int countOverlap(int[][] oeanFloor) {
        int overlap = 0;
        for (int i = 0; i < oeanFloor.length; i++) {
            for (int j = 0; j < oeanFloor.length; j++) {
                if (oeanFloor[i][j] > 1) {
                    overlap++;
                }
            }
        }
        return overlap;
    }
}
