package PracticeProblems.Completed.Java1;

import java.util.Arrays;

/**
 * @author Jacob Swineford
 */
public class LineOfCoinsInts {

    public static void main(String[] args) {

        int currentCoin = 0;
        int[] allCoins = new int[100]; // 0 - 99
        for (int k = 0; k < 100; k++) {
           allCoins[k] += k;
        }

        for (int i = 1; i <= 100; i++) {
            while (currentCoin < 100) {
                allCoins[currentCoin] = allCoins[currentCoin] / -1;
                currentCoin += i;
            }
            currentCoin = 0;
        }

        System.out.println(Arrays.toString(allCoins));
    }
}
