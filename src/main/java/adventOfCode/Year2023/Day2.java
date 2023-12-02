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

    }

    private static void oneStar(List<String> l) {
        ArrayList<Game> games = parseString(l);
        int sum = 0;
        for (Game game : games) {
            if (game.red && game.blue && game.green) {
                sum += game.id;
            }
        }
        System.out.println("One Star: " + sum);
    }

    private static ArrayList<Game> parseString(List<String> l) {
        ArrayList<Game> games = new ArrayList<>();
        for (String s : l) {
            int id = getID(s);
            boolean blue = getCubes(s, "blue");
            boolean red = getCubes(s, "red");
            boolean green = getCubes(s, "green");
            Game game = new Game(id, blue, red, green);
            games.add(game);
        }
        return games;
    }

    private static boolean getCubes(String s, String color) {
        String games = s.split(":")[1];
        String[] game = games.split(";");
        boolean validGame = true;
        for (String g : game) {
            String[] cubes = g.split(",");
            for (String cube : cubes) {
                if (cube.contains(color)) {
                    String[] splits = cube.trim().split(" ");
                    String amount = splits[0].trim();
                    int cubesColor = Integer.parseInt(amount);
                    if ("blue".equals(color)) {
                        validGame = validGame && cubesColor <= 14;
                    } else if ("red".equals(color)) {
                        validGame = validGame && cubesColor <= 12;
                    } else if ("green".equals(color)) {
                        validGame = validGame && cubesColor <= 13;
                    }

                }
            }
        }
        return validGame;
    }

    private static int getID(String s) {
        String idString = s.split(":")[0].split(" ")[1];
        return Integer.parseInt(idString);
    }

    private record Game(int id, boolean blue, boolean red, boolean green) {
    }
}
