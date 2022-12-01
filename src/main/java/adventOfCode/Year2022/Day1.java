package adventOfCode.Year2022;

import util.FileUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public class Day1 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day1.class);
        oneStar(l);
        twoStar(l);
    }

    private static void twoStar(List<String> l) {
        LinkedList<Long> calories = new LinkedList<>();
        long nr = 0;
        for (String s : l) {
            if (s.isBlank()) {
                calories.add(nr);
                nr = 0;
            } else {
                nr += Long.parseLong(s);
            }
        }
        calories.sort(Long::compareTo);

        System.out.println("Tot: " + (calories.removeLast() + calories.removeLast() + calories.removeLast()));
    }

    private static void oneStar(List<String> l) {
        long max = 0;
        long nr = 0;
        for (String s : l) {
            if (s.isBlank()) {
                nr = 0;
            } else {
                nr += Long.parseLong(s);
                max = Math.max(nr, max);
            }
        }
        System.out.println("Most calories: " + max);
    }
}
