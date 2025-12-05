package XXL.Chess;

import processing.core.PApplet;

import XXL.Chess.setup.Constants;
import XXL.Chess.setup.Configuration;

import XXL.Chess.GameObject.Tile;
import XXL.Chess.physics.Vector2D;
import XXL.Chess.GameObject.Player;
import XXL.Chess.GameObject.Chessboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages game objects and logic for a chess game.
 */
public class GameObjectManager {
    private PApplet parent; // Reference to the main PApplet instance
    private Configuration config = new Configuration(); // Configuration for the game
    private Chessboard chessboard; // The chessboard object for the game

    // Mouse interaction variables
    public boolean mousePressed = false;
    public int[] mousePressedCoordinate = new int[] {-1,-1};

    // Game state flags
    private boolean playerTurn = config.getPlayerTurn(); // Current player's turn
    private Player player1 = config.getPlayer(); // Player 1
    private Player player2 = config.getOpponent(); // Player 2

    private boolean stop = false; // Flag to stop game
    private boolean checkmate = false; // Flag for checkmate condition
    private boolean stalemate = false; // Flag for stalemate condition

    /**
     * Constructor for GameObjectManager.
     * @param parent The main PApplet instance
     */
    public GameObjectManager(PApplet parent) {
        this.parent = parent;
        this.chessboard = new Chessboard(parent, config.getLayout());
    }

    /**
     * Draws the sidebar displaying game status.
     */
    public void drawSideBar() {
        // Background for sidebar
        this.parent.fill(180);
        this.parent.noStroke();
        this.parent.rect(Constants.WIDTH-Constants.SIDEBAR, 0, Constants.SIDEBAR, Constants.HEIGHT);

        // Text to display based on game state
        String text = "";
        if (stalemate) {
            text = "Stalemate";
        } else if (checkmate) {
            text = "CheckMate!\nPress R to\nrestart!";
        } else {
            text = playerTurn ? "White's Turn" : "Black's Turn";
        }
        
        // Text properties
        float textSize = 16;
        float textHeight = textSize * 0.75f;

        // Positioning text in sidebar
        float x = Constants.WIDTH - Constants.SIDEBAR + 10;
        float y = (Constants.HEIGHT + textHeight) / 2;

        // Drawing text
        this.parent.fill(0);
        this.parent.textSize(textSize);
        this.parent.textAlign(this.parent.LEFT, this.parent.CENTER);
        this.parent.text(text, x, y);
    }

    /**
     * Sets the state of mouse press.
     * @param choice True if mouse is pressed, false otherwise
     */
    public void setMousePressed(boolean choice) {
        this.mousePressed = choice;
    }

    /**
     * Sets the coordinates of the mouse press.
     * @param mouseX X-coordinate of the mouse press
     * @param mouseY Y-coordinate of the mouse press
     */
    public void setMousePressedCoordinates(int mouseX, int mouseY) {
        this.mousePressedCoordinate[0] = mouseX;
        this.mousePressedCoordinate[1] = mouseY;
    }
    
    /**
     * Resets the mouse press coordinates.
     */
    public void resetMousePressedCoordinate() {
        this.mousePressedCoordinate[0] = -1;
        this.mousePressedCoordinate[1] = -1;
    }

    /**
     * Toggles the player turn.
     */
    public void toggleTurn() {
        this.playerTurn = !this.playerTurn;
    }

    /**
     * Draws the chessboard tiles.
     */
    public void drawChessBoard() {
        this.chessboard.drawTiles();
    }

    /**
     * Moves and draws chess pieces.
     */
    public void drawChessPieces() {
        this.chessboard.move(config.getMaxTime(), config.getMovementSpeed(), this.playerTurn);
        this.chessboard.drawPieces();
    }

    /**
     * Draws the selected tile.
     */
    public void drawSelectedTile() {
        this.chessboard.drawSelectedTile();
    }

    /**
     * Draws possible moves.
     */
    public void drawPossibleMoves() {
        this.chessboard.drawPossibleMoves();        
    }

    /**
     * Checks if the game should stop based on game conditions.
     * @return True if game should stop, false otherwise
     */
    public boolean stop() {
        return this.stop;
    }

    /**
     * Evaluates user input and updates game state accordingly.
     */
    public void evaluateInput() {
        // Check for stalemate or checkmate conditions
        if (this.chessboard.isStalemate(this.playerTurn)) {
            if (this.chessboard.getCheckedTile()) {
                System.out.println("CheckMate!!");
                this.stop = true;
                this.checkmate = true;
            } else {
                System.out.println("STALEMATE!!");
                this.stop = true;
                this.stalemate = true;
            }
            return;
        }

        // Check if mouse press should reset
        if (this.chessboard.resetMousePress()) {
            resetMousePressedCoordinate();
            this.chessboard.mousePressedReset();
            toggleTurn();
        }
        
        // Update selected tile based on mouse press
        this.chessboard.setSelected(mousePressedCoordinate, playerTurn);
    }

}
