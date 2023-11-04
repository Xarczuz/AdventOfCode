package adventOfCode.Year2022;

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


    private static void oneStar(List<String> l2) {

        int lastRockPositionY = 0;
        String[][] cave = new String[10 * 4][7];
        for (String[] strings : cave) {
            Arrays.fill(strings, ".");
        }

        Shapes shapes = new Shapes();
        for (int i = 0; i < 1; i++) {
            XY[] shape = shapes.getNextRock();
            offsettShape(shape, lastRockPositionY);
            //TODO falling
            addShapeToCave(shape,cave);

        }
            Util.print(cave);


    }

    private static void addShapeToCave(XY[] shape, String[][] cave) {
        for (XY xy : shape) {
            cave[xy.y][xy.x] ="@";
        }
    }

    private static void offsettShape(XY[] shape, int lastRockPositionY) {
        for (XY xy : shape) {
            xy.x += 2;
            xy.y += lastRockPositionY + 3;
        }

    }


    private static void twoStar(List<String> l2) {
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
