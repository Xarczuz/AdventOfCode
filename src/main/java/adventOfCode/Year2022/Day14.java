package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;
import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class Day14 {

    private static int x;
    private static int y;
    private static final int defaultX = 500;
    private static final int defaultY = 0;

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        List<String> l = FileUtil.readfile(Day14.class);
        List<String> l2 = FileUtil.readfileExempel(Day14.class);

        TimeUtil.startTime();
//        oneStar(l2);
//        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {
        String[][] cave = parseInput(l);
        createFloor(cave);
        startingPoint();
        boolean done = false;
        long sum = 0;
        while (!done) {
            done = fallingSandTwoStar(cave);
        }
        sum = calculateSand(cave, sum);
        Util.print(cave);
        System.out.println("Sum: " + sum);
    }

    private static void createFloor(String[][] cave) {
        for (int i = 0; i < cave[0].length; i++) {
            cave[cave.length - 1][i] = "#";
        }
    }

    private static void oneStar(List<String> l) {
        String[][] cave = parseInput(l);
        startingPoint();
        boolean done = false;
        while (!done) {
            done = fallingSandOneStar(cave);
        }
        long sum = 0;
        sum = calculateSand(cave, sum);
        writeNumbersToEachRow(cave);
        Util.print(cave);
        System.out.println("Sum: " + sum);

    }

    private static void writeNumbersToEachRow(String[][] cave) {
        for (int i = 0; i < cave.length; i++) {
            cave[i][0] = "" + i;
        }
    }

    private static void startingPoint() {
        x = defaultX;
        y = defaultY;
    }

    private static long calculateSand(String[][] cave, long sum) {
        for (int y = 0; y < cave.length; y++) {
            for (int x = 0; x < cave[0].length; x++) {
                if (cave[y][x] == "O") {
                    sum++;
                }
            }
        }
        return sum;
    }

    private static boolean fallingSandOneStar(String[][] cave) {
        if (y + 1 >= cave.length) {
            return true;
        }
        sandLogic(cave);
        return false;
    }

    private static boolean fallingSandTwoStar(String[][] cave) {
        if (cave[defaultY][defaultX] == "O") {
            return true;
        }
        sandLogic(cave);
        return false;
    }

    private static void sandLogic(String[][] cave) {
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
    }

    private static String[][] parseInput(List<String> l) {
        int maxY = findMaxY(l);
        String[][] cave = new String[maxY][1000];
        insertDot(cave);
        insertWalls(l, cave);
        return cave;
    }

    private static int findMaxY(List<String> l) {
        int max = 0;
        for (String s : l) {
            String[] ar = s.split("->");
            for (String a : ar) {
                String[] newAr = a.split(",");
                int tempY = Integer.parseInt(newAr[1].trim());
                max = Math.max(tempY, max);
            }
        }
        return max + 3;
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
            int x = Integer.parseInt(cord[0].trim());
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
