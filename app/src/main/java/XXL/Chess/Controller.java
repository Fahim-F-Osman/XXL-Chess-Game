package XXL.Chess;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import XXL.Chess.setup.Constants;

public class Controller {
    private PApplet parent; // The main PApplet instance where events are handled
    private GameObjectManager gameObjectManager; // Manages game objects and their actions
    private boolean restart = false; // Flag indicating if game restart is requested

    /**
     * Constructor for Controller.
     *
     * @param parent            The PApplet instance where events are handled
     * @param gameObjectManager The GameObjectManager to control game objects
     */
    public Controller(PApplet parent, GameObjectManager gameObjectManager) {
        this.parent = parent;
        this.gameObjectManager = gameObjectManager;

        // Register this class as an event handler for key and mouse events
        parent.registerMethod("keyEvent", this);
        parent.registerMethod("mouseEvent", this);
    }

    /**
     * Check if a game restart is requested.
     *
     * @return true if restart is requested, false otherwise
     */
    public boolean willRestart() {
        return this.restart;
    }

    /**
     * Perform the game restart action.
     */
    public void restart() {
        this.restart = false; // Reset the restart flag
    }

    /**
     * Handle key events from Processing.
     * This method is invoked automatically by Processing when a key event occurs.
     *
     * @param key The KeyEvent object containing key event details
     */
    public void keyEvent(KeyEvent key) {
        if (key.getAction() == KeyEvent.PRESS) {
            keyPressed(key); // Call keyPressed method to handle key press actions
        }
    }

    /**
     * Handle mouse events from Processing.
     * This method is invoked automatically by Processing when a mouse event occurs.
     *
     * @param event The MouseEvent object containing mouse event details
     */
    public void mouseEvent(MouseEvent event) {
        switch (event.getAction()) {
            case MouseEvent.PRESS:
                gameObjectManager.setMousePressed(true);
                handleMousePress(event);
                break;
            case MouseEvent.RELEASE:
                gameObjectManager.setMousePressed(false);
                handleMouseRelease(event);
                break;
        }
    }

    /**
     * Handle mouse press event.
     *
     * @param event The MouseEvent object containing mouse press event details
     */
    private void handleMousePress(MouseEvent event) {
        // Convert mouse coordinates to board cell coordinates
        int mouseX = event.getX() / Constants.CELLSIZE;
        int mouseY = event.getY() / Constants.CELLSIZE;

        gameObjectManager.setMousePressedCoordinates(mouseX, mouseY);
    }

    /**
     * Handle mouse release event.
     *
     * @param event The MouseEvent object containing mouse release event details
     */
    private void handleMouseRelease(MouseEvent event) {
        // Convert mouse coordinates to board cell coordinates
        int mouseX = event.getX() / Constants.CELLSIZE;
        int mouseY = event.getY() / Constants.CELLSIZE;

        gameObjectManager.setMousePressedCoordinates(mouseX, mouseY);
    }

    /**
     * Handle key pressed event.
     *
     * @param key The KeyEvent object containing key pressed event details
     */
    private void keyPressed(KeyEvent key) {
        int keyCode = key.getKeyCode();

        // Check for specific key presses
        if (keyCode == 82) { // Key code 82 corresponds to 'R' key
            this.restart = true; // Set restart flag to true
        } else if (keyCode == 69) { // Key code 69 corresponds to 'E' key
            // Additional action for key 'E' if needed
        }
    }
}
