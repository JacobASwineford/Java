package Misc.CustomClasses.Other;

import java.util.Scanner;

/**
 * Private class assisting in vector calculations. This class features adding,
 * subtracting, dot products, and cross products for calculations between vectors.
 *
 * Vectors are useful for 3d geometry and finding the distance between points in
 * any dimension.
 *
 * @author Jacob Swineford
 */
public class Vector {

    private double[] vectorComp;

    private Vector(double... vectorNum) {
        vectorComp = new double[vectorNum.length];
        int i = 0;
        for (double d : vectorNum) {
            vectorComp[i] = d;
            i++;
        }
    }

    /**
     * Adds this vector to another vector. This is done by adding numbers
     * contained in corresponding positions and appending a new vector with those
     * values.
     *
     * This vector's length must be equal to the given vector's length, else a
     * DimensionMismatchException will be thrown.
     *
     * @param v given vector
     * @return appended vector
     * @throws DimensionMismatchException when vectors are not the same size
     */
    private Vector add(Vector v) throws DimensionMismatchException {
        sameSizeVectors(v);
        Vector r = new Vector(new double[vectorComp.length]);
        for (int i = 0; i < vectorComp.length; i++) {
            r.vectorComp[i] = v.vectorComp[i] + vectorComp[i];
        }

        return r;
    }

    /**
     * Subtracts the given vector from this vector. This is done by subtracting numbers
     * contained in corresponding positions and appending a new vector with those
     * values.
     *
     * This vector's length must be equal to the given vector's length, else a
     * DimensionMismatchException will be thrown.
     *
     * @param v given vector
     * @return resulting vector
     * @throws DimensionMismatchException when vectors are not the same size
     */
    private Vector subtract(Vector v) throws DimensionMismatchException {
        sameSizeVectors(v);
        Vector r = new Vector(new double[vectorComp.length]);
        for (int i = 0; i < vectorComp.length; i++) {
            r.vectorComp[i] = vectorComp[i] - v.vectorComp[i];
        }

        return r;
    }

    /**
     * Multiplies this vector and the given vector via the dot syntax
     * for vectors. This is done by multiplying numbers in corresponding
     * positions, and adding it to an overall product.
     *
     * This vector's length must be equal to the given vector's length, else a
     * DimensionMismatchException will be thrown.
     *
     * @param v given vector
     * @return double resulting from dot multiplication
     * @throws DimensionMismatchException when vectors are not the same size
     */
    private double dotProduct(Vector v) throws DimensionMismatchException {
        sameSizeVectors(v);
        double d = 0;
        for (int i = 0; i < vectorComp.length; i++) {
            d += v.vectorComp[i] * vectorComp[i];
        }
        return d;
    }

    /**
     * Finds the geometric length of this vector via pythagorean's
     * theorem.
     *
     * @return geometric length of this vector
     */
    private double geometricLength() {
        double length = 0;

        // pythagorean theorem
        for (double d : vectorComp) {
            length += Math.pow(d, 2);
        }
        length = Math.sqrt(length);

        return length;
    }

    /**
     * Multiplies this vector and the given vector via the cross syntax
     * for vectors. This is done through a matrix. Only vectors of
     * length 3 will be supported by this method.
     *
     * This vector's length must be equal to the given vector's length, else a
     * DimensionMismatchException will be thrown.
     *
     * @param v given vector
     * @return double resulting from dot multiplication
     * @throws DimensionMismatchException when vectors are not the same size
     */
    private Vector threeDimensionalCrossProduct(Vector v) throws DimensionMismatchException {
        sameSizeVectors(v);
        if (vectorComp.length != 3) {
            throw new DimensionMismatchException("Both vectors must be of size 3.");
        }
        return new Vector(
                (vectorComp[1] * v.vectorComp[2] - v.vectorComp[1] * vectorComp[2]),
                -(vectorComp[0] * v.vectorComp[2] - v.vectorComp[0] * vectorComp[2]),
                vectorComp[0] * v.vectorComp[1] - v.vectorComp[0] * vectorComp[1]);
    }

    /**
     * Transposes a copy of this vector.
     *
     * @return transposed copy
     */
    private Vector transpose() {
        Vector v = new Vector(new double[vectorComp.length]);
        for (int i = 0; i < vectorComp.length; i++) {
            v.vectorComp[i] = -vectorComp[i];
        }
        return v;
    }

    /**
     * Checks to see if this vector and the given vector are orthogonal, or
     * perpendicular. Two vectors are orthogonal if their dot product is 0.
     *
     * This vector's length must be equal to the given vector's length, else a
     * DimensionMismatchException will be thrown.
     *
     * @param v given vector
     * @return true if vectors are orthogonal, false otherwise
     * @throws DimensionMismatchException when vectors are not the same size
     */
    private boolean isOrthogonalTo(Vector v) throws DimensionMismatchException {
        sameSizeVectors(v);
        return this.dotProduct(v) == 0;
    }

    /**
     * Multiplies this vector by the given double.
     *
     * @param d double that is multiplied by this vector
     * @return resulting vector
     */
    private Vector multiply(double d) {
        Vector v = new Vector(new double[vectorComp.length]);
        for (int i = 0; i < vectorComp.length; i++) {
            v.vectorComp[i] = vectorComp[i] * d;
        }
        return v;
    }

    /**
     * Checks to see if this vector and the given vector are parallel. Two
     * vectors are parallel if one vector is a multiple of the other.
     *
     * This vector's length must be equal to the given vector's length, else a
     * DimensionMismatchException will be thrown.
     *
     * @param v given vector
     * @return true if vectors are parallel, false otherwise
     * @throws DimensionMismatchException when vectors are not the same size
     */
    private boolean isParallelTo(Vector v) throws DimensionMismatchException {
        sameSizeVectors(v);

        // check both ways to see if one vector is a multiple of the other,
        // then compare the results in the returned boolean statement
        double c1 = 0;
        double c2 = 0;
        for (int i = 0; i < vectorComp.length; i++) {
            // this % v
            if (vectorComp[i] % v.vectorComp[i] == 0) {
                c1++;
            }

            // v % this
            if(v.vectorComp[i] % vectorComp[i] == 0) {
                c2++;
            }
        }

        // if they are parallel, one of these are true
        return c1 == vectorComp.length || c2 == vectorComp.length;
    }

    private void sameSizeVectors(Vector v) throws DimensionMismatchException {
        if (v.vectorComp.length != vectorComp.length) {
            throw new DimensionMismatchException(v.vectorComp.length
                    + " != " + vectorComp.length);
        }
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder("<");
        for (double d : vectorComp) {
            String str = d + " ";
            st.append(str);
        }
        String s = st.toString().trim();
        s += ">";
        return s;
    }

    public static void main(String[] args) throws DimensionMismatchException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter first vector numbers: ");
        String[] arr1 = in.nextLine().split(" ");
        System.out.print("Enter second vector numbers: ");
        String[] arr2 = in.nextLine().split(" ");

        // parse doubles and iterate for vector initialization
        double[] dArr1 = new double[arr1.length];
        double[] dArr2 = new double[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            dArr1[i] = Double.parseDouble(arr1[i]);
            dArr2[i] = Double.parseDouble(arr2[i]);
        }

        Vector f = new Vector(dArr1);
        Vector s = new Vector(dArr2);

        System.out.println("\nAdded: " + f.add(s));
        System.out.println("Dot Product: " + f.dotProduct(s));

        try {
            System.out.println("3 Space Cross Product: " + f.threeDimensionalCrossProduct(s)); // op is transposed copy
        } catch (DimensionMismatchException e) {
            System.out.println("Cannot compute 3 space cross product.");
        }

        System.out.println("\nSubtracted f - s: " + f.subtract(s));
        System.out.println("Subtracted s - f: " + s.subtract(f));

        System.out.println("\nTransposed f: " + f.transpose());
        System.out.println("Transposed s: " + s.transpose());

        System.out.println("\nGeometric Length f: " + f.geometricLength());
        System.out.println("Geometric Length s: " + s.geometricLength());

        System.out.println("\nOrthogonal: " + f.isOrthogonalTo(s));
        System.out.println("Parallel: " + f.isParallelTo(s));
    }
}
