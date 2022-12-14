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
        List<String> l3 = FileUtil.readfileExempel2(Day9.class);
        TimeUtil.startTime();
        oneStar(l2);
        oneStar(l3);
        oneStar(l);
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l2);
        twoStar(l3);
        twoStar(l);
        TimeUtil.endTime();
    }


    private static void twoStar(List<String> l) {
        Rope head = new Rope();
        Rope tempHead = head;
        for (int i = 0; i < 8; i++) {
            tempHead.tail = new Rope();
            tempHead = tempHead.tail;
        }
        Rope realTail = new Rope();
        tempHead.tail = realTail;


        for (String s : l) {
            String[] ss = s.split(" ");
            String direction = ss[0];
            int moves = Integer.parseInt(ss[1]);

            for (int i = 0; i < moves; i++) {
                head.visited++;
                if ("R".equals(direction)) {
                    head.setX(head.x + 1);
                    head.setY(head.y);
                } else if ("U".equals(direction)) {
                    head.setY(head.y + 1);
                    head.setX(head.x);
                } else if ("L".equals(direction)) {
                    head.setX(head.x - 1);
                    head.setY(head.y);
                } else if ("D".equals(direction)) {
                    head.setY(head.y - 1);
                    head.setX(head.x);
                }
                tempHead = head;
                Rope tempTail = tempHead.tail;
                while (tempTail != null) {
                    if (tempTail.dist(tempHead) >= 2) {
                        tempTail.visited++;
                        if (tempHead.x == tempTail.x) {
                            if (tempHead.y > tempTail.y) {
                                tempTail.setY(tempTail.y + 1);
                            } else {
                                tempTail.setY((tempTail.y - 1));
                            }
                        } else if (tempHead.y == tempTail.y) {
                            if (tempHead.x > tempTail.x) {
                                tempTail.setX(tempTail.x + 1);
                            } else {
                                tempTail.setX((tempTail.x - 1));
                            }
                        } else {
                            if (tempHead.x > tempTail.x) {
                                tempTail.setX(tempTail.x + 1);
                            } else {
                                tempTail.setX((tempTail.x - 1));
                            }
                            if (tempHead.y > tempTail.y) {
                                tempTail.setY(tempTail.y + 1);
                            } else {
                                tempTail.setY((tempTail.y - 1));
                            }
                        }
                        tempTail.hs.add(tempTail.y + "," + tempTail.x);
                    }
                    tempHead = tempTail;
                    tempTail = tempHead.tail;
                }
            }
        }

        System.out.println("Star two: " + realTail.visited + " -- " + (realTail.hs.size() + 1));
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
        Rope head;
        Rope tail;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Rope rope = (Rope) o;

            if (x != rope.x) return false;
            if (y != rope.y) return false;
            if (xPrev != rope.xPrev) return false;
            if (yPrev != rope.yPrev) return false;
            if (visited != rope.visited) return false;
            if (!head.equals(rope.head)) return false;
            if (!tail.equals(rope.tail)) return false;
            return hs.equals(rope.hs);
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + xPrev;
            result = 31 * result + yPrev;
            result = 31 * result + visited;
            result = 31 * result + head.hashCode();
            result = 31 * result + tail.hashCode();
            result = 31 * result + hs.hashCode();
            return result;
        }

        public double dist(Rope tempHead) {
            double dd1 = Math.pow(tempHead.x - x, 2);
            double dd2 = Math.pow(tempHead.y - y, 2);
            double dis = Math.sqrt(dd1 + dd2);
            return dis;
        }
    }
}
