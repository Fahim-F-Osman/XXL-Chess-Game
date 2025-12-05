package XXL.Chess.movement;

import XXL.Chess.movement.Movement;
import XXL.Chess.physics.Vector2D;
import XXL.Chess.GameObject.Tile;
import XXL.Chess.setup.Constants;

import java.util.HashMap;

/**
 * Class implementing movement logic for a Camel chess piece.
 */
public class CamelMovement extends Movement {

    /**
     * Constructor for CamelMovement.
     * 
     * @param chessboard The 2D array of Tile objects representing the chessboard
     * @param coordinate The current coordinate of the camel
     */
    public CamelMovement(Tile[][] chessboard, Vector2D coordinate) {
        super(chessboard, coordinate);
    }

    /**
     * Calculates possible moves for the camel at the given position.
     * 
     * @param vector The current position of the camel
     * @return HashMap where keys are possible move positions (Vector2D) and values are RGB color representations (int[])
     */
    @Override
    public HashMap<Vector2D, int[]> getPossibleMoves(Vector2D vector) {
        HashMap<Vector2D, int[]> moves = new HashMap<>();
        int x = (int) vector.getX();
        int y = (int) vector.getY();
        boolean playerTurn = getChessBoard()[y][x].getChesspiece().isWhite();

        // Camel movement pattern offsets
        int[] camelX = { 3, 3, -3, -3, 1, 1, -1, -1 };
        int[] camelY = { 1, -1, 1, -1, 3, -3, 3, -3 };

        // Check each possible move for the camel
        for (int i = 0; i < camelX.length; i++) {
            int newX = x + camelX[i];
            int newY = y + camelY[i];

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
