package adventOfCode.Year2023;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day7 {


    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day7.class);
        List<String> l2 = FileUtil.readfileExempel(Day7.class);
        TimeUtil.startTime();
        oneStar(l); // 255048101
        oneStar(l2);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l); // 253718286
        twoStar(l2);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {
        ArrayList<CardAndRank> cardAndRanks = parseString(l);
        cardAndRanks.sort(getCardAndRankComparator2());
        long sum = 0;
        for (int i = 0; i < cardAndRanks.size(); i++) {
            CardAndRank cardAndRank = cardAndRanks.get(i);
            sum += (long) cardAndRank.nr * (i + 1);
        }
        System.out.println("Two star: " + sum);
    }

    private static void oneStar(List<String> l) {
        ArrayList<CardAndRank> cardAndRanks = parseString(l);
        cardAndRanks.sort(getCardAndRankComparator());
        long sum = 0;
        for (int i = 0; i < cardAndRanks.size(); i++) {
            CardAndRank cardAndRank = cardAndRanks.get(i);
            sum += (long) cardAndRank.nr * (i + 1);
        }
        System.out.println("One star: " + sum);
    }

    private static Comparator<CardAndRank> getCardAndRankComparator() {
        return (o1, o2) -> {
            String cards1 = o1.cards;
            String cards2 = o2.cards;

            int type1 = getHandType(cards1);
            int type2 = getHandType(cards2);
            if (type1 > type2) {
                return 1;
            } else if (type1 < type2) {
                return -1;
            } else {
                ArrayList<Integer> cardScores1 = getCardScores(cards1);
                ArrayList<Integer> cardScores2 = getCardScores(cards2);
                for (int i = 0; i < cardScores1.size(); i++) {
                    Integer score1 = cardScores1.get(i);
                    Integer score2 = cardScores2.get(i);
                    if (score1 > score2) {
                        return 1;
                    } else if (score1 < score2) {
                        return -1;
                    }
                }
                return 0;
            }
        };
    }

    private static ArrayList<Integer> getCardScores(String cards) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (char c : cards.toCharArray()) {
            if (c >= '2' && c <= '9') {
                integers.add(Integer.valueOf(String.valueOf(c)));
            } else if (c == 'T') {
                integers.add(10);
            } else if (c == 'J') {
                integers.add(11);
            } else if (c == 'Q') {
                integers.add(12);
            } else if (c == 'K') {
                integers.add(13);
            } else if (c == 'A') {
                integers.add(14);
            }
        }
        return integers;
    }

    protected static int getHandType2(String cards) {
        int[] ints = new int[90];
        for (char c : cards.toCharArray()) {
            ints[c]++;
        }
        int jokers = ints['J'];
        ints['J'] = 0;
        if (jokers == 4 || jokers == 5) {
            return 7;
        }
        for (int nr : ints) {
            if (jokers == 1) {
                nr++;
            } else if (jokers == 2) {
                nr += 2;
            } else if (jokers == 3) {
                nr += 3;
            }
            if (nr == 5) {
                return 7;
            }
        }
        for (int nr : ints) {
            if (jokers == 1) {
                nr++;
            } else if (jokers == 2) {
                nr += 2;
            } else if (jokers == 3) {
                nr += 3;
            }
            if (nr == 4) {
                return 6;
            }
        }

        int pairs = 0;
        for (int nr : ints) {
            if (nr == 2) {
                pairs++;
            }
        }

        if (pairs == 2 && jokers == 1) {
            return 5;
        }
        for (int nr : ints) {
            if (nr == 3 && pairs == 1) {
                return 5;
            }
        }
        if (pairs == 1 && jokers == 1) {
            return 4;
        }
        if (jokers == 2) {
            return 4;
        }
        for (int nr : ints) {
            if (nr == 3) {
                return 4;
            }
        }
        if (pairs == 2) {
            return 3;
        }
        if (jokers == 1 && pairs == 0) {
            return 2;
        }
        if (pairs == 1) {
            return 2;
        }
        for (int nr : ints) {
            if (nr == 2) {
                return 2;
            }
        }
        return 1;
    }

    private static Comparator<CardAndRank> getCardAndRankComparator2() {
        return (o1, o2) -> {
            String cards1 = o1.cards;
            String cards2 = o2.cards;

            int type1 = getHandType2(cards1);
            int type2 = getHandType2(cards2);
            if (type1 > type2) {
                return 1;
            } else if (type1 < type2) {
                return -1;
            } else {
                ArrayList<Integer> cardScores1 = getCardScores2(cards1);
                ArrayList<Integer> cardScores2 = getCardScores2(cards2);
                for (int i = 0; i < cardScores1.size(); i++) {
                    Integer score1 = cardScores1.get(i);
                    Integer score2 = cardScores2.get(i);
                    if (score1 > score2) {
                        return 1;
                    } else if (score1 < score2) {
                        return -1;
                    }
                }
                return 0;
            }
        };
    }

    private static ArrayList<Integer> getCardScores2(String cards) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (char c : cards.toCharArray()) {
            if (c >= '2' && c <= '9') {
                integers.add(Integer.valueOf(String.valueOf(c)));
            } else if (c == 'T') {
                integers.add(10);
            } else if (c == 'J') {
                integers.add(1);
            } else if (c == 'Q') {
                integers.add(12);
            } else if (c == 'K') {
                integers.add(13);
            } else if (c == 'A') {
                integers.add(14);
            }
        }
        return integers;
    }

    private static int getHandType(String cards) {
        int[] ints = new int[90];
        for (char c : cards.toCharArray()) {
            ints[c]++;
        }
        boolean pair1 = false;
        boolean pair2 = false;
        for (int nr : ints) {
            if (nr == 5) {
                return 7;
            }
        }
        for (int nr : ints) {
            if (nr == 4) {
                return 6;
            }
        }
        for (int nr : ints) {
            if (nr == 2 && !pair1) {
                pair1 = true;
            } else if (nr == 2 && !pair2) {
                pair2 = true;
            }
        }
        for (int nr : ints) {
            if (nr == 2 && !pair1) {
                pair1 = true;
            } else if (nr == 3 && pair1) {
                return 5;
            }
        }
        for (int nr : ints) {
            if (nr == 3) {
                return 4;
            }
        }
        pair1 = false;
        pair2 = false;
        for (int nr : ints) {
            if (nr == 2 && !pair1) {
                pair1 = true;
            } else if (nr == 2 && !pair2) {
                pair2 = true;
            }
        }
        if (pair1 && pair2) {
            return 3;
        }
        for (int nr : ints) {
            if (nr == 2) {
                return 2;
            }
        }
        return 1;
    }


    private static ArrayList<CardAndRank> parseString(List<String> l) {
        ArrayList<CardAndRank> cardAndRanks = new ArrayList<>();
        for (String s : l) {
            String[] ss = s.split(" ");
            CardAndRank cardAndRank = new CardAndRank();
            cardAndRank.cards = ss[0];
            cardAndRank.nr = Integer.parseInt(ss[1]);
            cardAndRanks.add(cardAndRank);
        }
        return cardAndRanks;
    }

    private static class CardAndRank {
        String cards;
        int nr;
    }


}
