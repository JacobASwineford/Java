package Homework.Java2.SwinefordHW2;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Contains the radius, length (height),
 * volume and surface of a cylinder.
 *
 * @author Jacob Swineford
 */
public class CylinderSort implements Comparable<CylinderSort> {

    private double height;
    private double radius;
    private double volume;
    private double surfaceArea;

    /**
     * Initializes fields for height, radius, volume and area.
     *
     * @param height the height of the cylinder
     * @param radius the radius of the base
     */
    public CylinderSort(double height, double radius) {
        this.height = height;
        this.radius = radius;
        volume = Math.PI * Math.pow(radius, 2) * height;
        surfaceArea = 2 * (Math.PI * Math.pow(radius, 2))
                + height * (2 * Math.PI * radius);
    }

    /**
     * Returns the height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the radius.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Returns the volume.
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Returns the surface area.
     */
    public double getSurfaceArea() {
        return surfaceArea;
    }

    /**
     * Sets height to a new value, and modifies the calculated
     * values to match.
     */
    public void setHeight(double height) {
        this.height = height;
        volume = Math.PI * Math.pow(radius, 2) * height;
        surfaceArea = 2 * (Math.PI * Math.pow(radius, 2))
                + height * (2 * Math.PI * radius);
    }

    /**
     * Sets radius to a new value, and modifies the calculated
     * values to match.
     */
    public void setRadius(double radius) {
        this.radius = radius;
        volume = Math.PI * Math.pow(radius, 2) * height;
        surfaceArea = 2 * (Math.PI * Math.pow(radius, 2))
                + height * (2 * Math.PI * radius);
    }

    @Override
    public String toString() {
        return "[length: " + String.format("%.3f", height) +
                ", radius: " + String.format("%.3f", radius) +
                ", surface area: " + String.format("%.3f", surfaceArea) +
                ", volume: " + String.format("%.3f", volume) + "]";
    }

    /**
     * Returns a negative integer, zero, or a positive integer depending on
     * whether this network user's login is less than, equal to, or greater than
     * the login of the given network user in ASCII order.
     */
    @Override
    public int compareTo(CylinderSort o) {
        return Double.compare(volume, o.getVolume());
    }

    /**
     * Sorts Cylinders using the specification required
     * by the homework.
     *
     * This requires there be 5 randomly generated cylinders
     * added to an array, which id to be outputted with and without
     * sorting. This is done through the CylinderSort class implementing
     * the interface Comparable<CylinderSort> and passing it's compareTo()
     * method to Arrays.StatisticalSort.
     *
     * @author Jacob Swineford
     */
    public static void main(String[] args) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        CylinderSort[] c = new CylinderSort[5];

        for (int i = 0; i < c.length; i++) {
            c[i] = new CylinderSort(rand.nextDouble(50),
                    rand.nextDouble(10));
        }

        System.out.println("Unsorted: ");
        for (CylinderSort y : c) {
            System.out.println(y);
        }

        System.out.println("\nSorted by Volume: ");
        Arrays.sort(c);
        for (CylinderSort z : c) {
            System.out.println(z);
        }
    }
}
