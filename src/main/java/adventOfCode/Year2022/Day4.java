package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day4 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day4.class);
        List<String> l2 = FileUtil.readfileExempel(Day4.class);
        TimeUtil.startTime();
        oneStar(l2);
        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l2);
        twoStar(l);
        TimeUtil.endTime();
    }

    private static void oneStar(List<String> l) {
        int sum = 0;
        List<SectionAssigment> assignments = parseToSection(l);
        for (SectionAssigment assignment : assignments) {
            if (assignment.a <= assignment.c && assignment.b >= assignment.d
                    || assignment.c <= assignment.a && assignment.d >= assignment.b) {
                sum++;
            }
        }
        System.out.println("star one: " + sum);
    }

    private static List<SectionAssigment> parseToSection(List<String> l) {
        List<SectionAssigment> assignments = new ArrayList<>();
        for (String s : l) {
            assignments.add(createAssignmentPair(s));
        }
        return assignments;
    }

    private static SectionAssigment createAssignmentPair(String s) {
        SectionAssigment sectionAssigment = new SectionAssigment();
        String[] sections = s.split(",");
        String[] ab = sections[0].split("-");
        sectionAssigment.a = Integer.parseInt(ab[0]);
        sectionAssigment.b = Integer.parseInt(ab[1]);
        String[] cd = sections[1].split("-");
        sectionAssigment.c = Integer.parseInt(cd[0]);
        sectionAssigment.d = Integer.parseInt(cd[1]);
        return sectionAssigment;
    }

    private static void twoStar(List<String> l) {
        int sum = 0;
        List<SectionAssigment> assignments = parseToSection(l);
        for (SectionAssigment x : assignments) {
            int[] range = new int[x.max() + 1];
            for (int i = x.a; i <= x.b; i++) {
                range[i] = 1;
            }
            for (int i = x.c; i <= x.d; i++) {
                if (range[i] == 1) {
                    sum++;
                    break;
                }
            }
        }
        System.out.println("star two: " + sum);
    }

    private static class SectionAssigment {
        int a;
        int b;
        int c;
        int d;

        int max() {
            return Math.max(Math.max(a, b), Math.max(c, d));
        }

        @Override
        public String toString() {
            return "SectionAssigment{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    ", d=" + d +
                    '}';
        }
    }
}
