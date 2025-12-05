package XXL.Chess.GameObject.chessPieces;

import processing.core.PApplet;
import processing.core.PImage;

import XXL.Chess.interfaces.GameObject;
import XXL.Chess.setup.Constants;
import XXL.Chess.physics.Vector2D;

import XXL.Chess.GameObject.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class representing a chess piece.
 */
public abstract class ChessPiece implements GameObject {
    protected Vector2D coordinate; // Current coordinate of the chess piece
    protected boolean isWhite; // Color of the chess piece (true for white, false for black)
    protected PImage image; // Image representing the chess piece
    protected float speedX; // Speed of movement along the X-axis
    protected float speedY; // Speed of movement along the Y-axis
    protected ArrayList<Vector2D> possibleMoves; // List of possible moves for the chess piece
    protected boolean promotion = false; // Flag indicating if the piece is eligible for promotion
    protected int promotionCoordinate = -1; // Y-coordinate for promotion (typically for pawns)

    /**
     * Constructor for a chess piece.
     * @param coordinate Initial coordinate of the piece
     * @param isWhite True if the piece is white, false if black
     * @param image Image representing the piece
     */
    public ChessPiece(Vector2D coordinate, boolean isWhite, PImage image) {
        this.coordinate = coordinate;
        this.isWhite = isWhite;
        this.image = image;
    }

    /**
     * Gets the current coordinate of the chess piece.
     * @return Current coordinate
     */
    public Vector2D getCoordinate() {
        return this.coordinate;
    }

    /**
     * Sets the coordinate of the chess piece.
     * @param vec New coordinate to set
     */
    public void setCoordinate(Vector2D vec) {
        this.coordinate = vec;
    }

    /**
     * Checks if the chess piece is eligible for promotion.
     * @return True if eligible for promotion, false otherwise
     */
    public boolean isEligibleForPromotion() {
        return this.promotion;
    }

    /**
     * Gets the promotion coordinate (typically Y-coordinate for promotion).
     * @return Y-coordinate for promotion
     */
    public int getPromotionCoordinateY() {
        return this.promotionCoordinate;
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public void draw(PApplet app) {
        // Draws the chess piece image at its current coordinate
        app.image(this.image, coordinate.getX() * Constants.CELLSIZE, coordinate.getY() * Constants.CELLSIZE, Constants.CELLSIZE, Constants.CELLSIZE);
    }
    
    /**
     * Generates possible moves for the chess piece.
     * @param chessboard 2D array representing the chessboard tiles
     * @param coordinate Current coordinate of the chess piece
     * @return HashMap containing possible moves mapped to their respective information
     */
    public HashMap<Vector2D, int[]> makePossibleMoves(Tile[][] chessboard, Vector2D coordinate) {
        return null; // Placeholder method to be implemented in subclasses
    }

    /**
     * Moves the chess piece to a new coordinate with animation.
     * @param endCoordinate Target coordinate to move to
     * @param startCoordinate Starting coordinate of the piece
     * @param maxMoveTime Maximum time allowed for the move
     * @param minMoveSpeed Minimum speed required for the move
     */
    public void move(Vector2D endCoordinate, Vector2D startCoordinate, float maxMoveTime, float minMoveSpeed) {
        // Calculates and updates the speed along the X-axis and Y-axis
        calculateSpeedX(endCoordinate, startCoordinate, maxMoveTime, minMoveSpeed);    
        calculateSpeedY(endCoordinate, startCoordinate, maxMoveTime, minMoveSpeed);    
        
        // Moves the piece according to the calculated speeds
        this.coordinate = new Vector2D(this.coordinate.getX() + (this.speedX / Constants.CELLSIZE), this.coordinate.getY() + (this.speedY / Constants.CELLSIZE));
        
        // Ensures the piece reaches exactly the end coordinate
        if (calculateDistance(this.coordinate, startCoordinate) > calculateDistance(endCoordinate, startCoordinate)) {
            this.coordinate = endCoordinate;
        }
    }

    /**
     * Calculates the distance between two coordinates.
     * @param targetTile Target coordinate
     * @param currentTile Current coordinate
     * @return Distance between the two coordinates
     */
    public float calculateDistance(Vector2D targetTile, Vector2D currentTile) {
        float deltaX = targetTile.getX() - currentTile.getX();
        float deltaY = targetTile.getY() - currentTile.getY();
        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return distance;
    } 

    /**
     * Calculates the speed along the X-axis for movement.
     * @param targetTile Target coordinate
     * @param currentTile Current coordinate
     * @param maxMoveTime Maximum time allowed for the move
     * @param minMoveSpeed Minimum speed required for the move
     */
    public void calculateSpeedX(Vector2D targetTile, Vector2D currentTile, float maxMoveTime, float minMoveSpeed) {
        float deltaX = targetTile.getX() - currentTile.getX();
        float deltaY = targetTile.getY() - currentTile.getY();
        int sign = (deltaX > 0 ? 1 : -1 );
        float distance = calculateDistance(targetTile, currentTile);
        this.speedX = minMoveSpeed * sign;
        
        // Adjust speed if the move time exceeds the maximum allowed
        if (distance / minMoveSpeed > maxMoveTime) {
            this.speedX = (distance / maxMoveTime) * sign;
        }
        
        // Ensure no movement if deltaX is zero
        if (deltaX == 0) {
            this.speedX = 0;
        }
        
        // Adjust speed based on diagonal movement
        if (Math.abs(deltaX) != Math.abs(deltaY) && (deltaX != 0 && deltaY != 0)) {
            float tempSpeedX = Math.abs(deltaX / maxMoveTime);
            float tempSpeedY = Math.abs(deltaY / maxMoveTime);
            
            // Ensure minimum speed is maintained
            if (Math.abs(tempSpeedX) < minMoveSpeed) {
                this.speedX = (tempSpeedX / tempSpeedY) * minMoveSpeed * sign;
                this.speedX = this.speedX / maxMoveTime;
            }
        }
    }

    /**
     * Calculates the speed along the Y-axis for movement.
     * @param targetTile Target coordinate
     * @param currentTile Current coordinate
     * @param maxMoveTime Maximum time allowed for the move
     * @param minMoveSpeed Minimum speed required for the move
     */
    public void calculateSpeedY(Vector2D targetTile, Vector2D currentTile, float maxMoveTime, float minMoveSpeed) {
        float deltaX = targetTile.getX() - currentTile.getX();
        float deltaY = targetTile.getY() - currentTile.getY();
        int sign = (deltaY > 0 ? 1 : -1 );
        float distance = calculateDistance(targetTile, currentTile);
        this.speedY = minMoveSpeed * sign;
        
        // Adjust speed if the move time exceeds the maximum allowed
        if (distance / minMoveSpeed > maxMoveTime) {
            this.speedY = (distance / maxMoveTime) * sign;
        }
        
        // Ensure no movement if deltaY is zero
        if (deltaY == 0) {
            this.speedY = 0;
        }
        
        // Adjust speed based on diagonal movement
        if (Math.abs(deltaX) != Math.abs(deltaY) && (deltaX != 0 && deltaY != 0)) {
            float tempSpeedX = Math.abs(deltaX / maxMoveTime);
            float tempSpeedY = Math.abs(deltaY / maxMoveTime);
            
            // Ensure minimum speed is maintained
            if (Math.abs(tempSpeedY) < minMoveSpeed) {
                this.speedY = (tempSpeedY / tempSpeedX) * minMoveSpeed * sign;
                this.speedY = this.speedY / maxMoveTime;
            }
        }
    }
}
