package util;

public class Util {

    public static void print(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
    }
    public static void print(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char anInt : chars) {
                System.out.print(anInt);
            }
            System.out.println();
        }
    }
    public static void print(String[][] matrix) {
        for (String[] ints : matrix) {
            for (String anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
    }
    public static void print(int[] arr) {
        for (int ints : arr) {
            System.out.print(ints + " ");
        }
        System.out.println();
    }

    public static String[][] deepCopyMatrix(String[][] cave) {
        String[][] copy = new String[cave.length][cave[0].length];
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                copy[i][j]=cave[i][j];
            }
        }
        return copy;
    }
}
