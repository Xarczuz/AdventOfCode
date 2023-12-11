package adventOfCode.Year2023;

import classes.YX;
import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        ArrayList<ArrayList<String>> matrix = parseString(l);

        ArrayList<Integer> expansionY = new ArrayList<>();
        ArrayList<Integer> expansionX = new ArrayList<>();
        findEmptyiness(matrix, expansionY, expansionX);
        expandUniverse(matrix, expansionY, expansionX);
        assignNumbersToGalaxy(matrix);

        for (ArrayList<String> characters : matrix) {
            System.out.println(characters);
        }

    }

    private static void assignNumbersToGalaxy(ArrayList<ArrayList<String>> matrix) {
        new ArrayList<>();
        int index = 1;
        for (ArrayList<String> characters : matrix) {
            for (int i = 0; i < characters.size(); i++) {
                if (Objects.equals(characters.get(i), "#")) {
                    characters.set(i, String.valueOf(index));
                    index++;
                }
            }
        }
    }

    private static void expandUniverse(ArrayList<ArrayList<String>> matrix, ArrayList<Integer> expansionY, ArrayList<Integer> expansionX) {
        for (int i = 0; i < expansionY.size(); i++) {
            ArrayList<String> element = new ArrayList<>();
            for (int j = 0; j < matrix.getFirst().size(); j++) {
                element.add(".");
            }
            matrix.add(expansionY.get(i) + i, element);
        }
        for (ArrayList<String> characters : matrix) {
            System.out.println(characters);
        }
        System.out.println();
        for (int i = 0; i < expansionX.size(); i++) {
            for (int y = 0; y < matrix.size(); y++) {
                matrix.get(y).add(expansionX.get(i) + i, ".");
            }
        }
    }

    private static void findEmptyiness(ArrayList<ArrayList<String>> matrix, ArrayList<Integer> expansionY, ArrayList<Integer> expansionX) {
        for (int y = 0; y < matrix.size(); y++) {
            boolean shouldAdd = true;
            for (int x = 0; x < matrix.getFirst().size(); x++) {
                if (Objects.equals(matrix.get(y).get(x), "#")) {
                    shouldAdd = false;
                    break;
                }
            }
            if (shouldAdd) {
                expansionY.add(y);
            }
        }
        for (int x = 0; x < matrix.getFirst().size(); x++) {
            boolean shouldAdd = true;
            for (int y = 0; y < matrix.size(); y++) {
                if (Objects.equals(matrix.get(y).get(x), "#")) {
                    shouldAdd = false;
                    break;
                }
            }
            if (shouldAdd) {
                expansionX.add(x);
            }
        }
    }

    private static ArrayList<ArrayList<String>> parseString(List<String> l) {
        ArrayList<ArrayList<String>> matrix = new ArrayList<>();
        for (String s : l) {
            ArrayList<String> c = new ArrayList<>();
            matrix.add(c);
            for (char c1 : s.toCharArray()) {
                c.add(String.valueOf(c1));
            }
        }
        return matrix;
    }

    private static class Galaxy {
        YX YX;

    }


}
