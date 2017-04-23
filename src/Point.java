import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        int xx = that.x - this.x;
        int yy = that.y - this.y;
        if (xx == 0) {
            if (yy >= 0) return Double.POSITIVE_INFINITY;
            if (yy < 0) return Double.NEGATIVE_INFINITY;
        }
        return (double) yy / xx;
    }

    public int compareTo(Point that) {
        if ((this.y < that.y) || (this.y == that.y && this.x < that.x)) return -1;
        else if (this.y == that.y && this.x == that.x) return 0;
        else return 1;
    }

    public Comparator<Point> slopeOrder() {
        return new SlopeOrder();
    }

    private class SlopeOrder implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            double slope1 = Point.this.slopeTo(o1);
            double slope2 = Point.this.slopeTo(o2);
            if (slope1 < slope2) return -1;
            else if (slope1 == slope2) return 0;
            else return 1;
        }
    }

    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
}