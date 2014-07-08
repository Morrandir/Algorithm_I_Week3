import java.util.Arrays;



/**
 * Created by Qubo Song on 7/8/2014.
 */
public class Fast {
    public static void main(String[] args) {

        In in = new In(args[0]);
        int n = in.readInt();
        Point[] origins = new Point[n];
        Point[] points;
        Point[] startPoints = new Point[n];
        int startPIndex = 0;

        if (n <= 0) {
            throw new IllegalArgumentException(
                    "Number of origins must be greater than 0.");
        }

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            origins[i] = new Point(x, y);
            //origins[i].draw();
        }

        Arrays.sort(origins);

        for (int i = 0; i < n; i++) {
            int segIndex = 1;
            boolean pointAdded = false;
            Double slopeSeg = 0.0;
            Double slopeSeg1 = 0.0;
            Double slopeSeg2 = 0.0;
            points = Arrays.copyOfRange(origins, i, n);

            origins[i].draw();
            Arrays.sort(points, origins[i].SLOPE_ORDER);

            if (segIndex < points.length - 2) {
                slopeSeg = origins[i].slopeTo(points[segIndex]);
                slopeSeg1 = origins[i].slopeTo(points[segIndex + 1]);
                slopeSeg2 = origins[i].slopeTo(points[segIndex + 2]);
            }

            while (segIndex < points.length - 2) {
                if (Double.compare(slopeSeg, slopeSeg1) == 0
                        && Double.compare(slopeSeg, slopeSeg2) == 0) {

                    boolean lineCounted = false;
                    for (int j = 0; j < startPIndex; j++) {
                        if (origins[i].SLOPE_ORDER.compare(
                                points[segIndex],
                                startPoints[j]) == 0) {
                            lineCounted = true;
                            break;
                        }
                    }
                    if (!lineCounted) {
                        System.out.printf("%s -> ", origins[i]);
                        for (int k = segIndex;
                             k < points.length - 1
                                && Double.compare(slopeSeg, slopeSeg1) == 0;
                             k++) {
                            System.out.printf("%s -> ", points[k]);
                            segIndex++;
                            if (segIndex < points.length - 2) {
                                slopeSeg = slopeSeg1;
                                slopeSeg1 = slopeSeg2;
                                slopeSeg2 = origins[i].slopeTo((points[segIndex + 2]));
                            }
                        }
                        pointAdded = true;
                        origins[i].drawTo(points[segIndex]);
                        System.out.printf("%s%n", points[segIndex++]);
                        if (segIndex < points.length - 2) {
                            slopeSeg = slopeSeg1;
                            slopeSeg1 = slopeSeg2;
                            slopeSeg2 = origins[i].slopeTo((points[segIndex + 2]));
                        }
                    } else {
                        segIndex += 2;
                        if (segIndex < points.length - 2) {
                            slopeSeg = origins[i].slopeTo(points[segIndex]);
                            slopeSeg1 = origins[i].slopeTo(points[segIndex + 1]);
                            slopeSeg2 = origins[i].slopeTo(points[segIndex + 2]);
                            while (segIndex < points.length - 2
                                   && Double.compare(slopeSeg, slopeSeg1) == 0) {
                                segIndex++;
                                slopeSeg = slopeSeg1;
                                slopeSeg1 = slopeSeg2;
                                slopeSeg2 = origins[i].slopeTo(points[segIndex + 2]);
                            }
                        }
                    }
                } else {
                    segIndex++;
                    if (segIndex < points.length - 2) {
                        slopeSeg = slopeSeg1;
                        slopeSeg1 = slopeSeg2;
                        slopeSeg2 = origins[i].slopeTo(points[segIndex + 2]);
                    }
                }
            }
            if (pointAdded) {
                startPoints[startPIndex++] = origins[i];
            }
        }
    }
}
