package adventOfCode.Year2024;

import classes.CardinalDirection;
import classes.YX;
import util.FileUtil;
import util.TimeUtil;
import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;

public class Day16 {
    private static YX[] cardinalDirections = YX.cardinalDirectionsList();

    public static void main(String[] args) throws IOException, URISyntaxException {

//    List<String> l = FileUtil.readfile(Day15.class);
        List<String> l2 = FileUtil.readfileExempel(Day16.class);
        TimeUtil.startTime();
//    oneStar(l);
        oneStar(l2);
        TimeUtil.endTime();
        TimeUtil.startTime();
        //        twoStar(l);
        twoStar(l2);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l2) {
    }

    private static void oneStar(List<String> l) {
        char[][] lab = parse(l);
        YX yx = findStartCord(lab);
        System.out.println("Start: " + yx);
        LabAgent labAgent = new LabAgent();
        labAgent.direction = CardinalDirection.EAST;
        labAgent.location = yx;
        long sum = findShortestPath(lab, labAgent, new HashSet<>());
        System.out.println(sum);
    }

    private static YX findStartCord(char[][] lab) {
        YX yx = new YX();
        yx.x = 0;
        yx.y = 0;

        for (int y = 0; y < lab.length; y++) {
            for (int x = 0; x < lab[0].length; x++) {
                char c = lab[y][x];
                if (c == 'S') {
                    yx.x = x;
                    yx.y = y;
                    return yx;
                }
            }
        }
        return yx;
    }

    private static long findShortestPath(char[][] lab, LabAgent labAgent, HashSet<YX> visited) {


        return 0;
    }

    private static char[][] parse(List<String> l) {
        char[][] lab = new char[l.size()][l.getFirst().length()];
        for (int i = 0; i < l.size(); i++) {
            String s = l.get(i);
            char[] charArray = s.toCharArray();
            System.arraycopy(charArray, 0, lab[i], 0, charArray.length);
        }
        Util.print(lab);
        return lab;
    }

    private static class LabAgent {
        CardinalDirection direction;
        int rotation;
        int steps;
        YX location;

        LabAgent deepCopy(LabAgent original) {
            LabAgent copy = new LabAgent();
            copy.direction = original.direction;
            copy.rotation = original.rotation;
            copy.steps = original.steps;
            copy.location = original.location.deepCopy();
            return copy;
        }
    }

}
