package Misc.Utilities;

import javafx.geometry.Point2D;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * A class containing useful functions dealing with arrays.
 *
 * @author Jacob Swineford
 */
public class ArrayUtil {

    public static String hello = "helloInstance";

    public void h() {
        System.out.println(hello);
    }

    public static void g() {
        System.out.println("helloStatic");
    }

    /**
     * Finds out if the given (x, y) coordinates are within range
     * of the given array, such that an <code>ArrayIndexOutOfBounds</code>
     * Exception is not thrown. This method assumes the two dimensional
     * array is a box.
     */
    public static <T> boolean withinRange(int x, int y, T[][] arr) {
        return x > -1 && x < arr[0].length && y > -1 && y < arr.length;
    }

    /**
     * Finds the average of an array of doubles.
     */
    public static double findAverage(double[] val) {
        double av = 0;
        for (double d : val) av += d;
        return av / val.length;
    }

    /**
     * Finds the average of an array of integers.
     */
    public static int findAverage(int[] val) {
        int av = 0;
        for (double d : val) av += d;
        return av / val.length;
    }

    /**
     * Finds the variance of an array of doubles.
     */
    public static double findVariance(double[] val) {
        double summation = 0;
        double mean = findAverage(val);
        for (double d : val) summation += Math.pow((d - mean), 2);
        return summation / val.length;
    }

    /**
     * Finds the Standard Deviation of an array of doubles.
     */
    public static double findStandardDeviation(double[] val) {
        return Math.sqrt(findVariance(val));
    }

    /**
     * Finds the Standard Error of an array of doubles.
     */
    public static double findStandardError(double[] val) {
        double sd = findStandardDeviation(val);
        return sd / Math.sqrt(val.length);
    }

    /**
     * Returns a <code> Pair </code>, where the key is
     * list of <code> Point2D </code> with the (x, y)
     * coordinates of a row, and the value is the point of collision.
     * This method checks for collision; That is, once another object
     * is detected on a row's path, then the <code> Pair </code> is returned.
     *
     * The row returned depends on the direction. The direction
     * specifies which of the two directional paths are to be returned:
     * "right"
     * "left"
     *
     * If the coordinates specified don't index into a value within the array's range,
     * then this method returns null.
     *
     * If there is no collision (path runs off of array), then the point of collision
     * will be null.
     *
     * @param posX x starting coordinate
     * @param posY y starting coordinate
     * @param arr Array to test
     */
    public static <T> Pair<List<Point2D>, Point2D> getRowWithCollision(int posX, int posY, T[][] arr, String direction) {
        if (!withinRange(posX, posY, arr)) return null;
        List<Point2D> list = new ArrayList<>();
        while (true) {
            switch (direction) {
                case "right": posX++; break;
                case "left": posX--; break;
                default: return new Pair<>(list, null);
            }
            if (!withinRange(posX, posY, arr)) return new Pair<>(list, null);
            if (arr[posY][posX] != null) {
                return new Pair<>(list, new Point2D(posX, posY));
            }
            list.add(new Point2D(posX, posY));
        }
    }

    /**
     * Returns a <code> Pair </code>, where the key is
     * list of <code> Point2D </code> with the (x, y)
     * coordinates of a row, and the value is the point of collision.
     * This method checks for collision; That is, once another object
     * is detected on a row's path, then the <code> Pair </code> is returned.
     *
     * The lane returned depends on the direction. The direction
     * specifies which of the two directional paths are to be returned:
     * "top"
     * "bottom"
     *
     * If the coordinates specified don't index into a value within the array's range,
     * then this method returns null.
     *
     * If there is no collision (path runs off of array), then the point of collision
     * will be null.
     *
     * @param posX x starting coordinate
     * @param posY y starting coordinate
     * @param arr Array to test
     */
    public static <T> Pair<List<Point2D>, Point2D> getLaneWithCollision(int posX, int posY, T[][] arr, String direction) {
        if (!withinRange(posX, posY, arr)) return null;
        List<Point2D> list = new ArrayList<>();
        while (true) {
            switch (direction) {
                case "top": posY--; break;
                case "bottom": posY++; break;
                default: return new Pair<>(list, null);
            }
            if (!withinRange(posX, posY, arr)) return new Pair<>(list, null);
            if (arr[posY][posX] != null) {
                return new Pair<>(list, new Point2D(posX, posY));
            }
            list.add(new Point2D(posX, posY));
        }
    }

    /**
     * Returns a <code> Pair </code>, where the key is
     * list of <code> Point2D </code> with the (x, y)
     * coordinates of a row, and the value is the point of collision.
     * This method checks for collision; That is, once another object
     * is detected on a row's path, then the <code> Pair </code> is returned.
     *
     * The diagonal returned depends on the direction. The direction
     * specifies which of the four diagonals are to be returned:
     * "top-right"
     * "top-left"
     * "bottom-left"
     * "bottom-right"
     *
     * If the coordinates specified don't index into a value within the array's range,
     * then this method returns null.
     *
     * If there is no collision (path runs off of array), then the point of collision
     * will be null.
     *
     * @param posX x starting coordinate
     * @param posY y starting coordinate
     * @param arr Array to test
     */
    public static <T> Pair<List<Point2D>, Point2D> getDiagonalWithCollision(int posX, int posY, T[][] arr, String direction) {
        if (!withinRange(posX, posY, arr)) return null;
        List<Point2D> list = new ArrayList<>();
        while (true) {
            switch (direction) {
                case "top-right": posY--; posX++; break;
                case "top-left": posY--; posX--; break;
                case "bottom-right": posY++; posX++; break;
                case "bottom-left": posY++; posX--; break;
                default: return new Pair<>(list, null);
            }
            if (!withinRange(posX, posY, arr)) return new Pair<>(list, null);
            if (arr[posY][posX] != null) {
                return new Pair<>(list, new Point2D(posX, posY));
            }
            list.add(new Point2D(posX, posY));
        }
    }
}
