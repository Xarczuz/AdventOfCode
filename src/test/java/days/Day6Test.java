package days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import classes.Fish;
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day6Test {

    @Test
    void testLanternfish1ex() throws IOException {
        long fishes = new Day6().Lanternfish1(FileUtil.readFromFile("day6ex.txt"), 80);
        assertEquals(5934, fishes);
    }

    @Test
    void testLanternfish1() throws IOException {
        long fishes = new Day6().Lanternfish1(FileUtil.readFromFile("day6.txt"), 80);
        assertEquals(360268, fishes);
    }

    @Test
    void testLanternfish2ex() throws IOException {
        BigInteger fishes = new Day6().Lanternfish2(FileUtil.readFromFile("day6ex.txt"));
        assertEquals("26984457539", fishes.toString());
    }

    @Test
    void testLanternfish2() throws IOException {
        BigInteger fishes = new Day6().Lanternfish2(FileUtil.readFromFile("day6.txt"));
        assertEquals("1632146183902", fishes.toString());
    }

    @Test
    void testGrowLanternfish() {
        LinkedList<Fish> f = new LinkedList<>();
        f.addLast(new Fish(0));
        LinkedList<Fish> fishes = new Day6().grow(f, 80);
        assertEquals(1421, fishes.size());
    }

    @Test
    void testGrowLanternfish3() {
        for (int i = 2; i < 9; i++) {
            Fish fishes1 = new Fish(0);
            fishes1.unsyncFishes[i] = 1;
            BigInteger fishes = new Day6().grow2(fishes1, 256);
            System.out.println(fishes);
        }
    }
}