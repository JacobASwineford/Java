package Misc.CustomClasses.WordSearch;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Provides a means for generating a word search of varying width
 * and length, populated randomly with the given words.
 */
public class WordSearch {

    char[][] board;
    boolean[][] occupied;

    public WordSearch(int width, int height) {
        board = new char[height][width];
        occupied = new boolean[height][width];

        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = (char) rand.nextInt('a', 'z' + 1);
                //board[i][j] = '#';
            }
        }
    }

    /**
     * Populates the board with the given words, in random
     * positions. Words will not overlap each other.
     *
     * @param  words given word bank
     */
    public void populate(String[] words) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int x, y, biasX = 0, biasY = 0;
        boolean go = true;
        for (String word : words) {

            // if the given word is too big, then it needs to be skipped
            if (word.length() > board[0].length && word.length() > board.length)
                continue;

            while (go) {
                x = rand.nextInt(0, board[0].length);
                y = rand.nextInt(0, board.length);

                // need a bias of some sort that isnt 0 0
                biasX = 0;
                biasY = 0;
                while (biasX == 0 && biasY == 0) {
                    biasX = rand.nextInt(-1, 2);
                    biasY = rand.nextInt(-1, 2);
                }

                if (spread(word, x, y, biasX, biasY))
                    go = false;
            }
            go = true;
        }
    }

    /**
     * Spreads the given word along the board, starting at the specified
     * position and bias crawling until the word has been spread.
     *
     * @return true if the word has been spread, false otherwise
     */
    private boolean spread(String word, int x, int y, int biasX, int biasY) {
        // check to make sure that the current spread is doable.
        int curX = x;
        int curY = y;
        for (int i = 0; i < word.length(); i++) {
            if (!valid(curX, curY))
                return false;
            curX += biasX;
            curY += biasY;
        }

        // the bias is doable, do the stuff
        for (int i = 0; i < word.length(); i++) {
            board[y][x] = word.charAt(i);
            occupied[y][x] = true;
            y += biasY;
            x += biasX;
        }

        return true;
    }

    /**
     * Check to see if the current space on the board is valid. This includes
     * it not being occupied, and being within bounds.
     *
     * @param x board x
     * @param y board y
     */
    private boolean valid(int x, int y) {
        return x >= 0 && x < board[0].length && y >= 0 && y < board.length &&
                !occupied[y][x];
    }

    /**
     * Overridden toString() method.
     *
     * @return string representation of this word search.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] chars : board) {
            for (int j = 0; j < board[0].length; j++) {
                String s;
                if (j == board[0].length - 1)
                    s = chars[j] + "";
                else
                    s = chars[j] + " ";
                sb.append(s);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        WordSearch search = new WordSearch(10, 10);
        String sen = "welcome love happy memorial veteran";
        String[] words = sen.split(" ");
        System.out.println("words:\n");
        for (String word : words) {
            System.out.println("-> " + word);
        }
        System.out.println();
        search.populate(words);
        System.out.println(search);
    }
}
