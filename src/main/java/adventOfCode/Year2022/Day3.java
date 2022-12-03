package adventOfCode.Year2022;

import util.FileUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;

public class Day3 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day3.class);

        oneStar(l);


    }

    private static void oneStar(List<String> l) {
        long sum = 0;
        for (String s : l) {
            String s1 = s.substring(0, s.length() / 2);
            String s2 = s.substring(s.length() / 2);
            HashSet<Character> characters = new HashSet<>();
            for (int i = 0; i < s1.length(); i++) {
                for (int j = 0; j < s1.length(); j++) {
                    if (s1.charAt(i) == s2.charAt(j)) {
                        characters.add(s2.charAt(j));
                    }
                }
            }
            for (Character character : characters) {
                sum += toPrioValue(character);
            }
        }
        System.out.println("star 1 sum: " + sum);
    }

    private static int toPrioValue(Character character) {
        if (character > 97) {
            return character - 96;
        }
        return character - 64 + 26;
    }
}
