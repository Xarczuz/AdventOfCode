package adventOfCode.Year2021;

import java.util.List;

public class Day1 {

    public static long countDiff(List<String> arrayList){
        long prev = Long.parseLong(arrayList.get(0));
        long count=0;
        for (int i = 1; i < arrayList.size(); i++) {
            long current = Long.parseLong(arrayList.get(i));
            if (current>prev){
                count++;
            }
            prev=current;
        }
        return count;
    }

    public static long countDiffSums(List<String> arrayList){
        long prev1 = Long.parseLong(arrayList.get(0));
        long prev2 = Long.parseLong(arrayList.get(1));
        long prev3 = Long.parseLong(arrayList.get(2));
        long count=0;
        for (int i = 1; i+2 < arrayList.size(); i++) {
            long current1 = Long.parseLong(arrayList.get(i));
            long current2 = Long.parseLong(arrayList.get(i+1));
            long current3 = Long.parseLong(arrayList.get(i+2));

            if (current1+current2+current3>prev1+prev2+prev3){
                count++;
            }
            prev1 = current1;
            prev2 = current2;
            prev3 = current3;
        }
        return count;
    }
}
