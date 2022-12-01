package days;

import classes.Fish;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Day6 {

    public long Lanternfish1(ArrayList<String> strings, int daysToGrow) {
        LinkedList<Fish> fishes = parse(strings);
        fishes = grow(fishes, daysToGrow);

        return fishes.size();
    }

    public LinkedList<Fish> grow(LinkedList<Fish> fishes, int daysToGrow) {
        for (int i = 0; i < daysToGrow; i++) {
            LinkedList<Fish> newFishes = new LinkedList<>();
            for (Fish fish : fishes) {
                if (fish.internalTimer == 0) {
                    fish.internalTimer = 6;
                    newFishes.addLast(new Fish(8));
                } else {
                    fish.internalTimer--;
                }
            }
            for (Fish newFish : newFishes) {
                fishes.addLast(newFish);
            }
        }
        return fishes;
    }

    private LinkedList<Fish> parse(ArrayList<String> strings) {
        LinkedList<Fish> nr = new LinkedList<>();
        for (String string : strings) {
            String[] nrs = string.split(",");
            for (String s : nrs) {
                nr.addLast(new Fish(Integer.parseInt(s)));
            }
        }

        return nr;
    }

    public BigInteger Lanternfish2(ArrayList<String> strings) {
        LinkedList<Fish> fishes = parse2(strings);
        BigInteger big = grow3(fishes);

        return big;
    }

    private BigInteger grow3(LinkedList<Fish> fishes) {
        HashMap<Integer, Long> longHashMap = new HashMap<>();
        longHashMap.put(6, 3989468462L);
        longHashMap.put(5, 4368232009L);
        longHashMap.put(4, 4726100874L);
        longHashMap.put(3, 5217223242L);
        longHashMap.put(2, 5617089148L);
        longHashMap.put(1, 6206821033L);
        longHashMap.put(0, 6703087164L);
        BigInteger b = new BigInteger("0");
        for (Fish fish : fishes) {
            b = b.add(BigInteger.valueOf(longHashMap.get(fish.internalTimer)));

        }
        return b;
    }

    public BigInteger grow2(Fish fishes, int daysToGrow) {
        for (int i = 0; i < daysToGrow; i++) {
            long[] unsyncFishes = fishes.unsyncFishes;

            long prev = unsyncFishes[0];
            for (int j = 1; j < unsyncFishes.length; j++) {
                long temp = unsyncFishes[j];
                unsyncFishes[j] = prev;
                prev = temp;
            }
            unsyncFishes[2] += prev;
            unsyncFishes[0] = prev;

        }
        BigInteger b = new BigInteger("0");
        for (long unsyncFish : fishes.unsyncFishes) {
            b = b.add(new BigInteger(String.valueOf(unsyncFish)));
        }
        return b;
    }

    private LinkedList<Fish> parse2(ArrayList<String> strings) {
        LinkedList<Fish> nr = new LinkedList<>();
        for (String string : strings) {
            String[] nrs = string.split(",");
            for (String s : nrs) {
                nr.addLast(new Fish(Integer.parseInt(s)));
            }
        }

        return nr;
    }
}
