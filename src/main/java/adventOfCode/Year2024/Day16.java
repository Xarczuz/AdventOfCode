package adventOfCode.Year2024;

import classes.YX;
import util.FileUtil;
import util.TimeUtil;
import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Day16 {
    private static final YX[] cardinalDirections = YX.cardinalDirectionsList();
    static ArrayList<HashSet<YX>> agents = new ArrayList<>();
    static long bestScore = Long.MAX_VALUE;

//    private static void twoStar(List<String> l2) {
//    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        List<String> l = FileUtil.readfile(Day16.class);
        List<String> l2 = FileUtil.readfileExempel(Day16.class);
        List<String> l3 = FileUtil.readfileExempel2(Day16.class);
        oneStar(l2);
        oneStar(l3);
        TimeUtil.startTime();
        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
        //        twoStar(l);
//        twoStar(l2);
        TimeUtil.endTime();
    }

    private static YX findCord(char[][] lab, char symbol) {
        YX yx = new YX();
        for (int y = 0; y < lab.length; y++) {
            for (int x = 0; x < lab[0].length; x++) {
                char c = lab[y][x];
                if (c == symbol) {
                    yx.x = x;
                    yx.y = y;
                    return yx;
                }
            }
        }
        return yx;
    }

    private static void oneStar(List<String> l) {
        agents.clear();
        bestScore = Long.MAX_VALUE;
        char[][] lab = parse(l);
        YX start = findCord(lab, 'S');
        YX end = findCord(lab, 'E');
        System.out.println("Start: " + start);
        System.out.println("End: " + end);
        LabAgent labAgent = new LabAgent();
        labAgent.direction = YX.east();
        labAgent.location = start;
        HashSet<YX> visited = new HashSet<>();
        visited.add(start);
        HashMap<YX, Long> valueMap = new HashMap<>();
        long sum = findShortestPath(lab, labAgent, visited, end, valueMap, Long.MAX_VALUE);
        System.out.println(sum);


        tiles(lab);

        System.out.println("Score: " + sum);
        int tile = 0;
        for (char[] chars : lab) {
            for (char aChar : chars) {
                if (aChar == 'O') {
                    tile++;
                }
            }
        }
        System.out.println("Tiles: " + tile);
        // 106512 Right
    }

    private static void tiles(char[][] lab) {
        System.out.println("-----------------------------------------------------------------------");
        for (HashSet<YX> agent : agents) {
            char[][] chars = lab;
            for (YX yx : agent) {
//            if (chars[yx.y][yx.x] == 'S' || chars[yx.y][yx.x] == 'E') {
//                continue;
//            }
                chars[yx.y][yx.x] = 'O';
            }
            Util.print(chars);
            System.out.println("-----------------------------------------------------------------------");
        }
    }

    private static long findShortestPath(char[][] lab, LabAgent labAgent, HashSet<YX> visited, YX end, HashMap<YX, Long> valueMap, long minValue) {
        if (labAgent.score() > minValue) {
            return Long.MAX_VALUE;
        }
        if (valueMap.containsKey(labAgent.location)) {
            Long l = valueMap.get(labAgent.location);
            if (l + 1002 < labAgent.score()) {
                return Long.MAX_VALUE;
            }
        }
        valueMap.put(labAgent.location, labAgent.score());
        if (lab[labAgent.location.y][labAgent.location.x] == '#') {
            return Long.MAX_VALUE;
        }
        if (labAgent.location.equals(end)) {
            long score = labAgent.score();
            if (score < bestScore) {
                bestScore = score;
                agents.clear();
            }
            if (bestScore == score) {
                agents.add(visited);
            }
            System.out.println(score);
            return score;
        }
        for (YX cardinalDirection : cardinalDirections) {
            if (cardinalDirection.equals(labAgent.direction)) {
                LabAgent newLabAgent = labAgent.deepCopy();
                newLabAgent.location.go(cardinalDirection);
                if (visited.contains(newLabAgent.location) || isStepValid(newLabAgent, lab)) {
                    continue;
                }
                newLabAgent.steps++;
                HashSet<YX> visited1 = deepCopy(visited);
                visited1.add(newLabAgent.location);
                minValue = Math.min(minValue, findShortestPath(lab, newLabAgent, visited1, end, valueMap, minValue));
            } else {
                LabAgent newLabAgent = labAgent.deepCopy();
                newLabAgent.location.go(cardinalDirection);
                newLabAgent.direction = cardinalDirection;
                if (visited.contains(newLabAgent.location) || isStepValid(newLabAgent, lab)) {
                    continue;
                }
                newLabAgent.steps++;
                newLabAgent.rotation++;
                HashSet<YX> visited1 = deepCopy(visited);
                visited1.add(newLabAgent.location);
                minValue = Math.min(minValue, findShortestPath(lab, newLabAgent, visited1, end, valueMap, minValue));
            }
        }
        return minValue;
    }

    private static HashSet<YX> deepCopy(HashSet<YX> visited) {
        HashSet<YX> objects = new HashSet<>();
        for (YX yx : visited) {
            objects.add(yx.deepCopy());
        }
        return objects;
    }

    private static boolean isStepValid(LabAgent deepCopy, char[][] lab) {
        char c = lab[deepCopy.location.y][deepCopy.location.x];
        return c != '.' && c != 'E';
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
        YX direction;
        int rotation;
        int steps;
        YX location;

        LabAgent deepCopy() {
            LabAgent copy = new LabAgent();
            copy.direction = this.direction.deepCopy();
            copy.rotation = this.rotation;
            copy.steps = this.steps;
            copy.location = this.location.deepCopy();
            return copy;
        }

        public long score() {
            return this.steps + (this.rotation * 1000L);
        }
    }

}
