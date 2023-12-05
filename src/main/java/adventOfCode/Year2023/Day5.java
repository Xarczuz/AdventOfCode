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
//        oneStar(l); // 910845529
//        oneStar(l2);
        TimeUtil.endTime();
        TimeUtil.startTime();
//        twoStar(l);
        twoStar(l2);
        TimeUtil.endTime();
    }

    private static void twoStar(List<String> l) {
        Farm farm = parseStrings(l);
        initSeeds(farm);
        long location = Long.MAX_VALUE;
        for (Long seed : farm.seeds) {
            for (SeedMap seedMap : farm.seedMaps) {
                seed = convertSeed(seed, seedMap);
            }
            location = Math.min(seed, location);
        }
        System.out.println("Two star: " + location);
    }

    private static void initSeeds(Farm farm) {
        ArrayList<Long> newSeeds = new ArrayList<>();
        ArrayList<Long> seeds = farm.seeds;

        for (int i = 0; i < seeds.size(); i += 2) {


        }
        System.out.println();
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

    private static Long convertSeed(Long seed, SeedMap seedMap) {
        ArrayList<SeedMap.TheMap> theMaps = seedMap.theMaps;
        theMaps.sort(Comparator.comparingLong(o -> o.source));
        for (int i = 0; i < theMaps.size(); i++) {
            SeedMap.TheMap map = theMaps.get(i);
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
