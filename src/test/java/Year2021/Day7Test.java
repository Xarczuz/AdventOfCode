package Year2021;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import adventOfCode.Year2021.Day7;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day7Test {

    @Test
    void testPart1ex() throws IOException {
        int res = new Day7().part1(FileUtil.readFromFile("adventOfCode/Year2021/day7ex.txt"));
        assertEquals(37, res);
    }

    @Test
    void testtestPart1ex() throws IOException {
        int res = new Day7().part1(FileUtil.readFromFile("adventOfCode/Year2021/day7.txt"));
        assertEquals(328187, res);
    }


    @Test
    void testPart2ex() throws IOException {
        int res = new Day7().part2(FileUtil.readFromFile("adventOfCode/Year2021/day7ex.txt"));
        assertEquals(168, res);
    }

    @Test
    void testPart2() throws IOException {
        int res = new Day7().part2(FileUtil.readFromFile("adventOfCode/Year2021/day7.txt"));
        assertEquals(91257582, res);
    }

}