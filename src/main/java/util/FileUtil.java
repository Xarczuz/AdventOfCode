package util;

import adventOfCode.Year2022.Day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class FileUtil {

    private static final char OFSET = 48;
    private static final long[] TWO = new long[] {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192};

    private FileUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static ArrayList<String> readFromFile(String filename) throws IOException {
        Path of = Path.of("src/main/resources/" + filename);
        Stream<String> lines = Files.lines(of.toAbsolutePath());
        ArrayList<String> arrayList = new ArrayList<>(lines.toList());
        lines.close();
        return arrayList;
    }


    public static long bitToLong(String bit) {
        long nr = 0;
        for (int i = bit.length() - 1, y = 0; i >= 0; i--, y++) {
            nr = nr + (bit.charAt(i) - OFSET) * TWO[y];
        }
        return nr;
    }

    public static List<String> readfile(Class<Day1> day1Class, int i) throws IOException, URISyntaxException {
        return Files.readAllLines(Path.of(day1Class.getResource("day" + i).toURI()));
    }
}
