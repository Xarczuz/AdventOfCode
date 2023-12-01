package adventOfCode.Year2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import util.FileUtil;
import util.TimeUtil;

public class Day1 {

  public static void main(String[] args) throws IOException, URISyntaxException {
    List<String> l = FileUtil.readfile(Day1.class);
    List<String> l2 = FileUtil.readfileExempel(Day1.class);
    List<String> l3 = FileUtil.readfileExempel2(Day1.class);
    TimeUtil.startTime();
//    oneStar(l);
//    oneStar(l2);
    TimeUtil.endTime();
    TimeUtil.startTime();
    twoStar(l3);
    twoStar(l);
//    System.out.println(makeInt("seven123"));
    System.out.println(makeInt("jnthkgsrone6vnkdvkjznjnboneoneseven7"));
    TimeUtil.endTime();
  }

  private static void twoStar(List<String> l) {
    ArrayList<Integer> digits = removeNoneDigits2(l);
    System.out.println(digits);
    long sum = summarize(digits);
    System.out.println("Two star sum: " + sum);
    System.out.println();
  }

  private static void oneStar(List<String> l) {
    ArrayList<Integer> digits = removeNoneDigits(l);
    System.out.println(digits);
    long sum = summarize(digits);
    System.out.println("One star sum: " + sum);
  }

  private static long summarize(ArrayList<Integer> digits) {
    long sum = 0;
    for (Integer digit : digits) {
      sum += digit;
    }
    return sum;
  }

  private static ArrayList<Integer> removeNoneDigits(List<String> l) {
    ArrayList<Integer> digits = new ArrayList<>();
    for (String s : l) {
      ArrayList<Character> chars = new ArrayList<>();
      for (char c : s.toCharArray()) {
        if (c >= '0' && c <= '9') {
          chars.add(c);
        }
      }
      String stringDigit = chars.get(0) + String.valueOf(chars.get(chars.size() - 1));
      digits.add(Integer.parseInt(stringDigit));
    }
    return digits;
  }

  static String[] strings = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "1", "2", "3", "4", "5",
      "6", "7", "8", "9"};
  static String[] strings2 = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
  static Integer[] ints = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

  private static ArrayList<Integer> removeNoneDigits2(List<String> l) {
    ArrayList<Integer> digits = new ArrayList<>();

    for (String s : l) {
      ArrayList<Character> chars = makeInt(s);
      String stringDigit = chars.get(0) + String.valueOf(chars.get(chars.size() - 1));
      digits.add(Integer.parseInt(stringDigit));
    }
    return digits;
  }

  private static ArrayList<Character> makeInt(String s) {
    s = s.toLowerCase(Locale.ROOT);
    ArrayList<Pair> order = ordering(s);

    ArrayList<Character> chars = new ArrayList<>();
    chars.add(order.get(0).s.charAt(0));
    chars.add(order.get(order.size() - 1).s.charAt(0));

    return chars;
  }

  private static ArrayList<Pair> ordering(String s) {
    ArrayList<Pair> ordering = new ArrayList<>();
    indexes(
        s,
        ordering,
        strings,
        0
    );

    ordering.sort(Comparator.comparingInt(o -> o.i));
    ArrayList<Pair> orderingWithOnlyTowOrLess = new ArrayList<>();
    if (ordering.size() > 2) {
      orderingWithOnlyTowOrLess.add(ordering.get(0));
      orderingWithOnlyTowOrLess.add(ordering.get(ordering.size() - 1));
    } else {
      return ordering;
    }
    return orderingWithOnlyTowOrLess;
  }

  private static void indexes(String s, ArrayList<Pair> ordering, String[] strings1, int indexStart) {
    if (s.isEmpty()) {
      return;
    }
    for (String string : strings1) {
      int index = s.indexOf(string);
      if (index != -1) {
        ordering.add(new Pair(
            convertS(string),
            index + indexStart
        ));
        int beginIndex = index + string.length();
        if (beginIndex < s.length()) {
          String substring = s.substring(beginIndex);
          indexes(
              substring,
              ordering,
              new String[]{string},
              beginIndex
          );
        }
      }
    }
  }

  private static String convertS(String string) {
    if (string.length() > 1) {
      for (int i = 0; i < strings.length; i++) {
        String s = strings[i];
        if (string.equals(s)) {
          return String.valueOf(ints[i]);
        }
      }
    }
    return string;
  }

  record Pair(String s, int i) {

  }
}
