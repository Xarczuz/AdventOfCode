package adventOfCode.Year2023;

import util.FileUtil;
import util.TimeUtil;
import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day12 {


    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day12.class);
        List<String> l2 = FileUtil.readfileExempel(Day12.class);
        TimeUtil.startTime();
        oneStar(l);
        oneStar(l2);
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l);
//        twoStar(l2);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {

    }

    private static void oneStar(List<String> l) {
        ArrayList<HotSpring> hotSprings = parseString(l);
        long sum = 0;
        for (HotSpring hotSpring : hotSprings) {
            sum += findTotalArrangemnts(hotSpring);
        }
        System.out.println("One star: " + sum);
    }

    private static long findTotalArrangemnts(HotSpring hotSpring) {

        hotSpring.permutaionsOfArrangementsOfBrokenSprings();
        return 0;
    }

    private static ArrayList<HotSpring> parseString(List<String> l) {
        ArrayList<HotSpring> hotSprings = new ArrayList<>(l.size());
        for (String s : l) {
            String[] ss = s.split(" ");
            HotSpring hotSpring = new HotSpring();
            hotSpring.borkenRecord = ss[0].toCharArray();

            String[] ints = ss[1].split(",");
            int[] order = new int[ints.length];
            for (int i = 0; i < ints.length; i++) {
                order[i] = Integer.parseInt(ints[i]);
            }
            hotSpring.order = order;
            hotSprings.add(hotSpring);
        }
        return hotSprings;
    }

    private static class HotSpring {
        char[] borkenRecord;
        int[] order;

        public int amountOfBrokenSprings() {
            int sum = 0;
            for (char c : borkenRecord) {
                if (c == '?') {
                    sum++;
                }
            }
            return sum;
        }

        public ArrayList<char[]> permutaionsOfArrangementsOfBrokenSprings() {
            int a = amountOfBrokenSprings();
            char noSpring = '.';
            char spring = '#';

            long n1 = Util.factorial(a);
            long n2 = Util.factorial(a - 2);
            int r = (int) (n1 / n2);

            int maxLength = 0;
            for (int i = r; i >= 0; i--) {
                String binaryString = Integer.toBinaryString(i);
                maxLength = Math.max(maxLength, binaryString.length());
                if (binaryString.length() < maxLength) {
                    String x = "";
                    for (int j = 0; j < maxLength - binaryString.length(); j++) {
                        x += String.valueOf(0);
                    }
                    binaryString = x + binaryString;
                }
                System.out.println(binaryString);
            }

            System.out.println(r);
            return null;
        }
    }

}
