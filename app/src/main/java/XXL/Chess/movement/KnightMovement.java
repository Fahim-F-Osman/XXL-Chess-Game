package XXL.Chess.movement;

import XXL.Chess.movement.Movement;
import XXL.Chess.physics.Vector2D;
import XXL.Chess.GameObject.Tile;
import XXL.Chess.setup.Constants;

import java.util.HashMap;

/**
 * Class implementing movement logic for a Knight chess piece.
 */
public class KnightMovement extends Movement {

    /**
     * Constructor for KnightMovement.
     * 
     * @param chessboard The 2D array of Tile objects representing the chessboard
     * @param coordinate The current coordinate of the knight
     */
    public KnightMovement(Tile[][] chessboard, Vector2D coordinate) {
        super(chessboard, coordinate);
    }

    /**
     * Calculates possible moves for the knight at the given position.
     * 
     * @param vector The current position of the knight
     * @return HashMap where keys are possible move positions (Vector2D) and values are RGB color representations (int[])
     */
    @Override
    public HashMap<Vector2D, int[]> getPossibleMoves(Vector2D vector) {
        HashMap<Vector2D, int[]> moves = new HashMap<>();
        int x = (int) vector.getX();
        int y = (int) vector.getY();
        boolean playerTurn = getChessBoard()[y][x].getChesspiece().isWhite();

        // Possible knight moves relative to its position
        int[] knightX = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int[] knightY = { 1, 2, 2, 1, -1, -2, -2, -1 };

        // Check each possible knight move
        for (int i = 0; i < knightX.length; i++) {
            int newX = x + knightX[i];
            int newY = y + knightY[i];

            // Check if the new position is within board bounds
            if (Constants.isWithinRange(newX, newY)) {
                Tile targetTile = getChessBoard()[newY][newX];

                // Check if the target tile is either empty or contains an opponent's piece
                if (targetTile.getChesspiece() == null) {
                    moves.put(new Vector2D(newX, newY), Constants.blueRGB); // Empty tile, move is possible (blue color)
                } else if (targetTile.getChesspiece().isWhite() != playerTurn) {
                    moves.put(new Vector2D(newX, newY), Constants.lightRedRGB); // Opponent's piece, move is possible (light red color)
                }
            }
        }

        return moves;
    }
}
