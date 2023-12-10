package adventOfCode.Year2023;

import classes.XY;
import util.FileUtil;
import util.TimeUtil;
import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Day10 {


    private static ArrayList<Direction> directions;
    private static HashMap<CardinalDirection, XY> directionXYHashMap;

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day10.class);
        List<String> l2 = FileUtil.readfileExempel(Day10.class);
        List<String> l3 = FileUtil.readfileExempel2(Day10.class);
        List<String> l4 = FileUtil.readfileExempelX(Day10.class, 3);
        List<String> l5 = FileUtil.readfileExempelX(Day10.class, 4);
        initSetup();
        TimeUtil.startTime();
        oneStar(l);
        oneStar(l2);
        oneStar(l3);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l);
        twoStar(l2);
        twoStar(l4);
        twoStar(l5);
        TimeUtil.endTime();
    }


    private static void initSetup() {
        directions = new ArrayList<>();
        directions.add(new Direction('|', new XY[]{new XY(0, 1), new XY(0, -1)}, new CardinalDirection[]{CardinalDirection.SOUTH, CardinalDirection.NORTH}));
        directions.add(new Direction('-', new XY[]{new XY(1, 0), new XY(-1, 0)}, new CardinalDirection[]{CardinalDirection.EAST, CardinalDirection.WEST}));
        directions.add(new Direction('L', new XY[]{new XY(0, -1), new XY(1, 0)}, new CardinalDirection[]{CardinalDirection.NORTH, CardinalDirection.EAST}));
        directions.add(new Direction('J', new XY[]{new XY(0, -1), new XY(-1, 0)}, new CardinalDirection[]{CardinalDirection.WEST, CardinalDirection.NORTH}));
        directions.add(new Direction('7', new XY[]{new XY(0, 1), new XY(-1, 0)}, new CardinalDirection[]{CardinalDirection.SOUTH, CardinalDirection.WEST}));
        directions.add(new Direction('F', new XY[]{new XY(0, 1), new XY(1, 0)}, new CardinalDirection[]{CardinalDirection.SOUTH, CardinalDirection.EAST}));
        directionXYHashMap = new HashMap<>();
        directionXYHashMap.put(CardinalDirection.NORTH, new XY(0, -1));
        directionXYHashMap.put(CardinalDirection.EAST, new XY(1, 0));
        directionXYHashMap.put(CardinalDirection.SOUTH, new XY(0, 1));
        directionXYHashMap.put(CardinalDirection.WEST, new XY(-1, 0));

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
        System.out.println();
        Pipe[][] pipes = convertToPipes(l, matrixWithoutTilesOutside);
        findAllInnerAndOuterWalls(possibleSolution.visitedPaths, pipes, matrixWithoutTilesOutside);
        int sum = countInnerPoints(pipes, matrixWithoutTilesOutside);
        Util.print(matrixWithoutTilesOutside);
        System.out.println();
        System.out.println("Two star: " + sum);
    }

    private static int countInnerPoints(Pipe[][] pipes, char[][] matrixWithoutTilesOutside) {
        int sum = 0;
        for (Pipe[] pipe : pipes) {
            for (Pipe pipe1 : pipe) {
                if (pipe1.symbol == '.') {
                    if (pipe1.north == Type.INSIDE && pipe1.east == Type.INSIDE && pipe1.south == Type.INSIDE && pipe1.west == Type.INSIDE) {
                        sum++;
                        matrixWithoutTilesOutside[pipe1.point.y][pipe1.point.x] = 'I';
                    } else if (pipe1.north == Type.OUTSIDE && pipe1.east == Type.OUTSIDE && pipe1.south == Type.OUTSIDE && pipe1.west == Type.OUTSIDE) {
                        matrixWithoutTilesOutside[pipe1.point.y][pipe1.point.x] = 'O';
                    } else {
                        matrixWithoutTilesOutside[pipe1.point.y][pipe1.point.x] = '?';
                        System.out.println("y: " + pipe1.point.y + ", x: " + pipe1.point.x);
                    }
                } else {
                    matrixWithoutTilesOutside[pipe1.point.y][pipe1.point.x] = 'X';
                }
            }
        }
        return sum;
    }

    private static void findAllInnerAndOuterWalls(HashSet<XY> visitedPaths, Pipe[][] pipes, char[][] matrixWithoutTilesOutside) {
        Pipe[][] p = findAndFillConnections(visitedPaths, pipes, matrixWithoutTilesOutside);
        findAndFillOutside(visitedPaths, p, matrixWithoutTilesOutside);
        for (int i = 0; i < 100; i++) {
            applyLogic(p);
            checkSurroundings(p, matrixWithoutTilesOutside);
        }
    }


    private static void checkSurroundings(Pipe[][] p, char[][] matrix) {
        for (int y1 = 0; y1 < matrix.length; y1++) {
            for (int x1 = 0; x1 < matrix[0].length; x1++) {
                XY xy = new XY(x1, y1);
                Pipe pipe = p[xy.y][xy.x];
                if (pipe.north == Type.EMPTY) {
                    XY dir = directionXYHashMap.get(CardinalDirection.NORTH);
                    int y = xy.y + dir.y;
                    int x = xy.x + dir.x;
                    if (Util.isWithinRangeOfMatrix(y, x, matrix)) {
                        Pipe pipe1 = p[y][x];
                        if (pipe1.south == Type.INSIDE) {
                            pipe.north = Type.INSIDE;
                        } else if (pipe1.south == Type.OUTSIDE) {
                            pipe.north = Type.OUTSIDE;
                        }
                    }
                }
                if (pipe.east == Type.EMPTY) {
                    XY dir = directionXYHashMap.get(CardinalDirection.EAST);
                    int y = xy.y + dir.y;
                    int x = xy.x + dir.x;
                    if (Util.isWithinRangeOfMatrix(y, x, matrix)) {
                        Pipe pipe1 = p[y][x];
                        if (pipe1.west == Type.INSIDE) {
                            pipe.east = Type.INSIDE;
                        } else if (pipe1.west == Type.OUTSIDE) {
                            pipe.east = Type.OUTSIDE;
                        }
                    }
                }
                if (pipe.east == Type.EMPTY) {
                    XY dir = directionXYHashMap.get(CardinalDirection.NORTH);
                    int y = xy.y + dir.y;
                    int x = xy.x + dir.x;
                    if (Util.isWithinRangeOfMatrix(y, x, matrix)) {
                        Pipe pipe1 = p[y][x];
                        if (pipe.north == Type.CONNECTED && pipe1.south == Type.CONNECTED && pipe1.east != Type.CONNECTED) {
                            pipe.east = pipe1.east;
                        }
                    }
                }
                if (pipe.east == Type.EMPTY) {
                    XY dir = directionXYHashMap.get(CardinalDirection.SOUTH);
                    int y = xy.y + dir.y;
                    int x = xy.x + dir.x;
                    if (Util.isWithinRangeOfMatrix(y, x, matrix)) {
                        Pipe pipe1 = p[y][x];
                        if (pipe.south == Type.CONNECTED && pipe1.north == Type.CONNECTED && pipe1.east != Type.CONNECTED) {
                            pipe.east = pipe1.east;
                        }
                    }
                }
                if (pipe.south == Type.EMPTY) {
                    XY dir = directionXYHashMap.get(CardinalDirection.SOUTH);
                    int y = xy.y + dir.y;
                    int x = xy.x + dir.x;
                    if (Util.isWithinRangeOfMatrix(y, x, matrix)) {
                        if (p[y][x].north == Type.INSIDE) {
                            pipe.south = Type.INSIDE;
                        } else if (p[y][x].north == Type.OUTSIDE) {
                            pipe.south = Type.OUTSIDE;
                        }
                    }
                }
                if (pipe.west == Type.EMPTY) {
                    XY dir = directionXYHashMap.get(CardinalDirection.WEST);
                    int y = xy.y + dir.y;
                    int x = xy.x + dir.x;
                    if (Util.isWithinRangeOfMatrix(y, x, matrix)) {
                        Pipe pipe1 = p[y][x];
                        if (pipe1.east == Type.INSIDE) {
                            pipe.west = Type.INSIDE;
                        } else if (pipe1.east == Type.OUTSIDE) {
                            pipe.west = Type.OUTSIDE;
                        }
                    }
                }
                if (pipe.east == Type.EMPTY) {
                    XY dir = directionXYHashMap.get(CardinalDirection.NORTH);
                    int y = xy.y + dir.y;
                    int x = xy.x + dir.x;
                    if (Util.isWithinRangeOfMatrix(y, x, matrix)) {
                        Pipe pipe1 = p[y][x];
                        if (pipe.north == Type.CONNECTED && pipe1.south == Type.CONNECTED && pipe1.east != Type.CONNECTED) {
                            pipe.east = pipe1.east;
                        }
                    }
                }
                if (pipe.east == Type.EMPTY) {
                    XY dir = directionXYHashMap.get(CardinalDirection.SOUTH);
                    int y = xy.y + dir.y;
                    int x = xy.x + dir.x;
                    if (Util.isWithinRangeOfMatrix(y, x, matrix)) {
                        Pipe pipe1 = p[y][x];
                        if (pipe.south == Type.CONNECTED && pipe1.north == Type.CONNECTED && pipe1.east != Type.CONNECTED) {
                            pipe.east = pipe1.east;
                        }
                    }
                }
            }
        }
    }

    private static void applyLogic(Pipe[][] p) {
        for (Pipe[] pipes : p) {
            for (Pipe pipe : pipes) {
                if (!pipe.isAllSet()) {
                    if (pipe.symbol == '-') {
                        if (pipe.north == Type.OUTSIDE) {
                            pipe.south = Type.INSIDE;
                        } else if (pipe.north == Type.INSIDE) {
                            pipe.south = Type.OUTSIDE;
                        } else if (pipe.south == Type.OUTSIDE) {
                            pipe.north = Type.INSIDE;
                        } else if (pipe.south == Type.INSIDE) {
                            pipe.north = Type.OUTSIDE;
                        }
                    } else if (pipe.symbol == '|') {
                        if (pipe.west == Type.OUTSIDE) {
                            pipe.east = Type.INSIDE;
                        } else if (pipe.west == Type.INSIDE) {
                            pipe.east = Type.OUTSIDE;
                        } else if (pipe.east == Type.OUTSIDE) {
                            pipe.west = Type.INSIDE;
                        } else if (pipe.east == Type.INSIDE) {
                            pipe.west = Type.OUTSIDE;
                        }
                    } else if (pipe.symbol == 'F') {
                        if (pipe.north == Type.OUTSIDE) {
                            pipe.west = Type.OUTSIDE;
                        } else if (pipe.north == Type.INSIDE) {
                            pipe.west = Type.INSIDE;
                        } else if (pipe.west == Type.OUTSIDE) {
                            pipe.north = Type.OUTSIDE;
                        } else if (pipe.west == Type.INSIDE) {
                            pipe.north = Type.INSIDE;
                        }
                    } else if (pipe.symbol == 'L') {
                        if (pipe.west == Type.OUTSIDE) {
                            pipe.south = Type.OUTSIDE;
                        } else if (pipe.west == Type.INSIDE) {
                            pipe.south = Type.INSIDE;
                        } else if (pipe.south == Type.OUTSIDE) {
                            pipe.west = Type.OUTSIDE;
                        } else if (pipe.south == Type.INSIDE) {
                            pipe.west = Type.INSIDE;
                        }
                    } else if (pipe.symbol == 'J') {
                        if (pipe.east == Type.OUTSIDE) {
                            pipe.south = Type.OUTSIDE;
                        } else if (pipe.east == Type.INSIDE) {
                            pipe.south = Type.INSIDE;
                        } else if (pipe.south == Type.OUTSIDE) {
                            pipe.east = Type.OUTSIDE;
                        } else if (pipe.south == Type.INSIDE) {
                            pipe.east = Type.INSIDE;
                        }
                    } else if (pipe.symbol == '7') {
                        if (pipe.north == Type.OUTSIDE) {
                            pipe.east = Type.OUTSIDE;
                        } else if (pipe.north == Type.INSIDE) {
                            pipe.east = Type.INSIDE;
                        } else if (pipe.east == Type.OUTSIDE) {
                            pipe.north = Type.OUTSIDE;
                        } else if (pipe.east == Type.INSIDE) {
                            pipe.north = Type.INSIDE;
                        }
                    } else if (pipe.symbol == 'O') {
                        pipe.north = Type.OUTSIDE;
                        pipe.east = Type.OUTSIDE;
                        pipe.south = Type.OUTSIDE;
                        pipe.west = Type.OUTSIDE;
                    } else if (pipe.symbol == '.') {
                        if (pipe.north == Type.OUTSIDE || pipe.east == Type.OUTSIDE || pipe.south == Type.OUTSIDE || pipe.west == Type.OUTSIDE) {
                            pipe.north = Type.OUTSIDE;
                            pipe.east = Type.OUTSIDE;
                            pipe.south = Type.OUTSIDE;
                            pipe.west = Type.OUTSIDE;
                        } else if (pipe.north == Type.INSIDE || pipe.east == Type.INSIDE || pipe.south == Type.INSIDE || pipe.west == Type.INSIDE) {
                            pipe.north = Type.INSIDE;
                            pipe.east = Type.INSIDE;
                            pipe.south = Type.INSIDE;
                            pipe.west = Type.INSIDE;
                        }
                    }


                }
            }
        }
    }

    private static void findAndFillOutside(HashSet<XY> visited, Pipe[][] pipe, char[][] matrix) {
        for (XY xy : visited) {
            Pipe pipe1 = pipe[xy.y][xy.x];
            if (pipe1.north == Type.EMPTY) {
                if (Util.isWithinRangeOfMatrix(xy.y - 1, xy.x, matrix)) {
                    char symbol = matrix[xy.y - 1][xy.x];
                    if (symbol == 'O') {
                        pipe1.north = Type.OUTSIDE;
                    }
                } else {
                    pipe1.north = Type.OUTSIDE;
                }
            }
            if (pipe1.east == Type.EMPTY) {
                if (Util.isWithinRangeOfMatrix(xy.y, xy.x + 1, matrix)) {
                    char symbol = matrix[xy.y][xy.x + 1];
                    if (symbol == 'O') {
                        pipe1.east = Type.OUTSIDE;
                    }
                } else {
                    pipe1.east = Type.OUTSIDE;
                }
            }
            if (pipe1.south == Type.EMPTY) {
                if (Util.isWithinRangeOfMatrix(xy.y + 1, xy.x, matrix)) {
                    char symbol = matrix[xy.y + 1][xy.x];
                    if (symbol == 'O') {
                        pipe1.south = Type.OUTSIDE;
                    }
                } else {
                    pipe1.south = Type.OUTSIDE;

                }
            }
            if (pipe1.west == Type.EMPTY) {
                if (Util.isWithinRangeOfMatrix(xy.y, xy.x - 1, matrix)) {
                    char symbol = matrix[xy.y][xy.x - 1];
                    if (symbol == 'O') {
                        pipe1.west = Type.OUTSIDE;
                    }
                } else {
                    pipe1.west = Type.OUTSIDE;
                }
            }
        }
    }

    private static Pipe[][] findAndFillConnections(HashSet<XY> visited, Pipe[][] pipe, char[][] matrix) {

        for (XY xy : visited) {
            char symbol = matrix[xy.y][xy.x];
            for (int i = 0; i < directions.size(); i++) {
                Direction direction = directions.get(i);
                if (symbol == direction.symbol) {
                    for (int j = 0; j < direction.type.length; j++) {
                        if (direction.type[j] == CardinalDirection.SOUTH) {
                            pipe[xy.y][xy.x].south = Type.CONNECTED;
                        }
                        if (direction.type[j] == CardinalDirection.WEST) {
                            pipe[xy.y][xy.x].west = Type.CONNECTED;
                        }
                        if (direction.type[j] == CardinalDirection.EAST) {
                            pipe[xy.y][xy.x].east = Type.CONNECTED;
                        }
                        if (direction.type[j] == CardinalDirection.NORTH) {
                            pipe[xy.y][xy.x].north = Type.CONNECTED;
                        }
                    }
                }

            }
        }
        return pipe;
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
                    matrixWithoutTilesOutside[y][x] = 'O';
                }

            }
        }
        return matrixWithoutTilesOutside;
    }

    private enum Type {
        INSIDE,
        OUTSIDE,
        EMPTY,
        CONNECTED,
    }

    private static class Pipe {
        char symbol;
        XY point = new XY();
        Type north = Type.EMPTY;
        Type east = Type.EMPTY;
        Type south = Type.EMPTY;
        Type west = Type.EMPTY;

        boolean isAllSet() {
            if (north == Type.EMPTY) {
                return false;
            }
            if (east == Type.EMPTY) {
                return false;
            }
            if (south == Type.EMPTY) {
                return false;
            }
            if (west == Type.EMPTY) {
                return false;
            }
            return true;
        }
    }

    private enum CardinalDirection {
        NORTH,
        EAST,
        SOUTH,
        WEST
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
        CardinalDirection[] type;

        public Direction(char symbol, XY[] directions, CardinalDirection[] type) {
            this.symbol = symbol;
            this.directions = directions;
            this.type = type;
        }

    }

}
