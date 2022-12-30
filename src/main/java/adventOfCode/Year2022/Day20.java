package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Day20 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day20.class);
        List<String> l2 = FileUtil.readfileExempel(Day20.class);
        List<String> l3 = FileUtil.readfileExempel2(Day20.class);
        TimeUtil.startTime();
        oneStar(l3);
        oneStar(l2);
        oneStar(l); // to low 13356 5555 9536
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l2);
//        twoStar(l);
        TimeUtil.endTime();
    }

    private static void oneStar(List<String> l) {
        Point[] instructions = new Point[l.size()];
        for (int i = 0; i < l.size(); i++) {
            String s = l.get(i);
            int i1 = Integer.parseInt(s);
            instructions[i] = new Point(i1, i);
        }

        LinkedList<Point> ints = new LinkedList<>(Arrays.asList(instructions));
        Point zero = null;
        for (Point nr : instructions) {
            moveNrInArray(nr, ints);
            if (nr.x == 0) {
                zero = nr;
            }
        }


        System.out.println(ints);
        int start = ints.indexOf(zero);
        int x = Math.floorMod((start + 1000), instructions.length);
        int y = Math.floorMod((start + 2000), instructions.length);
        int z = Math.floorMod((start + 3000), instructions.length);

        System.out.println("Star one: " + (ints.get(x).x + ints.get(y).x + ints.get(z).x));

    }

    private static void moveNrInArray(Point nr, LinkedList<Point> ints) {
        int oldIndex = ints.indexOf(nr);
        int newIndex = (oldIndex + nr.x) % (ints.size() - 1);
        if (newIndex < 0) {
            newIndex = ints.size() + newIndex - 1;
        }

        ints.remove(nr);
        ints.add(newIndex, nr);
    }

    private static void twoStar(List<String> l) {

    }

    record Point(int x, int y) {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return "" + x;
        }
    }
}
