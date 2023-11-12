package adventOfCode.Year2022;

import classes.Direction;
import classes.XY;
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
//        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l2);
//        twoStar(l);
        TimeUtil.endTime();
    }


    private static void oneStar(List<String> l) {
        CaveAndAirJets caveAndAirJets = new CaveAndAirJets(new ArrayList<>(l));
        Shapes shapes = new Shapes();
        for (int i = 0; i < 2; i++) {
            XY[] shape = shapes.getNextRock();
            offsettShape(shape, caveAndAirJets);
            fallingRock(shape, caveAndAirJets);
            addShapeToCave(shape, caveAndAirJets);
        }

        String[][] cave = caveAndAirJets.cave;
        for (int i = cave.length - 1; i >= 0; i--) {
            String[] s = cave[i];
            for (String string : s) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

    private static void fallingRock(XY[] shape, CaveAndAirJets caveAndAirJets) {
        boolean collision = false;
        while (!collision) {
            Direction direction = caveAndAirJets.getNextAirJet();
            moveShapeLeftOrRight(direction, shape);
            collision = moveShapeDown(shape, caveAndAirJets);
            if (collision){
                moveShapeLeftOrRight(direction, shape);
            }
        }
    }

    private static boolean moveShapeDown(XY[] shape, CaveAndAirJets caveAndAirJets) {
        boolean move = true;
        for (XY xy : shape) {
            String strings = caveAndAirJets.cave[Math.max(xy.y - 1, 0)][xy.x];
            if (strings.equals("#")) {
                move = false;
                break;
            }
        }
        if (move) {
            for (XY xy : shape) {
                if (xy.y == 0) {
                    move = false;
                    break;
                } else {
                    xy.y -= 1;
                }
            }
        }
        return !move;
    }

    private static void moveShapeLeftOrRight(Direction direction, XY[] shape) {
        boolean move = true;
        if (direction == Direction.LEFT) {
            for (XY xy : shape) {
                if (xy.x == 0) {
                    move = false;
                    break;
                }
            }
            if (move) {
                for (XY xy : shape) {
                    xy.x -= 1;
                }
            }
        } else {
            for (XY xy : shape) {
                if (xy.x == 6) {
                    move = false;
                    break;
                }
            }
            if (move) {
                for (XY xy : shape) {
                    xy.x += 1;
                }
            }
        }
    }

    private static void addShapeToCave(XY[] shape, CaveAndAirJets caveAndAirJets) {
        int lastYPosition = 0;
        for (XY xy : shape) {
            caveAndAirJets.cave[xy.y][xy.x] = "#";
            lastYPosition = Math.max(xy.y, lastYPosition);
        }
        caveAndAirJets.lastRockPositionY = lastYPosition;
    }

    private static void offsettShape(XY[] shape, CaveAndAirJets caveAndAirJets) {
        for (XY xy : shape) {
            xy.x += 2;
            xy.y += caveAndAirJets.nextRockIndexY();
        }
    }

    private static void twoStar(List<String> l) {
    }


    private static class CaveAndAirJets {
        ArrayList<Direction> airJets = new ArrayList<>();
        int airIndex = 0;
        int lastRockPositionY = 0;
        String[][] cave = new String[3 * 4][7];


        public CaveAndAirJets(ArrayList<String> airJets) {
            this.airJets = parseAirJets(airJets);
            for (String[] strings : cave) {
                Arrays.fill(strings, ".");
            }
        }

        public int nextRockIndexY() {
            return lastRockPositionY + 3;
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
        static XY[] horizontalBar = new XY[]{new XY(0, 0), new XY(1, 0), new XY(2, 0), new XY(3, 0)};
        //.#.
        //###
        //.#.
        static XY[] plus = new XY[]{new XY(1, 0), new XY(0, 1), new XY(1, 1), new XY(2, 1), new XY(1, 2),};
        //..#
        //..#
        //###
        static XY[] invertedL = new XY[]{new XY(0, 0), new XY(1, 0), new XY(2, 0), new XY(2, 1), new XY(2, 2),};
        //#
        //#
        //#
        //#
        static XY[] verticalBar = new XY[]{new XY(0, 0), new XY(0, 1), new XY(0, 2), new XY(0, 3)};
        //##
        //##
        static XY[] square = new XY[]{new XY(0, 0), new XY(1, 0), new XY(0, 1), new XY(1, 1),};

        public static ArrayList<XY[]> getShapes() {
            ArrayList<XY[]> array = new ArrayList<>();
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

        public XY[] getNextRock() {
            rockIndex = getNextRockIndex(rockIndex, getShapes().size());
            XY[] shape = getShapes().get(rockIndex);
            rockIndex++;
            return copyShape(shape);
        }

        public XY[] copyShape(XY[] shape) {
            XY[] copy = new XY[shape.length];
            for (int i = 0; i < shape.length; i++) {
                XY xy = shape[i];
                copy[i] = xy.deepCopy();
            }
            return copy;
        }
    }
}
