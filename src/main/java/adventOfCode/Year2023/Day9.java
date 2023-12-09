package adventOfCode.Year2023;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day9 {


    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day9.class);
        List<String> l2 = FileUtil.readfileExempel(Day9.class);
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
        ArrayList<Board> boards = parseString(l);
        long sum = 0;
        for (Board board : boards) {
            createHistoryOfSequences(board);
            sum += addAllPreviousValues(board);
        }
        System.out.println("One star: " + sum);
    }

    private static long addAllPreviousValues(Board board) {
        ArrayList<NumberSequence> numberSequences = board.numberSequences;
        Integer lastHistoryNr = numberSequences.getLast().nrs.getFirst();
        for (int i = numberSequences.size() - 2; i >= 0; i--) {
            NumberSequence numberSequence = numberSequences.get(i);
            Integer historyNr = numberSequence.nrs.getFirst();
            lastHistoryNr = historyNr - lastHistoryNr;
        }
        return lastHistoryNr;
    }

    private static void oneStar(List<String> l) {
        ArrayList<Board> boards = parseString(l);
        long sum = 0;
        for (Board board : boards) {
            createHistoryOfSequences(board);
            sum += addAllNextValues(board);
        }
        System.out.println("One star: " + sum);
    }

    private static long addAllNextValues(Board board) {
        ArrayList<NumberSequence> numberSequences = board.numberSequences;
        Integer lastHistoryNr = numberSequences.getLast().nrs.getLast();
        for (int i = numberSequences.size() - 2; i >= 0; i--) {
            NumberSequence numberSequence = numberSequences.get(i);
            Integer historyNr = numberSequence.nrs.getLast();
            lastHistoryNr += historyNr;
        }
        return lastHistoryNr;
    }

    private static void createHistoryOfSequences(Board board) {
        ArrayList<NumberSequence> numberSequences = board.numberSequences;
        while (!isDifferenceAllZero(numberSequences.getLast())) {
            NumberSequence newDifference = new NumberSequence();
            NumberSequence prev = numberSequences.getLast();
            numberSequences.add(newDifference);
            Integer lastNr = prev.nrs.getFirst();
            for (int i = 1; i < prev.nrs.size(); i++) {
                Integer nr = prev.nrs.get(i);
                int e = nr - lastNr;
                newDifference.nrs.add(e);
                lastNr = nr;
            }
        }
    }

    private static boolean isDifferenceAllZero(NumberSequence nextHistory) {
        for (Integer nr : nextHistory.nrs) {
            if (nr != 0) {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<Board> parseString(List<String> l) {
        ArrayList<Board> boards = new ArrayList<>();
        for (String s : l) {
            String[] nrs = s.split(" ");

            NumberSequence ns = new NumberSequence();
            Board board = new Board();
            board.numberSequences.add(ns);
            boards.add(board);
            for (String nr : nrs) {
                ns.nrs.add(Integer.parseInt(nr.trim()));
            }
        }
        return boards;
    }

    private static class Board {
        ArrayList<NumberSequence> numberSequences = new ArrayList<>();
    }

    private static class NumberSequence {
        ArrayList<Integer> nrs = new ArrayList<>();
    }
}
