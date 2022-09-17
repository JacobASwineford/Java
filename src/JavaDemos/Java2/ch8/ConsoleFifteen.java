package JavaDemos.Java2.ch8;

import java.util.Scanner;

/**
 * A console application that plays the game of Fifteen.
 *
 * @author Jacob Swineford
 */
public class ConsoleFifteen {


    public static void main(String[] args) {
        Fifteen game = new Fifteen();
        System.out.println("\n***********************");
        System.out.println("*** Game of Fifteen ***");
        System.out.println("***********************");

        Scanner in = new Scanner(System.in);
        int count = 1;
        while (!game.over()) {
            System.out.println("\n" + game);
            System.out.print(count + ": Tile to move (0 to quit): ");
            int tile = in.nextInt();
            if (tile == 0) {
                break;
            }
            game.slide(tile);
            count++;
        }

        System.out.println();
        if (game.over()) {
            System.out.println("YOU HAVE SOLVED THE PUZZLE.");
        } else {
            System.out.print("YOUR POWERS ARE WEAK, YOUNG MAN.");
        }
    }
}

