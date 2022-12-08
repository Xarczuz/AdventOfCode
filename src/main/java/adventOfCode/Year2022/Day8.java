package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Day8 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day8.class);
        List<String> l2 = FileUtil.readfileExempel(Day8.class);
        TimeUtil.startTime();
        oneStar(l2);
        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l2);
        twoStar(l);
        TimeUtil.endTime();
    }

    private static void oneStar(List<String> l) {
        int xSize = l.get(0).length();
        int ySize = l.size();
        int[][] forest = new int[xSize][ySize];
        int[][] treesVisable = new int[xSize][ySize];
        parseTrees(l, forest);

        look1(xSize, ySize, forest, treesVisable);
        look2(xSize, ySize, forest, treesVisable);
        look3(xSize, ySize, forest, treesVisable);
        look4(xSize, ySize, forest, treesVisable);

        int visable = 0;
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                if (treesVisable[i][j] == 1) {
                    visable++;
                }
            }
        }
        System.out.println("Star one: " + visable);
    }

    private static void look4(int xSize, int ySize, int[][] forest, int[][] treesVisable) {
        for (int j = xSize - 1; j >= 0; j--) {
            int max = -1;
            for (int i = ySize - 1; i >= 0; i--) {
                int tree = forest[i][j];
                if (tree > max) {
                    treesVisable[i][j] = 1;
                    max = tree;
                }
            }
        }
    }

    private static void look3(int xSize, int ySize, int[][] forest, int[][] treesVisable) {
        for (int i = xSize - 1; i >= 0; i--) {
            int max = -1;
            for (int j = ySize - 1; j >= 0; j--) {
                int tree = forest[i][j];
                if (tree > max) {
                    treesVisable[i][j] = 1;
                    max = tree;
                }
            }
        }
    }

    private static void look2(int xSize, int ySize, int[][] forest, int[][] treesVisable) {
        for (int j = 0; j < xSize; j++) {
            int max = -1;
            for (int i = 0; i < ySize; i++) {
                int tree = forest[i][j];
                if (tree > max) {
                    treesVisable[i][j] = 1;
                    max = tree;
                }
            }
        }
    }

    private static void look1(int xSize, int ySize, int[][] forest, int[][] treesVisable) {
        for (int i = 0; i < xSize; i++) {
            int max = -1;
            for (int j = 0; j < ySize; j++) {
                int tree = forest[i][j];
                if (tree > max) {
                    treesVisable[i][j] = 1;
                    max = tree;
                }
            }
        }
    }

    private static void parseTrees(List<String> l, int[][] forest) {
        for (int i = 0; i < l.size(); i++) {
            String t = l.get(i);
            char[] charArray = t.toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                char c = charArray[j];
                forest[i][j] = c - 48;
            }
        }
    }

    private static void twoStar(List<String> l) {
        int xSize = l.get(0).length();
        int ySize = l.size();
        int[][] forest = new int[xSize][ySize];
        parseTrees(l, forest);
        int maxView = 0;
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {

                maxView = Math.max(maxView, findViewDistance(forest[i][j], i, j, forest));

            }
        }
        System.out.println("Star two: " + maxView);
    }

    private static int findViewDistance(int mainTree, int y1, int x1, int[][] forest) {
        int north = getNorth(mainTree, y1, x1, forest);
        int south = getSouth(mainTree, y1, x1, forest);
        int west = getWest(mainTree, y1, x1, forest);
        int east = getEast(mainTree, y1, x1, forest);
        return north * south * west * east;
    }

    private static int getEast(int mainTree, int y1, int x1, int[][] forest) {
        int east = 0;
        for (int k = x1 + 1; k < forest[x1].length; k++) {
            int i1 = check(mainTree, y1, k, forest);
            if (i1 == 1) {
                east++;
                continue;
            } else if (i1 == 2) {
                east++;
            }
            return east;
        }
        return east;
    }

    private static int getWest(int mainTree, int y1, int x1, int[][] forest) {
        int west = 0;
        for (int k = x1 - 1; k >= 0; k--) {
            int i1 = check(mainTree, y1, k, forest);
            if (i1 == 1) {
                west++;
                continue;
            } else if (i1 == 2) {
                west++;
            }
            return west;
        }
        return west;
    }

    private static int getSouth(int mainTree, int y1, int x1, int[][] forest) {
        int south = 0;
        for (int k = y1 + 1; k < forest[y1].length; k++) {
            int i1 = check(mainTree, k, x1, forest);
            if (i1 == 1) {
                south++;
                continue;
            } else if (i1 == 2) {
                south++;
            }
            return south;
        }
        return south;
    }

    private static int getNorth(int mainTree, int y1, int x1, int[][] forest) {
        int north = 0;
        for (int k = y1 - 1; k >= 0; k--) {
            int i1 = check(mainTree, k, x1, forest);
            if (i1 == 1) {
                north++;
                continue;
            } else if (i1 == 2) {
                north++;
            }
            return north;
        }
        return north;
    }

    private static int check(int mainTree, int y, int x, int[][] forest) {
        if (mainTree > forest[y][x]) {
            return 1;
        }
        if (mainTree <= forest[y][x]) {
            return 2;
        }
        return 0;
    }
}
