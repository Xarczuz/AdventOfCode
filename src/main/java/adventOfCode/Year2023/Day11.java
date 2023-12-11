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


    private static final int stepsOffset = 1000000 - 2;

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day11.class);
        List<String> l2 = FileUtil.readfileExempel(Day11.class);
        TimeUtil.startTime();
        oneStar(l);
        oneStar(l2);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l);
        twoStar(l2);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {
        ArrayList<ArrayList<String>> matrix = parseString(l);
        ArrayList<Integer> expansionY = new ArrayList<>();
        ArrayList<Integer> expansionX = new ArrayList<>();
        findEmptyiness(matrix, expansionY, expansionX);
        expandUniverse(matrix, expansionY, expansionX);
        ArrayList<Galaxy> galaxies = assignNumbersToGalaxy(matrix);
        long sum = sumOfShortestPaths2(galaxies, matrix);
        for (ArrayList<String> characters : matrix) {
            System.out.println(characters);
        }
        System.out.println("Two star: " + sum);
    }

    private static long sumOfShortestPaths2(ArrayList<Galaxy> galaxies, ArrayList<ArrayList<String>> matrix) {
        long sum = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                Galaxy g1 = galaxies.get(i);
                Galaxy g2 = galaxies.get(j);
                long steps = 0;
                for (int y = g1.yx.y; y < g2.yx.y; y++) {
                    if (matrix.get(y).get(g2.yx.x).equals("x")) {
                        steps += Day11.stepsOffset;
                    }
                    steps++;
                }
                if (g1.yx.x > g2.yx.x) {
                    for (int x = g1.yx.x; x > g2.yx.x; x--) {
                        if (matrix.get(g2.yx.y).get(x).equals("x")) {
                            steps += Day11.stepsOffset;
                        }
                        steps++;
                    }
                } else if (g1.yx.x < g2.yx.x) {
                    for (int x = g1.yx.x; x < g2.yx.x; x++) {
                        if (matrix.get(g2.yx.y).get(x).equals("x")) {
                            steps += Day11.stepsOffset;
                        }
                        steps++;
                    }
                }
                sum += steps;
            }
        }
        return sum;
    }

    private static void oneStar(List<String> l) {
        ArrayList<ArrayList<String>> matrix = parseString(l);
        ArrayList<Integer> expansionY = new ArrayList<>();
        ArrayList<Integer> expansionX = new ArrayList<>();
        findEmptyiness(matrix, expansionY, expansionX);
        expandUniverse(matrix, expansionY, expansionX);
        ArrayList<Galaxy> galaxies = assignNumbersToGalaxy(matrix);
        int sum = sumOfShortestPaths(galaxies);
        for (ArrayList<String> characters : matrix) {
            System.out.println(characters);
        }
        System.out.println("One star: " + sum);
    }

    private static int sumOfShortestPaths(ArrayList<Galaxy> galaxies) {
        int sum = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                Galaxy g1 = galaxies.get(i);
                Galaxy g2 = galaxies.get(j);
                sum += Math.abs(g1.yx.x - g2.yx.x) + Math.abs(g1.yx.y - g2.yx.y);
            }
        }
        return sum;
    }

    private static ArrayList<Galaxy> assignNumbersToGalaxy(ArrayList<ArrayList<String>> matrix) {
        ArrayList<Galaxy> galaxies = new ArrayList<>();
        int index = 1;
        for (int y = 0; y < matrix.size(); y++) {
            ArrayList<String> characters = matrix.get(y);
            for (int x = 0; x < characters.size(); x++) {
                if (Objects.equals(characters.get(x), "#")) {
                    characters.set(x, String.valueOf(index));
                    galaxies.add(new Galaxy(index, new YX(y, x)));
                    index++;
                }
            }
        }
        return galaxies;
    }

    private static void expandUniverse(ArrayList<ArrayList<String>> matrix, ArrayList<Integer> expansionY, ArrayList<Integer> expansionX) {
        for (int i = 0; i < expansionY.size(); i++) {
            ArrayList<String> element = new ArrayList<>();
            for (int j = 0; j < matrix.getFirst().size(); j++) {
                element.add("x");
            }
            matrix.add(expansionY.get(i) + i, element);
        }
        for (ArrayList<String> characters : matrix) {
            System.out.println(characters);
        }
        System.out.println();
        for (int i = 0; i < expansionX.size(); i++) {
            for (int y = 0; y < matrix.size(); y++) {
                matrix.get(y).add(expansionX.get(i) + i, "x");
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
        int nr;
        YX yx;

        public Galaxy(int nr, classes.YX yx) {
            this.nr = nr;
            this.yx = yx;
        }
    }

}
