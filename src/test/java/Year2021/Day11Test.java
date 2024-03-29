package Year2021;

import java.io.IOException;

import adventOfCode.Year2021.Day11;
import org.junit.jupiter.api.Test;
import util.FileUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {

    @Test
    void testPart1ex() throws IOException {
        Integer res = new Day11().part1(FileUtil.readFromFile("adventOfCode/Year2021/day11ex.txt"));

        assertEquals(1656,res);
    }

    @Test
    void testPart1() throws IOException {
        Integer res = new Day11().part1(FileUtil.readFromFile("adventOfCode/Year2021/day11.txt"));

        assertEquals(1661,res);
    }

    @Test
    void testPart2ex() throws IOException {
        Integer res = new Day11().part2(FileUtil.readFromFile("adventOfCode/Year2021/day11ex.txt"));

        assertEquals(195,res);
    }

    @Test
    void testPart2() throws IOException {
        Integer res = new Day11().part2(FileUtil.readFromFile("adventOfCode/Year2021/day11.txt"));

        assertEquals(334,res);
    }
}