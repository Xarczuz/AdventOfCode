package adventOfCode.year2022;

import util.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Day1 {


    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileReader.readfile(Day1.class, 1);
        long max = 0;
        long nr = 0;
        for (String s : l) {
            if (s.isBlank()) {
                nr = 0;
            } else {
                nr += Long.parseLong(s);
                max = Math.max(nr, max);
            }
        }
        System.out.println(max);
    }
}
