package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Day10 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day10.class);
        List<String> l2 = FileUtil.readfileExempel(Day10.class);
        List<String> l3 = FileUtil.readfileExempel2(Day10.class);
        TimeUtil.startTime();
        oneStar(l);
        oneStar(l2);
        oneStar(l3);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l2);
        //twoStar(l);
        TimeUtil.endTime();
    }

    private static void oneStar(List<String> l) {
        int[] ints = {20, 60, 100, 140, 180, 220};
        int sum = 0;
        LinkedList<String> list = new LinkedList<>(l);
        int x = 1;
        ArrayList<Instruction> instructions = new ArrayList<>();
        for (int cycle = 1; cycle <= 220; cycle++) {
            ArrayList<UUID> uuidsToDelete = new ArrayList<>();
            String instruction = "";
            for (Instruction inst : instructions) {
                if (inst.cycleToAdd == cycle) {
                    x += inst.value;
                    uuidsToDelete.add(inst.uuid);
                }
            }
            for (UUID uuid : uuidsToDelete) {
                int i = 0;
                for (; i < instructions.size(); i++) {
                    Instruction inst = instructions.get(i);
                    if (inst.uuid == uuid) {
                        break;
                    }
                }
                instructions.remove(i);
            }
            if (!list.isEmpty() && instructions.isEmpty()) {
                instruction = list.removeFirst();
            }
            if (instruction.contains("noop")) {
                Instruction e = new Instruction();
                e.cycleToAdd = cycle + 1;
                instructions.add(e);

            } else if (instruction.contains("addx")) {
                String stringNr = instruction.split(" ")[1];
                int intNr = Integer.parseInt(stringNr);
                Instruction instruction1 = new Instruction();
                instruction1.cycleToAdd = cycle + 2;
                instruction1.value = intNr;
                instructions.add(instruction1);
            }


//            System.out.println("cycle: " + cycle + " x: " + x);
            for (int anInt : ints) {
                if (cycle == anInt) {
                    sum += anInt * x;
                }
            }

        }
        System.out.println("One star: " + sum);

    }

    private static void twoStar(List<String> l) {

    }

    static class Instruction {
        public int value;
        UUID uuid = UUID.randomUUID();
        int cycleToAdd;
        String instruction = "";
    }
}
