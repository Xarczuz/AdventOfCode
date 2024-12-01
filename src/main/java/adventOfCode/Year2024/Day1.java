package adventOfCode.Year2024;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day1.class);
        List<String> l2 = FileUtil.readfileExempel(Day1.class);
        TimeUtil.startTime();
        oneStar(l);
        oneStar(l2);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l);
        twoStar(l2);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {
        Result2 result2 = parse2(l);
        long sum = 0;
        for (int i = 0; i < result2.int1.length; i++) {
            int i1 = result2.int1[i];
            int i2 = result2.int2[i1];
            sum += (long) i1 * i2;
        }

        System.out.println("Sum: " + sum);
    }

    private static Result2 parse2(List<String> l) {
        int[] int1 = new int[l.size()];
        int[] int2 = new int[147500];
        for (int i = 0; i < l.size(); i++) {
            String[] s1 = l.get(i).split("   ");
            int i1 = Integer.parseInt(s1[0].trim());
            int1[i] = i1;
            int i2 = Integer.parseInt(s1[1].trim());
            int2[i2] += 1;
        }

        return new Result2(int1, int2);
    }

    private static void oneStar(List<String> l) {
        Result res = parse(l);
        long sum = 0;
        for (int i = 0; i < res.arr1.size(); i++) {
            Integer i1 = res.arr1.get(i);
            Integer i2 = res.arr2.get(i);
            sum += Math.abs(i2 - i1);
        }

        System.out.println("Sum: " + sum);
    }

    private static Result parse(List<String> l) {
        ArrayList<Integer> arr1 = new ArrayList<>(l.size());
        ArrayList<Integer> arr2 = new ArrayList<>(l.size());
        for (String s : l) {
            String[] s1 = s.split("   ");
            arr1.add(Integer.parseInt(s1[0].trim()));
            arr2.add(Integer.parseInt(s1[1].trim()));
        }
        arr1.sort(Integer::compareTo);
        arr2.sort(Integer::compareTo);
        return new Result(arr1, arr2);
    }

    private record Result(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
    }

    private record Result2(int[] int1, int[] int2) {
    }
}
