package days;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.Util;

class Day11Test {

    @Test
    void part1() throws IOException {
        new Day11().part1(Util.readFromFile("day11ex.txt"));

    }
}