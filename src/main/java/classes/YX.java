package classes;

public class YX {

    public int y;
    public int x;

    public YX() {
    }

    public YX(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public static YX north() {
        YX yx = new YX();
        yx.x = 0;
        yx.y = -1;
        return yx;
    }

    public static YX south() {
        YX yx = new YX();
        yx.x = 0;
        yx.y = 1;
        return yx;
    }

    public static YX west() {
        YX yx = new YX();
        yx.x = -1;
        yx.y = 0;
        return yx;
    }

    public static YX east() {
        YX yx = new YX();
        yx.x = 1;
        yx.y = 0;
        return yx;
    }

    public static YX[] cardinalDirectionsList() {
        return new YX[]{YX.north(), YX.south(), YX.east(), YX.west()};
    }

    @Override
    public String toString() {
        return "YX{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        YX yx = (YX) o;
        return y == yx.y && x == yx.x;
    }

    @Override
    public int hashCode() {
        int result = y;
        result = 31 * result + x;
        return result;
    }

    public YX deepCopy() {
        return new YX(this.y, this.x);
    }

    public void go(YX cardinalDirection) {
        this.x += cardinalDirection.x;
        this.y += cardinalDirection.y;
    }

    public boolean isOpposite(YX cardinalDirection) {
        boolean b = this.x + cardinalDirection.x == 0;
        boolean a = this.y + cardinalDirection.y == 0;
        return b && a;
    }
}
