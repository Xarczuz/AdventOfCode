package days;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.Util;

class Day9Test {

    @Test
    void part1() throws IOException {
        new Day9().part1(Util.readFromFile("day9ex.txt"));

    }
}