package Year2021;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import adventOfCode.Year2021.Day9;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day9Test {

    @Test
    void testPart1ex() throws IOException {
        int res = new Day9().part1(FileUtil.readFromFile("adventOfCode/Year2021/day9ex.txt"));
        assertEquals(15, res);
    }

    @Test
    void testPart1() throws IOException {
        int res = new Day9().part1(FileUtil.readFromFile("adventOfCode/Year2021/day9.txt"));
        assertEquals(572, res);
    }

    @Test
    void testPart2ex() throws IOException {
        long res = new Day9().part2(FileUtil.readFromFile("adventOfCode/Year2021/day9ex.txt"));
        assertEquals(1134, res);
    }

    @Test
    void testPart2() throws IOException {
        long res = new Day9().part2(FileUtil.readFromFile("adventOfCode/Year2021/day9.txt"));
        assertEquals(847044, res);
    }
}