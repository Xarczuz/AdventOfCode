package adventOfCode.Year2023;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day5 {


    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day5.class);
        List<String> l2 = FileUtil.readfileExempel(Day5.class);
        TimeUtil.startTime();
        oneStar(l); // 910845529
        oneStar(l2);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l); // 77435348
        twoStar(l2);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {
        Farm farm = parseStrings(l);
        long location = Long.MAX_VALUE;
        invertMap(farm);
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            location = i;
            long temp = i;
            ArrayList<SeedMap> seedMaps = farm.seedMaps;
            for (int j = seedMaps.size() - 1; j >= 0; j--) {
                SeedMap seedMap = seedMaps.get(j);
                temp = convertSeed2(temp, seedMap);
            }
            if (isConvertedToValidSeed(temp, farm)) {
                break;
            } else {
                location = Long.MAX_VALUE;
            }
            if (i % 100000 == 0) {
                System.out.println(i);
            }
        }
        System.out.println("Two star: " + location);
    }

    private static boolean isConvertedToValidSeed(long location, Farm farm) {
        ArrayList<Long> seeds = farm.seeds;
        for (int i = 0; i < seeds.size(); i += 2) {
            Long seed = seeds.get(i);
            Long range = seeds.get(i + 1);
            if (location >= seed && location < seed + range) {
                return true;
            }
        }
        return false;
    }

    private static void invertMap(Farm farm) {
        for (SeedMap seedMap : farm.seedMaps) {
            for (SeedMap.TheMap theMap : seedMap.theMaps) {
                long d = theMap.destination;
                long s = theMap.source;
                theMap.source = d;
                theMap.destination = s;
            }
        }
        for (SeedMap seedMap : farm.seedMaps) {
            seedMap.theMaps.sort(Comparator.comparingLong(o -> o.source));
        }
    }


    private static void oneStar(List<String> l) {
        Farm farm = parseStrings(l);
        long location = Long.MAX_VALUE;
        for (Long seed : farm.seeds) {
            for (SeedMap seedMap : farm.seedMaps) {
                seed = convertSeed(seed, seedMap);
            }
            location = Math.min(seed, location);
        }
        System.out.println("One star: " + location);
    }

    private static Long convertSeed2(Long seed, SeedMap seedMap) {
        ArrayList<SeedMap.TheMap> theMaps = seedMap.theMaps;
        for (SeedMap.TheMap map : theMaps) {
            if (seed >= map.source && seed < map.source + map.range) {
                if (map.source > map.destination) {
                    seed = seed - Math.abs(map.source - map.destination);
                } else if (map.source < map.destination) {
                    seed = seed + Math.abs(map.source - map.destination);
                }
                break;
            }
        }
        return seed;
    }

    private static Long convertSeed(Long seed, SeedMap seedMap) {
        ArrayList<SeedMap.TheMap> theMaps = seedMap.theMaps;
        for (SeedMap.TheMap map : theMaps) {
            if (seed >= map.source && seed <= map.source + map.range) {
                if (map.source > map.destination) {
                    seed = seed - Math.abs(map.source - map.destination);
                } else if (map.source < map.destination) {
                    seed = seed + Math.abs(map.source - map.destination);
                }
                break;
            }
        }
        return seed;
    }

    private static Farm parseStrings(List<String> l) {
        ArrayList<SeedMap> seedMaps = new ArrayList<>();
        ArrayList<Long> seeds = new ArrayList<>();
        String[] seedsList = l.get(0).split(":")[1].split(" ");
        for (String s : seedsList) {
            if (!s.isEmpty()) {
                seeds.add(Long.parseLong(s.trim()));
            }
        }
        for (int i = 1; i < l.size(); i++) {
            String s = l.get(i);
            if (s.contains("map")) {
                SeedMap e = new SeedMap();
                e.mapType = s.split(" ")[0];
                seedMaps.add(e);
            } else if (!s.trim().isEmpty()) {
                String[] numbers = s.split(" ");
                SeedMap seedMap = seedMaps.get(seedMaps.size() - 1);
                SeedMap.TheMap e = new SeedMap.TheMap();
                seedMap.theMaps.add(e);
                e.destination = Long.parseLong(numbers[0]);
                e.source = Long.parseLong(numbers[1]);
                e.range = Long.parseLong(numbers[2]);
                seedMap.theMaps.sort(Comparator.comparingLong(o -> o.source));
            }
        }
        return new Farm(seeds, seedMaps);
    }

    record Farm(ArrayList<Long> seeds, ArrayList<SeedMap> seedMaps) {
    }

    private static final class SeedMap {
        String mapType;
        ArrayList<TheMap> theMaps = new ArrayList<>();

        protected static class TheMap {
            long destination;
            long source;
            long range;
        }
    }

}
