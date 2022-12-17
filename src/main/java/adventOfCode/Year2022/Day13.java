package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day13 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day13.class);
        List<String> l2 = FileUtil.readfileExempel(Day13.class);
        TimeUtil.startTime();
//        oneStar(l2);
        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l2);
        //twoStar(l);
        TimeUtil.endTime();
    }

    private static void oneStar(List<String> l) {
        int sum = 0;
        int pair = 1;
        for (int i = 0; i < l.size(); i += 3, pair++) {
            ArrayList<Object> leftArr = new ArrayList<>();
            parseString(l.get(i).toCharArray(), 0, leftArr);
            ArrayList<Object> rightArr = new ArrayList<>();
            parseString(l.get(i + 1).toCharArray(), 0, rightArr);

            if (isRightOrder(leftArr, rightArr)) {
                sum += pair;
            }
        }

        System.out.println("Star one: ");
    }

    private static boolean isRightOrder(ArrayList<Object> leftArr, ArrayList<Object> rightArr) {


        return false;
    }

    private static int parseString(char[] charArray, int i, ArrayList<Object> arr) {
        String s = "";
        for (; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == '[') {
                ArrayList<Object> e = new ArrayList<>();
                arr.add(e);

                i = parseString(charArray, i + 1, e);
                continue;
            }
            if (c == ']') {
                if (!s.isBlank()) {
                    arr.add(s);
                }
                break;
            }
            if (c == ',') {
                if (s.isBlank()) {
                } else {
                    arr.add(s);
                }
                s = "";
                continue;
            }
            s += c;
        }
        return i;
    }


    private static void twoStar(List<String> l) {

    }
}
