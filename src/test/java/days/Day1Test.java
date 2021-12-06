package days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day1Test {

    @Test
    void testDay1() throws IOException {
        long res = Day1.countDiff(FileUtil.readFromFile("day1.txt"));

        assertEquals(1527,res);
    }

    @Test
    void testDay1Level2() throws IOException {
        long res = Day1.countDiffSums(FileUtil.readFromFile("day1.txt"));

        System.out.println(res);
    }
    @Test
    void testDay1Level2Help() throws IOException {
        long res = Day1.countDiffSums(FileUtil.readFromFile("day1ex.txt"));

        assertEquals(5,res);
    }
}