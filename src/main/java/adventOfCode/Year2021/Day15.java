package adventOfCode.Year2021;

import java.util.ArrayList;
import java.util.HashMap;

public class Day15 {
    int[][] mazeMatrix = new int[0][];
    HashMap<Integer, Integer> fastPath = new HashMap<>();
    int[][] mazeMatrixPaths = new int[0][];
    public long part1(ArrayList<String> strings) {
        mazeMatrix = parse(strings);

        return min;
    }

    public long part12(ArrayList<String> strings) {
        mazeMatrix = parse(strings);
        mazeMatrixPaths = init();
        findPath(-1, 0, 0);

        return min;
    }

    private void findPath(int tot, int x, int y) {
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
        if (tot1>mazeMatrixPaths[y][x]){
            return;
        }else{
            mazeMatrixPaths[y][x]=tot1;
        }
        yPlus(tot1, x, y);
        xPlus(tot1, x, y);
        yMinus(tot1, x, y);
        xMinus(tot1, x, y);
    }

    private void xMinus(int tot1, int x, int y) {
        int i = x - 1;
        if (i < 0) {
            return;
        }
        findPath(tot1,i,y);
    }

    private void yMinus(int tot1, int x, int y) {
        int i = y - 1;
        if (i < 0) {
            return;
        }
        findPath(tot1,x,i);

    }

    private void xPlus(int tot1, int x, int y) {
        int i = x + 1;
        if (i > mazeMatrix[0].length - 1) {
            return;
        }
        findPath(tot1,i,y);

    }

    private void yPlus(int tot1, int x, int y) {
        int i = y + 1;
        if (i > mazeMatrix.length - 1) {
            return;
        }
        findPath(tot1,x,i);

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
        yPlus(tot1, x, y, depth, strikes);
        xPlus(tot1, x, y, depth, strikes);
        yMinus(tot1, x, y, depth, strikes);
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

    private int[][] parse(ArrayList<String> strings) {
        int[][] maze = new int[strings.size()*5][strings.get(0).length()*5];
        for (int i = 0; i < strings.size(); i++) {
            for (int j = 0; j < strings.get(0).length(); j++) {
                maze[i][j] = strings.get(i).charAt(j) - 48;

                maze[strings.size()+i][j] =   mod9(((strings.get(i).charAt(j) - 48)+1));
                maze[2*strings.size()+i][j] = mod9(((strings.get(i).charAt(j) - 48)+2));
                maze[3*strings.size()+i][j] = mod9(((strings.get(i).charAt(j) - 48)+3));
                maze[4*strings.size()+i][j] = mod9(((strings.get(i).charAt(j) - 48)+4));
                maze[i][strings.get(0).length()+j] = mod9(((strings.get(i).charAt(j) - 48)+1));
                maze[i][2*strings.get(0).length()+j] = mod9(((strings.get(i).charAt(j) - 48)+2));
                maze[i][3*strings.get(0).length()+j] = mod9(((strings.get(i).charAt(j) - 48)+3));
                maze[i][4*strings.get(0).length()+j] = mod9(((strings.get(i).charAt(j) - 48)+4));
            }
        }
//        for (int i = 0; i < strings.size(); i++) {
//            for (int j = 0; j < strings.get(0).length(); j++) {
//                maze[i][j] = strings.get(i).charAt(j) - 48;
//                maze[i][strings.get(0).length()+j] = ((strings.get(i).charAt(j) - 48)+1)%9;
//                maze[i][2*strings.get(0).length()+j] = ((strings.get(i).charAt(j) - 48)+2)%9;
//                maze[i][3*strings.get(0).length()+j] = ((strings.get(i).charAt(j) - 48)+3)%9;
//                maze[i][4*strings.get(0).length()+j] = ((strings.get(i).charAt(j) - 48)+4)%9;
//            }
//        }
        return maze;
    }

    private int mod9(int i) {
        return i>9?1:i;
    }

    private int[][] init() {
        int[][] m = new int[mazeMatrix.length*5][];
        for (int i = 0; i < mazeMatrix.length*5; i++) {
            int[] n = new int[mazeMatrix[0].length*5];
            for (int j = 0; j < mazeMatrix[0].length*5; j++) {
                n[j] = Integer.MAX_VALUE;
            }
            m[i]=n;
        }

        return m;
    }
}
