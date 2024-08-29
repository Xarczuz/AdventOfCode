package adventOfCode.Year2023;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {

    @Test
    void verticalReflection() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("#...##..#");
        arr.add("#....#..#");
        arr.add("..##..###");
        arr.add("#####.##.");
        arr.add("#####.##.");
        arr.add("..##..###");
        arr.add("#....#..#");
        assertEquals(4, Day13.horizontalReflection(arr, false));
    }

    @Test
    void horizontalReflection1() {
        ArrayList<String> arr = new ArrayList<>();
        String s = "..#...###..##\n" + "..##....##...\n" + "#.#.#....####\n" + "##.###.#.##.#\n" + "##.###.#.##.#\n" + "#.#.#....####\n" + ".###....##...\n" + "..#...###..##\n" + "..#...###..##\n" + ".###....##...\n" + "#.#.#....####";
        StringBuilder s1 = new StringBuilder();
        for (char c : s.toCharArray()) {

            if (c == '\n') {

                arr.add(s1.toString());
                s1 = new StringBuilder();
            } else {

                s1.append(c);
            }
        }
        assertEquals(8, Day13.horizontalReflection(arr, false));
    }

    @Test
    void horizontalReflection() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("#...##..#");
        arr.add("#....#..#");
        arr.add("..##..###");
        arr.add("#####.##.");
        arr.add("#####.##.");
        arr.add("..##..###");
        arr.add("#....#..#");
        assertEquals(4, Day13.horizontalReflection(arr, false));
    }


    @Test
    void horizontalReflectionStar2() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("#...##..#");
        arr.add("#....#..#");
        arr.add("..##..###");
        arr.add("#####.##.");
        arr.add("#####.##.");
        arr.add("..##..###");
        arr.add("#....#..#");
        assertEquals(4, Day13.horizontalReflection2(arr, false));
    }

    @Test
    void transposeArray() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("#...##..#");
        arr.add("#....#..#");
        arr.add("..##..###");
        arr.add("#####.##.");
        arr.add("#####.##.");
        arr.add("..##..###");
        arr.add("#....#..#");
        ArrayList<String> transpedArr = Day13.transposeArray(arr);
        for (String s : arr) {
            System.out.println(s);
        }
        System.out.println();
        for (String s : transpedArr) {
            System.out.println(s);
        }
    }

    @Test
    void transposeArray2() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("123");
        arr.add("456");
        arr.add("789");

        ArrayList<String> expectedArr = new ArrayList<>();
        expectedArr.add("369");
        expectedArr.add("258");
        expectedArr.add("147");


        ArrayList<String> transpedArr = Day13.transposeArray(arr);
        for (String s : expectedArr) {
            System.out.println(s);
        }
        System.out.println();
        for (String s : transpedArr) {
            System.out.println(s);
        }
        assertEquals(expectedArr, transpedArr);
    }
}