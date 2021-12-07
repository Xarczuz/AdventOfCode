package days;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day13Test {

    @Test
    void testPart1ex() throws IOException {
        new Day13().part1(FileUtil.readFromFile("day13ex.txt"));

    }
}