package days;

import classes.XY;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day15 {
    private final int maxStrikes = 5;
    int[][] mazeMatrix = new int[0][];
    HashMap<Integer, Integer> fastPath = new HashMap<>();
    HashMap<Integer, Integer> fastPath2 = new HashMap<>();

    public long part1(ArrayList<String> strings) {
        mazeMatrix = parse(strings);
        HashSet<XY> visitedXY = new HashSet<>();
        visitedXY.add(new XY(0, 0));

        findPath1(-1, 0, 0, 0, 0);
        return min;
    }

    int min = 800;

    private void findPath1(int tot, int x, int y, int depth, int strikes) {
        if (x == mazeMatrix[0].length - 1 && y == mazeMatrix.length - 1) {
            tot = tot + mazeMatrix[y][x];
            if (tot < min) {
                min = tot;
                System.out.println(min);
            }
            return;
        }

        int tot1 = tot + mazeMatrix[y][x];
        if (tot1 > min) {
            return;
        }
        if (tot1 >= getOrDefault(depth)) {
            strikes++;
            if (strikes > maxStrikes) {//(10 - 370) (15 - 368) (4 - 370)
                return;
            }
        } else {
            fastPath.put(depth, tot1);
            strikes = 0;
        }
        yPlus(tot1, x, y, depth, strikes);
        yMinus(tot1, x, y, depth, strikes);
        xPlus(tot1, x, y, depth, strikes);
        xMinus(tot1, x, y, depth, strikes);
    }

    private void xPlus(int tot, int x, int y, int depth, int strikes) {
        int i = x + 1;
        if (i > mazeMatrix[0].length - 1) {
            return;
        }

        findPath1(tot, i, y, depth + 1, strikes);
    }

    private void yPlus(int tot, int x, int y, int depth, int strikes) {
        int i = y + 1;
        if (i > mazeMatrix.length - 1) {
            return;
        }

        findPath1(tot, x, i, depth + 1, strikes);
    }

    private void xMinus(int tot, int x, int y, int depth, int strikes) {
        int i = x - 1;
        if (i < 0) {
            return;
        }

        findPath1(tot, i, y, depth + 1, strikes);
    }

    private void yMinus(int tot, int x, int y, int depth, int strikes) {
        int i = y - 1;
        if (i < 0) {
            return;
        }
        findPath1(tot, x, i, depth + 1, strikes);
    }

    private int getOrDefault(int depth) {
        return fastPath.getOrDefault(depth, Integer.MAX_VALUE);
    }

    private HashMap<Integer, Integer> stupidSolution(int[][] maze) {
        int tot = 0;
        HashMap<Integer, Integer> fastPath = new HashMap<>();
        int depth = 1;
        for (int i = 1; i < maze.length - 1; i++) {
            tot += maze[i][0];
            fastPath.put(depth, tot);
            depth++;
        }

        for (int i = 0; i < maze[0].length; i++) {
            tot += maze[maze.length - 1][i];
            fastPath.put(depth, tot);
            depth++;
        }
        return fastPath;
    }

    private int[][] parse(ArrayList<String> strings) {
        int[][] maze = new int[strings.size()][strings.get(0).length()];
        for (int i = 0; i < strings.size(); i++) {
            for (int j = 0; j < strings.get(0).length(); j++) {
                maze[i][j] = strings.get(i).charAt(j) - 48;
            }
        }
        return maze;
    }

}
