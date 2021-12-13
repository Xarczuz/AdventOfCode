package days;

import classes.Fold;
import classes.Origami;
import java.util.ArrayList;

public class Day13 {

    public int part1(ArrayList<String> strings) {
        Origami origamiPaper = parse(strings);

        if (origamiPaper.folds.get(0).xy.equals("y")) {

            origamiPaper.matrix = foldY(origamiPaper.folds.get(0).point, origamiPaper.matrix);
        } else {
            origamiPaper.matrix = foldX(origamiPaper.folds.get(0).point, origamiPaper.matrix);
        }

        return count(origamiPaper.matrix);
    }

    public int part2(ArrayList<String> strings) {
        Origami origamiPaper = parse(strings);

        for (Fold fold : origamiPaper.folds) {
            if (fold.xy.equals("y")) {
                origamiPaper.matrix = foldY(fold.point, origamiPaper.matrix);
            } else {
                origamiPaper.matrix = foldX(fold.point, origamiPaper.matrix);
            }
        }
        print(origamiPaper.matrix);

        return count(origamiPaper.matrix);
    }

    private void print(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }

    public static int count(int[][] matrix) {
        int sum = 0;
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (anInt > 0) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private int[][] foldX(int point, int[][] matrix) {
        int[][] newMatrix = new int[matrix.length][point];

        for (int y = 0; y < matrix.length; y++) {
            System.arraycopy(matrix[y], 0, newMatrix[y], 0, point);
        }

        for (int y = 0; y < matrix.length; y++) {

            for (int x = point + 1, xFold = point - 1; x < matrix[0].length; x++, xFold--) {
                if (xFold < 0) {
                    break;
                }
                if (newMatrix[y][xFold] == 0) {
                    newMatrix[y][xFold] = matrix[y][x];
                }
            }

        }

        return newMatrix;
    }

    private int[][] foldY(int point, int[][] matrix) {
        int[][] newMatrix = new int[point][matrix[0].length];

        for (int y = 0; y < point; y++) {
            System.arraycopy(matrix[y], 0, newMatrix[y], 0, matrix[0].length);
        }

        for (int y = point + 1, yfold = point - 1; y < matrix.length; y++, yfold--) {
            if (yfold < 0) {
                break;
            }
            for (int x = 0; x < matrix[0].length; x++) {
                if (newMatrix[yfold][x] == 0) {
                    newMatrix[yfold][x] = matrix[y][x];
                }
            }
        }
        return newMatrix;
    }

    private Origami parse(ArrayList<String> strings) {
        int maxX = 0;
        int maxY = 0;
        ArrayList<Fold> folds = new ArrayList<>();
        for (String string : strings) {
            if (string.contains(",")) {
                String[] xy = string.split(",");
                maxX = Math.max(maxX, Integer.parseInt(xy[0]));
                maxY = Math.max(maxY, Integer.parseInt(xy[1]));
            } else if (string.contains("=")) {
                String[] s = string.split(" ");
                String[] s1 = s[2].split("=");
                folds.add(new Fold(s1[0], Integer.parseInt(s1[1])));
            }
        }

        int[][] matrix = new int[maxY + 1][maxX + 1];
        for (String string : strings) {
            if (string.contains(",")) {
                String[] xy = string.split(",");
                int x = Integer.parseInt(xy[0]);
                int y = Integer.parseInt(xy[1]);
                matrix[y][x] = 1;
            }
        }
        return new Origami(matrix, folds);
    }
}
