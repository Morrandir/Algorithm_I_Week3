/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private class SlopeComparator implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            nComparatorCalls++;
            return Double.compare(slopeTo(o1), slopeTo(o2));
        }
    }

    private static int nSlopeToCalls = 0;
    private static int nComparatorCalls = 0;
    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeComparator();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        nSlopeToCalls++;
        if (this.x == that.x) {
            if (this.y == that.y) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        } else if (this.y == that.y) {
            return 0;
        }
        return (that.y - this.y) * 1.0 / (that.x - this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y < that.y) {
            return -1;
        } else if (this.y > that.y) {
            return 1;
        } else {
            if (this.x < that.x) {
                return -1;
            } else if (this.x > that.x) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point p1 = new Point(2, 3);
        Point p2 = new Point(5, 5);
        System.out.printf("%f%n", p1.slopeTo(p2));

        p1 = new Point(5, 3);
        System.out.printf("%f%n", p1.slopeTo(p2));

        p1 = new Point(5, 8);
        System.out.printf("%f%n", p1.slopeTo(p2));

        p1 = new Point(3, 3);
        p2 = new Point(4, 4);
        Point p3 = new Point(5, 5);
        System.out.printf("%d%n", p1.SLOPE_ORDER.compare(p2, p3));

        p3 = new Point(5, 6);
        System.out.printf("%d%n", p1.SLOPE_ORDER.compare(p2, p3));

        p3 = new Point(5, 4);
        System.out.printf("%d%n", p1.SLOPE_ORDER.compare(p2, p3));

        p3 = new Point(3, 4);
        System.out.printf("%d%n", p1.SLOPE_ORDER.compare(p2, p3));

        p3 = new Point(3, 2);
        System.out.printf("%d%n", p1.SLOPE_ORDER.compare(p2, p3));

        p3 = new Point(2, 3);
        System.out.printf("%d%n", p1.SLOPE_ORDER.compare(p2, p3));

        p3 = new Point(4, 3);
        System.out.printf("%d%n", p1.SLOPE_ORDER.compare(p2, p3));

    }
}
