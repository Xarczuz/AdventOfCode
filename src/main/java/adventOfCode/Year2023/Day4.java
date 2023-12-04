package adventOfCode.Year2023;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        ArrayList<Ticket> tickets = parseString(l);
        int[] scratchcards = new int[tickets.size()];
        Arrays.fill(scratchcards, 1);
        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            int result = 0;
            for (Integer playingNr : ticket.playingNrs) {
                for (Integer winningNr : ticket.winningNrs) {
                    if (playingNr.compareTo(winningNr) == 0) {
                        result++;
                    }
                }
            }
            for (int j = 1; j <= result; j++) {
                if (i + j < scratchcards.length) {
                    for (int k = 0; k < scratchcards[i]; k++) {
                        scratchcards[i + j]++;
                    }
                }
            }
        }
        long sum = 0;
        for (int scratchcard : scratchcards) {
            sum += scratchcard;
        }
        System.out.println("Two star: " + sum);
    }

    private static void oneStar(List<String> l) {
        ArrayList<Ticket> tickets = parseString(l);
        long sum = 0;
        for (Ticket ticket : tickets) {
            long result = 0;
            for (Integer playingNr : ticket.playingNrs) {
                for (Integer winningNr : ticket.winningNrs) {
                    if (playingNr.compareTo(winningNr) == 0) {
                        if (result == 0) {
                            result++;
                        } else {
                            result *= 2;
                        }
                    }
                }
            }
            sum += result;
        }
        System.out.println("One star: " + sum);
    }

    private static ArrayList<Ticket> parseString(List<String> l) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        for (String s : l) {
            String[] strings = s.split(":");
            String id = strings[0].replace("Card", "").trim();
            String[] numbers = strings[1].split("\\|");
            String[] winningNrs = numbers[0].trim().split(" ");
            String[] playingNrs = numbers[1].trim().split(" ");

            ArrayList<Integer> winningNumbersInts = convertStringToIntArraylist(winningNrs);
            ArrayList<Integer> playingNumbersInts = convertStringToIntArraylist(playingNrs);

            tickets.add(new Ticket(Integer.parseInt(id), winningNumbersInts, playingNumbersInts));
        }

        return tickets;
    }

    private static ArrayList<Integer> convertStringToIntArraylist(String[] winningNrs) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (String winningNr : winningNrs) {
            if (!winningNr.isEmpty()) {
                arr.add(Integer.parseInt(winningNr));
            }
        }
        return arr;
    }

    record Ticket(int id, ArrayList<Integer> winningNrs, ArrayList<Integer> playingNrs) {
    }

}
