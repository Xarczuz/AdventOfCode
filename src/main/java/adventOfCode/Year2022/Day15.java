package adventOfCode.Year2022;

import classes.XY;
import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Day15 {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        List<String> l = FileUtil.readfile(Day15.class);
        List<String> l2 = FileUtil.readfileExempel(Day15.class);

        TimeUtil.startTime();
//        oneStar(l2, 10);
//        oneStar(l, 2000000);
        TimeUtil.endTime();
        TimeUtil.startTime();
        //   twoStar(l2, 0, 20, 0, 20);
        twoStar(l, 0, 4000000, 0, 4000000);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l, int minX, int maxX, int minY, int maxY) {
        ArrayList<SensorBeacon> sensorBeacons = parseInput(l);
        calculateManhattanDistances(sensorBeacons);
        XY xy = findEmptySpaceWhereBeaconCanNotBePResent3(sensorBeacons, minX, maxX, minY, maxY);
        System.out.println("Frequency " + ((xy.x * 4000000L) + xy.y));
        System.out.println(0);
        System.out.println(maxX);
    }

    private static class Intervall {
        int start;
        int end;

        public Intervall(int start, int end) {
            this.start = Math.max(0, start);
            this.end = Math.min(4000000, end);
        }

        @Override
        public String toString() {
            return "Intervall{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    private static XY findEmptySpaceWhereBeaconCanNotBePResent3(ArrayList<SensorBeacon> sensorBeacons, int minX, int maxX, int minY, int maxY) {
        for (int y = minY; y <= maxY; y++) {
            ArrayList<Intervall> intervall = new ArrayList<>();
            for (SensorBeacon sensorBeacon : sensorBeacons) {
                if (sensorBeacon.beacon.y == y && sensorBeacon.beacon.x >= minX && sensorBeacon.beacon.x <= maxX) {
                    intervall.add(new Intervall(sensorBeacon.beacon.x, sensorBeacon.beacon.x));
                }
                if (sensorBeacon.sensor.y == y && sensorBeacon.sensor.x >= minX && sensorBeacon.sensor.x <= maxX) {
                    intervall.add(new Intervall(sensorBeacon.sensor.x, sensorBeacon.sensor.x));
                }
                int a = sensorBeacon.sensor.y - sensorBeacon.distance;
                if (a <= y) {
                    int rest = y - a;
                    if (rest <= sensorBeacon.distance) {
                        Intervall newIntervall = new Intervall(sensorBeacon.sensor.x - rest, sensorBeacon.sensor.x + rest);
                        intervall.add(newIntervall);
                    }
                }
                int b = sensorBeacon.sensor.y + sensorBeacon.distance;
                if (b >= y) {
                    int rest = b - y;
                    if (rest <= sensorBeacon.distance) {
                        Intervall newIntervall = new Intervall(sensorBeacon.sensor.x - rest, sensorBeacon.sensor.x + rest);
                        intervall.add(newIntervall);
                    }
                }
            }
            intervall.sort(Comparator.comparingInt(o -> o.start));
            int aa = isThereAMissingPoint(intervall);
            if (aa != -1) {
                return new XY(aa, y);
            }
        }
        return new XY();
    }

    private static int isThereAMissingPoint(ArrayList<Intervall> intervall) {
        int last = 0;
        for (Intervall intervall1 : intervall) {
            if (intervall1.start <= last) {
                last = Math.max(last, intervall1.end);
            } else {
                return intervall1.start - 1;
            }


        }
        return -1;
    }

    private static void oneStar(List<String> l, int y) {
        ArrayList<SensorBeacon> sensorBeacons = parseInput(l);
        calculateManhattanDistances(sensorBeacons);
        int minX = findMinX(sensorBeacons);
        int maxX = findMaxX(sensorBeacons);

        int nr = findEmptySpaceWhereBeaconCanNotBePResent(sensorBeacons, minX, maxX, y);
        System.out.println("Empty spaces: " + nr);
        System.out.println(minX);
        System.out.println(maxX);
    }

    private static int findEmptySpaceWhereBeaconCanNotBePResent(ArrayList<SensorBeacon> sensorBeacons, int minX, int maxX, int y) {
        HashMap<XY, String> line = new HashMap<>();
        for (SensorBeacon sensorBeacon : sensorBeacons) {
            if (sensorBeacon.beacon.y == y) {
                line.put(sensorBeacon.beacon, "B");
            }
            if (sensorBeacon.sensor.y == y) {
                line.putIfAbsent(sensorBeacon.beacon, "S");
            }
            for (int x = minX; x <= maxX; x++) {
                int dist = calculateManhattanDistance(x, sensorBeacon.sensor.x, y, sensorBeacon.sensor.y);
                if (dist <= sensorBeacon.distance) {
                    XY key = new XY(x, y);
                    line.putIfAbsent(key, "#");
                }
            }
        }
        return calculateVoidBeaconsSpots(line);
    }

    private static int calculateVoidBeaconsSpots(HashMap<XY, String> line) {
        int sum = 0;
        for (String s : line.values()) {
            if (s.equals("#") || s.equals("S")) {
                sum++;
            }
        }
        return sum;
    }

    private static int findMaxX(ArrayList<SensorBeacon> sensorBeacons) {
        int x = Integer.MIN_VALUE;
        for (SensorBeacon sensorBeacon : sensorBeacons) {
            x = Math.max(sensorBeacon.sensor.x + sensorBeacon.distance, x);
            x = Math.max(sensorBeacon.beacon.x + sensorBeacon.distance, x);
        }
        return x;
    }

    private static int findMinX(ArrayList<SensorBeacon> sensorBeacons) {
        int x = Integer.MAX_VALUE;
        for (SensorBeacon sensorBeacon : sensorBeacons) {
            x = Math.min(sensorBeacon.sensor.x - sensorBeacon.distance, x);
            x = Math.min(sensorBeacon.beacon.x - sensorBeacon.distance, x);
        }
        return x;
    }

    private static void calculateManhattanDistances(ArrayList<SensorBeacon> sensorBeacons) {
        for (SensorBeacon sensorBeacon : sensorBeacons) {
            int x1 = sensorBeacon.beacon.x;
            int x2 = sensorBeacon.sensor.x;
            int y1 = sensorBeacon.beacon.y;
            int y2 = sensorBeacon.sensor.y;
            sensorBeacon.distance = calculateManhattanDistance(x1, x2, y1, y2);
        }
    }

    private static int calculateManhattanDistance(int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static ArrayList<SensorBeacon> parseInput(List<String> l) {
        ArrayList<SensorBeacon> array = new ArrayList<>(l.size());
        for (String s : l) {
            String[] SB = s.split(":");
            XY sensor = parseXY(SB[0]);
            XY beacon = parseXY(SB[1]);
            SensorBeacon sensorBeacon = new SensorBeacon(sensor, beacon, 0);
            array.add(sensorBeacon);
        }
        return array;
    }

    private static XY parseXY(String s) {
        XY xy = new XY();
        String[] cords = s.split(",");
        String x = cords[0].substring(cords[0].indexOf("=") + 1);
        String y = cords[1].substring(cords[1].indexOf("=") + 1);
        xy.x = Integer.parseInt(x);
        xy.y = Integer.parseInt(y);
        return xy;
    }


    private static class SensorBeacon {
        XY sensor;
        XY beacon;
        int distance;

        public SensorBeacon(XY sensor, XY beacon, int distance) {
            this.sensor = sensor;
            this.beacon = beacon;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "SensorBeacon{" +
                    "sensor=" + sensor +
                    ", beacon=" + beacon +
                    ", distance=" + distance +
                    '}';
        }
    }
}
