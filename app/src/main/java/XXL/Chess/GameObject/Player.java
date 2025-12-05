package XXL.Chess.GameObject;

public class Player {
    public String name;              // Player's name
    public int time;                 // Total time allocated to the player in seconds
    public int min;                  // Minutes part of the time
    public int sec;                  // Seconds part of the time
    public int increment;            // Time increment per move in seconds
    public boolean isWhite;          // Flag indicating if the player controls the white pieces
    public boolean game_over_by_time; // Flag indicating if the player's game is over due to time

    /**
     * Constructor to initialize a Player object.
     *
     * @param name      The player's name
     * @param time      Total time allocated to the player in seconds
     * @param increment Time increment per move in seconds
     * @param isWhite   Flag indicating if the player controls the white pieces
     */
    public Player(String name, int time, int increment, boolean isWhite) {
        this.name = name;
        this.time = time;
        this.min = time / 60;           // Convert total time to minutes
        this.sec = (time % 60);         // Remaining seconds
        this.increment = increment;     // Time increment per move
        this.isWhite = isWhite;         // Whether player is white
        this.game_over_by_time = false; // Game over flag due to time
    }

    /**
     * Checks if the player controls the white pieces.
     *
     * @return true if the player controls white pieces, false otherwise
     */
    public boolean isWhite() {
        return this.isWhite;
    }

    /**
     * Returns the minutes part of the remaining time as a string.
     *
     * @return Minutes part of remaining time
     */
    public String getMin() {
        return Integer.toString(this.min);
    }

    /**
     * Returns the seconds part of the remaining time as a formatted string (two digits).
     *
     * @return Seconds part of remaining time (formatted as two digits)
     */
    public String getSec() {
        String line = Integer.toString(this.sec);
        if (line.length() == 1) {
            line = "0" + line; // Ensure seconds are two digits (e.g., '05' instead of '5')
        }
        return line;
    }

    /**
     * Decrements the remaining time by one second.
     *
     * @return true if the game is over due to time, false otherwise
     */
    public boolean ChangeTime() {
        if (this.game_over_by_time) {
            return true; // Game already over by time
        }
        if (this.sec == 0) {
            this.sec = 59;
            this.min--;
            if (this.min <= 0) {
                this.game_over_by_time = true; // Game over by time exhaustion
                this.min = 0;
                this.sec = 0;
            }
            return this.game_over_by_time;
        }
        this.sec--;
        return this.game_over_by_time;
    }

    /**
     * Increments the remaining time by the specified increment.
     */
    public void Increment() {
        this.sec += increment; // Add increment to seconds
        if (this.sec > 59) {
            this.sec = this.sec % 60; // Wrap around seconds
            this.min++; // Increment minutes
        }
    }

    /**
     * Returns a string representation of the Player object.
     *
     * @return String representation of Player object
     */
    public String toString() {
        return "________________________________\nName: " + this.name +
                "\nAllowed Time: " + this.time +
                "\nIncremented By: " + increment +
                "\nWhite: " + isWhite +
                "\n________________________________";
    }
}
