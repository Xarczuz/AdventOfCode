package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;
import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Day19 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day19.class);
        List<String> l2 = FileUtil.readfileExempel(Day19.class);
        TimeUtil.startTime();
        oneStar(l2);
        //oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l2);
        //twoStar(l);
        TimeUtil.endTime();
    }

    private static void oneStar(List<String> l) {
        int[] instructions = new int[l.size()];
        for (int i = 0; i < l.size(); i++) {
            String s = l.get(i);
            instructions[i] = Integer.parseInt(s);
        }
        Util.print(instructions);

        int[] ints = new int[l.size()];
        System.arraycopy(instructions, 0, ints, 0, l.size());

        for (int nr : instructions) {
            int indexOfNr = findIndex(nr, ints);
            moveNrInArray(nr, indexOfNr, ints);
        }


        Util.print(ints);
        int start = 4; //to be decided
        int x = getGroveCoordinates(start, 1000, instructions.length);
        int y = getGroveCoordinates(start, 2000, instructions.length);
        int z = getGroveCoordinates(start, 3000, instructions.length);
        System.out.println("Star one:" + x + " " + y + " " + z);

    }

    private static void moveNrInArray(int nr, int indexOfNr, int[] ints) {
        int start = indexOfNr;
        for (int i = 0; i < Math.abs(nr); i++) {
            if (nr < 0) {
                int i1 = Math.floorMod(start - 1, ints.length);
                int next = ints[i1];
                ints[Math.floorMod(start, ints.length)] = next;
                ints[i1] = nr;
                start--;
            } else {
                int next = ints[(start + 1) % ints.length];
                ints[start % ints.length] = next;
                ints[(start + 1) % ints.length] = nr;
                start++;
            }
        }


    }

    private static int findIndex(int nr, int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == nr) {
                return i;
            }
        }
        return Integer.MIN_VALUE;
    }

    private static int getGroveCoordinates(int start, int nrs, int length) {
        for (int i = 0; i < nrs; i++) {
            if (start % length == 0 && start != 0) {
                start = 0;
            } else {
                start++;
            }
        }
        return start;
    }

    private static void twoStar(List<String> l) {

    }
}
