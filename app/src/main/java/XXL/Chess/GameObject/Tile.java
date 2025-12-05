package XXL.Chess.GameObject;

import processing.core.PApplet;

import XXL.Chess.interfaces.TileInterface;
import XXL.Chess.interfaces.GameObject;
import XXL.Chess.physics.Vector2D;
import XXL.Chess.GameObject.chessPieces.ChessPiece;

public class Tile implements TileInterface, GameObject {
    private ChessPiece chessPiece; // The chess piece on this tile
    private Vector2D coordinate; // The coordinates of this tile on the board
    private float x; // The x-coordinate for drawing this tile on the screen
    private float y; // The y-coordinate for drawing this tile on the screen
    private boolean isWhite; // Flag indicating if this tile is white or black
    private int[] tileColor; // Color of the tile
    private int[] originalTileColor; // Original color of the tile
    private int cellSize; // Size of the tile (width and height)

    /**
     * Constructor for creating a Tile object.
     *
     * @param coordinate The coordinates of the tile on the board
     * @param isWhite    Flag indicating if the tile is white
     * @param cellSize   Size of the tile (width and height)
     */
    public Tile(Vector2D coordinate, boolean isWhite, int cellSize) {
        this.coordinate = coordinate;
        this.isWhite = isWhite;
        setColor(isWhite);
        this.cellSize = cellSize;
        this.x = coordinate.getX() * cellSize;
        this.y = coordinate.getY() * cellSize;
    }

    /**
     * Sets the chess piece on this tile.
     *
     * @param piece The chess piece to set on this tile
     */
    public void setChesspiece(ChessPiece piece) {
        this.chessPiece = piece;
    }

    /**
     * Gets the chess piece on this tile.
     *
     * @return The chess piece on this tile
     */
    public ChessPiece getChesspiece() {
        return this.chessPiece;
    }

    /**
     * Gets the coordinates of this tile on the board.
     *
     * @return The coordinates of this tile
     */
    public Vector2D getCoordinate() {
        return this.coordinate;
    }

    /**
     * Checks if this tile is white.
     *
     * @return true if the tile is white, false otherwise
     */
    public boolean isWhite() {
        return isWhite;
    }

    /**
     * Gets the size of the tile (width and height).
     *
     * @return The size of the tile
     */
    public int getCellSize() {
        return cellSize;
    }

    /**
     * Sets the size of the tile (width and height).
     *
     * @param cellSize The size of the tile
     */
    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    /**
     * Gets the color of the tile.
     *
     * @return The color of the tile
     */
    public int[] getTileColor() {
        return tileColor;
    }

    /**
     * Resets the tile color to its original color.
     */
    public void resetTileColor() {
        this.tileColor = this.originalTileColor;
    }

    /**
     * Sets the color of the tile.
     *
     * @param tileColor The color to set for the tile
     */
    public void setTileColor(int[] tileColor) {
        this.tileColor = tileColor;
    }

    /**
     * Sets the color of the tile based on whether it's white or black.
     *
     * @param isWhite Flag indicating if the tile is white
     */
    private void setColor(boolean isWhite) {
        this.isWhite = isWhite;
        // Update tileColor based on isWhite
        if (isWhite) {
            this.tileColor = GameObject.whiteRGB;
            this.originalTileColor = GameObject.whiteRGB;
        } else {
            this.tileColor = GameObject.blackRGB;
            this.originalTileColor = GameObject.blackRGB;
        }
    }

    // Implementing methods from GameObject interface

    /**
     * Draws the tile on the screen using Processing's PApplet.
     *
     * @param app The main PApplet instance
     */
    @Override
    public void draw(PApplet app) {
        app.stroke(0, 255);
        app.fill(this.tileColor[0], this.tileColor[1], this.tileColor[2]);
        app.rect(this.x, this.y, this.cellSize, this.cellSize);
    }

    /**
     * Checks if two tiles have the same coordinates.
     *
     * @param tile The tile to compare with
     * @return true if the tiles have the same coordinates, false otherwise
     */
    public boolean tilesMatch(Tile tile) {
        return this.coordinate.getX() == tile.getCoordinate().getX() &&
               this.coordinate.getY() == tile.getCoordinate().getY();
    }
}
