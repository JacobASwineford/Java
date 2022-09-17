package Misc.Utilities;

/**
 * @author Jacob Swineford
 */
public class PhysicsUtil {

    public static double[] rightAngleTriangleProperties(Vector v) {
        double[] arr = new double[4];
        arr[0] = v.magnitude * Math.cos(v.angle); // x
        arr[1] = v.magnitude * Math.sin(v.angle); // y
        arr[2] = v.magnitude;
        arr[3] = v.angle;
        return arr;
    }

    /**
     * @param v Vectors to add
     * @return Resulting Vector
     */
    public static Vector addTwoDimensionalVectors(Vector... v) {
        double angle;
        double magnitude;
        double x = 0; double y = 0; // properties for pythagorean's theorem
        for (Vector vector : v) {
            double[] properties = rightAngleTriangleProperties(vector);
            x += properties[0];
            y += properties[1];
        }
        magnitude = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        angle = Math.atan(y / x);
        return new Vector(magnitude, angle);
    }
}
