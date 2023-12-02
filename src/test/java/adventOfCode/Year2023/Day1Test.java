package adventOfCode.Year2023;

import org.junit.jupiter.api.Test;

import static adventOfCode.Year2023.Day1.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

    @Test
    void makeIntTest() {
        assertEquals(29, makeInt2("two1nine"));
        assertEquals(83, makeInt2("eightwothree"));
        assertEquals(13, makeInt2("abcone2threexyz"));
        assertEquals(24, makeInt2("xtwone3four"));
        assertEquals(42, makeInt2("4nineeightseven2"));
        assertEquals(14, makeInt2("zoneight234"));
        assertEquals(76, makeInt2("7pqrstsixteen"));
        assertEquals(26, makeInt2("2pqrstsix "));
        assertEquals(66, makeInt2("six "));
        assertEquals(21, makeInt2("twone"));
        assertEquals(41, makeInt2("kpqsxmvhp4twohnlsone3eighttwones"));
        assertEquals(85, makeInt2("eightgmjxseven5fivefiveslbfsqrjrnbhqzgr"));
        assertEquals(86, makeInt2("8five6"));
        assertEquals(99, makeInt2("pfxsfxsvkjrb9"));
        assertEquals(47, makeInt2("42onef6seven"));
        assertEquals(33, makeInt2("39njjvzt7threetkccstz"));
        assertEquals(59, makeInt2("59nczhdqzdr"));
        assertEquals(85, makeInt2("dl8three5"));
        assertEquals(71, makeInt2("ghlgnsztmtsevenfour1bsctrtmp"));
        assertEquals(23, makeInt2("rpxtwone83"));
        assertEquals(55, makeInt2("fiveeight1fivehvqrnzxqlkrcmd"));
        assertEquals(13, makeInt2("13"));
        assertEquals(36, makeInt2("34536"));
        assertEquals(58, makeInt2("5twooneeightbhxfhpvjmlgtkccqgmqjnq"));
        assertEquals(94, makeInt2("nine4pvtl"));
        assertEquals(43, makeInt2("zstrmphtxdvdpsnhpnq4threenbjznsb"));
        assertEquals(17, makeInt2("spq17sevenhjfkkjzdf"));
        assertEquals(11, makeInt2("one"));
        assertEquals(22, makeInt2("two"));
        assertEquals(33, makeInt2("three"));
        assertEquals(44, makeInt2("four"));
        assertEquals(55, makeInt2("five"));
        assertEquals(66, makeInt2("six"));
        assertEquals(77, makeInt2("seven"));
        assertEquals(88, makeInt2("eight"));
        assertEquals(99, makeInt2("nine"));
        assertEquals(11, makeInt2("1"));
        assertEquals(33, makeInt2("39njjvzt7threetkccstz"));
        assertEquals(29, makeInt2("ne, two, three, four, five, six, seven, eight, and nine"));
        assertEquals(21, makeInt2("In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281."));
    }

    @Test
    void name() {
        assertEquals(2, findLeft("two1nine"));
        assertEquals(9, findRight("two1nine"));
    }
}