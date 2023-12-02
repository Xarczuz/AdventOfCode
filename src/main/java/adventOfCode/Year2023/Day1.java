package adventOfCode.Year2023;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

    static String[] strings2 = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    static Integer[] ints = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day1.class);
        List<String> l2 = FileUtil.readfileExempel(Day1.class);
        List<String> l3 = FileUtil.readfileExempel2(Day1.class);
        TimeUtil.startTime();
        oneStar(l);
        oneStar(l2);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l3);
        twoStar(l);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {
        long digits = removeNoneDigits2(l);
        System.out.println("Two star sum: " + digits);
        System.out.println();
    }

    private static void oneStar(List<String> l) {
        long sum = removeNoneDigits(l);
        System.out.println("One star sum: " + sum);
    }

    private static int removeNoneDigits(List<String> l) {
        int sum = 0;
        for (String s : l) {
            ArrayList<Character> chars = new ArrayList<>();
            for (char c : s.toCharArray()) {
                if (c >= '0' && c <= '9') {
                    chars.add(c);
                }
            }
            String stringDigit = chars.get(0) + String.valueOf(chars.get(chars.size() - 1));
            sum += Integer.parseInt(stringDigit);
        }
        return sum;
    }

    private static long removeNoneDigits2(List<String> l) {
        long sum = 0;
        for (String s : l) {
            sum += makeInt2(s);
        }
        return sum;
    }

    static int makeInt2(String input) {
        int left = findLeft(input);
        int right = findRight(input);
        return left * 10 + right;
    }

    static int findRight(String input) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = input.toCharArray();
        for (int j = charArray.length - 1; j >= 0; j--) {
            char c = charArray[j];
            if (c >= '0' && c <= '9') {
                return c - '0';
            } else {
                sb.insert(
                        0,
                        c
                );
                for (int i = 0; i < strings2.length; i++) {
                    String s = strings2[i];
                    if (sb.toString().contains(s)) {
                        return ints[i];
                    }
                }
            }
        }
        throw new RuntimeException("ERROR");
    }

    static int findLeft(String input) {
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (c >= '0' && c <= '9') {
                return c - '0';
            } else {
                sb.append(c);
                for (int i = 0; i < strings2.length; i++) {
                    String s = strings2[i];
                    if (sb.toString().contains(s)) {
                        return ints[i];
                    }
                }
            }
        }
        throw new RuntimeException("ERROR");
    }

}
