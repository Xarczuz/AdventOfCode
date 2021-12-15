package days;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day15Test {

    @Test
    void part1ex() throws IOException {
        long res = new Day15().part12(FileUtil.readFromFile("day15ex.txt"));

        assertEquals(40, res);
    }

    @Test
    void part1ex2() throws IOException {
        long res = new Day15().part12(FileUtil.readFromFile("day15ex2.txt"));

        assertEquals(24, res);
    }

    @Test
    void part1() throws IOException {
        long res = new Day15().part12(FileUtil.readFromFile("day15.txt"));

        assertEquals(366, res);//413
    }
}