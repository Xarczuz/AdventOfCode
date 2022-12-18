package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day18 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day18.class);
        List<String> l2 = FileUtil.readfileExempel(Day18.class);
        List<String> l3 = FileUtil.readfileExempel2(Day18.class);
        TimeUtil.startTime();
        oneStar(l2);
        oneStar(l3);
        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l2);
        //twoStar(l);
        TimeUtil.endTime();
    }

    private static void oneStar(List<String> l) {
        ArrayList<Cube> cubes = new ArrayList<>();
        for (String s : l) {
            String[] nrs = s.split(",");
            Cube cube = new Cube();
            cube.x = Integer.parseInt(nrs[0]);
            cube.y = Integer.parseInt(nrs[1]);
            cube.z = Integer.parseInt(nrs[2]);

            for (Cube cube1 : cubes) {
                cube1.connected(cube);
            }
            cubes.add(cube);
        }
        int sum = 0;
        for (Cube cube : cubes) {
            sum += cube.exposed;
        }

        System.out.println("Star one: " + sum);

    }

    private static void twoStar(List<String> l) {

    }

    static class Cube {
        int x;
        int y;
        int z;
        int exposed = 6;

        public boolean connected(Cube cube) {
            int twoSame = 0;
            if (this.x == cube.x && this.y == cube.y && (this.z == cube.z + 1 || this.z == cube.z - 1)) {
                this.exposed--;
                cube.exposed--;
                return true;
            }
            if (this.x == cube.x && this.z == cube.z && (this.y == cube.y + 1 || this.y == cube.y - 1)) {
                this.exposed--;
                cube.exposed--;
                return true;
            }
            if (this.z == cube.z && this.y == cube.y && (this.x == cube.x + 1 || this.x == cube.x - 1)) {
                this.exposed--;
                cube.exposed--;
                return true;
            }

            return false;
        }

        @Override
        public String toString() {
            return "Cube{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    ", exposed=" + exposed +
                    '}';
        }
    }
}
