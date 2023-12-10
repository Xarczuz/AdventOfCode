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
        List<String> l4 = FileUtil.readfileExempelX(Day10.class, 3);
        initSetup();
        TimeUtil.startTime();
//        oneStar(l);
//        oneStar(l2);
//        oneStar(l3);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l);
        twoStar(l2);
        twoStar(l4);
        TimeUtil.endTime();
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

    private static void twoStar(List<String> l) {
        char[][] matrix = parseString(l);
        PossibleSolution possibleSolution = findLoop(matrix);
        char[][] matrixWithoutExtraSymbols = removeAllExtraSymbols(l, matrix, possibleSolution);

        char[][] matrixWithoutTilesOutside = removeAllTilesOutside(l, matrixWithoutExtraSymbols, possibleSolution);

        Util.print(matrix);
        System.out.println();
        Util.print(matrixWithoutExtraSymbols);
        System.out.println();
        Util.print(matrixWithoutTilesOutside);
        Pipe[][] pipes = convertToPipes(l, matrixWithoutTilesOutside);


        System.out.println("One star: " + possibleSolution.nr / 2);
    }

    private static Pipe[][] convertToPipes(List<String> l, char[][] matrixWithoutTilesOutside) {
        Pipe[][] pipes = new Pipe[l.size()][l.getFirst().toCharArray().length];

        for (int y = 0; y < pipes.length; y++) {
            for (int x = 0; x < pipes[0].length; x++) {
                pipes[y][x] = new Pipe();
                pipes[y][x].symbol = matrixWithoutTilesOutside[y][x];
                pipes[y][x].point.y = y;
                pipes[y][x].point.x = x;
            }
        }

        return pipes;
    }

    private enum Type {
        NORTH,
        EAST,
        SOUTH,
        WEST,
        EMPTY,
        CONNECTED
    }

    private static class Pipe {
        char symbol;
        XY point = new XY();
        Type north = Type.EMPTY;
        Type east = Type.EMPTY;
        Type south = Type.EMPTY;
        Type west = Type.EMPTY;
    }

    private static char[][] removeAllTilesOutside(List<String> l, char[][] matrix, PossibleSolution possibleSolution) {
        char[][] matrixWithoutTilesOutside = new char[l.size()][l.getFirst().toCharArray().length];
        XY[] dirs = new XY[]{new XY(0, 1), new XY(0, -1), new XY(1, 0), new XY(-1, 0)};
        HashSet<XY> visitedPaths = possibleSolution.visitedPaths;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                boolean inside = true;

                if (!visitedPaths.contains(new XY(x, y))) {
                    for (XY dir : dirs) {
                        int tempX = x;
                        int tempy = y;
                        boolean checkInside = false;
                        while (Util.isWithinRangeOfMatrix(tempy, tempX, matrix)) {
                            tempX += dir.x;
                            tempy += dir.y;

                            if (visitedPaths.contains(new XY(tempX, tempy))) {
                                checkInside = true;
                                break;
                            }
                        }
                        inside &= checkInside;
                    }
                }
                if (inside) {
                    matrixWithoutTilesOutside[y][x] = matrix[y][x];
                } else {
                    matrixWithoutTilesOutside[y][x] = '0';
                }

            }
        }
        return matrixWithoutTilesOutside;
    }

    private static char[][] removeAllExtraSymbols(List<String> l, char[][] matrix, PossibleSolution possibleSolution) {
        char[][] matrixWithoutExtraSymbols = new char[l.size()][l.getFirst().toCharArray().length];
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                if (possibleSolution.visitedPaths.contains(new XY(x, y))) {
                    matrixWithoutExtraSymbols[y][x] = matrix[y][x];
                } else {
                    matrixWithoutExtraSymbols[y][x] = '.';
                }
            }
        }
        return matrixWithoutExtraSymbols;
    }

    private static void oneStar(List<String> l) {
        char[][] matrix = parseString(l);
        int steps = findLoop(matrix).nr / 2;
        Util.print(matrix);
        System.out.println("One star: " + steps);
    }

    private static PossibleSolution findLoop(char[][] matrix) {
        int steps = 0;
        char startingSymbol = ' ';
        int startingX = 0;
        int startingY = 0;
        PossibleSolution possibleSolution = new PossibleSolution();
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                int currentLoopSteps;
                if (matrix[y][x] == 'S') {
                    for (Direction direction : directions) {
                        matrix[y][x] = direction.symbol;
                        PossibleSolution ps = findSteps(y, x, matrix);
                        currentLoopSteps = ps.nr;
                        if (currentLoopSteps > steps) {
                            steps = currentLoopSteps;
                            startingX = x;
                            startingY = y;
                            possibleSolution = ps;
                            startingSymbol = direction.symbol;
                        }
                    }
                    break;
                }
            }
        }
        matrix[startingY][startingX] = startingSymbol;
        return possibleSolution;
    }

    private static PossibleSolution findSteps(int startY, int startX, char[][] matrix) {
        PossibleSolution possibleSolution = new PossibleSolution();
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
                        possibleSolution.nr = -1;
                        return possibleSolution;
                    }
                    break;
                }
            }
        }
        possibleSolution.nr = steps;
        possibleSolution.visitedPaths = visited;
        return possibleSolution;
    }

    private static boolean isValidDirection(Path sourcePath, XY direction, char[][] matrix) {
        int y = sourcePath.y + direction.y;
        int x = sourcePath.x + direction.x;

        if (!Util.isWithinRangeOfMatrix(y, x, matrix)) {
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

    private static class PossibleSolution {
        int nr;
        HashSet<XY> visitedPaths;
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
