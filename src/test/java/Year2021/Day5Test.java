package Year2021;

import static org.junit.jupiter.api.Assertions.assertEquals;

import adventOfCode.Year2021.Day5;
import classes.Lines;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day5Test {

    @Test
    void testDay5Level1() throws IOException {
        int res = Day5.part1(FileUtil.readFromFile("adventOfCode/Year2021/day5.txt"));
        assertEquals(6113, res);

    }

    @Test
    void testDay5exLevel1() throws IOException {
        int res = Day5.part1(FileUtil.readFromFile("adventOfCode/Year2021/day5ex.txt"));
        assertEquals(5, res);
    }

    @Test
    void testDay5Level2() throws IOException {
        int res = Day5.level2(FileUtil.readFromFile("adventOfCode/Year2021/day5.txt"));
        assertEquals(20373, res);

    }

    @Test
    void testDay5exLevel2() throws IOException {
        int res = Day5.level2(FileUtil.readFromFile("adventOfCode/Year2021/day5ex.txt"));
        assertEquals(12, res);
    }

    @Test
    void testDay5Lines() throws IOException {
        Lines lines = new Lines("0,0 -> 8,8");
        lines.addVerticalPoints();

        System.out.println(lines);
    }
}