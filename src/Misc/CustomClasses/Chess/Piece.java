package Misc.CustomClasses.Chess;

/**
 * Replicates a piece on a board. Pieces haves moves, kills, and
 * piece-types.
 *
 * @author Jacob Swineford
 */
public class Piece {

    private int moves = 0;
    private int kills = 0;
    private int specialMoves = 0;
    private PieceType pieceType;

    Piece(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getSpecialMoves() {
        return specialMoves;
    }

    public void setSpecialMoves(int specialMoves) {
        this.specialMoves = specialMoves;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
