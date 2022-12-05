package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public class Day5 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day5.class);
        List<String> l2 = FileUtil.readfileExempel(Day5.class);
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
        SupplyStacks supplyStacks = parseSupplyStacks(l);
        moveCrates(l, supplyStacks);

        System.out.print("Star one: ");
        for (int i = 0; i < supplyStacks.stacks.size(); i++) {
            System.out.print(supplyStacks.stacks.get(i).pollLast());
        }
        System.out.println();
    }

    private static void moveCrates(List<String> l, SupplyStacks supplyStacks) {
        for (int i = supplyStacks.maxCratesOneStack + 2; i < l.size(); i++) {
            String x = l.get(i);
            String[] moves = x.split(" ");

            LinkedList<String> stackFrom = supplyStacks.stacks.get(Integer.parseInt(moves[3]) - 1);
            LinkedList<String> stackTo = supplyStacks.stacks.get(Integer.parseInt(moves[5]) - 1);
            LinkedList<String> tempArr = new LinkedList<>();
            for (int j = 0; j < Integer.parseInt(moves[1]); j++) {
                tempArr.add(stackFrom.removeLast());
            }
            for (int j = 0; j < Integer.parseInt(moves[1]); j++) {
                stackTo.add(tempArr.removeFirst());
            }
        }
    }

    private static SupplyStacks parseSupplyStacks(List<String> l) {
        SupplyStacks supplyStacks = createStacks(l);
        fillStacks(l, supplyStacks);
        return supplyStacks;
    }

    private static void fillStacks(List<String> l, SupplyStacks supplyStacks) {
        for (int i = supplyStacks.maxCratesOneStack - 1; i >= 0; i--) {
            String s = l.get(i);
            int stackIndex = 0;
            addSupplies(supplyStacks, s, stackIndex);
        }
    }

    private static void addSupplies(SupplyStacks supplyStacks, String s, int stackIndex) {
        for (int j = 1; j < s.length(); j += 4) {
            char c = s.charAt(j);
            if (c == ' ') {
                stackIndex++;
                continue;
            }
            supplyStacks.stacks.get(stackIndex).add(String.valueOf(c));
            stackIndex++;
        }
    }

    private static SupplyStacks createStacks(List<String> l) {
        SupplyStacks supplyStacks = new SupplyStacks();
        for (int i = 0; i < l.size(); i++) {
            String s = l.get(i);
            if (s.charAt(1) == '1') {
                String[] stacks = s.split(" ");
                supplyStacks = new SupplyStacks(stacks[stacks.length - 1], i);
                break;
            }
        }
        return supplyStacks;
    }

    private static void twoStar(List<String> l) {
        SupplyStacks supplyStacks = parseSupplyStacks(l);
        moveCrates2(l, supplyStacks);

        System.out.print("Star two: ");
        for (int i = 0; i < supplyStacks.stacks.size(); i++) {
            System.out.print(supplyStacks.stacks.get(i).pollLast());
        }
        System.out.println();
    }

    private static void moveCrates2(List<String> l, SupplyStacks supplyStacks) {
        for (int i = supplyStacks.maxCratesOneStack + 2; i < l.size(); i++) {
            String x = l.get(i);
            String[] moves = x.split(" ");

            LinkedList<String> stackFrom = supplyStacks.stacks.get(Integer.parseInt(moves[3]) - 1);
            LinkedList<String> stackTo = supplyStacks.stacks.get(Integer.parseInt(moves[5]) - 1);
            LinkedList<String> tempArr = new LinkedList<>();
            for (int j = 0; j < Integer.parseInt(moves[1]); j++) {
                tempArr.add(stackFrom.removeLast());
            }
            for (int j = 0; j < Integer.parseInt(moves[1]); j++) {
                stackTo.add(tempArr.removeLast());
            }
        }
    }

    private static class SupplyStacks {
        LinkedList<LinkedList<String>> stacks;
        int maxCratesOneStack;

        public SupplyStacks() {
        }

        public SupplyStacks(String stacks, int maxCratesOneStack) {
            this.maxCratesOneStack = maxCratesOneStack;
            this.stacks = new LinkedList<>();
            for (int i = 0; i < Integer.parseInt(stacks); i++) {
                this.stacks.add(new LinkedList<>());
            }
        }
    }
}
