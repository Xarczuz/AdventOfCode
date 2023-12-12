package adventOfCode.Year2023;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day12 {


    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day12.class);
        List<String> l2 = FileUtil.readfileExempel(Day12.class);
        TimeUtil.startTime();
//        oneStar(l);
//        oneStar(l2);
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l);
        twoStar(l2);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {
        ArrayList<HotSpring> hotSprings = parseString2(l);
        long sum = 0;
        for (HotSpring hotSpring : hotSprings) {
            sum += findTotalArrangemnts(hotSpring);
        }
        System.out.println("Two star: " + sum);
    }

    private static ArrayList<HotSpring> parseString2(List<String> l) {
        ArrayList<HotSpring> hotSprings = new ArrayList<>(l.size());
        for (String s : l) {
            String[] ss = s.split(" ");
            HotSpring hotSpring = new HotSpring();
            hotSpring.borkenRecord = ss[0].toCharArray();
            char[] fiveX = new char[hotSpring.borkenRecord.length * 5];

            for (int i = 0; i < fiveX.length; i++) {
                fiveX[i] = hotSpring.borkenRecord[i % hotSpring.borkenRecord.length];
            }
            hotSpring.borkenRecord = fiveX;

            String[] ints = ss[1].split(",");
            int[] order = new int[ints.length * 5];
            for (int i = 0; i < ints.length * 5; i++) {

                order[i] = Integer.parseInt(ints[i % ints.length]);

            }
            hotSpring.order = order;
            hotSprings.add(hotSpring);
        }
        return hotSprings;
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
        long sum = 0;
        ArrayList<char[]> arrangementsOfBrokenSprings = hotSpring.permutaionsOfArrangementsOfBrokenSprings();
        for (char[] chars : arrangementsOfBrokenSprings) {
            char[] copy = hotSpring.copyBrokenRecord();
            int index = 0;
            for (int i = 0; i < copy.length; i++) {
                if (copy[i] == '?') {
                    copy[i] = chars[index];
                    index++;
                }
            }
            if (isValidArrangement(copy, hotSpring)) {
                sum++;
            }
        }
        return sum;
    }

    private static boolean isValidArrangement(char[] copy, HotSpring hotSpring) {
        int[] order = hotSpring.order;
        int amount = 0;
        boolean first = false;
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (char c : copy) {
            if (c == '#') {
                amount++;
                first = true;
            } else if (c == '.' && first) {
                first = false;
                integerArrayList.add(amount);
                amount = 0;
            }
        }
        if (copy[copy.length - 1] == '#') {
            integerArrayList.add(amount);
        }

        if (integerArrayList.size() != order.length) {
            return false;
        }

        for (int j = 0; j < integerArrayList.size(); j++) {
            Integer i = integerArrayList.get(j);
            if (i != order[j]) {
                return false;

            }
        }

        return true;
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

        private int amountOfBrokenSprings() {
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
            ArrayList<char[]> permutaions = new ArrayList<>();
            int r = (int) (Math.pow(2, a));
            System.out.println(r);
            int maxLength = 0;
            for (int i = r - 1; i >= 0; i--) {
                String binaryString = Integer.toBinaryString(i);
                maxLength = Math.max(maxLength, binaryString.length());
                if (binaryString.length() < maxLength) {
                    binaryString = String.valueOf(0).repeat(maxLength - binaryString.length()) + binaryString;
                }
//                System.out.println(binaryString);
                char[] chars = binaryString.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    if (chars[j] == '1') {
                        chars[j] = spring;
                    } else {
                        chars[j] = noSpring;
                    }
                }
                permutaions.add(chars);
            }

            return permutaions;
        }

        public char[] copyBrokenRecord() {
            char[] copy = new char[borkenRecord.length];
            System.arraycopy(borkenRecord, 0, copy, 0, borkenRecord.length);
            return copy;
        }
    }

}
