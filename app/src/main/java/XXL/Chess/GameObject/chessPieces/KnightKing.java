package XXL.Chess.GameObject.chessPieces;

import processing.core.PImage;
import XXL.Chess.physics.Vector2D;
import XXL.Chess.GameObject.Tile;
import XXL.Chess.movement.Movement;
import XXL.Chess.movement.KnightMovement;
import XXL.Chess.movement.KingMovement;

import java.util.HashMap;

/**
 * Class representing a combined Knight and King chess piece.
 */
public class KnightKing extends ChessPiece {

    /**
     * Constructor for the KnightKing class.
     * @param coordinate Initial coordinate of the KnightKing
     * @param isWhite True if the KnightKing is white, false if black
     * @param image Image representing the KnightKing
     */
    public KnightKing(Vector2D coordinate, boolean isWhite, PImage image) {
        super(coordinate, isWhite, image);
    }

    /**
     * Generates possible moves for the KnightKing on the chessboard.
     * @param chessboard 2D array representing the chessboard tiles
     * @param coordinate Current coordinate of the KnightKing
     * @return HashMap containing possible moves mapped to their respective information
     */
    @Override
    public HashMap<Vector2D, int[]> makePossibleMoves(Tile[][] chessboard, Vector2D coordinate) {
        HashMap<Vector2D, int[]> moves = new HashMap<>();
        try {
            // Calculate possible moves using KnightMovement strategy
            Movement movement = new KnightMovement(chessboard, coordinate);
            moves.putAll(movement.getPossibleMoves(coordinate));
            
            // Calculate possible moves using KingMovement strategy
            movement = new KingMovement(chessboard, coordinate);
            moves.putAll(movement.getPossibleMoves(coordinate));
        } catch(Exception e) {
            // Handle any exceptions that might occur during movement calculation
            // Currently catching all exceptions; adjust as per specific needs
        }
        return moves;
    }

}
