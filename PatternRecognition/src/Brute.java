import java.util.Arrays;

/**
 * Created by Qubo Song on 7/7/2014.
 */
public class Brute {

    public static void main(String[] args) {

        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];

        if (n <= 0) {
            throw new IllegalArgumentException(
                    "Number of points must be greater than 0.");
        }

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }

        for (int p = 0; p < n; p++) {
            for (int q = p + 1; q < n; q++) {
                for (int r = q + 1; r < n; r++) {
                    for (int s = r + 1; s < n; s++) {
                        if ((points[p].SLOPE_ORDER.compare(points[q], points[r])
                                == 0)
                            && (points[p].SLOPE_ORDER.compare(points[q], points[s])
                                == 0)) {
                            Point[] linePoints = new Point[4];
                            linePoints[0] = points[p];
                            linePoints[1] = points[q];
                            linePoints[2] = points[r];
                            linePoints[3] = points[s];

                            Arrays.sort(linePoints);
                            linePoints[0].drawTo(linePoints[3]);
                            System.out.printf("%s -> %s -> %s -> %s%n",
                                    linePoints[0].toString(),
                                    linePoints[1].toString(),
                                    linePoints[2].toString(),
                                    linePoints[3].toString());
                        }
                    }
                }
            }
        }

    }
}
