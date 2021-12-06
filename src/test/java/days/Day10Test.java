package days;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.Util;

class Day10Test {

    @Test
    void part1() throws IOException {
        new Day10().part1(Util.readFromFile("day10ex.txt"));

    }
}