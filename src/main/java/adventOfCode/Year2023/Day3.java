package adventOfCode.Year2023;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Day3 {

    static int[][] yx = new int[][]{{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day3.class);
        List<String> l2 = FileUtil.readfileExempel(Day3.class);
        List<String> l3 = FileUtil.readfileExempel2(Day3.class);
        TimeUtil.startTime();
        oneStar(l); // wrong 549292
        oneStar(l2);
        oneStar(l3);
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l);
//        twoStar(l2);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {

    }

    private static void oneStar(List<String> l) {
        String[][] schematic = parseString(l);

        int sum = sumOfValidSchematics(schematic);
        System.out.println(sum);
    }

    private static int sumOfValidSchematics(String[][] schematic) {
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        boolean valid = false;
        for (int y = 0; y < schematic.length; y++) {
            for (int x = 0; x < schematic[0].length; x++) {
                String point = schematic[y][x];
                char c = point.charAt(0);
                if (c >= '0' && c <= '9') {
                    sb.append(point);
                    valid = valid || validPoint(y, x, schematic);
                } else {
                    if (valid) {
                        sum += Integer.parseInt(sb.toString());
                        valid = false;
                    }
                    sb = new StringBuilder();
                }
            }
        }
        return sum;
    }

    private static boolean validPoint(int y, int x, String[][] schematic) {

        for (int[] ints : yx) {
            int y1 = y + ints[0];
            int x1 = x + ints[1];
            if (y1 >= 0 && x1 >= 0 && y1 < schematic.length && x1 < schematic[0].length) {
                String point = schematic[y1][x1];
                char c = point.charAt(0);
                if (c == '.' || c >= '0' && c <= '9') {
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private static String[][] parseString(List<String> l) {
        String[][] schematic = new String[l.get(0).length()][l.size()];
        for (int y = 0; y < l.size(); y++) {
            String s = l.get(y);
            char[] charArray = s.toCharArray();
            for (int x = 0; x < charArray.length; x++) {
                char c = charArray[x];
                schematic[y][x] = String.valueOf(c);
            }
        }
        return schematic;
    }

}
