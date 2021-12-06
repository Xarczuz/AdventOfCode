package days;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day9Test {

    @Test
    void part1() throws IOException {
        new Day9().part1(FileUtil.readFromFile("day9ex.txt"));

    }
}