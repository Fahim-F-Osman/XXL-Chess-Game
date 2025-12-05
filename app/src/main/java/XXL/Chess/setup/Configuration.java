package XXL.Chess.setup;

import processing.data.JSONObject;
import processing.data.JSONArray;

import XXL.Chess.GameObject.Player; // Assuming Player class exists in XXL.Chess.GameObject package
import XXL.Chess.physics.Vector2D; // Assuming Vector2D class exists in XXL.Chess.physics package

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

/**
 * Class for loading and managing configurations for a chess game.
 */
public class Configuration {

    private JSONObject conf; // JSON object holding configuration data

    private String layoutFile; // File path to the layout configuration
    private HashMap<Vector2D, Character> layout; // Map to store layout data (position and character)

    private Player player1; // Player 1 object
    private Player player2; // Player 2 object
    
    private float pieceMovementSpeed; // Speed at which pieces can move
    private float maxMovementTime; // Maximum allowed movement time
    
    private boolean playerTurn; // Flag indicating current player's turn

    /**
     * Constructor to initialize the Configuration object.
     */
    public Configuration() {
        try {
            // Load configuration data
            loadConfiguration();
            // Initialize players based on configuration
            initializePlayers();
            // Determine which player's turn it is
            determinePlayerTurn();
            // Load layout configuration
            loadLayout();

        } catch (FileNotFoundException e) {
            System.out.println("Layout File Doesn't Exist");
            System.exit(1);
        } catch (Exception e) {
            System.out.println("Error loading configuration: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Loads the main configuration file and initializes relevant variables.
     * 
     * @throws Exception If there's an error parsing or reading the configuration file.
     */
    private void loadConfiguration() throws Exception {
        // Load JSON configuration from file
        String jsonStr = readFile(Constants.configPath);
        conf = JSONObject.parse(jsonStr); // Parse JSON string into JSONObject

        // Read specific configuration values
        layoutFile = conf.getString("layout");
        pieceMovementSpeed = (float) conf.getFloat("piece_movement_speed");
        maxMovementTime = conf.getFloat("max_movement_time");
    }

    /**
     * Initializes player objects based on configuration data.
     * 
     * @throws Exception If there's an error initializing player details from configuration.
     */
    private void initializePlayers() throws Exception {
        JSONObject playerDetails = conf.getJSONObject("time_controls");
        JSONObject p1 = playerDetails.getJSONObject("player");
        JSONObject p2 = playerDetails.getJSONObject("cpu");

        // Determine player color based on configuration
        boolean isPlayer1White = conf.getString("player_colour").equals("white");

        // Initialize player objects
        player1 = new Player("player", p1.getInt("seconds"), p1.getInt("increment"), isPlayer1White);
        player2 = new Player("cpu", p2.getInt("seconds"), p2.getInt("increment"), !isPlayer1White);
    }

    /**
     * Determines which player's turn it is based on initial configuration.
     */
    private void determinePlayerTurn() {
        playerTurn = player1.isWhite(); // Assuming isWhite() method determines if player is white
    }

    /**
     * Loads the layout configuration file and populates the layout map.
     * 
     * @throws FileNotFoundException If the layout configuration file is not found.
     */
    private void loadLayout() throws FileNotFoundException {
        // Load layout file
        File f = new File(layoutFile);
        Scanner scan = new Scanner(f);
        layout = new HashMap<>();

        // Read layout file line by line
        int i = 0;
        while (scan.hasNext()) {
            String s = scan.nextLine();
            char[] ch = s.toCharArray();
            for (int j = 0; j < ch.length; j++) {
                if (ch.length > 0 && ch[j] != ' ') {
                    Vector2D temp_coord = new Vector2D(j, i);
                    layout.put(temp_coord, ch[j]);
                }
            }
            i++;
        }
        scan.close();
    }

    /**
     * Returns the layout map containing positions and characters based on layout configuration.
     * 
     * @return HashMap representing the layout configuration.
     */
    public HashMap<Vector2D, Character> getLayout() {
        return this.layout;
    }

    /**
     * Utility method to read a file and return its content as a string.
     * 
     * @param filePath Path of the file to read.
     * @return Content of the file as a string.
     * @throws FileNotFoundException If the file specified by filePath is not found.
     */
    private String readFile(String filePath) throws FileNotFoundException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                contentBuilder.append(scanner.nextLine()).append("\n");
            }
        }
        return contentBuilder.toString();
    }

    // Getter methods for various configuration values

    public float getMovementSpeed() {
        return this.pieceMovementSpeed;
    }

    public float getMaxTime() {
        return this.maxMovementTime;
    }

    public Player getPlayer() {
        return this.player1;
    }

    public Player getOpponent() {
        return this.player2;
    }

    public boolean getPlayerTurn() {
        return this.playerTurn;
    }

}
