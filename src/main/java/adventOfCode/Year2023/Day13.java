package adventOfCode.Year2023;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day13 {


    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day13.class);
        List<String> l2 = FileUtil.readfileExempel(Day13.class);
//        TimeUtil.startTime();
//        oneStar(l);
//        oneStar(l2);
//        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l);
        twoStar(l2);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {
        long sum = 0;
        ArrayList<String> arr = new ArrayList<>();
        for (String s : l) {
            if (!s.isEmpty()) {
                arr.add(s);
            } else {
                sum += mirror2(arr);
                arr.clear();
            }
        }
        sum += mirror2(arr);
        System.out.println(sum);
    }

    private static void oneStar(List<String> l) {
        long sum = 0;
        ArrayList<String> arr = new ArrayList<>();
        for (String s : l) {
            if (!s.isEmpty()) {
                arr.add(s);
            } else {
                sum += mirror(arr);
                arr.clear();
            }
        }
        sum += mirror(arr);
        System.out.println(sum);
    }

    private static long mirror(ArrayList<String> arr) {
        int sum = horizontalReflection(arr, false) * 100;
        ArrayList<String> transposedArr = transposeArray(arr);
        sum += verticalReflection(transposedArr);
        return sum;
    }

    private static long mirror2(ArrayList<String> arr) {
        int sum = horizontalReflection2(arr, false) * 100;
        ArrayList<String> transposedArr = transposeArray(arr);
        sum += verticalReflection2(transposedArr);
        return sum;
    }

    protected static ArrayList<String> transposeArray(ArrayList<String> arr) {
        ArrayList<String> arrStrings = new ArrayList<>();
        for (String s : arr) {
            char[] charArray = s.toCharArray();
            for (int i = charArray.length - 1; i >= 0; i--) {
                char c = charArray[i];
                if (arrStrings.size() < charArray.length) {
                    arrStrings.add(String.valueOf(c));
                } else {
                    int abs = Math.abs(charArray.length - i) - 1;
                    arrStrings.set(abs, arrStrings.get(abs) + c);
                }
            }
        }

        return arrStrings;
    }

    protected static int verticalReflection(ArrayList<String> arr) {
        return horizontalReflection(arr, true);
    }

    protected static int verticalReflection2(ArrayList<String> arr) {
        return horizontalReflection2(arr, true);
    }


    protected static int horizontalReflection(ArrayList<String> arr, boolean fromVertical) {
        String prev = arr.getFirst();
        int refPoint = -1;
        for (int i = 1; i < arr.size(); i++) {
            String current = arr.get(i);
            if (prev.equals(current)) {
                if (compare2List(arr.subList(0, i), arr.subList(i, arr.size()))) {
                    refPoint = i;
                    break;
                }
            }
            prev = current;
        }
        if (refPoint != -1) {
            if (fromVertical) {
                return arr.size() - refPoint;
            }
            return refPoint;
        } else {
            return 0;
        }
    }

    protected static int horizontalReflection2(ArrayList<String> arr, boolean fromVertical) {
        String prev = arr.getFirst();
        int refPoint = -1;
        for (int i = 1; i < arr.size(); i++) {
            String current = arr.get(i);
            if (prev.equals(current)) {
                if (compare2List2(arr.subList(0, i), arr.subList(i, arr.size()))) {
                    refPoint = i;
                    break;
                }
            }
            prev = current;
        }
        if (refPoint != -1) {
            if (fromVertical) {
                return arr.size() - refPoint;
            }
            return refPoint;
        } else {
            return 0;
        }
    }

    private static boolean compare2List(List<String> firstPart, List<String> secondPart) {
        int index = Math.min(firstPart.size(), secondPart.size()) - 1;
        for (int i = 0, j = firstPart.size() - 1; 0 <= index; i++, j--, index--) {
            String s1 = firstPart.get(j);
            String s2 = secondPart.get(i);
            if (!s1.equals(s2)) {
                return false;
            }
        }
        return true;
    }

    private static boolean compare2List2(List<String> firstPart, List<String> secondPart) {
        int index = Math.min(firstPart.size(), secondPart.size()) - 1;
        for (int i = 0, j = firstPart.size() - 1; 0 <= index; i++, j--, index--) {
            String s1 = firstPart.get(j);
            String s2 = secondPart.get(i);
            if (!s1.equals(s2)) {
                return false;
            }
        }
        return true;
    }
}
