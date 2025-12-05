package XXL.Chess.setup;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Class responsible for loading chess piece images.
 */
public class LoadImages {

    // Public fields to store chess piece images
    public PImage whitePawn;
    public PImage whiteRook;
    public PImage whiteQueen;
    public PImage whiteKnight;
    public PImage whiteKing;
    public PImage whiteKnightKing;
    public PImage whiteChancellor;
    public PImage whiteCamel;
    public PImage whiteBishop;
    public PImage whiteArchbishop;
    public PImage whiteAmazon;
    public PImage blackPawn;
    public PImage blackRook;
    public PImage blackQueen;
    public PImage blackKnight;
    public PImage blackKing;
    public PImage blackKnightKing;
    public PImage blackChancellor;
    public PImage blackCamel;
    public PImage blackBishop;
    public PImage blackArchbishop;
    public PImage blackAmazon;

    private PApplet sketch; // Processing PApplet instance

    /**
     * Constructor for LoadImages.
     * 
     * @param sketch The PApplet sketch instance
     */
    public LoadImages(PApplet sketch) {    
        this.sketch = sketch;  
        loadImages(); // Load all images upon initialization
    }

    /**
     * Loads all chess piece images from their respective files.
     */
    private void loadImages() {
        // Load white pieces
        whitePawn = loadImage("w-pawn.png");
        whiteRook = loadImage("w-rook.png");
        whiteQueen = loadImage("w-queen.png");
        whiteKnight = loadImage("w-knight.png");
        whiteKing = loadImage("w-king.png");
        whiteKnightKing = loadImage("w-knight-king.png");
        whiteChancellor = loadImage("w-chancellor.png");
        whiteCamel = loadImage("w-camel.png");
        whiteBishop = loadImage("w-bishop.png");
        whiteArchbishop = loadImage("w-archbishop.png");
        whiteAmazon = loadImage("w-amazon.png");

        // Load black pieces
        blackPawn = loadImage("b-pawn.png");
        blackRook = loadImage("b-rook.png");
        blackQueen = loadImage("b-queen.png");
        blackKnight = loadImage("b-knight.png");
        blackKing = loadImage("b-king.png");
        blackKnightKing = loadImage("b-knight-king.png");
        blackChancellor = loadImage("b-chancellor.png");
        blackCamel = loadImage("b-camel.png");
        blackBishop = loadImage("b-bishop.png");
        blackArchbishop = loadImage("b-archbishop.png");
        blackAmazon = loadImage("b-amazon.png");
    }

    /**
     * Loads an image from the file path relative to the sketch.
     * 
     * @param filename The filename of the image to load
     * @return The loaded PImage object
     */
    private PImage loadImage(String filename) {
        try {
            // Get the file path relative to the sketch
            String filePath = this.sketch.getClass().getResource(filename).getPath().replace("%20", " ");
            // Load the image using Processing's loadImage method
            return sketch.loadImage(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // or handle the error in an appropriate way
        }
    }
}
