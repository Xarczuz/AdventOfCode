package days;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    @Test
    void testPart1ex() throws IOException {
        int res = new Day12().part1(FileUtil.readFromFile("day12ex.txt"));
        assertEquals(10,res);
    }

    @Test
    void testPart1ex2() throws IOException {
        int res = new Day12().part1(FileUtil.readFromFile("day12ex2.txt"));
        assertEquals(19,res);
    }

    @Test
    void testPart1ex3() throws IOException {
        int res = new Day12().part1(FileUtil.readFromFile("day12ex3.txt"));
        assertEquals(226,res);
    }

    @Test
    void testPart1() throws IOException {
        int res = new Day12().part1(FileUtil.readFromFile("day12.txt"));
        assertEquals(3463,res);
    }

    @Test
    void testPart2ex() throws IOException {
        int res = new Day12().part2(FileUtil.readFromFile("day12ex.txt"));
        assertEquals(36,res);
    }

    @Test
    void testPart2ex2() throws IOException {
        int res = new Day12().part2(FileUtil.readFromFile("day12ex2.txt"));
        assertEquals(103,res);
    }

    @Test
    void testPart2ex3() throws IOException {
        int res = new Day12().part2(FileUtil.readFromFile("day12ex3.txt"));
        assertEquals(3509,res);
    }

    @Test
    void testPart2() throws IOException {
        int res = new Day12().part2(FileUtil.readFromFile("day12.txt"));
        assertEquals(91533,res);
    }
}