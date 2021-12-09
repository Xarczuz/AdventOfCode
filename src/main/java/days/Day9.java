package days;

import java.util.ArrayList;

public class Day9 {

    public int part1(ArrayList<String> strings) {
        int[][] matrix = parse(strings);
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int nr = matrix[i][j];
                if (checkNorth(nr, i, j, matrix)
                        && checkEast(nr, i, j, matrix)
                        && checkSouth(nr, i, j, matrix)
                        && checkWest(nr, i, j, matrix)) {
                    sum += nr + 1;
                }
            }
        }
        return sum;
    }

    public long part2(ArrayList<String> strings) {
        int[][] matrix = parse(strings);
        ArrayList<Integer[]> low = lowPoints(matrix);
        ArrayList<Integer> basins = new ArrayList<>();
        for (Integer[] integers : low) {
            int i = integers[0];
            int j = integers[1];
            int size = basins(i, j, matrix, 0);
            basins.add(size);
        }
        basins.sort(Integer::compareTo);
        long sum = 1;
        for (int i = basins.size() - 1, count = 0; count < 3; i--, count++) {
            sum *= basins.get(i);
        }
        return sum;
    }

    private int basins(int i, int j, int[][] matrix, int basin) {
        if (i < 0 || j < 0 || i > matrix.length - 1 || j > matrix[0].length - 1) {
            return basin;
        }
        if (matrix[i][j] == 9 || matrix[i][j] == -99) {
            return basin;
        }
        if (matrix[i][j] < 9 && matrix[i][j] >= 0) {
            matrix[i][j] = -99;
            basin++;
            basin = basins(i + 1, j, matrix, basin);
            basin = basins(i - 1, j, matrix, basin);
            basin = basins(i, j + 1, matrix, basin);
            basin = basins(i, j - 1, matrix, basin);
        }
        return basin;
    }

    private ArrayList<Integer[]> lowPoints(int[][] matrix) {
        ArrayList<Integer[]> lowPoints = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int nr = matrix[i][j];
                if (checkNorth(nr, i, j, matrix)
                        && checkEast(nr, i, j, matrix)
                        && checkSouth(nr, i, j, matrix)
                        && checkWest(nr, i, j, matrix)) {
                    lowPoints.add(new Integer[] {i, j});
                }
            }
        }
        return lowPoints;
    }

    private static boolean checkWest(int nr, int i, int j, int[][] matrix) {
        if (j - 1 < 0) {
            return true;
        }
        int west = matrix[i][j - 1];
        return nr < west;
    }

    private static boolean checkSouth(int nr, int i, int j, int[][] matrix) {
        if (i + 1 > matrix.length - 1) {
            return true;
        }
        int south = matrix[i + 1][j];
        return nr < south;
    }

    private static boolean checkEast(int nr, int i, int j, int[][] matrix) {
        if (j + 1 > matrix[i].length - 1) {
            return true;
        }
        int east = matrix[i][j + 1];
        return nr < east;
    }

    private static boolean checkNorth(int nr, int i, int j, int[][] matrix) {
        if (i - 1 < 0) {
            return true;
        }
        int north = matrix[i - 1][j];
        return nr < north;
    }

    private int[][] parse(ArrayList<String> strings) {
        int[][] matrix = new int[strings.size()][];
        for (int j = 0, stringsSize = strings.size(); j < stringsSize; j++) {
            String string = strings.get(j);
            int[] nrs = new int[string.length()];
            char[] charArray = string.toCharArray();
            for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
                char c = charArray[i];
                nrs[i] = c - 48;
            }
            matrix[j] = nrs;
        }
        return matrix;
    }
}
