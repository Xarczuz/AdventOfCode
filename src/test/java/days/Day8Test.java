package days;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.Util;

class Day8Test {

    @Test
    void part1() throws IOException {
        new Day8().part1(Util.readFromFile("day8ex.txt"));

    }
}