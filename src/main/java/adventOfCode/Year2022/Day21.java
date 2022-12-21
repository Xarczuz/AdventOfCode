package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day21 {

    public static void main(String[] args) throws IOException, URISyntaxException, IllegalAccessException {
        List<String> l = FileUtil.readfile(Day21.class);
        List<String> l2 = FileUtil.readfileExempel(Day21.class);
        TimeUtil.startTime();
        oneStar(l2);
        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l2);
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
        } else if (nrOrInstruction[1].charAt(0) == '*') {
            double monkeyNr1 = findMonkeyNr2(nrOrInstruction[0], map);
            double monkeyNr2 = findMonkeyNr2(nrOrInstruction[2], map);
            return monkeyNr1 * monkeyNr2;
        } else if (nrOrInstruction[1].charAt(0) == '-') {
            double monkeyNr1 = findMonkeyNr2(nrOrInstruction[0], map);
            double monkeyNr2 = findMonkeyNr2(nrOrInstruction[2], map);
            return monkeyNr1 - monkeyNr2;
        } else if (nrOrInstruction[1].charAt(0) == '+') {
            double monkeyNr1 = findMonkeyNr2(nrOrInstruction[0], map);
            double monkeyNr2 = findMonkeyNr2(nrOrInstruction[2], map);
            return monkeyNr1 + monkeyNr2;
        }
        double monkeyNr1 = findMonkeyNr2(nrOrInstruction[0], map);
        double monkeyNr2 = findMonkeyNr2(nrOrInstruction[2], map);
        return monkeyNr1 / monkeyNr2;
    }

    private static double findMonkeyNr3(String monkey, HashMap<String, String[]> map, HashMap<String, String[]> stuffToAdd) throws IllegalAccessException {
        String[] nrOrInstruction = map.get(monkey);
        if (nrOrInstruction.length == 0) {
            throw new IllegalAccessException("demo");

        }
        if (nrOrInstruction.length == 1) {
            return Double.parseDouble(nrOrInstruction[0]);
        } else if (nrOrInstruction[1].charAt(0) == '*') {
            double monkeyNr1 = findMonkeyNr3(nrOrInstruction[0], map, stuffToAdd);
            double monkeyNr2 = findMonkeyNr3(nrOrInstruction[2], map, stuffToAdd);
            if (stuffToAdd != null) {
                stuffToAdd.put(nrOrInstruction[0], new String[]{String.valueOf(monkeyNr1)});
                stuffToAdd.put(nrOrInstruction[2], new String[]{String.valueOf(monkeyNr2)});
            }
            return monkeyNr1 * monkeyNr2;
        } else if (nrOrInstruction[1].charAt(0) == '-') {
            double monkeyNr1 = findMonkeyNr3(nrOrInstruction[0], map, stuffToAdd);
            double monkeyNr2 = findMonkeyNr3(nrOrInstruction[2], map, stuffToAdd);
            if (stuffToAdd != null) {
                stuffToAdd.put(nrOrInstruction[0], new String[]{String.valueOf(monkeyNr1)});
                stuffToAdd.put(nrOrInstruction[2], new String[]{String.valueOf(monkeyNr2)});
            }
            return monkeyNr1 - monkeyNr2;
        } else if (nrOrInstruction[1].charAt(0) == '+') {
            double monkeyNr1 = findMonkeyNr3(nrOrInstruction[0], map, stuffToAdd);
            double monkeyNr2 = findMonkeyNr3(nrOrInstruction[2], map, stuffToAdd);
            if (stuffToAdd != null) {
                stuffToAdd.put(nrOrInstruction[0], new String[]{String.valueOf(monkeyNr1)});
                stuffToAdd.put(nrOrInstruction[2], new String[]{String.valueOf(monkeyNr2)});
            }
            return monkeyNr1 + monkeyNr2;
        }
        double monkeyNr1 = findMonkeyNr3(nrOrInstruction[0], map, stuffToAdd);
        double monkeyNr2 = findMonkeyNr3(nrOrInstruction[2], map, stuffToAdd);
        if (stuffToAdd != null) {
            stuffToAdd.put(nrOrInstruction[0], new String[]{String.valueOf(monkeyNr1)});
            stuffToAdd.put(nrOrInstruction[2], new String[]{String.valueOf(monkeyNr2)});
        }
        return monkeyNr1 / monkeyNr2;
    }

    private static void twoStar(List<String> l) {
        HashMap<String, String[]> map = new HashMap<>();
        for (String s : l) {
            String[] line = s.split(":");
            String monekys = line[1].replace(" ", "");
            if (Character.isDigit(monekys.charAt(0))) {
                String[] arr = new String[]{monekys};
                map.put(line[0], arr);
            } else {
                String[] arr = new String[3];
                arr[0] = monekys.substring(0, 4);
                arr[1] = monekys.substring(4, 5);
                arr[2] = monekys.substring(5);
                map.put(line[0], arr);
            }
        }

        String[] root = map.get("root");
        double sum2 = findMonkeyNr2(root[2], map);

        map.put("humn", new String[]{});

        for (String s : map.keySet()) {
            try {
                HashMap<String, String[]> stuffToAdd = new HashMap<>();
                findMonkeyNr3(s, map, stuffToAdd);
                for (Map.Entry<String, String[]> stringEntry : stuffToAdd.entrySet()) {
                    map.put(stringEntry.getKey(), stringEntry.getValue());
                }
            } catch (IllegalAccessException e) {
            }
        }

        double humn = 1;
        double increase = 1;
        double dincrease = 1;
        while (true) {
            map.put("humn", new String[]{String.valueOf(humn)});
            double sum1 = findMonkeyNr2(root[0], map);

            if (Double.compare(sum1, sum2) == 0) {
                break;
            }
            if (Math.abs(sum1 - sum2) < 10000) {
                increase = 1;
                humn -= 1;
            } else if (Double.compare(sum1, sum2) == 1) {
                increase++;
                humn = humn + increase;
            } else {
                dincrease++;
                humn = humn - dincrease;

            }
            System.out.println(humn);
        }

        System.out.println("Star two: " + humn);
    }

}
