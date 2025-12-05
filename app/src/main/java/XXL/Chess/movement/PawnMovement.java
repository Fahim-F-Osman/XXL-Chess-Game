package XXL.Chess.movement;

import XXL.Chess.movement.Movement;
import XXL.Chess.physics.Vector2D;
import XXL.Chess.GameObject.Tile;
import XXL.Chess.setup.Constants;
import XXL.Chess.GameObject.chessPieces.ChessPiece;

import java.util.HashMap;
import java.util.Map;

/**
 * Class implementing movement logic for a Pawn chess piece.
 */
public class PawnMovement extends Movement {

    /**
     * Constructor for PawnMovement.
     * 
     * @param chessboard The 2D array of Tile objects representing the chessboard
     * @param coordinate The current coordinate of the pawn
     */
    public PawnMovement(Tile[][] chessboard, Vector2D coordinate) {
        super(chessboard, coordinate);
    }

    /**
     * Calculates possible moves for the pawn at the given position.
     * 
     * @param vector The current position of the pawn
     * @return HashMap where keys are possible move positions (Vector2D) and values are RGB color representations (int[])
     */
    @Override
    public HashMap<Vector2D, int[]> getPossibleMoves(Vector2D vector) {
        HashMap<Vector2D, int[]> moves = new HashMap<>();
        int x = (int) vector.getX();
        int y = (int) vector.getY();
        boolean playerTurn = chessboard[y][x].getChesspiece().isWhite();
        int direction = playerTurn ? 1 : -1;

        // Forward movement
        int forwardY = y + direction;
        if (Constants.isWithinRange(x, forwardY) && chessboard[forwardY][x].getChesspiece() == null) {
            moves.put(new Vector2D(x, forwardY), Constants.blueRGB);

            // Double move from starting position
            int doubleForwardY = y + 2 * direction;
            if ((y == 1 || y == Constants.BOARD_HEIGHT - 2) && chessboard[doubleForwardY][x].getChesspiece() == null) {
                moves.put(new Vector2D(x, doubleForwardY), Constants.blueRGB);
            }
        }

        // Diagonal captures
        int[] offsets = {1, -1};
        for (int offset : offsets) {
            int captureX = x + offset;
            int captureY = y + direction;
            if (Constants.isWithinRange(captureX, captureY) && chessboard[captureY][captureX].getChesspiece() != null) {
                if (chessboard[captureY][captureX].getChesspiece().isWhite() != playerTurn) {
                    moves.put(new Vector2D(captureX, captureY), Constants.lightRedRGB);
                }
            }
        }

        // Promotion check
        for (Map.Entry<Vector2D, int[]> entry : moves.entrySet()) {
            if (entry.getKey().getY() == Constants.BOARD_HEIGHT / 2 && entry.getKey().getX() == this.coordinate.getX()) {
                entry.setValue(Constants.purpleRGB); // Indicates potential promotion move (purple color)
            }
        }

        return moves;
    }
}
