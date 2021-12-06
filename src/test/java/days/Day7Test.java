package days;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day7Test {

    @Test
    void part1() throws IOException {
        new Day7().part1(FileUtil.readFromFile("day7ex.txt"));
    }
}