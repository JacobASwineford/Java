package Misc.CustomClasses.BetterSnake2;

import java.util.LinkedList;

/**
 * Class representing a snake in the game of Snake. Snakes have the ability to move
 * vertically or horizontally, and can expand by eating a fruit.
 *
 * Snakes are represented here as a list of snake segments that are privy to the
 * aforementioned operations.
 *
 * @author Jacob Swineford
 */
public class Snake {

    private LinkedList<Segment> segments;
    private int[] direction = new int[] {1, 0};

    /**
     * Constructor for a snake. Snakes start at the specified position,
     * and are constructed backwards from there. It is the user's responsibility
     * to not initialize the snake in a position that will cause an exception.
     *
     * @param x specified x
     * @param y specified y
     * @param length length of the snake
     */
    Snake(int x, int y, int length) {
        segments = new LinkedList<>();
        for (int i = 0; i < length; i++)
            segments.add(new Segment(x--, y));
    }

    /**
     * Expand this snake by one segment. This snake is expanded by making a new
     * segment sharing the position of the tail segment of the snake. When the snake
     * has advanced, the segments will spread out and all have unique positions.
     */
    void expand() {
        Segment last = segments.getLast();
        segments.add(new Segment(last.getX(), last.getY()));
    }

    /**
     * Advance this snake according to it's direction. This snake is advanced by
     * moving the head in accordance to the direction, and the rest of the segments
     * following the previous segment's position in front of it.
     */
    void advance() {
        Segment first = segments.getFirst();
        int[] previous = new int[] {first.getX(), first.getY()};
        first.setX(first.getX() + direction[0]);
        first.setY(first.getY() + direction[1]);

        for (int i = 1; i < segments.size(); i++) {
            Segment working = segments.get(i);
            int xp = working.getX();
            int yp = working.getY();
            working.setX(previous[0]);
            working.setY(previous[1]);
            previous[0] = xp;
            previous[1] = yp;
        }
    }

    /**
     * Sets the direction of this snake. Upon each advance of this snake, the head will
     * move in this direction.
     */
    void setDirection(int[] direction) {
        this.direction = direction;
    }

    /**
     * Gets the segments of this snake.
     */
    LinkedList<Segment> getSegments () {return segments;}
}
