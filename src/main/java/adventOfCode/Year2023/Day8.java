package adventOfCode.Year2023;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Day8 {


    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day8.class);
        List<String> l2 = FileUtil.readfileExempel(Day8.class);
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
        Nodes node = parseString(l);

        String location = "AAA";
        long steps = 0;
        while (!"ZZZ".equals(location)) {
            char direction = node.getDirection();

            String locationTemp;
            if (direction == 'R') {
                locationTemp = node.paths.get(location).right;
            } else {
                locationTemp = node.paths.get(location).left;
            }
            steps++;
            location = locationTemp;
        }
        System.out.println("One Star: " + steps);
    }

    private static Nodes parseString(List<String> l) {
        Nodes nodes = new Nodes();
        nodes.leftAndRight = l.get(0).trim();
        for (int i = 2; i < l.size(); i++) {
            String s = l.get(i);
            String[] loc = s.split("=");
            Path p = new Path();
            p.location = loc[0].trim();
            String[] lr = loc[1].replace("(", "").replace(")", "").split(",");
            p.left = lr[0].trim();
            p.right = lr[1].trim();
            nodes.paths.put(p.location, p);
        }
        return nodes;
    }

    private static class Nodes {
        String leftAndRight;
        HashMap<String, Path> paths = new HashMap<>();

        int index = 0;

        protected char getDirection() {
            char[] charArray = leftAndRight.toCharArray();
            char ret = charArray[index];
            index++;
            index = index % charArray.length;
            return ret;
        }
    }

    private static class Path {
        String location;
        String left;
        String right;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Path path = (Path) o;

            if (!Objects.equals(location, path.location)) return false;
            if (!Objects.equals(left, path.left)) return false;
            return Objects.equals(right, path.right);
        }

        @Override
        public int hashCode() {
            int result = location != null ? location.hashCode() : 0;
            result = 31 * result + (left != null ? left.hashCode() : 0);
            result = 31 * result + (right != null ? right.hashCode() : 0);
            return result;
        }
    }
}
