package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day12 {

    static int max = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day12.class);
        List<String> l2 = FileUtil.readfileExempel(Day12.class);
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
        max = Integer.MAX_VALUE;

        ArrayList<Square[]> heightMap = parseHeightmap(l);
        Square[][] heightMaps = heightMap.toArray(new Square[0][0]);
        int x = 0;
        int y = 0;
        for (int i = 0; i < heightMap.size(); i++) {
            for (int h = 0; h < heightMaps[0].length; h++) {
                Square square = heightMaps[i][h];
                if (square.height == 'S') {
                    x = h;
                    y = i;
                }
            }

        }
        ArrayList<Integer> steps = new ArrayList<>();
        TravelPath travelPath = new TravelPath();
        travelPath.currentPos.y = y;
        travelPath.currentPos.x = x;
        findPath(heightMaps, travelPath, steps);
        steps.sort(Integer::compareTo);

        System.out.println("Star one: " + steps.get(0));
    }

    private static void findPath(Square[][] heightMap, TravelPath travelPath, ArrayList<Integer> steps) {
        int y = travelPath.currentPos.y;
        int x = travelPath.currentPos.x;
        if (travelPath.steps > max || y < 0 || x < 0 || y > heightMap.length - 1 || x > heightMap[0].length - 1) {
            return;
        }
        Square square = heightMap[y][x];
        if (square.height == 'S') {
            square.height = 'a';
        }

        if (travelPath.currentHeight + 1 == square.height
                || travelPath.currentHeight >= square.height) {
            travelPath.currentHeight = square.height;

            if (travelPath.steps >= square.minSteps) {
                return;
            } else {
                square.minSteps = travelPath.steps;
            }

            travelPath.steps++;
            travelPath.currentPos.height = String.valueOf(square.height);
            TravelPath north = travelPath.copy();
            north.currentPos.y--;
            findPath(heightMap, north, steps);
            TravelPath east = travelPath.copy();
            east.currentPos.x++;
            findPath(heightMap, east, steps);
            TravelPath south = travelPath.copy();
            south.currentPos.y++;
            findPath(heightMap, south, steps);
            TravelPath west = travelPath.copy();
            west.currentPos.x--;
            findPath(heightMap, west, steps);
        }

        if (travelPath.currentHeight == '{') {
            steps.add(travelPath.steps);
            max = Math.min(travelPath.steps, max);
        }
    }

    private static ArrayList<Square[]> parseHeightmap(List<String> l) {
        ArrayList<Square[]> heightMap = new ArrayList<>();

        for (int y = 0; y < l.size(); y++) {
            String s = l.get(y);
            int x = 0;
            heightMap.add(new Square[s.toCharArray().length]);
            for (char c : s.toCharArray()) {
                Square square = new Square();
                if (c == 'E') {
                    square.height = '{';
                } else {
                    square.height = c;
                }
                square.coordinate = new Point(x, y, String.valueOf(c));
                heightMap.get(y)[x] = square;
                x++;
            }
        }
        return heightMap;
    }

    private static void twoStar(List<String> l) {
        max = Integer.MAX_VALUE;
        ArrayList<Square[]> heightMap = parseHeightmap(l);
        Square[][] heightMaps = heightMap.toArray(new Square[0][0]);
        ArrayList<Point> pointsToStart = new ArrayList<>();

        for (int y = 0; y < heightMap.size(); y++) {
            for (int x = 0; x < heightMaps[0].length; x++) {
                Square square = heightMaps[y][x];
                if (square.height == 'S' || square.height == 'a') {
                    Point p = new Point(x, y, String.valueOf(square.height));
                    pointsToStart.add(p);
                }
            }
        }

        ArrayList<Integer> steps = new ArrayList<>();
        for (Point point : pointsToStart) {
            TravelPath travelPath = new TravelPath();
            travelPath.currentPos.y = point.y;
            travelPath.currentPos.x = point.x;
            findPath(heightMaps, travelPath, steps);
        }
        steps.sort(Integer::compareTo);

        System.out.println("Star two: " + steps.get(0));
    }

    static class Square {
        Point coordinate;
        char height;

        int minSteps = Integer.MAX_VALUE;

        @Override
        public String toString() {
            return "Square{" +
                    "coordinate=" + coordinate +
                    ", height=" + height +
                    ", minSteps=" + minSteps +
                    '}';
        }

    }

    static class TravelPath {
        int steps = -1;

        Point currentPos = new Point(0, 0, "S");
        char currentHeight = 'a';

        public TravelPath copy() {
            TravelPath clone = new TravelPath();
            clone.currentHeight = this.currentHeight;
            clone.currentPos = this.currentPos.copy();
            clone.steps = this.steps;

            return clone;
        }

    }

    static class Point {
        int x;
        int y;
        String height;

        public Point(int x, int y, String c) {
            this.x = x;
            this.y = y;
            this.height = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", height='" + height + '\'' +
                    '}';
        }

        public Point copy() {
            return new Point(x, y, height);
        }
    }
}
