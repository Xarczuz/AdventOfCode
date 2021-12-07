package days;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day10Test {

    @Test
    void testPart1ex() throws IOException {
        new Day10().part1(FileUtil.readFromFile("day10ex.txt"));

    }
}