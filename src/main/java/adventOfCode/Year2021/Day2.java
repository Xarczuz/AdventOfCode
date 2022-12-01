package adventOfCode.Year2021;

public class Day2 {

    public long day2(Iterable<String> list) {
        Cordinates c = new Cordinates();
        for (String s : list) {
            String[] sArr = s.split(" ");
            switch (sArr[0]) {
                case "forward":
                    c.horizontal += Integer.parseInt(sArr[1]);
                    break;
                case "down":
                    c.depth += Integer.parseInt(sArr[1]);
                    break;
                case "up":
                    c.depth -= Integer.parseInt(sArr[1]);
                    break;
                default:
                    //do nothing
            }
        }
        return c.horizontal * c.depth;
    }

    public long day2Level2(Iterable<String> list) {
        Cordinates c = new Cordinates();
        for (String s : list) {
            String[] sArr = s.split(" ");
            int parseInt = Integer.parseInt(sArr[1]);
            switch (sArr[0]) {
                case "forward" -> forward(c, parseInt);
                case "down" -> down(c, parseInt);
                case "up" -> up(c, parseInt);
                default -> throw new IllegalStateException("Unexpected value: " + sArr[0]);
            }
        }
        return c.horizontal * c.depth;
    }

    private static void forward(Cordinates c, int parseInt) {
        c.horizontal += parseInt;
        c.depth += parseInt * c.aim;
    }

    private static void up(Cordinates c, int parseInt) {
        c.aim = c.aim - parseInt;
    }

    private static void down(Cordinates c, int parseInt) {
        c.aim = c.aim + parseInt;
    }

    public static class Cordinates {
        public long horizontal;
        public long depth;
        public long aim;
    }
}
