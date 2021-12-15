package classes;

public class XY {

        public int x;
        public int y;

    public XY() {
    }

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
        public String toString() {
            return "XY{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        XY xy = (XY) o;

        if (x != xy.x) {
            return false;
        }
        return y == xy.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
