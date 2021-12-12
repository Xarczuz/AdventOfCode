package days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day12 {

    public int part1(ArrayList<String> strings) {
        HashMap<String, ArrayList<String>> caveMap = parse(strings);
        System.out.println(caveMap);
        ArrayList<String> strings1 = new ArrayList<>();
        strings1.add("start");
        HashSet<String> smallCave = new HashSet<>();
        smallCave.add("start");
        smallCave.add("end");
        visitPaths("start", smallCave, caveMap, strings1);

        return sum;
    }

    int sum = 0;
    HashSet<String> h = new HashSet<>();

    private void visitPaths(String start, HashSet<String> smallCave, HashMap<String, ArrayList<String>> caveMap, ArrayList<String> strings) {
        ArrayList<String> linkedCaves = caveMap.getOrDefault(start, new ArrayList<>());
        for (String c : linkedCaves) {
            if (c.equals("end")) {
                sum++;
                strings.add("end");
                h.add(strings.toString());
//                System.out.println(strings);
            }
            if (!smallCave.contains(c)) {
                HashSet<String> smallCave1 = new HashSet<>(smallCave);
                if (isLowerCase(c)) {
                    smallCave1.add(c);
                }
                ArrayList<String> strings1 = new ArrayList<>(strings);
                strings1.add(c);
                visitPaths(c, smallCave1, caveMap, strings1);
            }
        }
    }

    private boolean isLowerCase(String c) {
        for (char l : c.toCharArray()) {
            if (l > 90) {
                return true;
            }
        }
        return false;
    }

    private HashMap<String, ArrayList<String>> parse(ArrayList<String> strings) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String s : strings) {
            String[] path = s.split("-");
            ArrayList<String> value = map.getOrDefault(path[0], new ArrayList<>());
            value.add(path[1]);
            map.put(path[0], value);

            value = map.getOrDefault(path[1], new ArrayList<>());
            value.add(path[0]);
            map.put(path[1], value);

        }

        return map;
    }

    public int part2(ArrayList<String> readFromFile) {
        HashMap<String, ArrayList<String>> caveMap = parse(readFromFile);
        System.out.println(caveMap);
        StringBuilder strings1 = new StringBuilder();
        strings1.append("start");
        HashMap<String, Integer> smallCave = new HashMap<>();
        smallCave.put("start", 2);
        smallCave.put("end", 2);
        for (String small : caveMap.keySet()) {
            if (isLowerCase(small)) {
                HashMap<String, Integer> smallCaveUnique = new HashMap<>(smallCave);
                for (String sc : caveMap.keySet()) {
                    if (isLowerCase(sc) && !sc.equals(small)) {
                        smallCaveUnique.put(sc, 1);
                    }
                }
                visitPaths2("start", smallCaveUnique, caveMap, strings1);
            }
        }
        return h.size();
    }

    private void visitPaths2(String start, HashMap<String, Integer> smallCave, HashMap<String, ArrayList<String>> caveMap, StringBuilder strings) {
        ArrayList<String> linkedCaves = caveMap.getOrDefault(start, new ArrayList<>());
        for (String c : linkedCaves) {
            if (c.equals("end")) {
                h.add(strings + "end");
            } else if (!c.equals("start")) {
                Integer nrVisits = smallCave.getOrDefault(c, 0);
                if (nrVisits < 2) {
                    HashMap<String, Integer> smallCave1 = new HashMap<>(smallCave);
                    if (isLowerCase(c)) {
                        smallCave1.put(c, nrVisits + 1);
                    }
                    visitPaths2(c, smallCave1, caveMap, new StringBuilder(strings).append(c));
                }
            }
        }
    }
}
