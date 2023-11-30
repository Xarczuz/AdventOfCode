package adventOfCode.Year2023;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Day1 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day1.class);
        TimeUtil.startTime();
        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {

    }

    private static void oneStar(List<String> l) {

    }
}
