package adventOfCode.Year2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import util.FileUtil;
import util.TimeUtil;

public class Day2 {

  public static void main(String[] args) throws IOException, URISyntaxException {
    List<String> l = FileUtil.readfile(Day2.class);
    List<String> l2 = FileUtil.readfileExempel(Day2.class);
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
    ArrayList<Report> reports = parse(l);
    int sum = 0;
    for (Report report : reports) {
      boolean safeReport = isSafeReport(report);
      if (safeReport) {
        sum++;
      } else {
        int size = report.nrs.size();
        for (int i = 0; i < size; i++) {
          ArrayList<Integer> arrayList = new ArrayList<>(report.nrs);
          arrayList.remove(i);
          Report newReport = new Report(arrayList);
          if (isSafeReport(newReport)) {
            sum++;
            break;
          }
        }
      }
    }
    System.out.println("Sum: " + sum);
  }

  private static void oneStar(List<String> l) {

    ArrayList<Report> reports = parse(l);
    int sum = 0;
    for (Report report : reports) {
      isSafeReport(report);
      if (isSafeReport(report)) {
        sum++;
      }
    }
    System.out.println("Sum: " + sum);
  }

  private static boolean isSafeReport(Report report) {
    boolean safe = true;
    ArrayList<Integer> nrs = report.nrs;
    int prev = nrs.getFirst();
    for (int i = 1; i < nrs.size(); i++) {
      Integer nr = nrs.get(i);
      if (nr > prev && Math.abs(nr - prev) < 4 && Math.abs(nr - prev) > 0) {
      } else {
        safe = false;
        break;
      }
      prev = nr;
    }
    if (safe) {
      return safe;
    }
    safe = true;
    prev = nrs.getFirst();
    for (int i = 1; i < nrs.size(); i++) {
      Integer nr = nrs.get(i);
      if (nr < prev && Math.abs(nr - prev) < 4 && Math.abs(nr - prev) > 0) {
      } else {
        safe = false;
        break;
      }
      prev = nr;
    }

    return safe;
  }

  private static ArrayList<Report> parse(List<String> l) {
    ArrayList<Report> reports = new ArrayList<>();
    for (String s : l) {
      String[] s1 = s.split(" ");
      ArrayList<Integer> nrs = new ArrayList<>();
      for (String string : s1) {
        nrs.add(Integer.parseInt(string));
      }
      reports.add(new Report(nrs));
    }
    return reports;
  }

  private record Report(ArrayList<Integer> nrs) {

  }
}
