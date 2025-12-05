package XXL.Chess.GameObject.chessPieces;

import processing.core.PImage;
import XXL.Chess.physics.Vector2D;
import XXL.Chess.movement.Movement;
import XXL.Chess.movement.CamelMovement;
import XXL.Chess.GameObject.Tile;

import java.util.HashMap;

/**
 * Class representing a Camel chess piece.
 * Inherits properties and methods from ChessPiece.
 */
public class Camel extends ChessPiece {

    /**
     * Constructor for the Camel class.
     * @param coordinate Initial coordinate of the Camel
     * @param isWhite True if the Camel is white, false if black
     * @param image Image representing the Camel
     */
    public Camel(Vector2D coordinate, boolean isWhite, PImage image) {
        super(coordinate, isWhite, image);
    }

    /**
     * Generates possible moves for the Camel on the chessboard.
     * @param chessboard 2D array representing the chessboard tiles
     * @param coordinate Current coordinate of the Camel
     * @return HashMap containing possible moves mapped to their respective information
     */
    @Override
    public HashMap<Vector2D, int[]> makePossibleMoves(Tile[][] chessboard, Vector2D coordinate) {
        HashMap<Vector2D, int[]> moves = new HashMap<>();
        try {
            // Calculate possible moves using CamelMovement strategy
            Movement movement = new CamelMovement(chessboard, coordinate);
            moves = movement.getPossibleMoves(coordinate);
        } catch(Exception e) {
            // Handle any exceptions that might occur during movement calculation
            // Currently catching all exceptions; adjust as per specific needs
        }
        return moves;
    }

}
