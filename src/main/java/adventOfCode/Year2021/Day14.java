package adventOfCode.Year2021;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Day14 {

    public long part1(ArrayList<String> strings, int loops) {
        Info info = parse(strings);

        for (int i = 0; i < loops; i++) {
            StringBuilder sb = new StringBuilder();
            char a = info.template.charAt(0);
            sb.append(a);
            for (int x = 0; x < info.template.length() - 1; x++) {
                a = info.template.charAt(x);
                char b = info.template.charAt(x + 1);
                char c = info.map.get(String.valueOf(a) + String.valueOf(b));
                sb.append(c).append(b);
            }
            info.template = sb.toString();
        }
        return count(info);
    }

    public long part2(ArrayList<String> strings, int loops) {
        Info info = parse(strings);
        parseMap(info);
        long[] chars = initCount(info);
        countRest(loops, info, chars);
        long l = count2(chars);
        return l;
    }

    private void countRest(int loops, Info info, long[] chars) {
        for (long i = 0; i < loops; i++) {
            HashMap<String, Long> map = new HashMap<>(25);

            for (String s : info.newMap.keySet()) {
                long l = info.newMap.getOrDefault(s, 0L);
                char a = s.charAt(0);
                char b = s.charAt(1);
                char c = info.map.get(String.valueOf(new char[] {a, b}));
                chars[c - 65] += l;
                String key = String.valueOf(new char[] {a, c});
                map.put(key, map.getOrDefault(key, 0L) + l);
                String key1 = String.valueOf(new char[] {c, b});
                map.put(key1, map.getOrDefault(key1, 0L) + l);
            }

            info.newMap = map;

        }
    }

    private long[] initCount(Info info) {
        long[] chars = new long[25];
        for (char c : info.template.toCharArray()) {
            chars[c - 65]++;
        }
        return chars;
    }

    private long count2(long[] chars) {
        long big = Long.MIN_VALUE;
        long small = Long.MAX_VALUE;
        for (long r : chars) {
            if (r != 0 && r <= small) {
                small = r;
            }
            if (r >= big) {
                big = r;
            }
        }
        return BigInteger.valueOf(big).subtract(BigInteger.valueOf(small)).longValue();
    }

    private void parseMap(Info info) {
        HashMap<String, Long> map = new HashMap<>();

        for (int x = 0; x < info.template.length() - 1; x++) {
            StringBuilder sb = new StringBuilder();
            char a = info.template.charAt(x);
            char b = info.template.charAt(x + 1);
            sb.append(a).append(b);
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0L) + 1);
        }

        info.newMap = map;
    }

    private long count(Info info) {
        long[] chars = initCount(info);
        long big = Integer.MIN_VALUE;
        long small = Integer.MAX_VALUE;
        for (long r : chars) {
            if (r != 0 && r <= small) {
                small = r;
            }
            if (r >= big) {
                big = r;
            }
        }
        return big - small;
    }

    private Info parse(ArrayList<String> strings) {
        Info info = new Info();
        HashMap<String, Character> map = new HashMap<>();
        info.template = strings.get(0);
        for (int i = 2; i < strings.size(); i++) {
            String[] ss = strings.get(i).split("->");

            map.put(ss[0].trim(), ss[1].trim().charAt(0));
        }
        info.map = map;
        return info;
    }

    private class Info {
        public HashMap<String, Long> newMap;
        String template;
        HashMap<String, Character> map;

        @Override
        public String toString() {
            return "Info{" +
                    "newMap=" + newMap +
                    ", template='" + template + '\'' +
                    ", map=" + map +
                    '}';
        }
    }
}
