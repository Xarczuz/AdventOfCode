package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;
import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class Day14 {
    static int offsetX = 490;
    static int offsetY = 430;
    private static int x;
    private static int y;
    private static final int defaultX = 500 - offsetX;
    private static final int defaultY = 0;

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        List<String> l = FileUtil.readfile(Day14.class);
        List<String> l2 = FileUtil.readfileExempel(Day14.class);

        TimeUtil.startTime();
        oneStar(l2);
        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {
    }

    private static void oneStar(List<String> l) {
        String[][] cave = parseInput(l);
        cave[0][500 - offsetX] = ".";
        x = 500 - offsetX;
        y = 0;
        boolean done = false;
        while (!done) {
            done = fallingSand(cave);
        }
        int sum = 0;
        for (int y = 0; y < cave.length; y++) {
            for (int x = 0; x < cave[0].length; x++) {
                if (cave[y][x] == "O") {
                    sum++;
                }
            }
        }
        Util.print(cave);
        System.out.println("Sum: " + sum);

    }

    private static boolean fallingSand(String[][] cave) {
        if (y + 1 >= cave.length) {
            System.out.println("done");
            return true;
        }
        if (cave[y + 1][x] == ".") {
            y++;
        } else if ((cave[y + 1][x] == "#") && (cave[y + 1][x - 1] == "#") && (cave[y + 1][x + 1] == "#")) {
            cave[y][x] = "O";
            y = defaultY;
            x = defaultX;
        } else if ((cave[y + 1][x] == "#") && (cave[y + 1][x - 1] == "O") && (cave[y + 1][x + 1] == "O")) {
            cave[y][x] = "O";
            y = defaultY;
            x = defaultX;
        } else if ((cave[y + 1][x] == "#") && (cave[y + 1][x - 1] == "#") && (cave[y + 1][x + 1] == "O")) {
            cave[y][x] = "O";
            y = defaultY;
            x = defaultX;
        } else if ((cave[y + 1][x] == "#") && (cave[y + 1][x - 1] == "O") && (cave[y + 1][x + 1] == "#")) {
            cave[y][x] = "O";
            y = defaultY;
            x = defaultX;
        } else if (cave[y + 1][x] == "O") {
            if (cave[y + 1][x - 1] == ".") {
                y++;
                x--;
            } else if (cave[y + 1][x + 1] == ".") {
                y++;
                x++;
            } else if (cave[y + 1][x] == "O" && (cave[y + 1][x - 1] == "O" || cave[y + 1][x + 1] == "O" || ((cave[y][x - 1] == "#") || cave[y][x + 1] == "#"))) {
                cave[y][x] = "O";
                y = defaultY;
                x = defaultX;
            }
        } else if (cave[y + 1][x] == "#" && cave[y + 1][x - 1] == ".") {
            y++;
            x--;
        } else if (cave[y + 1][x] == "#" && cave[y + 1][x + 1] == ".") {
            y++;
            x++;
        }

        return false;
    }

    private static String[][] parseInput(List<String> l) {
        String[][] cave = new String[600 - offsetY][630 - offsetX];
        insertDot(cave);
        insertWalls(l, cave);
        return cave;
    }

    private static void insertWalls(List<String> l, String[][] cave) {
        for (String s : l) {
            String[] walls = s.split("->");
            int prevX = -1;
            int prevY = -1;
            insertWall(walls, prevX, prevY, cave);
        }
    }

    private static void insertWall(String[] walls, int prevX, int prevY, String[][] sS) {
        for (String wall : walls) {
            String[] cord = wall.split(",");
            int x = Integer.parseInt(cord[0].trim()) - offsetX;
            int y = Integer.parseInt(cord[1].trim());
            String wallChar = "#";
            if (prevX != -1) {
                if (y > prevY) {
                    for (int i = prevY; i < y; i++) {
                        sS[i][x] = wallChar;
                    }
                }
                if (x > prevX) {
                    for (int i = prevX; i < x; i++) {
                        sS[y][i] = wallChar;
                    }
                }
                if (y < prevY) {
                    for (int i = prevY; i > y; i--) {
                        sS[i][x] = wallChar;
                    }
                }
                if (x < prevX) {
                    for (int i = prevX; i > x; i--) {
                        sS[y][i] = wallChar;
                    }
                }
            }
            sS[y][x] = wallChar;
            prevX = x;
            prevY = y;
        }
    }

    public static void insertDot(String[][] cave) {
        for (String[] ints : cave) {
            Arrays.fill(ints, ".");
        }
    }
}
