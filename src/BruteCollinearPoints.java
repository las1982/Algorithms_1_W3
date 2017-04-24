import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] lineSegments;
    private Point[][] pts = new Point[0][];

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new java.lang.NullPointerException();
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i] == null) throw new java.lang.NullPointerException();
                else if (points[i].compareTo(points[j]) == 0) throw new java.lang.IllegalArgumentException();
            }
        }
        for (int p = 0; p < points.length; p++) {
            for (int q = p + 1; q < points.length; q++) {
                for (int r = q + 1; r < points.length; r++) {
                    if (points[p].slopeTo(points[q]) != points[p].slopeTo(points[r])) continue;
                    for (int s = r + 1; s < points.length; s++) {
                        if (points[p].slopeTo(points[q]) != points[p].slopeTo(points[s])) continue;
                        createLineSegments(new Point[]{points[p], points[q], points[r], points[s]});
                    }
                }
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