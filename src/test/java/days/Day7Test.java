package days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day7Test {

    @Test
    void testPart1ex() throws IOException {
        int res = new Day7().part1(FileUtil.readFromFile("day7ex.txt"));
        assertEquals(37, res);
    }

    @Test
    void testtestPart1ex() throws IOException {
        int res = new Day7().part1(FileUtil.readFromFile("day7.txt"));
        assertEquals(328187, res);
    }


    @Test
    void testPart2ex() throws IOException {
        int res = new Day7().part2(FileUtil.readFromFile("day7ex.txt"));
        assertEquals(168, res);
    }

    @Test
    void testPart2() throws IOException {
        int res = new Day7().part2(FileUtil.readFromFile("day7.txt"));
        assertEquals(91257582, res);
    }

}