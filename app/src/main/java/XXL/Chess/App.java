package XXL.Chess;

import processing.core.PApplet;

import XXL.Chess.setup.Constants;

public class App extends PApplet {
    private GameObjectManager gameObjectManager;
    private Controller controller;

    /**
     * Setup the size of the window.
     */
    public void settings() {
        size(Constants.WIDTH, Constants.HEIGHT);
    }

    /**
     * Initialize the game objects and controller.
     */
    public void setup() {
        frameRate(Constants.FPS); // Set the frame rate of the game
        gameObjectManager = new GameObjectManager(this); // Initialize game object manager
        controller = new Controller(this, gameObjectManager); // Initialize game controller
        surface.setTitle("XXL Chess"); // Set the title of the window
    }

    /**
     * Main game loop. Draws game elements and handles user input.
     */
    public void draw() {
        // Check if game needs to restart
        if (controller.willRestart()) {
            controller.restart(); // Restart the game
            gameObjectManager = new GameObjectManager(this); // Reset game object manager
            controller = new Controller(this, gameObjectManager); // Reset game controller
        }

        // Draw the sidebar, chessboard, and chess pieces
        gameObjectManager.drawSideBar();
        gameObjectManager.drawChessBoard();
        gameObjectManager.drawChessPieces();

        // Stop processing further if the game is stopped
        if (gameObjectManager.stop()) {
            return;
        }

        // Evaluate user input and update selected tile and possible moves
        gameObjectManager.evaluateInput();
        gameObjectManager.drawSelectedTile();
        gameObjectManager.drawPossibleMoves();
    }

    /**
     * Main method that launches the Processing application.
     * 
     * @param args Command-line arguments (not used here)
     */
    public static void main(String[] args) {
        PApplet.main("XXL.Chess.App"); // Launch the Processing application
    }

}
