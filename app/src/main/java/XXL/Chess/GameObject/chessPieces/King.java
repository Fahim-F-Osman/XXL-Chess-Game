package XXL.Chess.GameObject.chessPieces;

import processing.core.PImage;
import XXL.Chess.physics.Vector2D;
import XXL.Chess.GameObject.Tile;
import XXL.Chess.movement.Movement;
import XXL.Chess.movement.KingMovement;

import java.util.HashMap;

/**
 * Class representing a King chess piece.
 */
public class King extends ChessPiece {

    /**
     * Constructor for the King class.
     * @param coordinate Initial coordinate of the King
     * @param isWhite True if the King is white, false if black
     * @param image Image representing the King
     */
    public King(Vector2D coordinate, boolean isWhite, PImage image) {
        super(coordinate, isWhite, image);
    }

    /**
     * Generates possible moves for the King on the chessboard.
     * @param chessboard 2D array representing the chessboard tiles
     * @param coordinate Current coordinate of the King
     * @return HashMap containing possible moves mapped to their respective information
     */
    @Override
    public HashMap<Vector2D, int[]> makePossibleMoves(Tile[][] chessboard, Vector2D coordinate) {
        HashMap<Vector2D, int[]> moves = new HashMap<>();
        try {
            // Calculate possible moves using KingMovement strategy
            Movement movement = new KingMovement(chessboard, coordinate);
            moves = movement.getPossibleMoves(coordinate);
        } catch(Exception e) {
            // Handle any exceptions that might occur during movement calculation
            // Currently catching all exceptions; adjust as per specific needs
        }
        return moves;
    }

}
