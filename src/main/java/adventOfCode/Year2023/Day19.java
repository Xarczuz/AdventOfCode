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
//        oneStar(l);
        oneStar(l2);
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l);
        twoStar(l2);
        TimeUtil.endTime();
    }

    static long ss = 0;

    private static void twoStar(List<String> l) {
        Work work = parseWork(l);

        Parts part = new Parts(new Interval(1, 4000), new Interval(1, 4000), new Interval(1, 4000), new Interval(1, 4000));
        long sum = checkPart2(part, work.system, "in");

        System.out.println(sum);
        System.out.println(ss);
    }

    private static long checkPart2(Parts part, HashMap<String, ArrayList<Condition>> system, String in) {
        ArrayList<Condition> conditions = system.get(in);

        if (in.equals("A")) {
            long calc = part.calc();
            ss += calc;
            return calc;
        } else if (in.equals("R")) {
            return 0;
        }
        //167409079868000
        //167010937327821
        //174205762239804
        //182796038177793
        //156954768310743
        long sum = 0;
        for (Condition condition : conditions) {
            String part1 = condition.part;
            Interval interval = part.getInterval(part1);
            if (condition.type == Type.less) {
                if (interval.end < condition.number) {
                    sum += checkPart2(part, system, condition.target);
                }
                if (interval.start < condition.number) {
                    Parts parts = part.deepCopy();
                    Interval interval1 = parts.getInterval(part1);
                    interval1.end = condition.number - 1;
                    Interval interval2 = part.getInterval(part1);
                    interval2.start = condition.number;
                    sum += checkPart2(parts, system, condition.target);
                }
            } else if (condition.type == Type.greater) {
                if (interval.start > condition.number) {
                    sum += checkPart2(part, system, condition.target);
                }
                if (interval.end > condition.number) {
                    Parts parts = part.deepCopy();
                    Interval interval1 = parts.getInterval(part1);
                    interval1.start = condition.number + 1;
                    Interval interval2 = part.getInterval(part1);
                    interval2.end = condition.number;

                    sum += checkPart2(parts, system, condition.target);
                }
            } else if (condition.type == Type.none) {
                sum += checkPart2(part, system, condition.target);
            }
        }
        return sum;
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
            Interval interval = part.getInterval(part1);
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

        public long calc() {
            return (long) (x.end - x.start) * (s.end - s.start) * (a.end - a.start) * (m.end - m.start);
        }

        public Parts deepCopy() {
            return new Parts(x.deepCopy(), m.deepCopy(), a.deepCopy(), s.deepCopy());
        }

        public Interval getInterval(String part1) {
            Interval interval = null;
            if ("x".equals(part1)) {
                interval = x;
            }
            if ("s".equals(part1)) {
                interval = s;
            }
            if ("m".equals(part1)) {
                interval = m;
            }
            if ("a".equals(part1)) {
                interval = a;
            }
            return interval;
        }
    }

    private static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Interval deepCopy() {
            return new Interval(start, end);
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
