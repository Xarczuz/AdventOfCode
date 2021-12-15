package classes;

import java.util.ArrayList;

public class Lines {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private ArrayList<Integer> yPoints;
    private ArrayList<Integer> xPoints;
    private ArrayList<XY> verticalPoints;

    public Lines(String s) {
        parse(s);
        xPoints = new ArrayList<>();
        yPoints = new ArrayList<>();
        if (x1 < x2) {
            addPoints(x1, x2, xPoints);
        } else {
            addPoints(x2, x1, xPoints);
        }
        if (y1 < y2) {
            addPoints(y1, y2, yPoints);
        } else {
            addPoints(y2, y1, yPoints);
        }
        addVerticalPoints();
    }

    public void addVerticalPoints() {
        verticalPoints = new ArrayList<>();
        if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
            for (int i = 0; i < xPoints.size(); i++) {
                for (int j = 0; j < yPoints.size(); j++) {
                    if (Math.abs(xPoints.get(i) - x2) == Math.abs(yPoints.get(j) - y2)) {
                        XY xy = new XY();
                        xy.x = xPoints.get(i);
                        xy.y = yPoints.get(j);
                        verticalPoints.add(xy);
                    }
                }
            }
        }
    }

    private void addPoints(int xy1, int xy2, ArrayList<Integer> Points) {
        for (int i = xy1; i <= xy2; i++) {
            Points.add(i);
        }
    }

    private int parse(String s) {
        String[] s2 = s.split("->");
        String[] xy1 = s2[0].split(",");
        x1 = Integer.parseInt(xy1[0].trim());
        y1 = Integer.parseInt(xy1[1].trim());

        String[] xy2;
        xy2 = s2[1].split(",");
        x2 = Integer.parseInt(xy2[0].trim());
        y2 = Integer.parseInt(xy2[1].trim());
        return x1;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public ArrayList<Integer> getyPoints() {
        return yPoints;
    }

    public ArrayList<Integer> getxPoints() {
        return xPoints;
    }

    public ArrayList<XY> getVerticalPoints() {
        return verticalPoints;
    }

    @Override
    public String toString() {
        return "Lines{" +
                "yPoints=" + yPoints +
                ", x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", xPoints=" + xPoints +
                ", newX=" + verticalPoints +
                '}';
    }


}
