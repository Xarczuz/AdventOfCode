package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

public class Day21 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day21.class);
        List<String> l2 = FileUtil.readfileExempel(Day21.class);
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
        HashMap<String, String> map = new HashMap<>();
        for (String s : l) {
            String[] line = s.split(":");
            map.put(line[0], line[1].replace(" ", ""));
        }
        double sum = findMonkeyNr("root", map);

        System.out.println("Star one: " + sum);
    }

    private static double findMonkeyNr(String monkey, HashMap<String, String> map) {
        String nrOrInstruction = map.get(monkey);
        if ('0' <= nrOrInstruction.charAt(0) && nrOrInstruction.charAt(0) <= '9') {
            return Double.parseDouble(nrOrInstruction);
        } else if (nrOrInstruction.charAt(4) == '*') {
            return findMonkeyNr(nrOrInstruction.substring(0, 4), map) * findMonkeyNr(nrOrInstruction.substring(5), map);
        } else if (nrOrInstruction.charAt(4) == '-') {
            return findMonkeyNr(nrOrInstruction.substring(0, 4), map) - findMonkeyNr(nrOrInstruction.substring(5), map);
        } else if (nrOrInstruction.charAt(4) == '+') {
            return findMonkeyNr(nrOrInstruction.substring(0, 4), map) + findMonkeyNr(nrOrInstruction.substring(5), map);
        }
        return findMonkeyNr(nrOrInstruction.substring(0, 4), map) / findMonkeyNr(nrOrInstruction.substring(5), map);


    }

    private static double findMonkeyNr2(String monkey, HashMap<String, String[]> map) {
        String[] nrOrInstruction = map.get(monkey);
        if (nrOrInstruction.length == 1) {
            return Double.parseDouble(nrOrInstruction[0]);
        } else if (nrOrInstruction.charAt(0) == '*') {
            return findMonkeyNr(nrOrInstruction.substring(0, 4), map) * findMonkeyNr(nrOrInstruction.substring(5), map);
        } else if (nrOrInstruction.charAt(0) == '-') {
            return findMonkeyNr(nrOrInstruction.substring(0, 4), map) - findMonkeyNr(nrOrInstruction.substring(5), map);
        } else if (nrOrInstruction.charAt(0) == '+') {
            return findMonkeyNr(nrOrInstruction.substring(0, 4), map) + findMonkeyNr(nrOrInstruction.substring(5), map);
        }
        return findMonkeyNr(nrOrInstruction.substring(0, 4), map) / findMonkeyNr(nrOrInstruction.substring(5), map);


    }

    private static void twoStar(List<String> l) {
        HashMap<String, String[]> map = new HashMap<>();
        for (String s : l) {
            String[] line = s.split(":");
            String monekys = line[1].replace(" ", "");
            if (Character.isDigit(monekys.charAt(0))) {

                String[] arr = new String[1];
                map.put(line[0], arr);
            } else {

                String[] arr = new String[3];
                map.put(line[0], arr);
            }
        }

        int humn = 81596030;
        String[] root = map.get("root");
        for (; humn < Double.MAX_VALUE; humn++) {
            map.put("humn", new String[]{String.valueOf(humn)});
            double sum1 = findMonkeyNr2(root[0], map);
            double sum2 = findMonkeyNr2(root[2], map);
            if (sum1 == sum2) {
                break;
            }
            System.out.println(humn);
        }

        System.out.println("Star two: " + humn);
    }

}
