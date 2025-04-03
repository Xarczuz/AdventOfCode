package adventOfCode.Year2024;

import util.FileUtil;
import util.TimeUtil;
import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day15 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day15.class);
        List<String> l2 = FileUtil.readfileExempel(Day15.class);
        TimeUtil.startTime();
        oneStar(l);
        oneStar(l2);
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l);
        twoStar(l2);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {

    }

    private static void oneStar(List<String> l) {
        parse(l);
    }

    private static void parse(List<String> l) {
        ArrayList<String> area = new ArrayList<>();
        ArrayList<String> path = new ArrayList<>();
        int i;
        for (i = 0; i < l.size(); i++) {
            String s = l.get(i);

            if (s.isBlank()) {
                break;
            }
            area.add(s);
        }
        for (; i < l.size(); i++) {
            String s = l.get(i);
            if (s.isBlank()) {
                continue;
            }
            path.add(s);
        }
        System.out.println(area);
        System.out.println(path);
        String[][] matrix = new String[area.size()][area.get(0).toCharArray().length];

        for (int j = 0; j < area.size(); j++) {
            for (int k = 0; k < area.get(0).toCharArray().length; k++) {
                matrix[j][k] = area.get(j).toCharArray()[k] + "";
            }
        }
        Util.print(matrix);
    }

}
