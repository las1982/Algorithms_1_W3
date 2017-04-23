import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] lineSegments = new LineSegment[0];

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new java.lang.NullPointerException();
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i] == null) throw new java.lang.NullPointerException();
                else if (points[i] == points[j]) throw new java.lang.NullPointerException();
            }
        }
        for (int p = 0; p < points.length; p++) {
            for (int q = p + 1; q < points.length; q++) {
                for (int r = q + 1; r < points.length; r++) {
                    if (points[p].slopeTo(points[q]) != points[p].slopeTo(points[r])) continue;
                    for (int s = r + 1; s < points.length; s++) {
                        if (points[p].slopeTo(points[q]) != points[p].slopeTo(points[s])) continue;
//                        System.out.println(points[p].toString() + points[q].toString() + points[r].toString() + points[s].toString());
                        createLineSegments(new Point[]{points[p], points[q], points[r], points[s]});
                    }
                }
            }
        }
    }

    private void createLineSegments(Point[] collinearPoints) {
//        print before sort
//        for (Point pt : collinearPoints){
//            System.out.print(pt.toString());
//        }
//        System.out.println();
        Arrays.sort(collinearPoints);
//        print after sort
//        for (Point pt : collinearPoints){
//            System.out.print(pt.toString());
//        }
//        System.out.println();
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