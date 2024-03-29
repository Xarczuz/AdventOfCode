package Year2021;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import adventOfCode.Year2021.Day3;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day3Test {

    @Test
    void testDay3ex() throws IOException {
        long s = Day3.day3(FileUtil.readFromFile("adventOfCode/Year2021/day3ex.txt"));

        assertEquals(198, s);
    }

    @Test
    void testDay3() throws IOException {
        long s = Day3.day3(FileUtil.readFromFile("adventOfCode/Year2021/day3.txt"));

        assertEquals(4006064, s);
    }

    @Test
    void testDay3exLevel2() throws IOException {
        long s = Day3.day3Level2(FileUtil.readFromFile("adventOfCode/Year2021/day3ex.txt"));

        assertEquals(230, s);
    }
    @Test
    void testDay3Level2() throws IOException {
        long s = Day3.day3Level2(FileUtil.readFromFile("adventOfCode/Year2021/day3.txt"));

        assertEquals(5941884, s);
    }
}