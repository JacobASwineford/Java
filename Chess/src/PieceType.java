import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;

/**
 * This enumerated type is supposed to represent spaces on a 2 dimensional board.
 * The color of each piece is represented according to their piece type.
 *
 * @author Jacob Swineford
 */

public enum PieceType {
    WHITE_PAWN(WHITE), BLACK_PAWN(BLACK),
    WHITE_ROOK(WHITE), BLACK_ROOK(BLACK),
    WHITE_BISHOP(WHITE), BLACK_BISHOP(BLACK),
    WHITE_KNIGHT(WHITE), BLACK_KNIGHT(BLACK),
    WHITE_QUEEN(WHITE), BLACK_QUEEN(BLACK),
    WHITE_KING(WHITE), BLACK_KING(BLACK);

    private Color color;

    PieceType(Color color) {
        this.color = color;
    }

    public Color color() {
        return color;
    }
}
