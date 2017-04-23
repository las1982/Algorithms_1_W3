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
            Arrays.sort(points);
            Point p0 = points[i];
            Arrays.sort(points, p0.slopeOrder());
//            System.out.println(Arrays.toString(points));
            int start = 0;
            int end = 0;
            for (int j = 0; j < points.length - 2; j++) {
                Point p1 = points[j];
                Point p2 = points[j + 1];
                Point p3 = points[j + 2];
                if (p0.slopeTo(p1) == p0.slopeTo(p2) && p0.slopeTo(p2) == p0.slopeTo(p3)) {
                    start = j;
                    end = j + 2;
                    for (int k = j + 3; k < points.length; k++) {
                        if (p0.slopeTo(p3) == p0.slopeTo(points[k])) {
                            end = k;
                            continue;
                        } else {
                            Point[] collinearPoints = new Point[end - start + 2];
                            collinearPoints[0] = p0;
                            int l = 1;
                            for (int n = start; n <= end; n++) {
                                collinearPoints[l++] = points[n];
                            }
                            createLineSegments(collinearPoints);
                            j = end;
                            break;
                        }
                    }
                }
            }
        }
    }

    private void createLineSegments(Point[] collinearPoints) {
        Arrays.sort(collinearPoints);
        Point min = collinearPoints[0];
        Point max = collinearPoints[collinearPoints.length - 1];
        LineSegment newLineSegment = new LineSegment(min, max);
        for (LineSegment ls : lineSegments){
            if (ls.equals(newLineSegment)) return;
        }
        LineSegment[] tempLineSegments = new LineSegment[numberOfSegments() + 1];
        for (int i = 0; i < numberOfSegments(); i++) {
            tempLineSegments[i] = lineSegments[i];
        }
        tempLineSegments[numberOfSegments()] = newLineSegment;
        lineSegments = tempLineSegments;
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }
}