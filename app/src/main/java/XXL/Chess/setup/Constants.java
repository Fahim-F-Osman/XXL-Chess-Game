package XXL.Chess.setup;

/**
 * Constants and utility methods for a chess game setup.
 */
public class Constants {

    // Size constants
    public static final int SPRITESIZE = 480; // Size of sprites
    public static final int CELLSIZE = 48;   // Size of each cell on the chess board
    public static final int SIDEBAR = 120;   // Width of the sidebar
    public static final int BOARD_WIDTH = 14; // Width of the chess board (number of cells)
    public static final int BOARD_HEIGHT = 14; // Height of the chess board (number of cells)

    // Derived size constants
    public static int WIDTH = CELLSIZE * BOARD_WIDTH + SIDEBAR; // Total width of the game window
    public static int HEIGHT = BOARD_HEIGHT * CELLSIZE;         // Total height of the game window

    // Game configuration
    public static final int FPS = 60;          // Frames per second
    public static final String configPath = "config.json"; // Path to the configuration file

    // RGB color definitions
    public static final int[] whiteRGB = {236, 218, 185};
    public static final int[] blackRGB = {174, 138, 104};
    public static final int[] greenRGB = {112, 137, 83};
    public static final int[] blueRGB = {201, 223, 231};
    public static final int[] lightRedRGB = {242, 168, 113};
    public static final int[] redRGB = {197, 41, 28};
    public static final int[] lightYellowRGB = {206, 210, 120};
    public static final int[] purpleRGB = {75, 0, 130};

    /**
     * Checks if the given coordinates (x, y) are within the bounds of the chess board.
     * 
     * @param x The x-coordinate to check
     * @param y The y-coordinate to check
     * @return true if (x, y) is within the board boundaries, false otherwise
     */
    public static boolean isWithinRange(int x, int y) {
        return x >= 0 && y >= 0 && x < Constants.BOARD_WIDTH && y < Constants.BOARD_HEIGHT;
    }

    /**
     * Checks if the given floating point coordinates (x, y) are within the bounds of the chess board.
     * 
     * @param x The x-coordinate to check
     * @param y The y-coordinate to check
     * @return true if (x, y) is within the board boundaries, false otherwise
     */
    public static boolean isWithinRange(float x, float y) {
        return x >= 0 && y >= 0 && x < Constants.BOARD_WIDTH && y < Constants.BOARD_HEIGHT;
    }

}
