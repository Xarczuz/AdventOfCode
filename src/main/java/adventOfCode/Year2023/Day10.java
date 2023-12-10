package adventOfCode.Year2023;

import classes.XY;
import util.FileUtil;
import util.TimeUtil;
import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class Day10 {


    static ArrayList<Direction> directions;

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day10.class);
        List<String> l2 = FileUtil.readfileExempel(Day10.class);
        List<String> l3 = FileUtil.readfileExempel2(Day10.class);
        initSetup();
        TimeUtil.startTime();
        oneStar(l);
        oneStar(l2);
        oneStar(l3);
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l);
//        twoStar(l2);
        TimeUtil.endTime();
    }


    private static void twoStar(List<String> l) {

    }

    private static void initSetup() {
        directions = new ArrayList<>();
        directions.add(new Direction('|', new XY[]{new XY(0, 1), new XY(0, -1)}));
        directions.add(new Direction('-', new XY[]{new XY(1, 0), new XY(-1, 0)}));
        directions.add(new Direction('L', new XY[]{new XY(0, -1), new XY(1, 0)}));
        directions.add(new Direction('J', new XY[]{new XY(0, -1), new XY(-1, 0)}));
        directions.add(new Direction('7', new XY[]{new XY(0, 1), new XY(-1, 0)}));
        directions.add(new Direction('F', new XY[]{new XY(0, 1), new XY(1, 0)}));
    }

    private static void oneStar(List<String> l) {
        char[][] matrix = parseString(l);
        int steps = findLoop(matrix);
        Util.print(matrix);
        System.out.println("One star: " + steps);

    }

    private static int findLoop(char[][] matrix) {
        int steps = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                int currentLoopSteps = 0;
                if (matrix[y][x] == 'S') {
                    for (Direction direction : directions) {
                        matrix[y][x] = direction.symbol;
                        currentLoopSteps = findSteps(y, x, matrix);
                        steps = Math.max(steps, currentLoopSteps);
                    }
                    break;
                }
            }
        }

        return steps / 2;
    }

    private static int findSteps(int startY, int startX, char[][] matrix) {
        char startingPoint = matrix[startY][startX];
        Stack<Path> stack = new Stack<>();
        stack.add(new Path(startingPoint, startY, startX));
        int steps = 0;
        HashSet<XY> visited = new HashSet<>();
        while (!stack.isEmpty()) {
            Path path = stack.pop();
            XY o = new XY(path.x, path.y);
            if (visited.contains(o)) {
                continue;
            }
            visited.add(o);
            steps++;
            for (Direction direction : directions) {
                if (path.symbol == direction.symbol) {
                    if (isValidDirection(path, direction.directions[0], matrix)) {
                        int y = path.y + direction.directions[0].y;
                        int x = path.x + direction.directions[0].x;
                        char symbol = matrix[y][x];
                        stack.add(new Path(symbol, y, x));
                    }
                    if (isValidDirection(path, direction.directions[1], matrix)) {
                        int y = path.y + direction.directions[1].y;
                        int x = path.x + direction.directions[1].x;
                        char symbol = matrix[y][x];
                        stack.add(new Path(symbol, y, x));
                    } else {
                        return -1;
                    }
                    break;
                }
            }
        }

        return steps;
    }

    private static boolean isValidDirection(Path sourcePath, XY direction, char[][] matrix) {
        int y = sourcePath.y + direction.y;
        int x = sourcePath.x + direction.x;

        if (y > matrix.length - 1 || y < 0 || x > matrix[0].length - 1 || x < 0) {
            return false;
        }
        char destinationSymbol = matrix[y][x];
        for (Direction d : directions) {
            if (d.symbol == destinationSymbol) {
                for (XY xy : d.directions) {
                    if (y + xy.y == sourcePath.y && x + xy.x == sourcePath.x) {
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }

    private static char[][] parseString(List<String> l) {
        char[][] matrix = new char[l.size()][l.getFirst().toCharArray().length];
        for (int y = 0; y < l.size(); y++) {
            String s = l.get(y);
            char[] charArray = s.toCharArray();
            for (int x = 0; x < charArray.length; x++) {
                char c = charArray[x];
                matrix[y][x] = c;
            }
        }
        return matrix;
    }

    private static class Path {
        char symbol;
        int y;
        int x;

        public Path(char symbol, int y, int x) {
            this.symbol = symbol;
            this.y = y;
            this.x = x;
        }
    }

    private static class Direction {
        char symbol;
        XY[] directions;

        public Direction(char symbol, XY[] directions) {
            this.symbol = symbol;
            this.directions = directions;
        }
    }

}
