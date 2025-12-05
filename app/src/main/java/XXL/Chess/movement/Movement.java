package XXL.Chess.movement;

import XXL.Chess.physics.Vector2D;
import XXL.Chess.GameObject.Tile;
import XXL.Chess.GameObject.chessPieces.ChessPiece;

import java.util.HashMap;

/**
 * Abstract class representing movement logic for chess pieces.
 */
public abstract class Movement {

    protected Tile[][] chessboard; // 2D array representing the chessboard
    protected Vector2D coordinate; // Current coordinate for movement logic
    protected HashMap<Boolean, ChessPiece> kingCoordinate = new HashMap<>(); // HashMap to store king coordinates

    /**
     * Constructor for Movement class.
     * 
     * @param chessboard The 2D array of Tile objects representing the chessboard
     * @param coordinate The current coordinate for movement logic
     */
    public Movement(Tile[][] chessboard, Vector2D coordinate) {
        this.chessboard = chessboard;
        this.coordinate = coordinate;
        this.kingCoordinate = getKing(); // Initialize king coordinates
    }

    /**
     * Getter method to retrieve the chessboard.
     * 
     * @return The 2D array of Tile objects representing the chessboard
     */
    public Tile[][] getChessBoard() {
        return this.chessboard;
    }

    /**
     * Getter method to retrieve the current coordinate for movement logic.
     * 
     * @return The current coordinate as a Vector2D object
     */
    public Vector2D getCoordinate() {
        return this.coordinate;
    }

    /**
     * Getter method to retrieve the HashMap containing king coordinates.
     * 
     * @return HashMap where key is Boolean (true for white, false for black) and value is ChessPiece object
     */
    public HashMap<Boolean, ChessPiece> getKingCoordinate() {
        return this.kingCoordinate;
    }

    /**
     * Abstract method to be implemented by subclasses to calculate possible moves for a chess piece.
     * 
     * @param vector The vector representing the current position of the chess piece
     * @return HashMap where key is Vector2D representing possible move and value is int array representing RGB color
     */
    public abstract HashMap<Vector2D, int[]> getPossibleMoves(Vector2D vector);

    /**
     * Method to find and return the coordinates of the kings on the chessboard.
     * 
     * @return HashMap where key is Boolean (true for white, false for black) and value is ChessPiece object representing the king
     */
    protected HashMap<Boolean, ChessPiece> getKing() {
        HashMap<Boolean, ChessPiece> kingCoordinate = new HashMap<>();
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                ChessPiece c = chessboard[i][j].getChesspiece();
                if (c != null && c.getClass().getSimpleName().equals("King")) {
                    kingCoordinate.put(c.isWhite(), c); // Store king with its color (true for white, false for black)
                }
            }
        }
        return kingCoordinate;
    }   
}
