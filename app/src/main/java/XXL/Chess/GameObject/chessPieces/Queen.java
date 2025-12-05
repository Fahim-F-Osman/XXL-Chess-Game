package XXL.Chess.GameObject.chessPieces;

import processing.core.PImage;
import XXL.Chess.physics.Vector2D;
import XXL.Chess.GameObject.Tile;
import XXL.Chess.movement.Movement;
import XXL.Chess.movement.BishopMovement;
import XXL.Chess.movement.RookMovement;

import java.util.HashMap;

/**
 * Class representing a Queen chess piece.
 */
public class Queen extends ChessPiece {

    /**
     * Constructor for the Queen class.
     * @param coordinate Initial coordinate of the Queen
     * @param isWhite True if the Queen is white, false if black
     * @param image Image representing the Queen
     */
    public Queen(Vector2D coordinate, boolean isWhite, PImage image) {
        super(coordinate, isWhite, image);
    }

    /**
     * Generates possible moves for the Queen on the chessboard.
     * @param chessboard 2D array representing the chessboard tiles
     * @param coordinate Current coordinate of the Queen
     * @return HashMap containing possible moves mapped to their respective information
     */
    @Override
    public HashMap<Vector2D, int[]> makePossibleMoves(Tile[][] chessboard, Vector2D coordinate) {
        HashMap<Vector2D, int[]> moves = new HashMap<>();
        try {
            // Calculate possible moves using both BishopMovement and RookMovement strategies
            Movement movement = new BishopMovement(chessboard, coordinate);
            moves.putAll(movement.getPossibleMoves(coordinate));
            
            movement = new RookMovement(chessboard, coordinate);
            moves.putAll(movement.getPossibleMoves(coordinate));
        } catch (Exception e) {
            // Handle any exceptions that might occur during movement calculation
            // Currently catching all exceptions; adjust as per specific needs
        }
        return moves;
    }
}
