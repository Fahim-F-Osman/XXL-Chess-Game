package XXL.Chess.GameObject;

import processing.core.PApplet;

import XXL.Chess.setup.Constants;
import XXL.Chess.physics.Vector2D;
import XXL.Chess.GameObject.chessPieces.*;
import XXL.Chess.setup.LoadImages;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/**
 * Represents the chessboard and manages game logic.
 */
public class Chessboard {
    private PApplet parent; // Reference to the main PApplet sketch
    private Tile[][] chessboard = new Tile[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH]; // 2D array representing the tiles on the chessboard
    private HashMap<Vector2D, Character> layout;  // Stores the layout of chess pieces on the board
    private HashMap<Boolean, ChessPiece> kingCoordinate = new HashMap<>(); // Mapping of king present
    private LoadImages imgDir; // Directory for loading images
    private HashMap<Vector2D, int[]> possibleMoves = new HashMap<>(); // Stores possible moves for each tile on the board

    // Selected tiles during player interaction
    private Tile selectedTile = null; // Currently selected tile
    private Tile previousTile = null; // Previously selected tile
    private Tile checkedTile = null; // Tile that is checked by an opponent's move

    // Tiles involved in move execution
    private Tile originTile = null; // Starting tile of a move
    private Tile targetTile = null; // Target tile of a move

    private boolean isMoving = false; // Flag indicating whether a move is in progress
    private boolean resetMousePress = false; // Flag to reset mouse press state after a move

    /**
     * Constructor for Chessboard class.
     * Initializes the chessboard based on the provided layout.
     *
     * @param parent The PApplet instance that serves as the main sketch window.
     * @param layout HashMap representing the initial layout of chess pieces on the board.
     */
    public Chessboard(PApplet parent, HashMap<Vector2D, Character> layout) {
        this.parent = parent;
        this.imgDir = new LoadImages(parent);
        this.layout = layout;

        // Initialize the chessboard tiles
        for (int col = 0; col < Constants.BOARD_HEIGHT; col++) {
            for (int row = 0; row < Constants.BOARD_WIDTH; row++) {
                Vector2D coord = new Vector2D(row, col);
                Tile tempTile = new Tile(coord, (row + col) % 2 == 0, Constants.CELLSIZE);
                this.chessboard[col][row] = tempTile;
            }
        }

        // Initialize chess pieces based on the layout
        initializeChessPieces(this.layout);
    }

    /**
     * Initializes chess pieces on the chessboard based on the provided layout.
     *
     * @param layout HashMap representing the initial layout of chess pieces on the board.
     */
    private void initializeChessPieces(HashMap<Vector2D, Character> layout) {
        for (Map.Entry<Vector2D, Character> entry : layout.entrySet()) {
            Vector2D position = entry.getKey();
            boolean isWhite = Character.isUpperCase(entry.getValue());
            char pieceChar = Character.toLowerCase(entry.getValue());
            ChessPiece chessPiece = createChessPiece(position, isWhite, pieceChar);
            this.chessboard[(int)position.getY()][(int)position.getX()].setChesspiece(chessPiece);
        }

        // Ensure exactly two kings are present, or terminate the program
        if (kingCoordinate.size() != 2) {
            System.exit(1);
        }
    }

    /**
     * Creates a chess piece based on the given parameters.
     *
     * @param position  The position of the chess piece on the board.
     * @param isWhite   Indicates if the chess piece is white.
     * @param pieceChar Character representing the type of chess piece ('p', 'r', 'n', etc.).
     * @return The created ChessPiece object.
     */
    private ChessPiece createChessPiece(Vector2D position, boolean isWhite, char pieceChar) {
        ChessPiece chessPiece = null;
        switch (pieceChar) {
            case 'p':
                chessPiece = new Pawn(position, isWhite, isWhite ? imgDir.whitePawn : imgDir.blackPawn);
                break;
            case 'r':
                chessPiece = new Rook(position, isWhite, isWhite ? imgDir.whiteRook : imgDir.blackRook);
                break;
            case 'n':
                chessPiece = new Knight(position, isWhite, isWhite ? imgDir.whiteKnight : imgDir.blackKnight);
                break;
            case 'b':
                chessPiece = new Bishop(position, isWhite, isWhite ? imgDir.whiteBishop : imgDir.blackBishop);
                break;
            case 'h':
                chessPiece = new ArchBishop(position, isWhite, isWhite ? imgDir.whiteArchbishop : imgDir.blackArchbishop);
                break;
            case 'c':
                chessPiece = new Camel(position, isWhite, isWhite ? imgDir.whiteCamel : imgDir.blackCamel);
                break;
            case 'g':
                chessPiece = new KnightKing(position, isWhite, isWhite ? imgDir.whiteKnightKing : imgDir.blackKnightKing);
                break;
            case 'a':
                chessPiece = new Amazon(position, isWhite, isWhite ? imgDir.whiteAmazon : imgDir.blackAmazon);
                break;
            case 'k':
                chessPiece = new King(position, isWhite, isWhite ? imgDir.whiteKing : imgDir.blackKing);
                kingCoordinate.put(isWhite, chessPiece);
                break;
            case 'e':
                chessPiece = new Chancellor(position, isWhite, isWhite ? imgDir.whiteChancellor : imgDir.blackChancellor);
                break;
            case 'q':
                chessPiece = new Queen(position, isWhite, isWhite ? imgDir.whiteQueen : imgDir.blackQueen);
                break;
            default:
                break;
        }
        return chessPiece;
    }

    /**
     * Checks if there is a checked tile.
     *
     * @return True if a tile is checked; false otherwise.
     */
    public boolean getCheckedTile() {
        return !(this.checkedTile == null);
    }

    /**
     * Draws all tiles of the chessboard.
     */
    public void drawTiles() {
        for (int col = 0; col < Constants.BOARD_HEIGHT; col++) {
            for (int row = 0; row < Constants.BOARD_WIDTH; row++) {
                this.chessboard[col][row].draw(this.parent);
            }
        }
    }

    /**
     * Draws all chess pieces on the chessboard.
     */
    public void drawPieces() {
        for (int col = 0; col < Constants.BOARD_HEIGHT; col++) {
            for (int row = 0; row < Constants.BOARD_WIDTH; row++) {
                ChessPiece c = this.chessboard[col][row].getChesspiece();
                if (c != null) {
                    c.draw(this.parent);
                }
            }
        }
    }

    /**
     * Highlights the selected tile, previous tile, and checked tile on the chessboard.
     */
    public void drawSelectedTile() {
        resetBoard();
        if (this.previousTile != null) {
            this.previousTile.setTileColor(Constants.lightYellowRGB);
        }
        if (checkedTile != null) {
            this.checkedTile.setTileColor(Constants.redRGB);   
        }
        if (selectedTile != null) {
            this.selectedTile.setTileColor(Constants.greenRGB);   
        }
    }

    /**
     * Highlights possible moves for the selected chess piece on the chessboard.
     * Only executes if a move is not currently in progress and a valid chess piece is selected.
     */
    public void drawPossibleMoves() {
        if (this.isMoving) {return;}
        if (this.selectedTile == null) {return;}
        if (this.selectedTile.getChesspiece() == null) {return;}
        if (this.possibleMoves == null) {return;}
        for (Map.Entry<Vector2D, int[]> entry : this.possibleMoves.entrySet()) {
            Vector2D vec = entry.getKey();
            int x = (int) vec.getX();
            int y = (int) vec.getY();
            this.chessboard[y][x].setTileColor(entry.getValue());
        }
    }

    /**
     * Resets the mouse press state flag.
     *
     * @return The current state of the mouse press reset flag.
     */
    public boolean resetMousePress() {
        return this.resetMousePress;
    }

    /**
     * Resets the mouse press state to false.
     */
    public void mousePressedReset() {
        this.resetMousePress = false;
    }

    /**
     * Promotes a pawn to a queen if eligible.
     *
     * @param t The tile containing the pawn to be promoted.
     */
    public void promoteToQueen(Tile t) {
        ChessPiece c = t.getChesspiece(); 
        if (c.isEligibleForPromotion() && c.getPromotionCoordinateY() == c.getCoordinate().getY()) {
            t.setChesspiece(new Queen(c.getCoordinate(), c.isWhite(), c.isWhite() ? imgDir.whiteQueen : imgDir.blackQueen));
        }
    }
    
    /**
     * Moves a chess piece from its origin tile to its target tile.
     *
     * @param time       Time elapsed for the move animation.
     * @param speed      Speed of the move animation.
     * @param playerTurn Indicates whether it's the player's turn.
     */
    public void move(float time, float speed, boolean playerTurn) {
        if (!this.isMoving) { return; }
        if (this.originTile == null) { return; }
        if (this.targetTile == null) { return; }

        ChessPiece c = this.originTile.getChesspiece();
        if (c == null) { return; }

        // Perform the move animation
        c.move(this.targetTile.getCoordinate(), this.originTile.getCoordinate(), time, speed);

        // If the move animation completes
        if (c.getCoordinate().isEqual(this.targetTile.getCoordinate())) {
            // Reset mouse press state and move flag
            this.resetMousePress = true;
            this.isMoving = false;

            // Update chessboard with moved chess piece
            this.chessboard[(int) this.originTile.getCoordinate().getY()][(int) this.originTile.getCoordinate().getX()].setChesspiece(null);
            this.chessboard[(int) this.targetTile.getCoordinate().getY()][(int) this.targetTile.getCoordinate().getX()].setChesspiece(c);

            // Update previous tile and check for pawn promotion
            this.previousTile = this.targetTile;
            promoteToQueen(this.targetTile);

            // Check if opponent's king is in check
            isCheck(this.chessboard, kingCoordinate, !playerTurn);
        }
    }

    /**
     * Sets the selected tile on the chessboard and calculates possible moves for the selected piece.
     *
     * @param coord       Coordinates [x, y] of the selected tile.
     * @param playerTurn  Indicates whether it's the player's turn.
     */
    public void setSelected(int[] coord, boolean playerTurn) {
        if (this.isMoving) {
            clearSelection();
            return; 
        }

        int x = coord[0];
        int y = coord[1];

        // Check if coordinates are within valid range
        if (!Constants.isWithinRange(x, y)) {
            clearSelection();
            return;
        }

        Tile selectedTile = this.chessboard[y][x];

        // Check if the selected tile has a chess piece and belongs to the current player
        if (selectedTile.getChesspiece() == null || selectedTile.getChesspiece().isWhite() != playerTurn) {
            for (Map.Entry<Vector2D,int[]> entry: this.possibleMoves.entrySet()) {
                if (selectedTile.getCoordinate().isEqual(entry.getKey())) {
                    this.targetTile = selectedTile;
                    isMoving = true;
                    clearSelection();
                    return;
                }
            }
            clearSelection();
            return;
        }

        // Set the selected tile and calculate possible moves
        this.selectedTile = selectedTile;
        this.originTile = selectedTile;
        this.possibleMoves = this.selectedTile.getChesspiece().makePossibleMoves(this.chessboard, this.selectedTile.getCoordinate());
        
        // Filter possible moves to avoid putting own king in check
        filterMovesToAvoidCheck(playerTurn);
    }

    /**
     * Filters out possible moves that would result in the current player's king being in check.
     *
     * @param playerTurn Indicates whether it's the player's turn.
     */
    private void filterMovesToAvoidCheck(boolean playerTurn) {
        ArrayList<Vector2D> movesToRemove = new ArrayList<>();
        ChessPiece originalPiece = this.selectedTile.getChesspiece();
        Vector2D originalCoord = originalPiece.getCoordinate();

        // Iterate over each possible move
        for (Map.Entry<Vector2D, int[]> entry : this.possibleMoves.entrySet()) {
            Vector2D targetPos = entry.getKey();
            int[] color = entry.getValue();

            // Simulate the move temporarily
            ChessPiece targetPiece = this.chessboard[(int) targetPos.getY()][(int) targetPos.getX()].getChesspiece();
            this.chessboard[(int) targetPos.getY()][(int) targetPos.getX()].setChesspiece(originalPiece);
            this.chessboard[(int) this.originTile.getCoordinate().getY()][(int) this.originTile.getCoordinate().getX()].setChesspiece(null);
            originalPiece.setCoordinate(targetPos);

            // Check if this move causes check
            boolean causesCheck = isChecked(this.chessboard, kingCoordinate, playerTurn);

            // Undo the move
            this.chessboard[(int) this.originTile.getCoordinate().getY()][(int) this.originTile.getCoordinate().getX()].setChesspiece(originalPiece);
            this.chessboard[(int) targetPos.getY()][(int) targetPos.getX()].setChesspiece(targetPiece);
            originalPiece.setCoordinate(originalCoord);

            if (causesCheck) {
                movesToRemove.add(targetPos);
            }
        }

        // Remove moves that cause check
        for (Vector2D pos : movesToRemove) {
            this.possibleMoves.remove(pos);
        }
    }

    /**
     * Clears the current selection and resets possible moves.
     */
    private void clearSelection() {
        this.selectedTile = null;
        this.possibleMoves = new HashMap<>();
    }

    /**
     * Resets the background color of all tiles on the chessboard.
     */
    public void resetBoard() {
        for (int i = 0; i < this.chessboard.length; i++) {
            for (int j = 0; j < this.chessboard[i].length; j++) {
                this.chessboard[i][j].resetTileColor();
            }
        }
    }

    /**
     * Determines if the opponent's king is in check.
     *
     * @param chessboard         The current state of the chessboard.
     * @param kingCoordinate     Mapping of player turns to their respective kings.
     * @param opponentPlayerTurn Indicates whether it's the opponent's turn.
     */
    public void isCheck(Tile[][] chessboard, HashMap<Boolean, ChessPiece> kingCoordinate, boolean opponentPlayerTurn) {
        ChessPiece k = kingCoordinate.get(opponentPlayerTurn);
        ArrayList<Vector2D> moves = getOpponentChessPiecesMoves(opponentPlayerTurn);

        // Check if any opponent's piece can attack the king's position
        for (Vector2D vec: moves) {
            if (k.getCoordinate().isEqual(vec)) {
                this.checkedTile = chessboard[(int) k.getCoordinate().getY()][(int) k.getCoordinate().getX()];
                return;
            }
        }
        
        // No check found
        this.checkedTile = null;
    }

    /**
     * Determines if the opponent's king is in check.
     *
     * @param chessboard         The current state of the chessboard.
     * @param kingCoordinate     Mapping of player turns to their respective kings.
     * @param opponentPlayerTurn Indicates whether it's the opponent's turn.
     * @return True if the opponent's king is in check; false otherwise.
     */
    public boolean isChecked(Tile[][] chessboard, HashMap<Boolean, ChessPiece> kingCoordinate, boolean opponentPlayerTurn) {
        ChessPiece k = kingCoordinate.get(opponentPlayerTurn);
        ArrayList<Vector2D> moves = getOpponentChessPiecesMoves(opponentPlayerTurn);
        
        // Check if any opponent's piece can attack the king's position
        for (Vector2D vec: moves) {
            if (k.getCoordinate().isEqual(vec)) {
                return true;
            }
        }
        // No check found
        return false;
    }

    /**
     * Retrieves possible moves for all opponent's chess pieces.
     *
     * @param playerTurn Indicates whether it's the player's turn.
     * @return List of positions where opponent's chess pieces can move.
     */
    public ArrayList<Vector2D> getOpponentChessPiecesMoves(boolean playerTurn) {
        ArrayList<Vector2D> moves = new ArrayList<>();
        try {
            HashMap<Vector2D, int[]> pieces = new HashMap<>();

            // Iterate through the chessboard to find opponent's chess pieces
            for (int i = 0; i < chessboard.length; i++) {
                for (int j = 0; j < chessboard[i].length; j++) {
                    ChessPiece c = chessboard[i][j].getChesspiece();
                    if (c != null && c.isWhite() != playerTurn) {
                        pieces.putAll(c.makePossibleMoves(this.chessboard, c.getCoordinate()));
                    }
                }
            }

            // Extract positions from possible moves
            for (Map.Entry<Vector2D, int[]> entry : pieces.entrySet()) {
                moves.add(entry.getKey());
            }
        } catch (Exception e) {}
        return moves;
    }

    /**
     * Checks if the game is in a stalemate situation for the given player.
     * Stalemate occurs when the player's king is not in check but the player has no legal moves left.
     *
     * @param playerTurn Indicates whether it's the player's turn.
     * @return True if the game is in stalemate for the player; false otherwise.
     */
    public boolean isStalemate(boolean playerTurn) {
        // Check if the player's king is in check
        if (isChecked(this.chessboard, this.kingCoordinate, playerTurn)) {
            return false;
        }
        
        // Check if there are any legal moves available for the player
        for (int i = 0; i < Constants.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Constants.BOARD_WIDTH; j++) {
                ChessPiece c = chessboard[i][j].getChesspiece();
                if (c != null && c.isWhite() != playerTurn) {
                    Vector2D originalVector = c.getCoordinate();

                    // Get all possible moves for the current piece
                    HashMap<Vector2D, int[]> possibleMoves = c.makePossibleMoves(this.chessboard, c.getCoordinate());
                    // Check if there are any legal moves that do not cause check
                    for (Map.Entry<Vector2D, int[]> entry : possibleMoves.entrySet()) {
                        Vector2D targetPos = entry.getKey();
                        int[] color = entry.getValue();
                        
                        // Simulate the move temporarily
                        ChessPiece targetPiece = chessboard[(int) targetPos.getY()][(int) targetPos.getX()].getChesspiece();
                        chessboard[(int) targetPos.getY()][(int) targetPos.getX()].setChesspiece(c);
                        chessboard[(int) c.getCoordinate().getY()][(int) c.getCoordinate().getX()].setChesspiece(null);
                        c.setCoordinate(targetPos);
                        
                        // Check if this move causes check
                        boolean causesCheck = isChecked(this.chessboard, this.kingCoordinate, !playerTurn);
                        // Undo the move
                        chessboard[(int) originalVector.getY()][(int) originalVector.getX()].setChesspiece(c);
                        chessboard[(int) targetPos.getY()][(int) targetPos.getX()].setChesspiece(targetPiece);
                        c.setCoordinate(originalVector);
                        
                        // If the move doesn't cause check, it's not stalemate
                        if (!causesCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        
        // If no legal moves are found and king is not in check, it's stalemate
        return true;
    }


}