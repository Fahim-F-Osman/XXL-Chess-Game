package XXL.Chess.movement;

import XXL.Chess.physics.Vector2D;
import XXL.Chess.GameObject.Tile;
import XXL.Chess.setup.Constants;

import java.util.HashMap;

/**
 * Class implementing movement logic for a Bishop chess piece.
 */
public class BishopMovement extends Movement {

    /**
     * Constructor for BishopMovement.
     * 
     * @param chessboard The 2D array of Tile objects representing the chessboard
     * @param coordinate The current coordinate of the bishop
     */
    public BishopMovement(Tile[][] chessboard, Vector2D coordinate) {
        super(chessboard, coordinate);
    }

    /**
     * Calculates possible moves for the bishop at the given position.
     * 
     * @param vector The current position of the bishop
     * @return HashMap where keys are possible move positions (Vector2D) and values are RGB color representations (int[])
     */
    @Override
    public HashMap<Vector2D, int[]> getPossibleMoves(Vector2D vector) {
        HashMap<Vector2D, int[]> moves = new HashMap<>();
        int x = (int) vector.getX();
        int y = (int) vector.getY();
        boolean playerTurn = getChessBoard()[y][x].getChesspiece().isWhite();

        // Bishop movement: diagonals (4 possible directions)
        int[] directionX = { 1, 1, -1, -1 };
        int[] directionY = { 1, -1, 1, -1 };

        // Check each diagonal direction
        for (int i = 0; i < directionX.length; i++) {
            int newX = x;
            int newY = y;

            // Move along the diagonal until we hit the board edge or another piece
            while (true) {
                newX += directionX[i];
                newY += directionY[i];

                // Check if the new position is within board bounds
                if (!Constants.isWithinRange(newX, newY)) {
                    break; // Break if we go out of bounds
                }

                Tile targetTile = getChessBoard()[newY][newX];

                if (targetTile.getChesspiece() == null) {
                    moves.put(new Vector2D(newX, newY), Constants.blueRGB); // Empty tile, move is possible (blue color)
                } else {
                    // If there's a piece, check if it's opponent's piece
                    if (targetTile.getChesspiece().isWhite() != playerTurn) {
                        moves.put(new Vector2D(newX, newY), Constants.lightRedRGB); // Opponent's piece, move is possible (light red color)
                    }
                    break; // Stop further movement in this direction after capturing or encountering a piece
                }
            }
        }

        return moves;
    }
}
