package days;

import java.util.ArrayList;

public class Day7 {
    public int part1(ArrayList<String> strings) {
        int[] arr = parse(strings);
        int max = 0;
        for (int i : arr) {
            max = Math.max(i, max);
        }

        int[] arrInts = new int[max + 1];

        for (int i : arr) {
            arrInts[i]++;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < max + 1; i++) {
            int tot = 0;
            for (int j = 0; j < arrInts.length; j++) {
                int amount = arrInts[j];
                int steps = Math.abs(i - j);
                tot += amount * steps;
            }
            min = Math.min(min, tot);
        }
        return min;
    }

    public int part2(ArrayList<String> strings) {
        int[] arr = parse(strings);
        int max = 0;
        for (int i : arr) {
            max = Math.max(i, max);
        }

        int[] arrInts = new int[max + 1];

        for (int i : arr) {
            arrInts[i]++;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < max + 1; i++) {
            int tot = 0;
            for (int j = 0; j < arrInts.length; j++) {
                int amount = arrInts[j];
                int steps = countSteps(i, j);
                tot += amount * steps;
            }
            min = Math.min(min, tot);
        }
        return min;
    }

    private int countSteps(int i, int j) {
        int steps = Math.abs(i - j);
        return (steps * (steps + 1) / 2);
    }

    private int[] parse(ArrayList<String> strings) {
        String[] s = strings.get(0).split(",");
        int[] nrs = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            nrs[i] = Integer.parseInt(s1);
        }
        return nrs;
    }
}
