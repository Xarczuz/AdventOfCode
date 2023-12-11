package adventOfCode.Year2023;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day11 {


    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day11.class);
        List<String> l2 = FileUtil.readfileExempel(Day11.class);
        TimeUtil.startTime();
//        oneStar(l);
        oneStar(l2);
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l);
//        twoStar(l2);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {

    }

    private static void oneStar(List<String> l) {
        ArrayList<ArrayList<Character>> matrix = parseString(l);


        for (int y = 0; y < matrix.size(); y++) {
            for (int x = 0; x < matrix.getFirst().size(); x++) {


            }
        }
        for (ArrayList<Character> characters : matrix) {
            System.out.println(characters);
        }

    }

    private static ArrayList<ArrayList<Character>> parseString(List<String> l) {
        ArrayList<ArrayList<Character>> matrix = new ArrayList<>();
        for (String s : l) {
            ArrayList<Character> c = new ArrayList<>();
            matrix.add(c);
            for (char c1 : s.toCharArray()) {
                c.add(c1);
            }
        }
        return matrix;
    }


}
