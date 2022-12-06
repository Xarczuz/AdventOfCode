package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Day6 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day6.class);
        List<String> l2 = FileUtil.readfileExempel(Day6.class);
        TimeUtil.startTime();
        oneStar(l2);
        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l2);
        twoStar(l);
        TimeUtil.endTime();
    }

    private static void oneStar(List<String> l) {
        System.out.println("Star one: ");
        for (String s1 : l) {
            findSignal(s1, 4);
        }
    }

    private static void findSignal(String s, int distinct) {
        char[] chars = s.toCharArray();
        LinkedList<Character> unique = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            if (unique.size() < distinct) {
                unique.add(chars[i]);
            }
            if (unique.size() <= distinct - 1) {
                continue;
            }
            HashSet<Character> set = new HashSet<>(unique);
            if (set.size() == distinct) {
                System.out.println(i + 1);
                return;
            }
            unique.removeFirst();
        }
    }

    private static void twoStar(List<String> l) {
        System.out.println("Star two: ");
        for (String s1 : l) {
            findSignal(s1, 14);
        }
    }
}
