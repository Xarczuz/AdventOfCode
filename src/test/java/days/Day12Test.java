package days;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.Util;

class Day12Test {

    @Test
    void part1() throws IOException {
        new Day12().part1(Util.readFromFile("day12ex.txt"));

    }
}