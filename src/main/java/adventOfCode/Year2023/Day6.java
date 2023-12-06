package adventOfCode.Year2023;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day6 {


    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day6.class);
        List<String> l2 = FileUtil.readfileExempel(Day6.class);
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
        ArrayList<Race> races = parseString(l);
        convertToOneRace(races);
        long result = calculateWins(races);
        System.out.println("Two star: " + result);
    }

    private static void convertToOneRace(ArrayList<Race> races) {
        StringBuilder time = new StringBuilder();
        StringBuilder distance = new StringBuilder();
        for (Race race : races) {
            time.append(race.time);
            distance.append(race.distance);
        }
        races.clear();
        Race e = new Race();
        e.time = Long.parseLong(time.toString());
        e.distance = Long.parseLong(distance.toString());
        races.add(e);
    }

    private static void oneStar(List<String> l) {
        ArrayList<Race> races = parseString(l);
        long result = calculateWins(races);
        System.out.println("One star: " + result);
    }

    private static long calculateWins(ArrayList<Race> races) {
        long sum = 1;
        for (Race race : races) {
            long T = race.time;
            long D = race.distance;
            double B = T / 2.0;
            double A = B * (B) - D;
            double x1 = Math.sqrt(A) + B;
            double x2 = -Math.sqrt(A) + B;
            x1 = Math.ceil(x1 - 1);
            x2 = Math.floor(x2 + 1);
            sum *= (long) (x1 - x2 + 1);
        }
        return sum;
    }

    private static ArrayList<Race> parseString(List<String> l) {
        ArrayList<Race> races = new ArrayList<>();
        ArrayList<Integer> time = parseInts(l.get(0).split(":")[1]);
        ArrayList<Integer> distance = parseInts(l.get(1).split(":")[1]);
        for (int j = 0; j < distance.size(); j++) {
            Race e = new Race();
            e.distance = distance.get(j);
            e.time = time.get(j);
            races.add(e);
        }
        return races;
    }

    private static ArrayList<Integer> parseInts(String l) {
        ArrayList<Integer> integers = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : l.toCharArray()) {
            if (c >= '0' && c <= '9') {
                sb.append(c);
            } else {
                if (!sb.toString().trim().isEmpty()) {

                    integers.add(Integer.parseInt(sb.toString()));
                    sb = new StringBuilder();
                }
            }
        }
        integers.add(Integer.parseInt(sb.toString()));
        return integers;
    }


    private static class Race {
        long time;
        long distance;
    }
}
