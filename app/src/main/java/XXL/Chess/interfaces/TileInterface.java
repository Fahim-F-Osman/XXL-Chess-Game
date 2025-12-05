package XXL.Chess.interfaces;

/**
 * Interface for managing properties of a tile in a chess game.
 */
public interface TileInterface {

    /**
     * Gets the size of the tile cell.
     * 
     * @return The size of the tile cell
     */
    int getCellSize();

    /**
     * Sets the size of the tile cell.
     * 
     * @param cellSize The size of the tile cell to set
     */
    void setCellSize(int cellSize);

    /**
     * Gets the color of the tile.
     * 
     * @return An array representing the RGB color of the tile
     */
    int[] getTileColor();

    /**
     * Sets the color of the tile.
     * 
     * @param tileColor An array representing the RGB color to set for the tile
     */
    void setTileColor(int[] tileColor);
}
