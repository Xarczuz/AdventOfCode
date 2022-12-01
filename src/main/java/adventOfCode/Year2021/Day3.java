package adventOfCode.Year2021;

import java.util.ArrayList;
import java.util.List;
import util.FileUtil;

public class Day3 {
    private static final int OFSET = 48;

    public static long day3(List<String> list) {
        long[] a = new long[list.get(0).length()];
        for (String s : list) {
            for (int i = 0; i < a.length; i++) {
                a[i] += (s.charAt(i) - OFSET);
            }
        }
        int totallines = list.size();
        String gamma = "";
        String epsilon = "";
        for (int i = 0; i < a.length; i++) {

            long a00 = totallines - a[i];
            if (a[i] > a00) {
                gamma += "1";
            } else {
                gamma += "0";
            }
        }
        epsilon = reverse(gamma);
        int[] two = new int[] {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096};
        long gammanr = 0;
        long epsilonnr = 0;
        for (int i = gamma.length() - 1, y = 0; i >= 0; i--, y++) {
            gammanr = gammanr + (gamma.charAt(i) - OFSET) * two[y];
        }
        for (int i = epsilon.length() - 1, y = 0; i >= 0; i--, y++) {
            epsilonnr = epsilonnr + (epsilon.charAt(i) - OFSET) * two[y];
        }
        return epsilonnr * gammanr;
    }

    public static long day3Level2(List<String> list) {
        String s = gammaAndEpsilon(list, true);
        String oxy = oxgenAndCarbon(s, new ArrayList<>(list), 0, true);
        s = gammaAndEpsilon(list, false);
        String carb = oxgenAndCarbon(s, new ArrayList<>(list), 0, false);
        long gammanr = FileUtil.bitToLong(oxy);
        long epsilonnr = FileUtil.bitToLong(carb);
        return gammanr * epsilonnr;
    }

    private static String oxgenAndCarbon(String s, ArrayList<String> list, int i, boolean b) {
        ArrayList<String> newlist = new ArrayList<>();
        for (String s1 : list) {
            if (s1.charAt(i) - OFSET == s.charAt(i) - OFSET) {
                newlist.add(s1);
            }
        }
        if (newlist.size() == 1) {
            return newlist.get(0);
        }
        return oxgenAndCarbon(gammaAndEpsilon(newlist, b), newlist, i + 1, b);
    }

    private static String gammaAndEpsilon(List<String> list, boolean b) {
        long[] a = new long[list.get(0).length()];
        for (String s : list) {
            for (int i = 0; i < a.length; i++) {
                a[i] += (s.charAt(i) - OFSET);
            }
        }
        int totallines = list.size();
        StringBuilder gamma = new StringBuilder();
        for (long l : a) {
            long a00 = totallines - l;
            if (b) {
                if (l >= a00) {
                    gamma.append("1");
                } else {
                    gamma.append("0");
                }
            } else {
                if (l >= a00) {
                    gamma.append("0");
                } else {
                    gamma.append("1");
                }
            }
        }
        return gamma.toString();
    }

    private static String reverse(String gamma) {
        String s = "";
        for (int i = 0; i < gamma.length(); i++) {
            int o = (gamma.charAt(i) - 48);
            if (o == 0) {
                s += 1;

            } else {
                s += 0;

            }
        }
        return s;
    }
}
