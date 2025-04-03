package classes;

public class YX {

    public int y;
    public int x;

    public YX() {
    }

    public YX(int y, int x) {
        this.x = x;
        this.y = y;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        YX YX = (YX) o;

        if (x != YX.x) return false;
        return y == YX.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public YX deepCopy() {
        return new YX(this.y, this.x);
    }
}
