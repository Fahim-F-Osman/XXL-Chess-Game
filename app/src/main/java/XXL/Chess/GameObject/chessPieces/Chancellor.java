package XXL.Chess.GameObject.chessPieces;

import processing.core.PImage;
import XXL.Chess.physics.Vector2D;
import XXL.Chess.GameObject.Tile;
import XXL.Chess.movement.Movement;
import XXL.Chess.movement.KnightMovement;
import XXL.Chess.movement.RookMovement;

import java.util.HashMap;

/**
 * Class representing a Chancellor chess piece.
 * Inherits properties and methods from ChessPiece.
 */
public class Chancellor extends ChessPiece {

    /**
     * Constructor for the Chancellor class.
     * @param coordinate Initial coordinate of the Chancellor
     * @param isWhite True if the Chancellor is white, false if black
     * @param image Image representing the Chancellor
     */
    public Chancellor(Vector2D coordinate, boolean isWhite, PImage image) {
        super(coordinate, isWhite, image);
    }

    /**
     * Generates possible moves for the Chancellor on the chessboard.
     * @param chessboard 2D array representing the chessboard tiles
     * @param coordinate Current coordinate of the Chancellor
     * @return HashMap containing possible moves mapped to their respective information
     */
    @Override
    public HashMap<Vector2D, int[]> makePossibleMoves(Tile[][] chessboard, Vector2D coordinate) {
        HashMap<Vector2D, int[]> moves = new HashMap<>();
        try {
            // Calculate possible moves using KnightMovement strategy
            Movement movement = new KnightMovement(chessboard, coordinate);
            moves.putAll(movement.getPossibleMoves(coordinate));
            
            // Calculate possible moves using RookMovement strategy
            movement = new RookMovement(chessboard, coordinate);
            moves.putAll(movement.getPossibleMoves(coordinate));
        } catch(Exception e) {
            // Handle any exceptions that might occur during movement calculation
            // Currently catching all exceptions; adjust as per specific needs
        }
        return moves;
    }

}
