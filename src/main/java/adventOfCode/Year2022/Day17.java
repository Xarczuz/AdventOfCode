package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Day17 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day17.class);
        List<String> l2 = FileUtil.readfileExempel(Day17.class);
        TimeUtil.startTime();
        oneStar(l2);
//        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l2);
//        twoStar(l);
        TimeUtil.endTime();
    }


    private static void oneStar(List<String> l2) {

    }


    private static void twoStar(List<String> l2) {
    }

}
