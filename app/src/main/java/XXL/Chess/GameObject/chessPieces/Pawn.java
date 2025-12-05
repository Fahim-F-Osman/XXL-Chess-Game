package XXL.Chess.GameObject.chessPieces;

import processing.core.PImage;
import XXL.Chess.physics.Vector2D;
import XXL.Chess.setup.Constants;
import XXL.Chess.GameObject.Tile;
import XXL.Chess.movement.Movement;
import XXL.Chess.movement.PawnMovement;

import java.util.HashMap;

/**
 * Class representing a Pawn chess piece.
 */
public class Pawn extends ChessPiece {

    /**
     * Constructor for the Pawn class.
     * @param coordinate Initial coordinate of the Pawn
     * @param isWhite True if the Pawn is white, false if black
     * @param image Image representing the Pawn
     */
    public Pawn(Vector2D coordinate, boolean isWhite, PImage image) {
        super(coordinate, isWhite, image);
        // Set promotion flag to true for pawn promotion upon reaching the last row
        this.promotion = true;
        // Set promotion coordinate to the middle of the board height
        this.promotionCoordinate = Constants.BOARD_HEIGHT / 2;
    }

    /**
     * Generates possible moves for the Pawn on the chessboard.
     * @param chessboard 2D array representing the chessboard tiles
     * @param coordinate Current coordinate of the Pawn
     * @return HashMap containing possible moves mapped to their respective information
     */
    @Override
    public HashMap<Vector2D, int[]> makePossibleMoves(Tile[][] chessboard, Vector2D coordinate) {
        HashMap<Vector2D, int[]> moves = new HashMap<>();
        try {
            // Calculate possible moves using PawnMovement strategy
            Movement movement = new PawnMovement(chessboard, coordinate);
            moves = movement.getPossibleMoves(coordinate);
        } catch(Exception e) {
            // Handle any exceptions that might occur during movement calculation
            // Currently catching all exceptions; adjust as per specific needs
        }
        return moves;
    }

}
