package adventOfCode.Year2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {

    @Test
    void getHandType2() {
        assertEquals(1, Day7.getHandType2("12345"));
        assertEquals(2, Day7.getHandType2("11345"));
        assertEquals(3, Day7.getHandType2("11335"));
        assertEquals(4, Day7.getHandType2("11135"));
        assertEquals(5, Day7.getHandType2("11133"));
        assertEquals(6, Day7.getHandType2("11113"));
        assertEquals(7, Day7.getHandType2("11111"));
        assertEquals(7, Day7.getHandType2("11J11"));
        assertEquals(7, Day7.getHandType2("QQJQQ"));
        assertEquals(7, Day7.getHandType2("QQJJQ"));
        assertEquals(7, Day7.getHandType2("JJJJJ"));
    }
}