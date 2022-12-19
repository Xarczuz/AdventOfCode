package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
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
        twoStar(l3);
//        twoStar(l2);
//        twoStar(l);
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
                if (cube1.isConnected(cube)) {
                    cube1.connectedCube(cube);
                }
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
        HashSet<Cube> cubes = new HashSet<>();
        int xMax = 0;
        int yMax = 0;
        int zMax = 0;
        for (String s : l) {
            String[] nrs = s.split(",");
            int x = Integer.parseInt(nrs[0]);
            int y = Integer.parseInt(nrs[1]);
            int z = Integer.parseInt(nrs[2]);
            Cube cube = new Cube(x, y, z);

            for (Cube cube1 : cubes) {
                if (cube1.isConnected(cube)) {
                    cube1.connectedCube(cube);
                }
            }
            cubes.add(cube);
            xMax = Math.max(x, xMax);
            yMax = Math.max(y, yMax);
            zMax = Math.max(z, zMax);
        }
        int sum = 0;
        for (Cube cube : cubes) {
            sum += cube.exposed;
        }

        ArrayList<Cube> airpockets = new ArrayList<>();
        for (int x = 0; x <= xMax; x++) {
            for (int y = 0; y <= yMax; y++) {
                for (int z = 0; z <= zMax; z++) {
                    Cube cube = new Cube(x, y, z);
                    if (!cubes.contains(cube)) {
                        int connected = 0;
                        for (Cube cube1 : cubes) {
                            if (cube1.isConnected(cube)) {
                                connected++;
                            }
                        }

                        cube.connected = connected;
                        airpockets.add(cube);
                    }
                }
            }
        }

        for (int i = 0; i < airpockets.size(); i++) {
            Cube cubeTrapped = airpockets.get(i);
            if (isTrapped(cubeTrapped, cubeTrapped, airpockets)) {
                sum -= cubeTrapped.connected;
                System.out.println(cubeTrapped);
            }
        }


        System.out.println("Star two: " + sum); //36-4 4078,3618 to high 2536 to low
    }

    private static boolean isTrapped(Cube origin, Cube cubeTrapped, ArrayList<Cube> airpockets) {
        if (cubeTrapped.x == 0 && cubeTrapped.y == 0 && cubeTrapped.z == 0) {
            return false;
        }
        if (cubeTrapped.whoHasVisited.contains(origin)) {
            return true;
        }

            cubeTrapped.whoHasVisited.add(origin);
            if (airpockets.contains(cubeTrapped)) {


                return isTrapped(origin, new Cube(cubeTrapped.x + 1, cubeTrapped.y, cubeTrapped.z), airpockets)
                        && isTrapped(origin, new Cube(cubeTrapped.x - 1, cubeTrapped.y, cubeTrapped.z), airpockets)
                        && isTrapped(origin, new Cube(cubeTrapped.x, cubeTrapped.y + 1, cubeTrapped.z), airpockets)
                        && isTrapped(origin, new Cube(cubeTrapped.x, cubeTrapped.y - 1, cubeTrapped.z), airpockets)
                        && isTrapped(origin, new Cube(cubeTrapped.x, cubeTrapped.y, cubeTrapped.z + 1), airpockets)
                        && isTrapped(origin, new Cube(cubeTrapped.x, cubeTrapped.y, cubeTrapped.z - 1), airpockets);


            }


        return false;
    }

    static class Cube {
        int x;
        int y;
        int z;
        int exposed = 6;
        int connected = 0;
        HashSet<Cube> whoHasVisited = new HashSet<>();

        public Cube() {
        }

        public Cube(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public boolean isConnected(Cube cube) {
            return isConnectedXY(cube) || isConnectedXZ(cube) || isConnectedZY(cube);
        }

        private boolean isConnectedZY(Cube cube) {
            return this.z == cube.z && this.y == cube.y && (this.x == cube.x + 1 || this.x == cube.x - 1);
        }

        private boolean isConnectedXZ(Cube cube) {
            return this.x == cube.x && this.z == cube.z && (this.y == cube.y + 1 || this.y == cube.y - 1);
        }

        private boolean isConnectedXY(Cube cube) {
            return this.x == cube.x && this.y == cube.y && (this.z == cube.z + 1 || this.z == cube.z - 1);
        }

        private void connectedCube(Cube cube) {
            this.exposed--;
            cube.exposed--;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cube cube = (Cube) o;

            if (x != cube.x) return false;
            if (y != cube.y) return false;
            return z == cube.z;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + z;
            return result;
        }
    }
}
