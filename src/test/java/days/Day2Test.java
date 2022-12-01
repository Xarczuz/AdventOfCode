package days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day2Test {

    @Test
    void testDay2() throws IOException {
        long res = new Day2().day2(FileUtil.readFromFile(("day2.txt")));
        assertEquals(2091984,res);
    }

    @Test
    void testDay2ex() throws IOException {
        long res = new Day2().day2(FileUtil.readFromFile(("day2ex.txt")));
        assertEquals(150,res);
    }

    @Test
    void testDay2Level2() throws IOException {
        long res = new Day2().day2Level2(FileUtil.readFromFile(("day2.txt")));
        assertEquals(2086261056,res);
    }
}