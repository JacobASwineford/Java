package Misc.ConsoleApplications;

/**
 * @author Jacob Swineford
 */
public class test {

    public static void main(String[] args) {
        int[][] grid = new int[10][10];
        for (int i = 0; i < 10; i++) grid[i][i] = 1;
        printGrid(grid);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int n = neighbors(grid, j, i);
                System.out.printf("neighbors for [%d, %d]: %d\n", j, i, n);
            }
        }
    }

    // assumes that the given x and y is in range of the given array
    private static int neighbors(int[][] grid, int x, int y) {
        int n = 0;
        int width = grid[0].length;
        int height = grid.length;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int nx = (x + j + width) % width;
                int ny = (y + i + height) % height;
                int a = grid[ny][nx];
                if (a == 1) {
                    System.out.printf("    at [%d, %d]: %d\n", nx, ny, a);
                }
                n += grid[ny][nx];
            }
        }
        return n - grid[y][x];
    }

    // 0 - 1 = -1 + 10 = 9 % 10 = 9
    // 0 - 0 = 0 + 10 = 10 % 10 = 0
    // 0 + 1 = 1 + 10 = 11 % 10 = 1
    private static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) System.out.print("* ");
                else System.out.print("- ");
            }
            System.out.println();
        }
    }









    static int gcd(int a, int b) {
        System.out.println("gcd1: a, b: " + a + ", " + b);
        while (a != b) {
            if (a > b)
            {
                a = a%b;
                System.out.println("a, b: " + a + ", " + b);
            }
            else b = b%a;
        }
        return a;
    }

    static int gcd2(int a, int b) {
        if (b == 0) return a;
        return gcd2(b, a%b);
    }
}
