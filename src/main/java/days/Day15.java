package days;

import java.util.ArrayList;
import java.util.HashMap;

public class Day15 {
    int[][] mazeMatrix = new int[0][];
    HashMap<Integer, Integer> fastPath = new HashMap<>();

    public long part1(ArrayList<String> strings) {
        mazeMatrix = parse(strings);

        fastPath = stupidSolution(mazeMatrix);

        findPath(-1, 0, 0, 0, 0);

        return min;
    }

    int min = 800;

    private void findPath(int tot, int x, int y, int depth, int strikes) {
//        if (strikes > 20) {//(10 - 370) (15 - 368) (4 - 370)
//            return;
//        }
//        if (tot > getOrDefault(depth)) {
//            strikes++;
//        } else {
//            fastPath.put(depth, tot);
//            strikes = 0;
//        }

        if (x == mazeMatrix[0].length - 1 && y == mazeMatrix.length - 1) {
            tot = tot + mazeMatrix[y][x];
            if (tot < min) {
                min = tot;
                System.out.println(min);
            }
            return;
        }
        yPlus(tot, x, y, depth, strikes);
        xPlus(tot, x, y, depth, strikes);
    }

    private void xPlus(int tot, int x, int y, int depth, int strikes) {
        int i = x + 1;
        if (i > mazeMatrix[0].length - 1) {
            return;
        }

        int tot1 = tot + mazeMatrix[y][x];
        if (tot1 > min) {
            return;
        }
        findPath(tot1, i, y, depth + 1, strikes);

    }

    private void yPlus(int tot, int x, int y, int depth, int strikes) {
        int i = y + 1;
        if (i > mazeMatrix.length - 1) {
            return;
        }
        int tot1 = tot + mazeMatrix[y][x];
        if (tot1 > min) {
            return;
        }
        findPath(tot1, x, i, depth + 1, strikes);
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
