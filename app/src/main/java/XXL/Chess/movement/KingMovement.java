package XXL.Chess.movement;

import XXL.Chess.movement.Movement;
import XXL.Chess.physics.Vector2D;
import XXL.Chess.GameObject.Tile;
import XXL.Chess.setup.Constants;

import java.util.HashMap;

/**
 * Class implementing movement logic for a King chess piece.
 */
public class KingMovement extends Movement {

    /**
     * Constructor for KingMovement.
     * 
     * @param chessboard The 2D array of Tile objects representing the chessboard
     * @param coordinate The current coordinate of the king
     */
    public KingMovement(Tile[][] chessboard, Vector2D coordinate) {
        super(chessboard, coordinate);
    }

    /**
     * Calculates possible moves for the king at the given position.
     * 
     * @param vector The current position of the king
     * @return HashMap where keys are possible move positions (Vector2D) and values are RGB color representations (int[])
     */
    @Override
    public HashMap<Vector2D, int[]> getPossibleMoves(Vector2D vector) {
        HashMap<Vector2D, int[]> moves = new HashMap<>();
        int x = (int) vector.getX();
        int y = (int) vector.getY();
        boolean playerTurn = getChessBoard()[y][x].getChesspiece().isWhite();

        // Possible directions a King can move (including diagonals and straight)
        int[] directionX = { -1, 0, 1, -1, 1, -1, 0, 1 };
        int[] directionY = { -1, -1, -1, 0, 0, 1, 1, 1 };

        // Check each direction
        for (int i = 0; i < directionX.length; i++) {
            int newX = x + directionX[i];
            int newY = y + directionY[i];

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
