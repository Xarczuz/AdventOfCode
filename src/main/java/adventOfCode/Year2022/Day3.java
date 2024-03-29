package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day3 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day3.class);
        List<String> l2 = FileUtil.readfileExempel(Day3.class);

        oneStar(l2);
        oneStar(l);

        TimeUtil.startTime();
        twoStar(l2);
        twoStar(l);
        TimeUtil.endTime();

        TimeUtil.startTime();
        twoStar2(l2);
        twoStar2(l);
        TimeUtil.endTime();
    }

    private static void twoStar2(List<String> l) {
        long sum = 0;
        for (int i = 0; i < l.size(); i += 3) {
            ArrayList<String> s1 = createList(l, i);
            ArrayList<String> s2 = createList(l, i + 1);
            ArrayList<String> s3 = createList(l, i + 2);
            List<String> chars = s1.stream().distinct().filter(s2::contains).filter(s3::contains).toList();
            for (String aChar : chars) {
                sum += toPrioValue(aChar.charAt(0));
            }
        }
        System.out.println("star 2 sum: " + sum);
    }

    private static ArrayList<String> createList(List<String> l, int i) {
        ArrayList<String> strings = new ArrayList<>();
        for (char s : l.get(i).toCharArray()) {
            strings.add(String.valueOf(s));
        }
        return strings;
    }

    private static void twoStar(List<String> l) {
        long sum = 0;
        int nrInGroup = 3;
        for (int i = 0; i < l.size(); i += nrInGroup) {
            int[] chars = createUniqueList(l, nrInGroup, i);
            sum = calculateScore(sum, nrInGroup, chars);
        }
        System.out.println("star 2 sum: " + sum);
    }

    private static int[] createUniqueList(List<String> l, int nrInGroup, int i) {
        int[] chars = new int[130];
        for (int j = 0; j < nrInGroup; j++) {
            prepareUniqueCharsBetweenLists(chars, l.get(i + j), j + 1);
        }
        return chars;
    }

    private static long calculateScore(long sum, int nrInGroup, int[] chars) {
        for (int j = 0; j < chars.length; j++) {
            if (chars[j] == nrInGroup) {
                sum += toPrioValue(j);
            }
        }
        return sum;
    }

    private static void prepareUniqueCharsBetweenLists(int[] chars, String s1, int i) {
        for (char c : s1.toCharArray()) {
            if (chars[c] == i - 1) {
                chars[c] = i;
            }
        }
    }

    private static void oneStar(List<String> l) {
        long sum = 0;
        for (String s : l) {
            String s1 = s.substring(0, s.length() / 2);
            String s2 = s.substring(s.length() / 2);
            HashSet<Character> characters = new HashSet<>();
            for (int i = 0; i < s1.length(); i++) {
                for (int j = 0; j < s1.length(); j++) {
                    if (s1.charAt(i) == s2.charAt(j)) {
                        characters.add(s2.charAt(j));
                    }
                }
            }
            for (Character character : characters) {
                sum += toPrioValue(character);
            }
        }
        System.out.println("star 1 sum: " + sum);
    }

    private static int toPrioValue(int character) {
        if (character > 97) {
            return character - 96;
        }
        return character - 38;
    }
}
