package util;

public final class TimeUtil {

    private static long start;

    private TimeUtil() {
    }

    public static void startTime() {
        System.out.println("***********Start*************");
        start = System.currentTimeMillis();
    }

    public static void endTime() {
        System.out.println("Time: " + (System.currentTimeMillis() - start) + " ms");
        System.out.println("***********End*************");
        System.out.println();
        start = 0;
    }
}
