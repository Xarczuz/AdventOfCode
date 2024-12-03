package adventOfCode.Year2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import util.FileUtil;
import util.TimeUtil;

public class Day3 {

  public static void main(String[] args) throws IOException, URISyntaxException {

    System.out.println("(11,8)".matches("\\(\\d+,\\d+\\).{0,}"));
    List<String> l = FileUtil.readfile(Day3.class);
    List<String> l2 = FileUtil.readfileExempel(Day3.class);
    TimeUtil.startTime();
    oneStar(l);
    oneStar(l2);
    TimeUtil.endTime();
    TimeUtil.startTime();
    twoStar(l);
    twoStar(l2);
    TimeUtil.endTime();
  }

  private static void twoStar(List<String> l) {
    long sum = parse2(l);

    System.out.println("Sum: " + sum);
  }

  private static void oneStar(List<String> l) {
    long sum = parse(l);

    System.out.println("Sum: " + sum);
  }

  private static long parse(List<String> l) {
    long sum = 0;
    for (String s : l) {
      String[] muls = s.split("mul");
      for (String mul : muls) {
        String[] split = mul.split(",");
        if (split.length > 1) {
          String s1 = split[0];
          if (s1.startsWith("(") && mul.matches("\\(\\d+,\\d+\\).*")) {
            String substring1 = s1.substring(1);
            String[] subst = split[1].split("\\)");
            String substring2 = subst[0];
            if (substring1.length() <= 3 && substring2.length() <= 3) {
              int i = Integer.parseInt(substring1);
              int i1 = Integer.parseInt(substring2);
              sum += (long) i * i1;
            }
          }
        }
      }
    }
    return sum;
  }

  private static long parse2(List<String> l) {
    long sum = 0;
    boolean active = true;
    for (String s : l) {
      String[] muls = s.split("mul");
      for (String mul : muls) {
        if (mul.contains("do()")) {
          active = true;
        } else if (mul.contains("don't()")) {
          active = false;
        }
        String[] split = mul.split(",");
        if (split.length > 1) {
          String s1 = split[0];
          if (s1.startsWith("(") && mul.matches("\\(\\d+,\\d+\\).*")) {
            String substring1 = s1.substring(1);
            String[] subst = split[1].split("\\)");
            String substring2 = subst[0];
            if (substring1.length() <= 3 && substring2.length() <= 3 && active) {
              int i = Integer.parseInt(substring1);
              int i1 = Integer.parseInt(substring2);
              sum += (long) i * i1;
            }
          }
        }
      }
    }
    return sum;
  }
}
