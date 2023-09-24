package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;
import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class Day14 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day14.class);
        List<String> l2 = FileUtil.readfileExempel(Day14.class);

        TimeUtil.startTime();
        oneStar(l2);
//        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {
    }

    private static void oneStar(List<String> l) {
        String[][] o = parseInput(l);

    }

    private static String[][] parseInput(List<String> l) {
        String[][] sS = new String[700][700];
        insertDot(sS);
        insertWalls(l, sS);
        Util.print(sS);
        return sS;
    }

    private static void insertWalls(List<String> l, String[][] sS) {
        for (String s : l) {
            String[] walls = s.split("->");
            int prevX = -1;
            int prevY = -1;
            insertWall(walls, prevX, prevY, sS);
        }
    }

    private static void insertWall(String[] walls, int prevX, int prevY, String[][] sS) {
        for (String wall : walls) {
            String[] cord = wall.split(",");
            int x = Integer.parseInt(cord[0].trim());
            int y = Integer.parseInt(cord[1].trim());
            String wallChar = "#";
            if (prevX != -1) {
                if (y > prevY) {
                    for (int i = prevY; i < y; i++) {
                        sS[i][x] = wallChar;
                    }
                }
                if (x > prevX) {
                    for (int i = prevX; i < x; i++) {
                        sS[y][i] = wallChar;
                    }
                }
                if (y < prevY) {
                    for (int i = prevY; i > y; i--) {
                        sS[i][x] = wallChar;
                    }
                }
                if (x < prevX) {
                    for (int i = prevX; i > x; i--) {
                        sS[y][i] = wallChar;
                    }
                }
            }
            sS[y][x] = wallChar;
            prevX = x;
            prevY = y;
        }
    }

    public static void insertDot(String[][] matrix) {
        for (String[] ints : matrix) {
            Arrays.fill(ints, ".");
        }
    }
}
