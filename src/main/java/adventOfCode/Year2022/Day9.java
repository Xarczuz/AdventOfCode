package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;

public class Day9 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> l = FileUtil.readfile(Day9.class);
        List<String> l2 = FileUtil.readfileExempel(Day9.class);
        TimeUtil.startTime();
        oneStar(l2);
        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l2);
        //twoStar(l);
        TimeUtil.endTime();
    }


    private static void twoStar(List<String> l) {

    }

    private static void oneStar(List<String> l) {
        Rope head = new Rope();
        Rope tail = new Rope();

        for (String s : l) {
            String[] ss = s.split(" ");
            String direction = ss[0];
            int moves = Integer.parseInt(ss[1]);

            for (int i = 0; i < moves; i++) {
                if ("R".equals(direction)) {
                    head.setX(head.x + 1);
                    head.setY(head.y);
                    if (tail.farX(head) && !tail.hs.contains(head.yPrev + "," + head.xPrev)) {
                        tail.visited++;
                        tail.y = head.yPrev;
                        tail.x = head.xPrev;
                        tail.hs.add(head.yPrev + "," + head.xPrev);
                    } else if (tail.farX(head)) {
                        tail.y = head.yPrev;
                        tail.x = head.xPrev;
                    }
                } else if ("U".equals(direction)) {
                    head.setY(head.y + 1);
                    head.setX(head.x);
                    if (tail.farY(head) && !tail.hs.contains(head.yPrev + "," + head.xPrev)) {
                        tail.visited++;
                        tail.y = head.yPrev;
                        tail.x = head.xPrev;
                        tail.hs.add(head.yPrev + "," + head.xPrev);
                    } else if (tail.farY(head)) {
                        tail.y = head.yPrev;
                        tail.x = head.xPrev;
                    }
                } else if ("L".equals(direction)) {
                    head.setX(head.x - 1);
                    head.setY(head.y);
                    if (tail.farX(head) && !tail.hs.contains(head.yPrev + "," + head.xPrev)) {
                        tail.visited++;
                        tail.y = head.yPrev;
                        tail.x = head.xPrev;
                        tail.hs.add(head.yPrev + "," + head.xPrev);
                    } else if (tail.farX(head)) {
                        tail.y = head.yPrev;
                        tail.x = head.xPrev;
                    }
                } else if ("D".equals(direction)) {
                    head.setY(head.y - 1);
                    head.setX(head.x);
                    if (tail.farY(head) && !tail.hs.contains(head.yPrev + "," + head.xPrev)) {
                        tail.visited++;
                        tail.y = head.yPrev;
                        tail.x = head.xPrev;
                        tail.hs.add(head.yPrev + "," + head.xPrev);
                    } else if (tail.farY(head)) {
                        tail.y = head.yPrev;
                        tail.x = head.xPrev;
                    }
                }
            }
        }

        System.out.println("Star one: " + tail.visited);
    }

    static class Rope {
        int x = 0;
        int y = 0;

        int xPrev = 0;
        int yPrev = 0;
        int visited = 1;
        HashSet<String> hs = new HashSet<>();

        public void setX(int x) {
            xPrev = this.x;
            this.x = x;
        }

        public void setY(int y) {
            yPrev = this.y;
            this.y = y;
        }

        boolean farX(Rope rope) {
            return x + 1 != rope.x && x - 1 != rope.x && x != rope.x;
        }

        boolean farY(Rope rope) {
            return y + 1 != rope.y && y - 1 != rope.y && y != rope.y;
        }
    }
}
