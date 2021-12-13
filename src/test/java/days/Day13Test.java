package days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day13Test {

    @Test
    void testPart1ex() throws IOException {
        int res = new Day13().part1(FileUtil.readFromFile("day13ex.txt"));

        assertEquals(17,res);
    }

    @Test
    void testPart2ex() throws IOException {
        int res = new Day13().part2(FileUtil.readFromFile("day13ex.txt"));

        assertEquals(16,res);
    }

    @Test
    void testPart1() throws IOException {
        int res = new Day13().part1(FileUtil.readFromFile("day13.txt"));

        assertEquals(747,res);
    }

    @Test
    void testPart2() throws IOException {
        int res = new Day13().part2(FileUtil.readFromFile("day13.txt"));

        assertEquals(102,res);
    }
}