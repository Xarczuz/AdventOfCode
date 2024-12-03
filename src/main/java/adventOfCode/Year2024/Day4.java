package adventOfCode.Year2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import util.FileUtil;
import util.TimeUtil;

public class Day4 {

  public static void main(String[] args) throws IOException, URISyntaxException {
    List<String> l = FileUtil.readfile(Day4.class);
    List<String> l2 = FileUtil.readfileExempel(Day4.class);
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

  }

  private static void oneStar(List<String> l) {

  }
}
