package Misc.Utilities;

/**
 * Magnitude and Angle
 *
 * @author Jacob Swineford
 */
public class Vector {

    double magnitude;
    double angle;

    Vector(double magnitude, double angle) {
        if (angle > 360) angle %= 360;
        if (angle < 0) angle += 360;
        this.angle = angle;
        this.magnitude = magnitude;
    }

}
