package adventOfCode.Year2023;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day2.class);
        List<String> l2 = FileUtil.readfileExempel(Day2.class);
        TimeUtil.startTime();
        oneStar(l);//2164
        oneStar(l2);
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l);
        twoStar(l2);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {
        ArrayList<Game> games = parseString(l);

    }

    private static void oneStar(List<String> l) {
        ArrayList<Game> games = parseString(l);
        int sum = 0;
        for (Game game : games) {
            boolean valid = true;
            for (Set set : game.sets) {
                if (set.green > 13 || set.red > 12 || set.blue > 14) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                sum += game.id;
            }
        }
        System.out.println("One Star: " + sum);
    }

    private static ArrayList<Game> parseString(List<String> l) {
        ArrayList<Game> games = new ArrayList<>();
        for (String s : l) {
            int id = getID(s);
            ArrayList<Set> set = getCubes(s);
            Game game = new Game(id, set);
            games.add(game);
        }
        return games;
    }

    private static ArrayList<Set> getCubes(String s) {
        String games = s.split(":")[1];
        String[] game = games.split(";");
        ArrayList<Set> arr = new ArrayList<>();
        for (String g : game) {
            String[] cubes = g.split(",");
            Set gameSet = new Set(0, 0, 0);
            arr.add(gameSet);
            for (String cube : cubes) {
                String[] splits = cube.trim().split(" ");
                String amount = splits[0].trim();
                int cubesColor = Integer.parseInt(amount);
                if ("blue".equals(splits[1])) {
                    gameSet.blue = cubesColor;
                } else if ("red".equals(splits[1])) {
                    gameSet.red = cubesColor;
                } else if ("green".equals(splits[1])) {
                    gameSet.green = cubesColor;
                }
            }
        }
        return arr;
    }

    private static int getID(String s) {
        String idString = s.split(":")[0].split(" ")[1];
        return Integer.parseInt(idString);
    }

    private record Game(int id, ArrayList<Set> sets) {
    }

    private static final class Set {
        int red;
        int green;
        int blue;

        private Set(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        @Override
        public String toString() {
            return "Set{" +
                    "red=" + red +
                    ", green=" + green +
                    ", blue=" + blue +
                    '}';
        }
    }
}
