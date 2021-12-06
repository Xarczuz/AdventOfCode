package days;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day12Test {

    @Test
    void part1() throws IOException {
        new Day12().part1(FileUtil.readFromFile("day12ex.txt"));

    }
}