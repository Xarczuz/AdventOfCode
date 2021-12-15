package days;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day15Test {

    @Test
    void part1ex() throws IOException {
        long res = new Day15().part1(FileUtil.readFromFile("day15ex.txt"));

        assertEquals(40, res);
    }

    @Test
    void part1() throws IOException {
        long res = new Day15().part1(FileUtil.readFromFile("day15.txt"));

        assertEquals(367, res);//413
    }
}