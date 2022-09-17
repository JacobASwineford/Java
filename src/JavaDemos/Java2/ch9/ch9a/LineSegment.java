package JavaDemos.Java2.ch9.ch9a;

/**
 * An immutable line segment.
 *
 * @author Jacob Swineford
 */
public class LineSegment {

    //coordinates of the endpoints
    private final double x1;
    private final double x2;
    private final double y1;
    private final double y2;

    /**
     * Creates a line segment with the given endpoints (x1, y1) and (x2, y2).
     */
    public LineSegment(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    /**
     * Returns the length of this line segment.
     */
    public double length() {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns the slope of this line segment.
     */
    public double slope() {
        return (y2 - y1) / (x2 - x1);
    }

    /**
     * Returns the coordinates of a point in (x, y) format.
     */
    private static String toPoint(double x, double y) {
        return String.format("(%.2f, %.2f)", x, y);
    }

    /**
     * returns a description of this line segment.
     */
    @Override
    public String toString() {
        String length = String.format("%.2f", length());
        String slope = String.format("%.2f", slope());
        return String.format("[%s; %s;] length = %s; slope = %s",
                toPoint(x1, y1), toPoint(x2, y2), length, slope);
    }

    /**
     * Test program for this class.
     */
    public static void main(String[] args) {
        LineSegment s = new LineSegment(2.3, 5.8, 3.1, 9.7);
        LineSegment d = new LineSegment(0, 0, 1, 2);
        System.out.println(s);
        System.out.println(d);

        LineSegment t = new LineSegment(2.3, 5.8, 2.3, 9.7);
        System.out.println(t);
    }
}

