import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] lineSegments = new LineSegment[0];

    public FastCollinearPoints(Point[] points) {     // finds all line segments containing 4 or more points
        if (points == null) throw new java.lang.NullPointerException();
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i] == null) throw new java.lang.NullPointerException("there is null");
                else if (points[i] == points[j]) {
                    throw new java.lang.NullPointerException("same elements");
                }
            }
        }
        for (int i = 0; i < points.length; i++) {
            Point p0 = points[i];
            Arrays.sort(points, p0.slopeOrder());
//            System.out.println(Arrays.toString(points));
            int nColPoints = 1;
            int start = 0;
            int end = 0;
            for (int j = 1; j < points.length; j++) {
                if (p0.slopeTo(points[start]) == p0.slopeTo(points[j])) {
                    nColPoints++;
                }
                else if (nColPoints < 3) {
                    start = j;
                    end = j;
                    nColPoints = 1;
                }
                else if (nColPoints >= 3) {
                    end = j - 1;
                    Point[] collinearPoints = new Point[end - start + 2];
//                    System.out.println(start + " " + end + " " + collinearPoints.length);
                    collinearPoints[0] = p0;
                    int l = 1;
                    for (int k = start; k <= end; k++) {
                        collinearPoints[l++] = points[k];
                    }
                    createLineSegments(collinearPoints);
                    start = j;
                    end = j;
                    nColPoints = 1;
                }
            }
        }
    }

    private void createLineSegments(Point[] collinearPoints) {
        Arrays.sort(collinearPoints);
        Point min = collinearPoints[0];
        Point max = collinearPoints[collinearPoints.length - 1];
        LineSegment[] tempLineSegments = new LineSegment[numberOfSegments() + 1];
        for (int i = 0; i < numberOfSegments(); i++) {
            tempLineSegments[i] = lineSegments[i];
        }
        tempLineSegments[numberOfSegments()] = new LineSegment(min, max);
        lineSegments = tempLineSegments;
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }
}