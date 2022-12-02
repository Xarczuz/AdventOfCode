package adventOfCode.Year2022;

import util.FileUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Day2 {

    static HashMap<String, String> map;

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day2.class);

        oneStar(l);
        twoStar(l);
    }

    private static void twoStar(List<String> l) {
        map = new HashMap<>();
        map.put("lose", "0");
        map.put("draw", "3");
        map.put("win", "6");
        map.put("x", "lose");
        map.put("y", "draw");
        map.put("z", "win");
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        int playerScore = 0;
        for (String row : l) {
            String[] round = row.split(" ");
            int opponent = Integer.parseInt(map.get(round[0].toLowerCase(Locale.ROOT)));
            String player = map.get(round[1].toLowerCase(Locale.ROOT));
            int needToPlayForRes = findType(player, opponent);
            int playerRes = Integer.parseInt(map.get(player));


            playerScore += needToPlayForRes + playerRes;

        }
        System.out.println("score: " + playerScore);
    }

    private static int findType(String player, int opponent) {
        if (opponent == 1 && "lose".equalsIgnoreCase(player)) {
            return 3;
        }
        if (opponent == 2 && "lose".equalsIgnoreCase(player)) {
            return 1;
        }
        if (opponent == 3 && "lose".equalsIgnoreCase(player)) {
            return 2;
        }
        if (opponent == 1 && "win".equalsIgnoreCase(player)) {
            return 2;
        }
        if (opponent == 2 && "win".equalsIgnoreCase(player)) {
            return 3;
        }
        if (opponent == 3 && "win".equalsIgnoreCase(player)) {
            return 1;
        }
        return opponent;
    }

    private static void oneStar(List<String> l) {
        map = new HashMap<>();
        map.put("lost", "0");
        map.put("draw", "3");
        map.put("win", "6");
        map.put("a", "1");
        map.put("x", "1");
        map.put("b", "2");
        map.put("y", "2");
        map.put("c", "3");
        map.put("z", "3");
        int playerScore = 0;
        for (String row : l) {
            String[] round = row.split(" ");
            int opponent = Integer.parseInt(map.get(round[0].toLowerCase(Locale.ROOT)));
            int player = Integer.parseInt(map.get(round[1].toLowerCase(Locale.ROOT)));

            String result = vs(player, opponent);
            int roundScore = Integer.parseInt(map.get(result));
            playerScore += roundScore + player;

        }
        System.out.println("score: " + playerScore);

    }

    private static String vs(int player1, int player2) {
        if (player1 == 1 && player2 == 2) {
            return "lost";
        }
        if (player1 == 1 && player2 == 3) {
            return "win";
        }
        if (player1 == 2 && player2 == 1) {
            return "win";
        }
        if (player1 == 2 && player2 == 3) {
            return "lost";
        }
        if (player1 == 3 && player2 == 1) {
            return "lost";
        }
        if (player1 == 3 && player2 == 2) {
            return "win";
        }
        return "draw";
    }


}
