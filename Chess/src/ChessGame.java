import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Chess is a game that dates back to the 5th century and has since then
 * evolved into a game of extreme strategy due to it's abstract nature.
 * Chess is very popular around the world and is one of the most played
 * board games in the 21st century. Each player moves one piece per turn
 * and tries to corner the enemy king into checkmate.
 *
 * In general, there are 16 pieces on either side, arranged on an 8x8 grid.
 * This is, however, not an absolute requirement, as this is just the default.
 *
 * There are 6 Individual units: Pawn, Rook, Knight, Bishop, King, and Queen.
 * They all have different move patterns and attack ranges.
 * 1. Pawn - Moves forward only, and can attack diagonally.
 * 2. Rook - Moves horizontally and vertically, with unlimited range.
 * 3. Knight - Moves in an outward bulls-eye fashion, minus the corners.
 * 4. Bishop - Moves Diagonally.
 * 5. Queen - Has the combined movement of the Bishop and Rook.
 * 6. King - Can move to adjacent spaces and corners. Cannot move
 * through attacked spaces.
 *
 * Pieces cannot move through other pieces. If the player wishes to take the enemy's
 * piece, then their attacking piece has to be in the same square that they attacked
 * at the end of their turn. There is only one exception to this rule: En Passant.
 *
 * There are two ways for the game to end.
 * 1. Checkmate - when the king can move nowhere safe and is guaranteed to be taken
 * next turn. This results in the opposing player winning.
 * 2. Stalemate - when the king cannot move and no other pieces owned
 * by the player can move legally. This results in a tie.
 *
 * @author Jacob Swineford
 */

public class ChessGame {

    private Piece[][] board;
    private boolean kingRestriction; // implies stalemate
    private boolean naturalSetting;
    private boolean pawnJumping;

    private boolean followTurns;
    private int currentTurn;
    public static final int TURN_WHITE = 1;
    public static final int TURN_BLACK = -1;
    public static final int NOT_FOLLOWING_TURNS = 0;

    public static final int DEFAULT_BOARD_SIZE = 8;

    // static variables used to as types for coordinates
    private static final int MOVES = 0;
    private static final int ATTACKS = 1;
    private static final int RANGE = 2;

    /**
     * Initializes the board to it's default state, using parameters
     * given by the user.
     *
     * These special rules are set to true:
     * - kingRestriction
     * - followTurns
     * - pawnJumping
     *
     * The current turn is automatically set to white, as this is the default
     * in professional play. If the board is 8x8, then naturalSetting is
     * enabled and the board is iterated upon.
     */
    public ChessGame(int boardSizeX, int boardSizeY) {
        board = new Piece[boardSizeY][boardSizeX];
        naturalSetting = true;
        kingRestriction = true;
        followTurns = true;
        pawnJumping = true;
        currentTurn = TURN_WHITE;
        setNaturalSetting();
    }

    /**
     * Initializes the board to be 8x8.
     *
     * These special rules are set to true:
     * - kingRestriction
     * - followTurns
     * - naturalSetting
     * - pawnJumping
     *
     * The current turn is automatically set to white, as this is the default
     * in professional play. The natural setting is automatically iterated upon.
     */
    public ChessGame() {
        this(DEFAULT_BOARD_SIZE, DEFAULT_BOARD_SIZE);
    }

    /**
     * Initializes the board to it's default state, using parameters
     * given by the user. if naturalSetting is enabled, then the board
     * is iterated upon accordingly.
     *
     * @param kingRestriction whether the king can move through attacked spaces
     * @param followTurns whether this class keeps track of turns
     * @param currentTurn the turn that the board starts on. CAUTION: IF followTurns
     *                    IS DISABLED, THEN IT IS RECOMMENDED THAT THIS PARAMETER
     *                    BE INITIALIZED TO THE STATIC VALUE NOT_FOLLOWING_TURNS.
     * @param pawnJumping whether pawns can move two tiles for their first move or not
     */
    public ChessGame(int boardSizeX, int boardSizeY, boolean kingRestriction, boolean pawnJumping,
                     boolean followTurns, int currentTurn, boolean naturalSetting) {
        board = new Piece[boardSizeY][boardSizeX];
        this.kingRestriction = kingRestriction;
        this.pawnJumping = pawnJumping;
        this.followTurns = followTurns;
        this.currentTurn = currentTurn;
        this.naturalSetting = naturalSetting;
        setNaturalSetting();
    }

    /**
     * Initializes the board to it's default state, using parameters
     * given by the user. if naturalSetting is enabled, then the board
     * is iterated upon accordingly. followTurns is automatically set to false.
     *
     * These special rules are set to true:
     * - kingRestriction
     *
     * @param kingRestriction whether the king can move through attacked spaces
     * @param pawnJumping whether pawns can move two tiles for their first move or not
     */
    public ChessGame(int boardSizeX, int boardSizeY, boolean kingRestriction,
                     boolean pawnJumping, boolean naturalSetting) {
        this(boardSizeX, boardSizeY, kingRestriction, pawnJumping,
                false, NOT_FOLLOWING_TURNS, naturalSetting);
    }

    /**
     * Returns true if there is currently a piece occupying the
     * specified tile. otherwise, this method returns false.
     */
    public boolean pieceIn(int x, int y) {
        return board[y][x] != null;
    }

    /**
     * Returns the current piece occupying the specified tile. If there
     * is no piece occupying the this tile, this method returns null.
     */
    public Piece pieceOf(int x, int y) {
        return board[y][x];
    }

    /**
     * Returns the current piece occupying the specified tile. If there
     * is no piece occupying the this tile, this method returns null.
     */
    public Piece pieceOf(Point2D p) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        return board[y][x];
    }

    /**
     * Moves the piece occupying the specified tile.
     * This method call handles both moves and attacks for pieces.
     * If the move specified cannot be made,
     * then this method call is ignored. This includes moves involving
     * initial empty spaces and those out of range for the given
     * coordinates.
     *
     * @param x1 the initial x position
     * @param y1 the initial y position
     * @param x2 the ending x position
     * @param y2 the ending y position
     */
    public void move(int x1, int y1, int x2, int y2) {
        if (board[y1][x1] == null) {
            return;
        }
        ArrayList<Point2D> mC = getMoveCoordinates(x1, y1);
        ArrayList<Point2D> aC = getAttackCoordinates(x1, y1);
        Piece iP = pieceOf(x1, y1);

        if (followTurns) {
            if (currentTurn == TURN_WHITE && iP.getPieceType().color() != Color.WHITE) {
                return;
            }
            if (currentTurn == TURN_BLACK && iP.getPieceType().color() != Color.BLACK) {
                return;
            }
        }

        if (mC.contains(new Point2D(x2, y2))) {
            // special conditions
            if (iP.getPieceType() == PieceType.WHITE_PAWN && y2 - y1 == 2) { // non-negative
                iP.setSpecialMoves(iP.getSpecialMoves() + 1);
            }
            if (iP.getPieceType() == PieceType.BLACK_PAWN && y1 - y2 == 2) { // non_negative
                iP.setSpecialMoves(iP.getSpecialMoves() + 1);
            }

            iP.setMoves(iP.getMoves() + 1);
            deletePiece(x1, y1);
            spawnPiece(iP, x2, y2);
            currentTurn = -currentTurn;
            return;
        }

        if (aC.contains(new Point2D(x2, y2))) {
            iP.setKills(iP.getKills() + 1);
            deletePiece(x1, y1);
            deletePiece(x2, y2);
            spawnPiece(iP, x2, y2);
            currentTurn = -currentTurn;
        }
    }

    /**
     * Spawns a piece at the specified tile, if the space is empty.
     */
    public void spawnPiece(Piece piece, int x, int y) {
        if (board[y][x] == null) {
            board[y][x] = piece;
        }
    }

    /**
     * Deletes the piece located at the specified tile.
     * Namely, this assigns the specified tile as null.
     */
    public void deletePiece(int x, int y) {
        board[y][x] = null;
    }

    /**
     * Returns an ArrayList<Point2D> that contains the coordinates
     * for valid non-attacking moves for the piece at the specified
     * tile. If the tile specified is null, then this method is ignored.
     */
    public ArrayList<Point2D> getMoveCoordinates(int x, int y) {
        if (!pieceIn(x, y)) {
            return null;
        }
        PieceType pt = board[y][x].getPieceType();

        if (pt == PieceType.WHITE_PAWN || pt == PieceType.BLACK_PAWN) {
            return getPawnCoordinates(x, y, MOVES);
        }
        if (pt == PieceType.WHITE_ROOK || pt == PieceType.BLACK_ROOK) {
            ArrayList<Point2D> r = new ArrayList<>();
            r.addAll(getVerticalCoordinates(x, y, MOVES));
            r.addAll(getHorizontalCoordinates(x, y, MOVES));
            return r;
         }
        if (pt == PieceType.WHITE_BISHOP || pt == PieceType.BLACK_BISHOP) {
            return getDiagonalCoordinates(x, y, MOVES);
        }
        if (pt == PieceType.WHITE_KNIGHT || pt == PieceType.BLACK_KNIGHT) {
            return getKnightCoordinates(x, y, MOVES);
        }
        if (pt == PieceType.WHITE_QUEEN || pt == PieceType.BLACK_QUEEN) {
            ArrayList<Point2D> r = new ArrayList<>();
            r.addAll(getDiagonalCoordinates(x, y, MOVES));
            r.addAll(getVerticalCoordinates(x, y, MOVES));
            r.addAll(getHorizontalCoordinates(x, y, MOVES));
            return r;
        }
        if (pt == PieceType.WHITE_KING || pt == PieceType.BLACK_KING) {
            return getKingCoordinates(x, y, MOVES);
        }
        return null;
    }

    /**
     * Returns an ArrayList<Point2D> that contains the coordinates
     * for valid attacking moves for the piece at the specified tile.
     * If the tile specified is null, then this method is ignored.
     */
    public ArrayList<Point2D> getAttackCoordinates(int x, int y) {
        if (!pieceIn(x, y)) {
            return null;
        }

        PieceType pt = board[y][x].getPieceType();

        if (pt == PieceType.WHITE_PAWN || pt == PieceType.BLACK_PAWN) {
            return getPawnCoordinates(x, y, ATTACKS);
        }
        if (pt == PieceType.WHITE_ROOK || pt == PieceType.BLACK_ROOK) {
            ArrayList<Point2D> r = new ArrayList<>();
            r.addAll(getVerticalCoordinates(x, y, ATTACKS));
            r.addAll(getHorizontalCoordinates(x, y, ATTACKS));
            return r;
        }
        if (pt == PieceType.WHITE_BISHOP || pt == PieceType.BLACK_BISHOP) {
            return getDiagonalCoordinates(x, y, ATTACKS);
        }
        if (pt == PieceType.WHITE_KNIGHT || pt == PieceType.BLACK_KNIGHT) {
            return getKnightCoordinates(x, y, ATTACKS);
        }
        if (pt == PieceType.WHITE_QUEEN || pt == PieceType.BLACK_QUEEN) {
            ArrayList<Point2D> r = new ArrayList<>();
            r.addAll(getDiagonalCoordinates(x, y, ATTACKS));
            r.addAll(getVerticalCoordinates(x, y, ATTACKS));
            r.addAll(getHorizontalCoordinates(x, y, ATTACKS));
           return r;
        }
        if (pt == PieceType.WHITE_KING || pt == PieceType.BLACK_KING) {
            return getKingCoordinates(x, y, ATTACKS);
        }
        return null;
    }

    /**
     * Returns an ArrayList<Point2D> that contains the range coordinates
     * for the piece at the specified tile. If the tile specified is null,
     * then this method is ignored.
     *
     * DISCLAIMER: THIS METHOD IS NOT MEANT TO BE USED TO FIND VALID ATTACK COORDINATES.
     * USE getAttackCoordinates() INSTEAD.
     */
    public ArrayList<Point2D> getRangeCoordinates(int x, int y) {
        if (!pieceIn(x, y)) {
            return null;
        }
        PieceType pt = board[y][x].getPieceType();

        if (pt == PieceType.WHITE_PAWN || pt == PieceType.BLACK_PAWN) {
            return getPawnCoordinates(x, y, RANGE);
        }
        if (pt == PieceType.WHITE_ROOK || pt == PieceType.BLACK_ROOK) {
            ArrayList<Point2D> r = new ArrayList<>();
            r.addAll(getVerticalCoordinates(x, y, RANGE));
            r.addAll(getHorizontalCoordinates(x, y, RANGE));
            return r;
        }
        if (pt == PieceType.WHITE_BISHOP || pt == PieceType.BLACK_BISHOP) {
            return getDiagonalCoordinates(x, y, RANGE);
        }
        if (pt == PieceType.WHITE_KNIGHT || pt == PieceType.BLACK_KNIGHT) {
            return getKnightCoordinates(x, y, RANGE);
        }
        if (pt == PieceType.WHITE_QUEEN || pt == PieceType.BLACK_QUEEN) {
            ArrayList<Point2D> r = new ArrayList<>();
            r.addAll(getDiagonalCoordinates(x, y, RANGE));
            r.addAll(getVerticalCoordinates(x, y, RANGE));
            r.addAll(getHorizontalCoordinates(x, y, RANGE));
            return r;
        }
        if (pt == PieceType.WHITE_KING || pt == PieceType.BLACK_KING) {
            return getKingCoordinates(x, y, RANGE);
        }
        return null;
    }

    /**
     * Checks to see if the piece at the specified tile has a given
     * move or attack coordinate.
     * If so, this method returns true. otherwise, false.
     */
    public boolean pieceHasCoordinate(int xLocation, int yLocation,
                                      int xC, int yC) {
        return getRangeCoordinates(xLocation, yLocation).
                contains(new Point2D(xC, yC));
    }

    @Override
    public String toString() {
        StringBuilder returned = new StringBuilder();
        for (Piece[] arr : board) {
            for (Piece p : arr) {
                if (p == null) {
                    returned.append(p); // pieceType cannot be referenced from null
                } else {
                    returned.append(p.getPieceType());
                }
                returned.append(" ");
            }
            returned.trimToSize();
            returned.append("\n");
        }
        return returned.toString();
    }

    /**
     * Returns the current board state.
     */
    public Piece[][] getBoardState() {
        return board.clone();
    }

    /**
     * Sets the natural setting for a game of chess on an 8x8 board, if naturalSetting
     * is enabled. Otherwise, this method is ignored.
     */
    private void setNaturalSetting() {
        if (!naturalSetting || board.length != 8 || board[0].length != 8) {
            return;
        }
        ArrayList<Piece> topBlack = new ArrayList<>();
        ArrayList<Piece> bottomWhite = new ArrayList<>();
        setProperPieces(topBlack, 0);
        setProperPieces(bottomWhite, 6);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == 0) {
                    board[i][j] = topBlack.get(j);
                }
                if (i == 1) {
                    board[i][j] = new Piece(PieceType.BLACK_PAWN);
                }
                if (i == 6) {
                    board[i][j] = new Piece(PieceType.WHITE_PAWN);
                }
                if (i == 7) {
                    board[i][j] = bottomWhite.get(j);
                }
            }
        }
    }

    /**
     * Fills the given ArrayList<Point2D> with the starting pieces.
     * Simplifies setNaturalSetting().
     */
    private void setProperPieces(ArrayList<Piece> arr, int row) {
        if (row == 0) {
            arr.add(new Piece(PieceType.BLACK_ROOK));
            arr.add(new Piece(PieceType.BLACK_KNIGHT));
            arr.add(new Piece(PieceType.BLACK_BISHOP));
            arr.add(new Piece(PieceType.BLACK_QUEEN));
            arr.add(new Piece(PieceType.BLACK_KING));
            arr.add(new Piece(PieceType.BLACK_BISHOP));
            arr.add(new Piece(PieceType.BLACK_KNIGHT));
            arr.add(new Piece(PieceType.BLACK_ROOK));
        } else if (row == 6) {
            arr.add(new Piece(PieceType.WHITE_ROOK));
            arr.add(new Piece(PieceType.WHITE_KNIGHT));
            arr.add(new Piece(PieceType.WHITE_BISHOP));
            arr.add(new Piece(PieceType.WHITE_QUEEN));
            arr.add(new Piece(PieceType.WHITE_KING));
            arr.add(new Piece(PieceType.WHITE_BISHOP));
            arr.add(new Piece(PieceType.WHITE_KNIGHT));
            arr.add(new Piece(PieceType.WHITE_ROOK));
        }
    }

    /**
     * Returns a list of vertical coordinates for the piece at the specified tile.
     * Depending on the type, this method will return different lists.
     *
     * THIS METHOD COOPERATES WITH THE NOTION THAT THE CALLER IS USING THE STATIC
     * VARIABLES SET ASIDE FOR THE TYPE.
     */
    private ArrayList<Point2D> getVerticalCoordinates(int x, int y, int type) {
        ArrayList<Point2D> moves = new ArrayList<>();
        ArrayList<Point2D> attacks = new ArrayList<>();
        ArrayList<Point2D> range = new ArrayList<>();
        int yTemp = y;
        int c = 0;

        // origin-up
        while (yTemp > -1) {
            if (c != 0) {
                if (pieceIn(x, yTemp)) {
                    boundedAttack(attacks, x, yTemp, pieceColor(x, y));
                    boundedAttackRange(range, x, yTemp);
                    break;
                } else {
                    boundedMove(moves, x, yTemp);
                    boundedAttackRange(range, x, yTemp);
                }
            }
            c++;
            yTemp--;
        }
        yTemp = y;
        c = 0;

        // origin-down
        while (yTemp < board.length) {
            if (c != 0) {
                if (pieceIn(x, yTemp)) {
                    boundedAttack(attacks, x, yTemp, pieceColor(x, y));
                    boundedAttackRange(range, x, yTemp);
                    break;
                } else {
                    boundedMove(moves, x, yTemp);
                    boundedAttackRange(range, x, yTemp);
                }
            }
            c++;
            yTemp++;
        }

        if (type == MOVES) {
            return moves;
        }
        if (type == ATTACKS) {
            return attacks;
        }
        return range;
    }

    /**
     * Returns a list of horizontal coordinates for the piece at the specified tile.
     * Depending on the type, this method will return different lists.
     *
     * THIS METHOD COOPERATES WITH THE NOTION THAT THE CALLER IS USING THE STATIC
     * VARIABLES SET ASIDE FOR THE TYPE.
     */
    private ArrayList<Point2D> getHorizontalCoordinates(int x, int y, int type) {
        ArrayList<Point2D> moves = new ArrayList<>();
        ArrayList<Point2D> attacks = new ArrayList<>();
        ArrayList<Point2D> range = new ArrayList<>();
        int xTemp = x;
        int c = 0;

        // origin-right
        while (xTemp > -1) {
            if (c != 0) {
                if (pieceIn(xTemp, y)) {
                    boundedAttack(attacks, xTemp, y, pieceColor(x, y));
                    boundedAttackRange(range, xTemp, y);
                    break;
                } else {
                    boundedMove(moves, xTemp, y);
                    boundedAttackRange(range, xTemp, y);
                }
            }
            c++;
            xTemp--;
        }
        xTemp = x;
        c = 0;

        // origin-left
        while (xTemp < board.length) {
            if (c != 0) {
                if (pieceIn(xTemp, y)) {
                    boundedAttack(attacks, xTemp, y, pieceColor(x, y));
                    boundedAttackRange(range, xTemp, y);
                    break;
                } else {
                    boundedMove(moves, xTemp, y);
                    boundedAttackRange(range, xTemp, y);
                }
            }
            c++;
            xTemp++;
        }

        if (type == MOVES) {
            return moves;
        }
        if (type == ATTACKS) {
            return attacks;
        }
        return range;
    }

    /**
     * Returns a list of diagonal coordinates for the piece at the specified tile.
     * Depending on the type, this method will return different lists.
     *
     * THIS METHOD COOPERATES WITH THE NOTION THAT THE CALLER IS USING THE STATIC
     * VARIABLES SET ASIDE FOR THE INDEX.
     */
    private ArrayList<Point2D> getDiagonalCoordinates(int x, int y, int type) {
        ArrayList<Point2D> moves = new ArrayList<>();
        ArrayList<Point2D> attacks = new ArrayList<>();
        ArrayList<Point2D> range = new ArrayList<>();
        int xTemp = x;
        int yTemp = y;
        int c = 0;

        // origin-left-down
        while (xTemp > -1 && yTemp < board.length) {
            if (c != 0) {
                if (pieceIn(xTemp, yTemp)) {
                    boundedAttack(attacks, xTemp, yTemp, pieceColor(x, y));
                    boundedAttackRange(range, xTemp, yTemp);
                    break;
                } else {
                    boundedMove(moves, xTemp, yTemp);
                    boundedAttackRange(range, xTemp, yTemp);
                }
            }
            c++;
            xTemp--;
            yTemp++;
        }
        xTemp = x;
        yTemp = y;
        c = 0;

        // origin-right-up
        while (xTemp < board.length && yTemp > -1) {
            if (c != 0) {
                if (pieceIn(xTemp, yTemp)) {
                    boundedAttack(attacks, xTemp, yTemp, pieceColor(x, y));
                    boundedAttackRange(range, xTemp, yTemp);
                    break;
                } else {
                    boundedMove(moves, xTemp, yTemp);
                    boundedAttackRange(range, xTemp, yTemp);
                }
            }
            c++;
            xTemp++;
            yTemp--;
        }
        xTemp = x;
        yTemp = y;
        c = 0;

        // origin-left-up
        while (xTemp > -1 && yTemp > -1) {
            if (c != 0) {
                if (pieceIn(xTemp, yTemp)) {
                    boundedAttack(attacks, xTemp, yTemp, pieceColor(x, y));
                    boundedAttackRange(range, xTemp, yTemp);
                    break;
                } else {
                    boundedMove(moves, xTemp, yTemp);
                    boundedAttackRange(range, xTemp, yTemp);
                }
            }
            c++;
            xTemp--;
            yTemp--;
        }
        xTemp = x;
        yTemp = y;
        c = 0;

        // origin-right-down
        while (xTemp < board.length && yTemp < board.length) {
            if (c != 0) {
                if (pieceIn(xTemp, yTemp)) {
                    boundedAttack(attacks, xTemp, yTemp, pieceColor(x, y));
                    boundedAttackRange(range, xTemp, yTemp);
                    break;
                } else {
                    boundedMove(moves, xTemp, yTemp);
                    boundedAttackRange(range, xTemp, yTemp);
                }
            }
            c++;
            xTemp++;
            yTemp++;
        }

        if (type == MOVES) {
            return moves;
        }
        if (type == ATTACKS) {
            return attacks;
        }
        return range;
    }

    /**
     * Returns a list of vertical, horizontal, or range coordinates
     * for the pawn at the specified tile.
     * Depending on the type, this method will return different lists.
     *
     * Pawn can move two spaces for their initial move. If pawnJumping is enabled,
     * then this definition holds true. Otherwise, this definition doesn't hold.
     * This is counted as the special move for pawns.
     *
     *
     * THIS METHOD COOPERATES WITH THE NOTION THAT THE CALLER IS USING THE STATIC
     * VARIABLES SET ASIDE FOR THE TYPE.
     */
    private ArrayList<Point2D> getPawnCoordinates(int x, int y, int type) {
        ArrayList<Point2D> moves = new ArrayList<>();
        ArrayList<Point2D> attacks = new ArrayList<>();
        ArrayList<Point2D> range = new ArrayList<>();
        Color color = pieceColor(x, y);

        if (type == MOVES) {
            if (color == PieceType.WHITE_PAWN.color()) {
                // special move
                if (board[y][x].getMoves() == 0 && pawnJumping) {
                    boundedMove(moves, x, y - 2);
                }

                boundedMove(moves, x, y - 1);
            } else {
                // special move
                if (board[y][x].getMoves() == 0 && pawnJumping) {
                    boundedMove(moves, x, y + 2);
                }

                boundedMove(moves, x, y + 1);
            }
            return moves;
        }

        if (type == ATTACKS) {
            boundedAttack(attacks, x - 1, y - 1, color);
            boundedAttack(attacks, x + 1, y - 1, color);
            boundedAttack(attacks, x - 1, y + 1, color);
            boundedAttack(attacks, x + 1, y + 1, color);
            return attacks;
        }

        if (type == RANGE) {
            boundedAttackRange(range, x - 1, y - 1);
            boundedAttackRange(range, x + 1, y - 1);
            boundedAttackRange(range, x - 1, y + 1);
            boundedAttackRange(range, x + 1, y + 1);
        }
        return range;
    }

    /**
     * Returns a list of vertical, horizontal, or range coordinates
     * for the King at the specified tile.
     * Depending on the type, this method will return different lists.
     *
     * THIS METHOD COOPERATES WITH THE NOTION THAT THE CALLER IS USING THE STATIC
     * VARIABLES SET ASIDE FOR THE TYPE.
     */
    private ArrayList<Point2D> getKingCoordinates(int x, int y, int type) {
        ArrayList<Point2D> moves = new ArrayList<>();
        ArrayList<Point2D> attacks = new ArrayList<>();
        ArrayList<Point2D> range = new ArrayList<>();
        Color color = pieceColor(x, y);

        if (type == MOVES) {
            boundedKingMove(moves, x + 1, y, color);
            boundedKingMove(moves, x + 1, y + 1, color);
            boundedKingMove(moves, x + 1, y - 1, color);
            boundedKingMove(moves, x - 1, y, color);
            boundedKingMove(moves, x - 1, y + 1, color);
            boundedKingMove(moves, x - 1, y - 1, color);
            boundedKingMove(moves, x, y + 1, color);
            boundedKingMove(moves, x, y - 1, color);
            return moves;
        }

        if (type == ATTACKS) {
            boundedKingAttack(attacks, x + 1, y, color);
            boundedKingAttack(attacks, x + 1, y + 1, color);
            boundedKingAttack(attacks, x + 1, y - 1, color);
            boundedKingAttack(attacks, x - 1, y, color);
            boundedKingAttack(attacks, x - 1, y + 1, color);
            boundedKingAttack(attacks, x - 1, y - 1, color);
            boundedKingAttack(attacks, x, y + 1, color);
            boundedKingAttack(attacks, x, y - 1, color);
            return attacks;
        }

        if (type == RANGE) {
            boundedAttackRange(range, x + 1, y);
            boundedAttackRange(range, x + 1, y + 1);
            boundedAttackRange(range, x + 1, y - 1);
            boundedAttackRange(range, x - 1, y);
            boundedAttackRange(range, x - 1, y + 1);
            boundedAttackRange(range, x - 1, y - 1);
            boundedAttackRange(range, x, y + 1);
            boundedAttackRange(range, x, y - 1);
        }
        return range;
    }

    /**
     * Returns a list of vertical, horizontal, or range coordinates
     * for the knight at the specified tile.
     * Depending on the type, this method will return different lists.
     *
     * THIS METHOD COOPERATES WITH THE NOTION THAT THE CALLER IS USING THE STATIC
     * VARIABLES SET ASIDE FOR THE TYPE.
     */
    private ArrayList<Point2D> getKnightCoordinates(int x, int y, int type) {
        ArrayList<Point2D> moves = new ArrayList<>();
        ArrayList<Point2D> attacks = new ArrayList<>();
        ArrayList<Point2D> range = new ArrayList<>();
        Color color = pieceColor(x, y);

        if (type == MOVES) {
            boundedMove(moves, x - 2, y - 1);
            boundedMove(moves, x - 1, y - 2);
            boundedMove(moves, x + 1, y - 2);
            boundedMove(moves, x + 2, y - 1);
            boundedMove(moves, x + 2, y + 1);
            boundedMove(moves, x + 1, y + 2);
            boundedMove(moves, x - 1, y + 2);
            boundedMove(moves, x - 2, y + 1);
            return moves;
        }

        if (type == ATTACKS) {
            boundedAttack(attacks, x - 2, y - 1, color);
            boundedAttack(attacks, x - 1, y - 2, color);
            boundedAttack(attacks, x + 1, y - 2, color);
            boundedAttack(attacks, x + 2, y - 1, color);
            boundedAttack(attacks, x + 2, y + 1, color);
            boundedAttack(attacks, x + 1, y + 2, color);
            boundedAttack(attacks, x - 1, y + 2, color);
            boundedAttack(attacks, x - 2, y + 1, color);
            return attacks;
        }

        if (type == RANGE) {
            boundedAttackRange(range, x - 2, y - 1);
            boundedAttackRange(range, x - 1, y - 2);
            boundedAttackRange(range, x + 1, y - 2);
            boundedAttackRange(range, x + 2, y - 1);
            boundedAttackRange(range, x + 2, y + 1);
            boundedAttackRange(range, x + 1, y + 2);
            boundedAttackRange(range, x - 1, y + 2);
            boundedAttackRange(range, x - 2, y + 1);
        }
        return range;
    }

    /**
     * Provides logic for pieces moving to spaces that are potentially out
     * of bounds. If the piece can move, then it is added to
     * the specified ArrayList<Point2D>.
     */
    private void boundedMove(ArrayList<Point2D> arr, int x, int y) {
        if (outOfRange(x, y) || pieceIn(x, y)) {
            return;
        }
        arr.add(new Point2D(x, y));
    }

    /**
     * Provides logic for pieces moving to spaces that are potentially out
     * of bounds. If the piece can move, then it is added to
     * the specified ArrayList<Point2D>.
     *
     * Kings cannot move to spaces that are currently being attacked by the
     * opposing pieces. If kingRestriction is enabled, this definition holds true.
     * Otherwise, this definition doesn't hold.
     */
    private void boundedKingMove(ArrayList<Point2D> arr, int x, int y, Color currentColor) {
        if (kingRestriction) {
            if (outOfRange(x, y) || pieceIn(x, y) || beingAttacked(x, y, currentColor)) {
                return;
            }
            arr.add(new Point2D(x, y));
        }
        boundedMove(arr, x, y);
    }

    /**
     * Checks if the specified tile is being attacked by the enemy's opposing pieces.
     */
    public boolean beingAttacked(int x, int y, Color currentColor) {
        HashSet<Point2D> attacking = new HashSet<>();
        for (Point2D p : pieceLocations()) {
            if (pieceOf(p).getPieceType().color() != currentColor) {
                attacking.addAll(getRangeCoordinates((int) p.getX(), (int) p.getY()));
            }
        }
        return attacking.contains(new Point2D(x, y));
    }

    /**
     * Provides logic for pieces attacking opposing pieces that are potentially
     * out of bounds. If the piece can move, then it is added
     * to the specified ArrayList<Point2D>.
     */
    private void boundedAttack(ArrayList<Point2D> arr, int x,
                               int y, Color currentPieceColor) {
        if (outOfRange(x, y) || pieceColor(x, y) == currentPieceColor ||
        board[y][x] == null) {
            return;
        }
        arr.add(new Point2D(x, y));
    }


    private void boundedKingAttack(ArrayList<Point2D> arr, int x,
                               int y, Color currentPieceColor) {
        if (outOfRange(x, y) || pieceColor(x, y) == currentPieceColor ||
                board[y][x] == null) {
            return;
        }
        if (kingRestriction && beingAttacked(x, y, currentPieceColor)) {
            return;
        }
        arr.add(new Point2D(x, y));
    }


    /**
     * Provides logic for the spaces that are currently being attacked.
     */
    private void boundedAttackRange(ArrayList<Point2D> arr, int x, int y) {
        if (outOfRange(x, y)) {
            return;
        }
        arr.add(new Point2D(x, y));
    }

    /**
     * Returns true if the given position is out of range for the board[][].
     * otherwise, returns false.
     */
    private boolean outOfRange(int x, int y) {
        return x >= board[0].length || x < 0 || y >= board.length || y < 0;
    }

    /**
     * Returns the color of the piece at the specified tile. If the specified
     * Piece is null, then null is returned.
     */
    public Color pieceColor(int x, int y) {
        try {
            return board[y][x].getPieceType().color();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Finds and returns an ArrayList<Point2d> of
     * all the coordinates of tiles that contain pieces.
     */
    public ArrayList<Point2D> pieceLocations() {
        ArrayList<Point2D> r = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != null) {
                    r.add(new Point2D(j, i));
                }
            }
        }
        return r;
    }

    /**
     * Zeros out the board, effectively setting all tiles to null.
     */
    public void zeroBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = null;
            }
        }
    }

    /**
     * Resets the board to it's natural setting, if natural setting is enabled.
     */
    public void resetToNaturalSetting() {
        if (naturalSetting) {
            zeroBoard();
            setNaturalSetting();
        }
    }

    /**
     * Returns true if the current king for white is in check. Otherwise, returns false.
     *
     * In the event that there is more than one white king, all will be checked
     * for the check condition.
     */
    public boolean whiteInCheck() {
        if (whitePieceTypes().contains(new Piece(PieceType.WHITE_KING))) {
            HashSet<Point2D> attacked = new HashSet<>();
            for (Point2D p : blackPieceLocations()) {
                int y = (int) p.getY();
                int x = (int) p.getX();
                attacked.addAll(getRangeCoordinates(x, y));
            }

            ArrayList<Point2D> kings = whiteKingLocations();
            for (Point2D p : kings) {
                if (attacked.contains(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if the current king for black is in check. Otherwise, returns false.
     *
     * In the event that there is more than one black king, all will be checked
     * for the check condition.
     */
    public boolean blackInCheck() {
        if (blackPieceTypes().contains(new Piece(PieceType.BLACK_KING))) {

            // find the locations of all white pieces and their attack coordinates
            HashSet<Point2D> attacked = new HashSet<>();

            for (Point2D p : whitePieceLocations()) {
                int y = (int) p.getY();
                int x = (int) p.getX();
                attacked.addAll(getRangeCoordinates(x, y));
            }

            ArrayList<Point2D> kings = blackKingLocations();
            for (Point2D p : kings) {
                if (attacked.contains(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if the current king for white is in checkmate. Otherwise, returns false.
     *
     * In the event that there is more than one white king, all will be checked
     * for the checkmate condition.
     */
    public boolean whiteInCheckmate() {
        ArrayList<Point2D> kings = whiteKingLocations();
        for (Point2D king : kings) {
            int kingX = (int) king.getX();
            int kingY = (int) king.getY();

            // find the attacking coordinates
            ArrayList<Point2D> attacking = new ArrayList<>();
            for (Point2D p : blackPieceLocations()) {
                int x2 = (int) p.getX();
                int y2 = (int) p.getY();
                if (getAttackCoordinates(x2, y2).contains(king)) {
                    attacking.add(new Point2D(x2, y2));
                }
            }

            // the king is in checkmate if it cannot move, block, or take the opposing
            // piece(s).

            // move
            if (getKingCoordinates(kingX, kingY, MOVES).size() != 0) {
                return false;
            }

            // block
            if (attacking.size() > 1) {
                return true;
            }

            if (canBeBlockedForKing((int) attacking.get(0).getX(),
                    (int) attacking.get(0).getY(), kingX, kingY, Color.WHITE)) {
                return false;
            }

            // take
            if (canBeTaken((int) attacking.get(0).getX(), (int) attacking.get(0).getY(),
                    Color.WHITE)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the current king for black is in checkmate. Otherwise, returns false.
     *
     * In the event that there is more than one black king, all will be checked
     * for the checkmate condition.
     */
    public boolean blackInCheckmate() {
        ArrayList<Point2D> kings = blackKingLocations();
        for (Point2D king : kings) {
            int kingX = (int) king.getX();
            int kingY = (int) king.getY();

            // find the attacking coordinates
            ArrayList<Point2D> attacking = new ArrayList<>();
            for (Point2D p : blackPieceLocations()) {
                int x2 = (int) p.getX();
                int y2 = (int) p.getY();
                if (getAttackCoordinates(x2, y2).contains(king)) {
                    attacking.add(new Point2D(x2, y2));
                }
            }

            // the king is in checkmate if it cannot move, block, or take the opposing
            // piece(s).

            // move
            if (getKingCoordinates(kingX, kingY, MOVES).size() != 0) {
                return false;
            }

            // block
            if (attacking.size() > 1) {
                return true;
            }

            if (canBeBlockedForKing((int) attacking.get(0).getX(),
                    (int) attacking.get(0).getY(), kingX, kingY, Color.BLACK)) {
                return false;
            }

            // take
            if (canBeTaken((int) attacking.get(0).getX(), (int) attacking.get(0).getY(),
                    Color.BLACK)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the current board configuration for white is in stalemate.
     * Otherwise, returns false.
     */
    public boolean whiteInStalemate() {
        int moves = 0;
        for (Point2D p : whitePieceLocations()) {
            moves += getMoveCoordinates((int) p.getX(), (int) p.getY()).size();
        }
        return moves == 0;
    }

    /**
     * Returns true if the current board configuration for black is in stalemate.
     * Otherwise, returns false.
     */
    public boolean blackInStalemate() {
        int moves = 0;
        for (Point2D p : blackPieceLocations()) {
            moves += getMoveCoordinates((int) p.getX(), (int) p.getY()).size();
        }
        return moves == 0;
    }

    /**
     * Returns true if the piece at the specified position can be blocked
     * by the opponent for their king. Otherwise, returns false.
     */
    private boolean canBeBlockedForKing(int x, int y, int xKing, int yKing, Color currentColor) {
        for (Point2D p : pieceLocations()) {
            if (pieceOf(p).getPieceType().color() != currentColor) {
                // make invisible moves and check for king
                int pX = (int) p.getX();
                int pY = (int) p.getY();

                for (Point2D point : getMoveCoordinates(pX, pY)) {
                    int pointX = (int) point.getX();
                    int pointY = (int) point.getY();
                    board[pointY][pointX] = new Piece(PieceType.WHITE_PAWN); // anything but null
                    if (!getAttackCoordinates(x, y).contains(new Point2D(xKing, yKing))) {
                        board[pointY][pointX] = null;
                        return true;
                    }
                    board[pointY][pointX] = null;
                }
            }
        }
        return false;
    }

    /**
     * Returns true of the piece at the specified position can be taken
     * by the opponent. Otherwise, returns false.
     */
    private boolean canBeTaken(int x, int y, Color currentColor) {
        for (Point2D p : pieceLocations()) {
            if (pieceOf(p).getPieceType().color() != currentColor) {
                ArrayList<Point2D> attacking = getAttackCoordinates(
                        (int) p.getX(), (int) p.getY());
                if (attacking.contains(new Point2D(x, y))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if the king at the current position can castle with
     * their respective rooks. Otherwise, returns false.
     *
     * Castling requires the following:
     * 1. the king is not in check
     * 2. the king hasn't moved
     * 3. the tiles between the rook and king are empty
     * 4. the castling rook hasn't moved
     * 5. the king does not move through check
     *
     * if there is more than one king on the board for the specified color,
     * then castling cannot happen if either are in check.
     *
     * DISCLAIMER: IT IS NOT THIS CLASS'S RESPONSIBILITY TO MOVE THE KING
     * AND ROOK TO THEIR FINAL CASTLING LOCATIONS.
     */
    public boolean liableForCastling(int xK, int yK) {
        PieceType rookType;
        Piece king = pieceOf(xK, yK);
        Color kingColor = pieceColor(xK, yK);

        if (kingColor == Color.BLACK) {
            rookType = PieceType.BLACK_ROOK;
            if (blackInCheck()) { // 1
                return false;
            }
        } else {
            rookType = PieceType.WHITE_ROOK;
            if (whiteInCheck()) { // 1
                return false;
            }

        }

        if (king.getMoves() != 0) { // 2
           return false;
        }

        // find locations of rooks
        ArrayList<Point2D> rooks = new ArrayList<>();
        for (Point2D p : pieceLocations()) {
            if (pieceHasCoordinate((int) p.getX(), (int) p.getY(), xK, yK) && // 3
            pieceOf(p).getPieceType() == rookType) {
                rooks.add(new Point2D((int) p.getX(), (int) p.getY()));
            }
        }
        if (rooks.size() == 0) {
            return false;
        }

        for (Point2D p : rooks) {
            if (pieceOf(p).getMoves() != 0) { // 4
                return false;
            }

            ArrayList<Point2D> h = getHorizontalCoordinates((int) p.getX(), (int) p.getY(),
                    MOVES);
            for (Point2D point : h) {
                if (beingAttacked((int) point.getX(), (int) point.getY(), kingColor)) { // 5
                    return false;
                }
            }
        }
       return true;
    }

    /**
     * Returns true if the pawn at the current position can perform an
     * En Passant. Returns false otherwise.
     *
     * En Passant requires the following:
     * 1. the enemy pawn has moved two spaces for it's initial move
     * 2. the enemy pawn is beside the given pawn on either side
     *
     * DISCLAIMER: IT IS NOT THIS CLASS'S RESPONSIBILITY TO MOVE AND TAKE THE
     * PAWNS TO THEIR FINAL EN PASSANT LOCATIONS.
     */
    public boolean liableForEnPassant(int xP, int yP, int xEP, int yEP) {
        Piece ePawn = pieceOf(xEP, yEP);
        Color pawnColor = pieceColor(xP, yP);
        Color ePawnColor = pieceColor(xEP, yEP);

        if (ePawn.getSpecialMoves() != 1) { // 1
            return false;
        }

        if (xP - xEP != 1 && xEP - xP != 1) { // 2
            return false;
        }

        return pawnColor != ePawnColor; // color; 2.5
    }

    /**
     * Returns an ArrayList<Point2D> for pieces that are considered black.
     */
    public ArrayList<Point2D> blackPieceLocations() {
        ArrayList<Point2D> r = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == null) {
                    continue;
                }
                if (board[i][j].getPieceType().color() == Color.BLACK) {
                    r.add(new Point2D(j, i));
                }
            }
        }
        return r;
    }

    /**
     * Returns an ArrayList<Point2D> for pieces that are considered white.
     */
    public ArrayList<Point2D> whitePieceLocations() {
        ArrayList<Point2D> r = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == null) {
                    continue;
                }
                if (board[i][j].getPieceType().color() == Color.WHITE) {
                    r.add(new Point2D(j, i));
                }
            }
        }
        return r;
    }

    /**
     * Returns an ArrayList<Piece> for pieces that are considered black.
     */
    public ArrayList<Piece> blackPieceTypes() {
        ArrayList<Piece> r = new ArrayList<>();
        for (Piece[] arr : board) {
            for (Piece p : arr) {
                if (p.getPieceType().color() == Color.BLACK) {
                    r.add(p);
                }
            }
        }
        return r;
    }

    /**
     * Returns an ArrayList<Piece> for pieces that are considered white.
     */
    public ArrayList<Piece> whitePieceTypes() {
        ArrayList<Piece> r = new ArrayList<>();
        for (Piece[] arr : board) {
            for (Piece p : arr) {
                if (p.getPieceType().color() == Color.BLACK) {
                    r.add(p);
                }
            }
        }
        return r;
    }

    /**
     * Returns the current turn if followTurns is enabled.
     * Otherwise, then this method returns the static value
     * NOT_FOLLOWING_TURNS.
     */
    public int getCurrentTurn() {
        if (!followTurns) {
            return NOT_FOLLOWING_TURNS;
        }
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     * Returns an ArrayList<Point2D> for pieces that are considered white kings.
     */
    private ArrayList<Point2D> whiteKingLocations() {
        ArrayList<Point2D> kings = new ArrayList<>();
        for (Point2D p : whitePieceLocations()) {
            int y = (int) p.getY();
            int x = (int) p.getX();
            if (board[y][x].getPieceType() == PieceType.WHITE_KING) {
                kings.add(new Point2D(x, y));
            }
        }
        return kings;
    }

    /**
     * Returns an ArrayList<Point2D> for pieces that are considered black kings.
     */
    private ArrayList<Point2D> blackKingLocations() {
        ArrayList<Point2D> kings = new ArrayList<>();
        for (Point2D p : blackPieceLocations()) {
            int y = (int) p.getY();
            int x = (int) p.getX();
            if (board[y][x].getPieceType() == PieceType.WHITE_KING) {
                kings.add(new Point2D(x, y));
            }
        }
        return kings;
    }

    public static void main(String[] args) {
        ChessGame c = new ChessGame();
        System.out.println(c.pieceColor(3, 3));
    }
}

