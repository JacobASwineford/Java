package Misc.ConsoleApplications.Toontown.ToonEnumerations;

/**
 * Enumeration describing the matrix of values pertaining to a gag
 * tracks carrying capacity.
 */
public enum GagCapacityMatrix {
    NormalProgression(new int[][] {
            {10, 10, 15, 20, 25, 30, 30},
            {-1, 5, 10, 15, 20, 25, 25},
            {-1, -1, 5, 10, 15, 20, 20},
            {-1, -1, -1, 5, 10, 15, 15},
            {-1, -1, -1, -1, 3, 7, 7},
            {-1, -1, -1, -1, -1, 3, 3},
            {-1, -1, -1, -1, -1, -1, 1}
    }),

    TrapProgression(new int[][] {
            {5, 7, 10, 15, 15, 20, 20},
            {-1, 3, 7, 10, 15, 15, 15},
            {-1, -1, 3, 7, 10, 15, 15},
            {-1, -1, -1, 3, 7, 10, 10},
            {-1, -1, -1, -1, 3, 5, 5},
            {-1, -1, -1, -1, -1, 2, 2},
            {-1, -1, -1, -1, -1, -1, 1}
    });

    private final int[][] matrix;

    GagCapacityMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] getMatrix() {return matrix;}
}
