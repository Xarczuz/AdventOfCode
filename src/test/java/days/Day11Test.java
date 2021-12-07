package days;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day11Test {

    @Test
    void testPart1ex() throws IOException {
        new Day11().part1(FileUtil.readFromFile("day11ex.txt"));

    }
}