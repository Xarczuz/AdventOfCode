package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;
import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public class Day20 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day20.class);
        List<String> l2 = FileUtil.readfileExempel(Day20.class);
        TimeUtil.startTime();
        oneStar(l2);
        oneStar(l); // to low 13356
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l2);
        twoStar(l);
        TimeUtil.endTime();
    }

    private static void oneStar(List<String> l) {
        int[] instructions = new int[l.size()];
        for (int i = 0; i < l.size(); i++) {
            String s = l.get(i);
            instructions[i] = Integer.parseInt(s);
        }
        Util.print(instructions);

        LinkedList<Integer> ints = new LinkedList<>();
        for (int instruction : instructions) {
            ints.add(instruction);
        }

        for (int nr : instructions) {
            int indexOfNr = ints.indexOf(nr);
            moveNrInArray(nr, indexOfNr, ints);
        }

        System.out.println(ints);
        int start = ints.indexOf(0); //to be decided
        int x = Math.floorMod((start + 1000), instructions.length);
        int y = Math.floorMod((start + 2000), instructions.length);
        int z = Math.floorMod((start + 3000), instructions.length);

        System.out.println("Star one: " + (ints.get(x) + ints.get(y) + ints.get(z)));

    }

    private static void moveNrInArray(int nr, int indexOfNr, LinkedList<Integer> ints) {
        int next;
        boolean remove = true;
        if (nr < 0) {
            next = Math.floorMod((indexOfNr + nr), ints.size());
            if (next < indexOfNr) {
                remove = false;
                ints.remove(indexOfNr);
            }
            if (next == 0) {
                ints.addLast(nr);
            } else {
                ints.add(next, nr);
            }
        } else {
            next = Math.floorMod((indexOfNr + nr), ints.size());
            if (next < indexOfNr) {
                remove = false;
                ints.remove(indexOfNr);
            }
            ints.add(next + 1, nr);
        }
        if (remove) {
            ints.remove(indexOfNr);
        }
    }

    private static void twoStar(List<String> l) {

    }
}
