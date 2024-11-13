package adventOfCode.Year2023;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day19 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day19.class);
        List<String> l2 = FileUtil.readfileExempel(Day19.class);
        TimeUtil.startTime();
        oneStar(l);
        oneStar(l2);
        TimeUtil.endTime();
//        TimeUtil.startTime();
//        twoStar(l);
//        twoStar(l2);
//        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {
        Work work = parseWork(l);

        Parts part = new Parts(new Interval(1, 4000), new Interval(1, 4000), new Interval(1, 4000), new Interval(1, 4000));
        checkPart2(part, work.system, "in");

        System.out.println();
    }

    private static void checkPart2(Parts part, HashMap<String, ArrayList<Condition>> system, String in) {

    }

    private static void oneStar(List<String> l) {
        Work work = parseWork(l);

        long sum = 0;
        for (Parts part : work.parts) {
            if (checkPart(part, work.system, "in")) {
                sum += part.sum();
            }
        }
        System.out.println(sum);
    }

    private static boolean checkPart(Parts part, HashMap<String, ArrayList<Condition>> system, String in) {
        ArrayList<Condition> conditions = system.get(in);

        if (in.equals("A")) {
            return true;
        } else if (in.equals("R")) {
            return false;
        }
        for (Condition condition : conditions) {
            String part1 = condition.part;
            Interval interval = new Interval(Integer.MIN_VALUE, Integer.MIN_VALUE);
            if ("x".equals(part1)) {
                interval = part.x;
            }
            if ("s".equals(part1)) {
                interval = part.s;
            }
            if ("m".equals(part1)) {
                interval = part.m;
            }
            if ("a".equals(part1)) {
                interval = part.a;
            }
            if (condition.type == Type.less) {
                if (interval.start < condition.number) {
                    return checkPart(part, system, condition.target);
                }
            } else if (condition.type == Type.greater) {
                if (interval.start > condition.number) {
                    return checkPart(part, system, condition.target);
                }
            } else if (condition.type == Type.none) {
                return checkPart(part, system, condition.target);
            }
        }
        return false;
    }

    private static Work parseWork(List<String> l) {
        HashMap<String, ArrayList<Condition>> system = new HashMap<>();
        int i = 0;
        for (; i < l.size(); i++) {
            String s = l.get(i);
            if (s.isEmpty()) {
                break;
            }
            int indexOf = s.indexOf('{');
            String name = s.substring(0, indexOf);
            String workflow = s.substring(indexOf);
            String replace = workflow.replace("{", "").replace("}", "");
            String[] split = replace.split(",");
            ArrayList<Condition> conditions = new ArrayList<>();
            for (String string : split) {
                Condition condition = new Condition(string);
                conditions.add(condition);
            }
            system.put(name, conditions);

        }
        i++;
        ArrayList<Parts> parts = new ArrayList<>();
        for (; i < l.size(); i++) {
            String line = l.get(i).replace("{", "").replace("}", "");
            String[] split = line.split(",");

            int x = Integer.parseInt(split[0].substring(split[0].indexOf('=') + 1));
            int m = Integer.parseInt(split[1].substring(split[1].indexOf('=') + 1));
            int a = Integer.parseInt(split[2].substring(split[2].indexOf('=') + 1));
            int s = Integer.parseInt(split[3].substring(split[3].indexOf('=') + 1));
            Parts p = new Parts(x, m, a, s);
            parts.add(p);
        }
        return new Work(system, parts);
    }

    enum Type {
        less, greater, none
    }

    private record Work(HashMap<String, ArrayList<Condition>> system, ArrayList<Parts> parts) {
    }

    private static class Parts {
        Interval x; //Extremely cool looking
        Interval m; //Musical
        Interval a; //Aerodynamic
        Interval s; //Shiny

        public Parts(Interval x, Interval m, Interval a, Interval s) {
            this.x = x;
            this.m = m;
            this.a = a;
            this.s = s;
        }

        public Parts(int x, int m, int a, int s) {
            this.x = new Interval(x, x);
            this.m = new Interval(m, m);
            this.a = new Interval(a, a);
            this.s = new Interval(s, s);
        }

        public long sum() {
            return x.start + s.start + a.start + m.start;
        }
    }

    private static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static class Condition {
        Type type = Type.none;
        int number;
        String target;
        String part;

        public Condition(String string) {
            if (string.contains(">")) {
                type = Type.greater;
                part = string.substring(0, string.indexOf('>'));
                String substring2 = string.substring(string.indexOf('>') + 1, string.indexOf(':'));
                number = Integer.parseInt(substring2);

            } else if (string.contains("<")) {
                type = Type.less;
                part = string.substring(0, string.indexOf('<'));
                String substring2 = string.substring(string.indexOf('<') + 1, string.indexOf(':'));
                number = Integer.parseInt(substring2);
            }
            target = string.substring(string.indexOf(':') + 1);
        }
    }

}
