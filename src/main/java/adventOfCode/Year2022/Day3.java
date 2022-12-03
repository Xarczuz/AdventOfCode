package adventOfCode.Year2022;

import util.FileUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;

public class Day3 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day3.class);
        List<String> l2 = FileUtil.readfileExempel(Day3.class);

        oneStar(l2);
        oneStar(l);

        twoStar(l2);
        twoStar(l);
    }

    private static void twoStar(List<String> l) {
        long sum = 0;
        for (int i = 0; i < l.size(); i += 3) {
            int[] chars = new int[255];
            String s1 = l.get(i);
            String s2 = l.get(i + 1);
            String s3 = l.get(i + 2);
            findBadge(chars, s1, 1);
            findBadge(chars, s2, 2);
            findBadge(chars, s3, 3);
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == 3) {
                    sum += toPrioValue(j);
                }
            }
        }
        System.out.println("star 2 sum: " + sum);
    }

    private static void findBadge(int[] chars, String s1, int i) {
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
        return character - 64 + 26;
    }
}
