package days;


import static org.junit.jupiter.api.Assertions.assertEquals;

import classes.InputOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class Day8Test {

    @Test
    void testPart1ex() throws IOException {
        int res = new Day8().part1(FileUtil.readFromFile("day8ex.txt"));
        assertEquals(26,res);
    }

    @Test
    void testPart1() throws IOException {
        int res = new Day8().part1(FileUtil.readFromFile("day8.txt"));
        assertEquals(519,res);
    }

    @Test
    void testPart2ex() throws IOException {
        long res = new Day8().part2(FileUtil.readFromFile("day8ex.txt"));
        assertEquals(61229,res);
    }
    @Test
    void testPart2() throws IOException {
        long res = new Day8().part2(FileUtil.readFromFile("day8.txt"));
        assertEquals(1027483,res);
    }

    @Test
    void testPart2ex2() throws IOException {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf");
        long res = new Day8().part2(arr);
        assertEquals(5353,res);
    }

    @Test
    void testPart2ex3() throws IOException {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc");
        long res = new Day8().part2(arr);
        assertEquals(9781,res);
    }

    @Test
    void testPart2ex4() throws IOException {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb");
        long res = new Day8().part2(arr);
        assertEquals(8418,res);
    }

    @Test
    void testPart2exMapNr() throws IOException {
        InputOutput in = new InputOutput();
        in.in.add("acedgfb");
        in.in.add("cdfbe");
        in.in.add("gcdfa");
        in.in.add("fbcad");
        in.in.add("dab");
        in.in.add("cefabd");
        in.in.add("cdfgeb");
        in.in.add("eafb");
        in.in.add("cagedb");
        in.in.add("ab");
        HashMap<String, Integer> res = new Day8().mapNr2(in);

        System.out.println(res);
    }
}