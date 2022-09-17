import java.util.Arrays;
import java.util.Comparator;

/**
 * Test class made for the purposes of testing and debugging the Matrix class.
 * Tests include all major methods of the Matrix class such as add, subtract,
 * multiply, transpose, and examples of Arrays.StatisticalSort with and without a callback.
 *
 * @author Jacob Swineford
 */
public class MatrixTest
{

    private static Matrix a = new Matrix(4, 4);
    private static Matrix b = new Matrix(4, 4);
    private static Matrix c = new Matrix(3, 4);
    private static Matrix d = new Matrix(4, 3);
    private static Matrix e = new Matrix(3, 3);
    private static Matrix f = new Matrix(2, 2);

    public static void main(String[] args) throws DimensionMismatchException
    {
        iterateAll();

        // add - no error
        System.out.println(a.add(b));

        // add - error
        try
        {
            a.add(d);

        } catch (DimensionMismatchException e)
        {
            System.out.println("A DME Occurred for Addition.");
        }

        // subtract - no error
        System.out.println("\n" + a.subtract(b));

        // subtract - error
        try {
            a.subtract(d);
        } catch (DimensionMismatchException e) {
            System.out.println("A DME Occurred for Subtraction.");
        }

        // Multiply - no error
        System.out.println("\n" + c.multiply(d));

        // Multiply - error
        try
        {
            c.multiply(a);
        } catch (DimensionMismatchException e) {
            System.out.println("A DME Occurred for Multiplication.");
        }

        // equals - true / false
        System.out.println("\n" + a.equals(b) + ", " + a.equals(d));

        // Transpose
        System.out.println("\n" + c.transpose());

        // Arrays.StatisticalSort ascending - supposed order is f e a (2,2) (3,3) (4,4)
        Matrix[] arr = {a, e, f};
        Arrays.sort(arr);
        for (Matrix m  : arr) {
            System.out.println(m.getRows() + "," +  m.getColumns());
        }
        System.out.println();

        // Arrays.srt descending (Comparator) (4,4) (3,3) (2,2)

        class MatrixComparator implements Comparator<Matrix>
        {
            @Override
            public int compare(Matrix o1, Matrix o2)
            {
                double d = o1.getAbsoluteSumOfMatrix() - o2.getAbsoluteSumOfMatrix();
                if (d > 0)
                {
                    return -1; // any negative number would do
                } else if (d == 0)
                {
                    return 0;
                } else
                {
                    return 1; // any positive number would do
                }
            }
        }
        Arrays.sort(arr, new MatrixComparator());
        for (Matrix m  : arr)
        {
            System.out.println(m.getRows() + "," +  m.getColumns());
        }

        int[] i = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(i));
    }

    /**
     * Iterates all matrices of this class by an integer counting
     * up by one going from top-left to bottom right.
     */
    private static void iterateAll()
    {
        // a
        int count = 1;
        for (int i = 0; i < a.getRows(); i++)
        {
            for (int j = 0; j < a.getColumns(); j++)
            {
                a.setEntry(i, j, count);
                count++;
            }
        }
        count = 1;

        // b
        for (int i = 0; i < b.getRows(); i++)
        {
            for (int j = 0; j < b.getColumns(); j++)
            {
                b.setEntry(i, j, count);
                count++;
            }
        }
        count = 1;

        // c
        for (int i = 0; i < c.getRows(); i++)
        {
            for (int j = 0; j < c.getColumns(); j++)
            {
                c.setEntry(i, j, count);
                count++;
            }
        }


        // d
        for (int i = 0; i < d.getRows(); i++)
        {
            for (int j = 0; j < d.getColumns(); j++)
            {
                d.setEntry(i, j, count);
                count++;
            }
        }
        count = 1;

        // e
        for (int i = 0; i < e.getRows(); i++)
        {
            for (int j = 0; j < e.getColumns(); j++)
            {
                e.setEntry(i, j, count);
                count++;
            }
        }
        count = 1;

        // f
        for (int i = 0; i < f.getRows(); i++)
        {
            for (int j = 0; j < f.getColumns(); j++)
            {
                f.setEntry(i, j, count);
                count++;
            }
        }

    }
}
