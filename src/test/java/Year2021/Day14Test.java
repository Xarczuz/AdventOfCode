package Year2021;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import adventOfCode.Year2021.Day14;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day14Test {

    @Test
    void testPart1ex() throws IOException {
        long res = new Day14().part1(FileUtil.readFromFile("adventOfCode/Year2021/day14ex.txt"),10);

        assertEquals(1588,res);
    }

    @Test
    void testPart2ex() throws IOException {
        long res = new Day14().part2(FileUtil.readFromFile("adventOfCode/Year2021/day14ex.txt"),40);

        assertEquals(2188189693529L,res);
    }

    @Test
    void testPart2() throws IOException {
        long res = new Day14().part2(FileUtil.readFromFile("adventOfCode/Year2021/day14.txt"),40);

        assertEquals(3572761917024L,res);
    }

    @Test
    void testPart1() throws IOException {
        long res = new Day14().part1(FileUtil.readFromFile("adventOfCode/Year2021/day14.txt"),10);

        assertEquals(2988,res);
    }
}