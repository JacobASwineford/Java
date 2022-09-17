
/**
 * A matrix of numbers used for the physical containment
 * of numbers and special calculations. This class
 * defines a Matrix as a two dimensional array with a finite
 * number of rows and columns.
 *
 * @author Jacob Swineford
 */
class Matrix implements Comparable<Matrix> {

    private int rows;
    private int columns;
    private double[][] matrix;

    /**
     * Constructs a matrix object with the specified number of rows
     * and columns.
     *
     * @param rows number of rows
     * @param columns number of columns
     */
    Matrix(int rows, int columns)
    {
        this.rows = rows;
        this.columns = columns;
        matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                matrix[i][j] = 0;
            }
        }
    }

    /**
     * Sets the double of the specified position of this matrix. Any
     * value that was there beforehand will be overwritten.
     *
     * @param row row index
     * @param column column index
     * @param num number to set at specified location
     */
    void setEntry(int row, int column, double num)
    {
        matrix[row][column] = num;
    }

    /**
     * Adds a given matrix to this matrix. This can only be done if this matrix
     * and the given matrix both have the same dimensions.
     *
     * @param m Addend matrix
     * @return Matrix made by adding these two matrices
     * @throws DimensionMismatchException if rows and/or columns don't match addition specifications
     */
    Matrix add(Matrix m) throws DimensionMismatchException
    {
        addSubtractEligibility(m);

        Matrix r = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                r.setEntry(i, j, matrix[i][j] + m.matrix[i][j]);
            }
        }

        return r;
    }

    /**
     * Subtracts a given matrix from this matrix. This can only be done if this matrix
     * and the given matrix both have the same dimensions.
     * @param m Subtracted matrix
     * @return Matrix made by subtracting these two matrices
     * @throws DimensionMismatchException if rows and/or columns don't match subtraction specifications
     */
    Matrix subtract(Matrix m) throws DimensionMismatchException
    {
        addSubtractEligibility(m);

        Matrix r = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                r.setEntry(i, j, matrix[i][j] - m.matrix[i][j]);
            }
        }

        return r;
    }

    /**
     * Multiplies a given matrix and this matrix. This can only be done if the number
     * of rows equals the number of columns from the given matrix.
     * @param m Multiplicand matrix
     * @return Matrix made by multiplying these two matrices
     * @throws DimensionMismatchException if rows and/or columns don't match multiplication specifications
     */
    Matrix multiply(Matrix m) throws DimensionMismatchException
    {
        if (rows != m.columns || columns != m.rows)
        {
            throw new DimensionMismatchException("Dimensions not fit for multiplication.");
        }

        Matrix r = new Matrix(rows, rows);

        int rRow = 0;
        int rColumn = 0;
        for (double[] row : matrix)
        {

            // for every row of this matrix, do calculations for each column
            // of the multiplicand's matrix. then, iterate the totals to the
            // bottom-most row of the returned matrix.
            for (int i = 0; i < m.columns; i++)
            {
                int total = 0;

                for (int j = 0; j < m.rows; j++)
                {
                    total += row[j] * m.matrix[j][i];
                }

                r.setEntry(rRow, rColumn, total);
                rColumn++;
            }

            rColumn = 0;
            rRow++;
        }

        return r;
    }

    /**
     * Checks to see if a given matrix is equal to this matrix. Two matrices are equal
     * if they have the same dimensions and have the same values.
     * @param m given matrix
     * @return true if the matrices have the same values, false otherwise
     */
    boolean equals(Matrix m)
    {
        try
        {
            addSubtractEligibility(m);
        } catch (DimensionMismatchException e)
        {
            return false;
        }

        // check if any of the values are different. if so, return false
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                if (matrix[i][j] != m.matrix[i][j])
                {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Copies this matrix and returns a transposed version.
     * @return Transposed matrix
     */
    Matrix transpose()
    {
        Matrix transposed = new Matrix(columns, rows);
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                transposed.setEntry(j, i, matrix[i][j]);
            }
        }
        return transposed;
    }

    /**
     * Compares the absolute sum of this matrix and the given one.
     * @param m given Matrix
     * @return positive, negative, zero
     */
    @Override
    public int compareTo(Matrix m)
    {
        double d = getAbsoluteSumOfMatrix() - m.getAbsoluteSumOfMatrix();
        if (d > 0)
        {
            return 1; // any positive number would do
        } else if (d == 0)
        {
            return 0;
        } else
            {
            return -1; // any negative number would do
            }
    }

    @Override
    public String toString()
    {
        StringBuilder r = new StringBuilder();
        StringBuilder s = new StringBuilder();
        for (double[] arr : matrix)
        {
            r.append("{");
            for (double d : arr)
            {
                String str = d + " ";
                s.append(str);
            }
            r.append(s.toString().trim());
            s.delete(0, s.length() - 1);
            r.append("}\n");
        }
        return r.toString();
    }

    int getRows()
    {
        return rows;
    }

    int getColumns()
    {
        return columns;
    }

    /**
     * Finds and returns the absolute sum of each double in this matrix.
     * @return absolute sum of this matrix
     */
    double getAbsoluteSumOfMatrix()
    {
        double total = 0;
        for (double[] arr : matrix)
        {
            for (double d : arr)
            {
                total += d;
            }
        }
        return total;
    }

    private void addSubtractEligibility(Matrix m) throws DimensionMismatchException
    {
        if (m.rows != rows)
        {
            throw new DimensionMismatchException("Different number of rows: "
                    + rows + " != " + m.rows);
        }
        if (m.columns != columns)
        {
            throw new DimensionMismatchException("Different number of columns: "
                    + columns + " != " + m.columns);
        }
    }
}

