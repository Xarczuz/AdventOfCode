package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day11 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day11.class);
        List<String> l2 = FileUtil.readfileExempel(Day11.class);
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
        ArrayList<Monkey> monkeys = parseMonkeys(l);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < monkeys.size(); j++) {
                Monkey monkey = monkeys.get(j);
                int size = monkey.items.size();
                for (int k = 0; k < size; k++) {
                    long worry;
                    Long item = monkey.items.removeFirst();
                    if (monkey.operation.equals("*")) {
                        worry = item * getOperationAmount(item, monkey);
                    } else {
                        worry = item + getOperationAmount(item, monkey);
                    }
                    long newWorry = worry / 3;
                    monkey.inspected++;
                    if (newWorry % monkey.divisible == 0) {
                        monkeys.get(monkey.ifTrue).items.addLast(newWorry);
                    } else {
                        monkeys.get(monkey.ifFalse).items.addLast(newWorry);

                    }
                }
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (Monkey monkey : monkeys) {
            list.add(monkey.inspected);
        }
        list.sort((o1, o2) -> o2 - o1);
        System.out.println("Star one: " + list.get(0) * list.get(1));
    }

    private static long getOperationAmount(Long item, Monkey monkey) {
        if (monkey.operationAmount.equals("old")) {
            return item;
        }
        return Long.parseLong(monkey.operationAmount);
    }

    private static ArrayList<Monkey> parseMonkeys(List<String> l) {
        ArrayList<Monkey> monkeys = new ArrayList<>();
        Monkey m = new Monkey();
        for (String s : l) {
            if (s.contains("Monkey")) {
                String nr = s.split(" ")[1].replace(":", "");
                m.nr = Integer.parseInt(nr);
            } else if (s.contains("Starting")) {
                int i = s.lastIndexOf(':') + 1;
                String[] items = s.substring(i).split(",");
                LinkedList<Long> list = new LinkedList<>();
                for (String item : items) {
                    list.addLast(Long.parseLong(item.trim()));
                }
                m.items = list;
            } else if (s.contains("Operation")) {
                String[] olds = getString(s, "old");
                String op = olds[1].trim();
                m.operation = op;
                m.operationAmount = olds[2].trim();
            } else if (s.contains("Test")) {
                String test = getString(s, "by")[1].trim();
                m.divisible = Integer.parseInt(test);
            } else if (s.contains("true")) {
                String monkey = getString(s, "monkey")[1].trim();
                m.ifTrue = Integer.parseInt(monkey);
            } else if (s.contains("false")) {
                String monkey = getString(s, "monkey")[1].trim();
                m.ifFalse = Integer.parseInt(monkey);
            } else if (s.isBlank()) {
                monkeys.add(m);
                m = new Monkey();
            }
        }
        return monkeys;
    }

    private static String[] getString(String s, String old) {
        return s.substring(s.indexOf(old)).split(" ");
    }

    private static void twoStar(List<String> l) {

        int worryLevel = 1;
        int[] ints = new int[]{1, 20, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};

        ArrayList<Monkey> monkeys = parseMonkeys(l);
        ArrayList<Monkey> list = findWorryLevel(monkeys, ints);

        System.out.println("Star one: " + worryLevel);
    }

    private static ArrayList<Monkey> findWorryLevel(ArrayList<Monkey> monkeys, int[] worryLevel) {

        for (int i = 1; i <= 10000; i++) {
            for (int j = 0; j < monkeys.size(); j++) {
                Monkey monkey = monkeys.get(j);
                int size = monkey.items.size();
                for (int k = 0; k < size; k++) {
                    long worry;
                    Long item = monkey.items.removeFirst();
                    if (monkey.operation.equals("*")) {
                        worry = item * getOperationAmount(item, monkey);
                    } else {
                        worry = item + getOperationAmount(item, monkey);
                    }
                    monkey.inspected++;
                    long newWorry = worry;
                    if (newWorry % monkey.divisible == 0) {
                        int ifTrue = monkey.ifTrue;
                        monkeys.get(ifTrue).items.addLast(newWorry);
                    } else {
                        int ifFalse = monkey.ifFalse;
                        monkeys.get(ifFalse).items.addLast(newWorry);
                    }
                }
            }
            for (int i1 : worryLevel) {
                if (i == i1) {
                    for (Monkey monkey : monkeys) {
                        System.out.println(monkey.inspected);
                    }
                    System.out.println("-----");

                }
            }
        }
        return monkeys;
    }

    static class Monkey {
        int nr;
        LinkedList<Long> items;
        String operation;
        String operationAmount;
        int divisible;
        int ifTrue;
        int ifFalse;
        int inspected = 0;
    }

}
