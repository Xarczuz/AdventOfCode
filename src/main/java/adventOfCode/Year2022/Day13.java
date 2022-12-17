package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static adventOfCode.Year2022.Day13.State.*;

public class Day13 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day13.class);
        List<String> l2 = FileUtil.readfileExempel(Day13.class);
        List<String> l3 = FileUtil.readfileExempel2(Day13.class);
        TimeUtil.startTime();
        oneStar(l2);
        oneStar(l3);
        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l2);
        twoStar(l);
        TimeUtil.endTime();
    }

    private static void oneStar(List<String> l) {
        int sum = 0;
        int pair = 0;
        for (int i = 0; i < l.size(); i += 3) {
            pair++;
            ArrayList<Object> leftArr = new ArrayList<>();
            parseString(l.get(i).toCharArray(), 0, leftArr);
            ArrayList<Object> rightArr = new ArrayList<>();
            parseString(l.get(i + 1).toCharArray(), 0, rightArr);

            State rightOrder = isRightOrder(leftArr, rightArr);
            if (rightOrder == TRUE) {
                sum += pair;
            }
        }
        System.out.println("Star one: " + sum);
    }

    private static State isRightOrder(ArrayList<Object> leftArr, ArrayList<Object> rightArr) {
        State s = TRUE;
        for (int i = 0; i < leftArr.size(); i++) {
            if (i >= rightArr.size()) {
                return FALSE;
            }
            Object left = leftArr.get(i);
            Object right = rightArr.get(i);
            if (left instanceof Integer && right instanceof Integer) {
                int leftInt = Integer.parseInt(left.toString());
                int rightInt = Integer.parseInt(right.toString());
                if (leftInt < rightInt) {
                    return TRUE;
                } else if (leftInt > rightInt) {
                    return FALSE;
                }
                s = State.DRAW;
            } else if (left instanceof ArrayList<?> && right instanceof ArrayList<?>) {
                State state = isRightOrder((ArrayList<Object>) left, (ArrayList<Object>) right);
                if (state == FALSE) {
                    return FALSE;
                }
                if (state == TRUE) {
                    return TRUE;
                }

            } else if (left instanceof Integer) {
                ArrayList<Object> newLeft = new ArrayList<>();
                newLeft.add(left);
                leftArr.set(i, newLeft);
                i--;
            } else if (right instanceof Integer) {
                ArrayList<Object> newRight = new ArrayList<>();
                newRight.add(right);
                rightArr.set(i, newRight);
                i--;
            }
        }
        if (leftArr.size() == rightArr.size()) {
            return DRAW;
        }
        return TRUE;
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
                    arr.add(Integer.parseInt(s));
                }
                break;
            }
            if (c == ',') {
                if (s.isBlank()) {
                } else {
                    arr.add(Integer.parseInt(s));
                }
                s = "";
                continue;
            }
            s += c;
        }
        return i;
    }

    private static void twoStar(List<String> l) {

        ArrayList<Object> arrayList = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).isBlank()) {
                continue;
            }
            ArrayList<Object> arr = new ArrayList<>();
            parseString(l.get(i).toCharArray(), 0, arr);
            arrayList.add(arr);

        }
        ArrayList<Object> e = new ArrayList<>();
        e.add(2);
        arrayList.add(e);
        ArrayList<Object> e2 = new ArrayList<>();
        e2.add(6);
        arrayList.add(e2);
        arrayList.sort((o1, o2) -> {
            if (isRightOrder((ArrayList<Object>) o1, (ArrayList<Object>) o2) == TRUE) {
                return -1;
            }
            return 1;
        });
        int i = 1;
        int k = 1;
        for (int j = 0; j < arrayList.size(); j++, k++) {
            Object o = arrayList.get(j);
            ArrayList<Object> x = (ArrayList<Object>) ((ArrayList<Object>) o).get(0);
            System.out.println(x);
            if (x.toString().equals("[[[[6]]]]") || x.toString().equals("[[[[2]]]]")) {
                i *= k;
            }

        }
        System.out.println("Star two: " + i);
    }


    enum State {
        TRUE, FALSE, DRAW
    }
}
