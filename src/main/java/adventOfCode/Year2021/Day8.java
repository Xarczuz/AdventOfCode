package adventOfCode.Year2021;

import classes.InputOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day8 {
    public int part1(ArrayList<String> strings) {
        ArrayList<InputOutput> inOut = parse(strings);

        int count = 0;
        for (InputOutput inputOutput : inOut) {
            for (String s : inputOutput.out) {
                int length = s.length();

                if (length == 2 || length == 4 || length == 3 || length == 7) {
                    count++;
                }
            }
        }
        return count;
    }

    public long part2(ArrayList<String> strings) {
        ArrayList<InputOutput> inOut = parse(strings);
        long sum = 0;
        for (InputOutput inputOutput : inOut) {
            HashMap<String, Integer> hashMap = mapNr2(inputOutput);
            long nr = makeNr(hashMap, inputOutput.out);
            sum += nr;
        }

        return sum;
    }

    private long makeNr(HashMap<String, Integer> hashMap, ArrayList<String> out) {
        StringBuilder nr = new StringBuilder();
        for (String s : out) {
            nr.append(hashMap.get(sortString(s)));
        }
        return Long.parseLong(nr.toString());
    }

    public HashMap<String, Integer> mapNr2(InputOutput inputOutput) {
        HashMap<String, Integer> nrs = new HashMap<>();
        HashMap<Character, String> map = new HashMap<>();
        HashMap<String, Character> mapInverted = new HashMap<>();
        createMap(inputOutput, nrs, map, mapInverted, false);
        while (nrs.size() < 10) {
            for (String s : inputOutput.in) {
                s = sortString(s);
                if (s.length() == 6) {
                    if (isD(map, s, "abdefg")) {
                        nrs.put(s, 6);
                    } else if (isD(map, s, "abcefg")) {
                        nrs.put(s, 0);
                    } else if (isD(map, s, "abcdfg")) {
                        nrs.put(s, 9);
                    }
                }
                if (s.length() == 5) {
                    if (isD(map, s, "acdfg")) {
                        nrs.put(s, 3);
                    } else if (isD(map, s, "acdeg")) {
                        nrs.put(s, 2);
                    } else if (isD(map, s, "abdfg")) {
                        nrs.put(s, 5);
                    }
                }
            }
        }
        return nrs;
    }

    private void createMap3(InputOutput inputOutput, HashMap<String, Integer> nrs, HashMap<Character, String> map, HashMap<String, Character> mapInverted) {
        boolean[] done = new boolean[9];
        String[] ss = new String[7];
        for (String s : inputOutput.in) {
            if (s.length() == 2) {
                ss[2] += s;
                ss[5] += s;
            }
            if (s.length() == 3) {
                s = sortString(s).replace(sortString(ss[2]), "");
                ss[0] = s;
            }
            if (s.length() == 4) {
                s = sortString(s).replace(sortString(ss[2]), "");
                ss[1] = s;
                ss[3] = s;
            }


        }

    }

    private void createMap(InputOutput inputOutput, HashMap<String, Integer> nrs, HashMap<Character, String> map, HashMap<String, Character> mapInverted, boolean flipp) {
        boolean[] done = new boolean[9];
        while (nrs.size() < 7) {
            for (String s : inputOutput.in) {
                if (!done[0] && s.length() == 2) {
                    if (!flipp) {
                        map.put(s.charAt(0), "c");
                        map.put(s.charAt(1), "f");
                        mapInverted.put("c", s.charAt(0));
                        mapInverted.put("f", s.charAt(1));
                    } else {
                        map.put(s.charAt(0), "f");
                        map.put(s.charAt(1), "c");
                        mapInverted.put("f", s.charAt(0));
                        mapInverted.put("c", s.charAt(1));
                    }
                    nrs.put(s, 1);
                    done[0] = true;
                } else if (!done[1] && s.length() == 3 && done[0]) {
                    putInMap("a", map, s.toCharArray(), mapInverted);
                    nrs.put(s, 7);
                    done[1] = true;
                } else if (!done[2] && s.length() == 5 && done[0] && done[1]) {
                    String tre = tre(inputOutput, map, mapInverted);
                    String fyra = fyra(inputOutput, map, mapInverted);
                    char lika = extracted(inputOutput, map, fyra, tre);
                    map.put(lika, "d");
                    mapInverted.put("d", lika);
                    done[2] = true;
                } else if (!done[3] && s.length() == 4 && done[0] && done[1] && done[2]) {
                    putInMap("b", map, s.toCharArray(), mapInverted);
                    nrs.put(s, 4);
                    nrs.put(treFull(inputOutput, mapInverted), 3);
                    done[3] = true;
                } else if (!done[4] && s.length() == 5 && done[0] && done[1] && done[2] && done[3]) {
                    String fem = fem(inputOutput, map, mapInverted);
                    if (fem == null) {
                        nrs.clear();
                        map.clear();
                        mapInverted.clear();
                        createMap(inputOutput, nrs, map, mapInverted, true);
                        return;
                    }
                    putInMap("g", map, fem.toCharArray(), mapInverted);
                    nrs.put(s, 5);
                    done[4] = true;
                } else if (!done[5] && s.length() == 5 && done[0] && done[1] && done[2] && done[3] && done[4]) {
                    String tva = tva(inputOutput, map, mapInverted);
                    putInMap("e", map, tva.toCharArray(), mapInverted);
                    nrs.put(s, 2);
                    done[5] = true;
                } else if (!done[6] && s.length() == 7 && nrs.containsValue(4)) {
                    nrs.put(s, 8);
                    done[6] = true;
                }
            }
        }
    }

    private String tva(InputOutput inputOutput, HashMap<Character, String> map, HashMap<String, Character> mapInverted) {
        for (String s : inputOutput.in) {
            if (s.length() == 5) {
                if (s.contains(String.valueOf(mapInverted.getOrDefault("a", 'X')))
                        && s.contains(String.valueOf(mapInverted.getOrDefault("c", 'X')))
                        && s.contains(String.valueOf(mapInverted.getOrDefault("d", 'X')))
                        && s.contains(String.valueOf(mapInverted.getOrDefault("g", 'X')))
                        && !s.contains(String.valueOf(mapInverted.getOrDefault("f", 'X')))) {
                    s = s.replace(String.valueOf(mapInverted.getOrDefault("a", 'X')), "");
                    s = s.replace(String.valueOf(mapInverted.getOrDefault("c", 'X')), "");
                    s = s.replace(String.valueOf(mapInverted.getOrDefault("d", 'X')), "");
                    s = s.replace(String.valueOf(mapInverted.getOrDefault("g", 'X')), "");
                    return s;
                }
            }
        }
        return null;
    }

    private String fem(InputOutput inputOutput, HashMap<Character, String> map, HashMap<String, Character> mapInverted) {
        for (String s : inputOutput.in) {
            if (s.length() == 5) {
                if (s.contains(String.valueOf(mapInverted.getOrDefault("a", 'X')))
                        && s.contains(String.valueOf(mapInverted.getOrDefault("b", 'X')))
                        && s.contains(String.valueOf(mapInverted.getOrDefault("d", 'X')))
                        && s.contains(String.valueOf(mapInverted.getOrDefault("f", 'X')))) {
                    s = s.replace(String.valueOf(mapInverted.getOrDefault("a", 'X')), "");
                    s = s.replace(String.valueOf(mapInverted.getOrDefault("b", 'X')), "");
                    s = s.replace(String.valueOf(mapInverted.getOrDefault("d", 'X')), "");
                    s = s.replace(String.valueOf(mapInverted.getOrDefault("f", 'X')), "");
                    return s;
                }
            }
        }
        return null;
    }

    private String fyra(InputOutput inputOutput, HashMap<Character, String> map, HashMap<String, Character> mapInverted) {
        for (String s : inputOutput.in) {
            if (s.length() == 4) {
                s = s.replace(String.valueOf(mapInverted.getOrDefault("c", 'X')), "");
                s = s.replace(String.valueOf(mapInverted.getOrDefault("f", 'X')), "");
                return s;
            }
        }

        return null;
    }

    private String tre(InputOutput inputOutput, HashMap<Character, String> map, HashMap<String, Character> mapInverted) {
        for (String s : inputOutput.in) {
            if (s.length() == 5) {
                if (s.contains(String.valueOf(mapInverted.getOrDefault("c", 'X')))
                        && s.contains(String.valueOf(mapInverted.getOrDefault("f", 'X')))) {
                    s = s.replace(String.valueOf(mapInverted.getOrDefault("c", 'X')), "");
                    s = s.replace(String.valueOf(mapInverted.getOrDefault("f", 'X')), "");
                    return s;
                }
            }
        }

        return null;

    }

    private String treFull(InputOutput inputOutput, HashMap<String, Character> mapInverted) {
        for (String s : inputOutput.in) {
            s = sortString(s);
            if (s.length() == 5) {
                if (s.contains(String.valueOf(mapInverted.getOrDefault("c", 'X')))
                        && s.contains(String.valueOf(mapInverted.getOrDefault("f", 'X')))) {
                    s = s.replace(String.valueOf(mapInverted.getOrDefault("c", 'X')), "");
                    s = s.replace(String.valueOf(mapInverted.getOrDefault("f", 'X')), "");
                    return sortString(s);
                }
            }
        }

        return null;

    }

    private char extracted(InputOutput inputOutput, HashMap<Character, String> map, String s1, String s) {
        if (!s.equals(s1)) {
            for (char c : s1.toCharArray()) {
                for (char c1 : s.toCharArray()) {
                    if (!map.containsKey(c)) {
                        if (c == c1) {
                            return c1;
                        }
                    }
                }
            }
        }

        return 'X';
    }

    private void putInMap(String b, HashMap<Character, String> map, char[] toCharArray, HashMap<String, Character> mapInverted) {
        for (char c : toCharArray) {
            if (!map.containsKey(c)) {
                map.put(c, b);
                mapInverted.put(b, c);
                return;
            }
        }
    }

    private boolean isD(HashMap<Character, String> map, String s, String contains) {
        StringBuilder temp = new StringBuilder();
        s = sortString(s);
        for (char c : s.toCharArray()) {
            temp.append(map.getOrDefault(c, ""));
        }
        String ny = sortString(temp.toString());
        return ny.equals(contains);
    }

    private ArrayList<InputOutput> parse(ArrayList<String> strings) {
        ArrayList<InputOutput> arr = new ArrayList<>();
        for (String string : strings) {
            String[] sArr = string.split("\\|");
            InputOutput inOut = new InputOutput();
            for (String s : sArr[0].trim().split(" ")) {
                inOut.in.add(sortString(s.trim()));
            }
            for (String s : sArr[1].trim().split(" ")) {
                inOut.out.add(sortString(s.trim()));
            }
            arr.add(inOut);
        }
        return arr;
    }

    static String sortString(String str) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return String.valueOf(arr);
    }
}
