package XXL.Chess.movement;

import XXL.Chess.movement.Movement;
import XXL.Chess.physics.Vector2D;
import XXL.Chess.GameObject.Tile;
import XXL.Chess.setup.Constants;

import java.util.HashMap;

public class RookMovement extends Movement {

    /**
     * Constructor for RookMovement.
     * 
     * @param chessboard The 2D array of Tile objects representing the chessboard
     * @param coordinate The current coordinate of the rook
     */
    public RookMovement(Tile[][] chessboard, Vector2D coordinate) {
        super(chessboard, coordinate);
    }

    /**
     * Calculates possible moves for the rook at the given position.
     * 
     * @param vector The current position of the rook
     * @return HashMap where keys are possible move positions (Vector2D) and values are RGB color representations (int[])
     */
    @Override
    public HashMap<Vector2D, int[]> getPossibleMoves(Vector2D vector) {
        HashMap<Vector2D, int[]> moves = new HashMap<>();
        int x = (int) vector.getX();
        int y = (int) vector.getY();
        boolean playerTurn = getChessBoard()[y][x].getChesspiece().isWhite();

        // Possible directions a Rook can move (orthogonal directions)
        int[] directionX = { 1, -1, 0, 0 };
        int[] directionY = { 0, 0, 1, -1 };

        // Check each direction
        for (int i = 0; i < directionX.length; i++) {
            int newX = x;
            int newY = y;

            // Move in the current direction until we encounter a piece or reach the edge of the board
            while (true) {
                newX += directionX[i];
                newY += directionY[i];

                // Check if the new position is within board bounds
                if (!Constants.isWithinRange(newX, newY)) {
                    break; // Break if we go out of bounds
                }

                Tile targetTile = getChessBoard()[newY][newX];

                if (targetTile.getChesspiece() == null) {
                    moves.put(new Vector2D(newX, newY), Constants.blueRGB);
                } else {
                    // If there's a piece, check if it's opponent's piece
                    if (targetTile.getChesspiece().isWhite() != playerTurn) {
                        moves.put(new Vector2D(newX, newY), Constants.lightRedRGB);
                    }
                    break; // Stop further movement in this direction
                }
            }
        }

        return moves;
    }
}
