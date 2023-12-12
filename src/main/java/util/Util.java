package util;

public class Util {

    public static void main(String[] args) {
        System.out.println(Util.factorial(20));
    }

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
                copy[i][j] = cave[i][j];
            }
        }
        return copy;
    }

    public static long gcd(long i, long i1) {
        while (i != i1) {
            if (i > i1) {
                i = i - i1;
            } else {
                i1 = i1 - i;
            }
        }
        return i;
    }

    public static boolean isWithinRangeOfMatrix(int y, int x, char[][] matrix) {
        return y <= matrix.length - 1 && y >= 0 && x <= matrix[0].length - 1 && x >= 0;
    }

    public static long factorial(int n) {
        long sum = 1;
        for (int i = 1; i <= n; i++) {
            sum *= i;
        }
        return sum;
    }
}
