package days;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day8Test {

    @Test
    void part1() throws IOException {
        new Day8().part1(FileUtil.readFromFile("day8ex.txt"));

    }
}