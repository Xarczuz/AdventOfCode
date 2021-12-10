package days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day10Test {

    @Test
    void autocomplete() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("[({(<(())[]>[[{[]{<()<>>");
        assertEquals(1,new Day10().autocomplete(arr).size());
    }

    @Test
    void autocomplete2() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("[(()[<>])]({[<{<<[]>>(");
        assertEquals(1,new Day10().autocomplete(arr).size());
    }

    @Test
    void autocomplete3() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("(((({<>}<{<{<>}{[]{[]{}");
        assertEquals(1,new Day10().autocomplete(arr).size());
    }

    @Test
    void autocomplete4() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("<{([([[(<>()){}]>(<<{{");
        arr.add("<{([([>(<<{{");
        assertEquals(0,new Day10().autocomplete(arr).size());
    }

    @Test
    void testPart1ex() throws IOException {
        long res = new Day10().part1(FileUtil.readFromFile("day10ex.txt"));
        assertEquals(26397, res);
    }

    @Test
    void testPart1() throws IOException {
        long res = new Day10().part1(FileUtil.readFromFile("day10.txt"));
        assertEquals(345441, res);
    }

    @Test
    void testPart2ex() throws IOException {
        long res = new Day10().part2(FileUtil.readFromFile("day10ex.txt"));
        assertEquals(288957, res);
    }

    @Test
    void testPart2() throws IOException {
        long res = new Day10().part2(FileUtil.readFromFile("day10.txt"));
        assertEquals(3235371166L, res);
    }
}