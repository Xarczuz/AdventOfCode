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

    public static void print(int[] arr) {
        for (int ints : arr) {
            System.out.print(ints + " ");
        }
        System.out.println();
    }

}
