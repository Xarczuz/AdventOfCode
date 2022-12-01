package days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day4Test {

    @Test
    void bingo1ex() throws IOException {
        int res = new Day4().bingo1(FileUtil.readFromFile("day4ex.txt"));

        assertEquals(4512, res);
    }

    @Test
    void bingo1() throws IOException {
        int res = new Day4().bingo1(FileUtil.readFromFile("day4.txt"));

        assertEquals(31424, res);
    }
    @Test
    void bingo2ex() throws IOException {
        int res = new Day4().bingo2(FileUtil.readFromFile("day4ex.txt"));

        assertEquals(1924, res);
    }

    @Test
    void bingo2() throws IOException {
        int res = new Day4().bingo2(FileUtil.readFromFile("day4.txt"));

        assertEquals(23042, res);
    }
}