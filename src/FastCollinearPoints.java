import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] lineSegments;
    private Point[][] pts = new Point[0][];

    public FastCollinearPoints(Point[] points) {     // finds all line segments containing 4 or more points
        if (points == null) throw new java.lang.NullPointerException();
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i] == null) throw new java.lang.NullPointerException("there is null");
                else if (points[i].compareTo(points[j]) == 0) {
                    throw new java.lang.IllegalArgumentException("same elements");
                }
            }
        }
        for (int i = 0; i < points.length; i++) {
            Arrays.sort(points);
            Point p0 = points[i];
            Arrays.sort(points, p0.slopeOrder());
            int start = 0;
            while (start < points.length - 2) {
                Point p1 = points[start];
                Point p2 = points[start + 1];
                Point p3 = points[start + 2];
                if (p0.slopeTo(p1) == p0.slopeTo(p2) && p0.slopeTo(p2) == p0.slopeTo(p3)) {
                    int end = start + 2;
                    while (end + 1 < points.length){
                        if (p0.slopeTo(p3) == p0.slopeTo(points[end + 1])) {
                            end++;
                        } else break;
                    }
                    Point[] collinearPoints = new Point[end - start + 2];
                    collinearPoints[0] = p0;
                    int l = 1;
                    for (int n = start; n <= end; n++) {
                        collinearPoints[l++] = points[n];
                    }
                    createLineSegments(collinearPoints);
                    start = ++end;
                } else start++;
            }
        }
        lineSegments = new LineSegment[pts.length];
        for (int i = 0; i < pts.length; i++){
            lineSegments[i] = new LineSegment(pts[i][0], pts[i][1]);
        }
    }

    private void createLineSegments(Point[] collinearPoints) {
        Arrays.sort(collinearPoints);
        Point min = collinearPoints[0];
        Point max = collinearPoints[collinearPoints.length - 1];
        for (Point[] pt : pts){
            if (pt[0].compareTo(min) == 0 && pt[1].compareTo(max) == 0) return;
        }
        Point[][] ptsTmp = new Point[pts.length + 1][];
        ptsTmp[ptsTmp.length - 1] = new Point[] {min, max};
        for (int i = 0; i < pts.length; i++){
            ptsTmp[i] = pts[i];
        }
        pts = ptsTmp;
    }

    public int numberOfSegments() {
        return segments().length;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }
}