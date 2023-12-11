package adventOfCode.Year2022;

import classes.Direction;
import classes.YX;
import util.FileUtil;
import util.TimeUtil;
import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day17 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day17.class);
        List<String> l2 = FileUtil.readfileExempel(Day17.class);
        TimeUtil.startTime();
        oneStar(l2);
        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l2);
//        twoStar(l);
        TimeUtil.endTime();
    }


    private static void oneStar(List<String> l) {
        CaveAndAirJets caveAndAirJets = new CaveAndAirJets(new ArrayList<>(l));
        Shapes shapes = new Shapes();
        for (int i = 0; i < 2022; i++) {
            YX[] shape = shapes.getNextRock();
            offsettShape(shape, caveAndAirJets);
            fallingRock(shape, caveAndAirJets);
            addShapeToCave(shape, caveAndAirJets);
        }
        printCave(caveAndAirJets.cave);
        System.out.println("Tower height: " + (caveAndAirJets.lastRockPositionY + 1));
    }

    private static void printCave( String[][] cave ) {

        for (int i = cave.length - 1; i >= 0; i--) {
            String[] s = cave[i];
            for (String string : s) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

    private static void fallingRock(YX[] shape, CaveAndAirJets caveAndAirJets) {
        boolean collision = false;
        while (!collision) {
            Direction direction = caveAndAirJets.getNextAirJet();
//            System.out.println(direction);
//            addShapeToCave2(shape,caveAndAirJets);
//            System.out.println();
            moveShapeLeftOrRight(direction, shape, caveAndAirJets);
//            addShapeToCave2(shape,caveAndAirJets);
//            System.out.println();
            collision = moveShapeDown(shape, caveAndAirJets);
//            addShapeToCave2(shape,caveAndAirJets);
//            System.out.println();
        }

    }

    private static boolean moveShapeDown(YX[] shape, CaveAndAirJets caveAndAirJets) {
        boolean move = true;
        for (YX YX : shape) {
            String strings = caveAndAirJets.cave[Math.max(YX.y - 1, 0)][YX.x];
            if (strings.equals("#")) {
                move = false;
                break;
            }
        }
        if (move) {
            for (YX YX : shape) {
                if (YX.y == 0) {
                    move = false;
                    break;
                } else {
                    YX.y -= 1;
                }
            }
        }
        return !move;
    }

    private static void moveShapeLeftOrRight(Direction direction, YX[] shape, CaveAndAirJets caveAndAirJets) {
        boolean move = true;
        if (direction == Direction.LEFT) {
            for (YX YX : shape) {
                String strings = caveAndAirJets.cave[YX.y][Math.max(0, YX.x - 1)];
                if (strings.equals("#")) {
                    move = false;
                    break;
                }
            }
            for (YX YX : shape) {
                if (YX.x == 0) {
                    move = false;
                    break;
                }
            }
            if (move) {
                for (YX YX : shape) {
                    YX.x -= 1;
                }
            }
        } else {
            for (YX YX : shape) {
                String strings = caveAndAirJets.cave[YX.y][Math.min(6, YX.x + 1)];
                if (strings.equals("#")) {
                    move = false;
                    break;
                }
            }
            for (YX YX : shape) {
                if (YX.x == 6) {
                    move = false;
                    break;
                }
            }
            if (move) {
                for (YX YX : shape) {
                    YX.x += 1;
                }
            }
        }
    }

    private static void addShapeToCave2(YX[] shape, CaveAndAirJets caveAndAirJets) {
        String[][] cave = Util.deepCopyMatrix(caveAndAirJets.cave);
        for (YX YX : shape) {
            cave[YX.y][YX.x] = "@";
        }
        printCave(cave);
    }

    private static void addShapeToCave(YX[] shape, CaveAndAirJets caveAndAirJets) {
        for (YX YX : shape) {
            caveAndAirJets.cave[YX.y][YX.x] = "#";
            caveAndAirJets.lastRockPositionY = Math.max(YX.y, caveAndAirJets.lastRockPositionY);
        }
    }

    private static void offsettShape(YX[] shape, CaveAndAirJets caveAndAirJets) {
        for (YX YX : shape) {
            YX.x += 2;
            YX.y += caveAndAirJets.nextRockIndexY();
        }
    }

    private static void twoStar(List<String> l) {
    }


    private static class CaveAndAirJets {
        ArrayList<Direction> airJets = new ArrayList<>();
        int airIndex = 0;
        int lastRockPositionY = 0;
        String[][] cave = new String[2022 * 4][7];


        public CaveAndAirJets(ArrayList<String> airJets) {
            this.airJets = parseAirJets(airJets);
            for (String[] strings : cave) {
                Arrays.fill(strings, ".");
            }
        }

        public int nextRockIndexY() {
            if (airIndex == 0) {
                return 3;
            }
            return lastRockPositionY + 4;
        }

        private ArrayList<Direction> parseAirJets(ArrayList<String> airJets) {
            ArrayList<Direction> directions = new ArrayList<>();
            for (String airJet : airJets) {
                for (char c : airJet.toCharArray()) {
                    if (c == '<') {
                        directions.add(Direction.LEFT);
                    } else {
                        directions.add(Direction.RIGHT);
                    }
                }
            }
            return directions;
        }

        public Direction getNextAirJet() {
            int index = airIndex % airJets.size();
            if (index == 0) {
                airIndex = 0;
            }
            Direction direction = airJets.get(airIndex);
            airIndex++;
            return direction;
        }
    }

    private static class Shapes {
        int rockIndex = 0;
        //####
        static YX[] horizontalBar = new YX[]{new YX(0, 0), new YX(0, 1), new YX(0, 2), new YX(0, 3)};
        //.#.
        //###
        //.#.
        static YX[] plus = new YX[]{new YX(0, 1), new YX(1, 0), new YX(1, 1), new YX(1, 2), new YX(2, 1),};
        //..#
        //..#
        //###
        static YX[] invertedL = new YX[]{new YX(0, 0), new YX(0, 1), new YX(0, 2), new YX(1, 2), new YX(2, 2),};
        //#
        //#
        //#
        //#
        static YX[] verticalBar = new YX[]{new YX(0, 0), new YX(1, 0), new YX(2, 0), new YX(3, 0)};
        //##
        //##
        static YX[] square = new YX[]{new YX(0, 0), new YX(0, 1), new YX(1, 0), new YX(1, 1),};

        public static ArrayList<YX[]> getShapes() {
            ArrayList<YX[]> array = new ArrayList<>();
            array.add(horizontalBar);
            array.add(plus);
            array.add(invertedL);
            array.add(verticalBar);
            array.add(square);
            return array;
        }

        private static int getNextRockIndex(int rockIndex, int amountOfShapes) {
            if (rockIndex % amountOfShapes == 0) {
                rockIndex = 0;
            }
            return rockIndex;
        }

        public YX[] getNextRock() {
            rockIndex = getNextRockIndex(rockIndex, getShapes().size());
            YX[] shape = getShapes().get(rockIndex);
            rockIndex++;
            return copyShape(shape);
        }

        public YX[] copyShape(YX[] shape) {
            YX[] copy = new YX[shape.length];
            for (int i = 0; i < shape.length; i++) {
                YX YX = shape[i];
                copy[i] = YX.deepCopy();
            }
            return copy;
        }
    }
}
