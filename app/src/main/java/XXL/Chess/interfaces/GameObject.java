package XXL.Chess.interfaces;

import processing.core.PApplet; // Processing library for graphics

import XXL.Chess.physics.Vector2D; // Assuming this is a custom vector class

import java.util.ArrayList; // Standard Java utility

/**
 * Interface representing a game object in a chess game.
 */
public interface GameObject {

    /**
     * Checks if the game object is white.
     * 
     * @return true if the game object is white, false otherwise
     */
    boolean isWhite();

    // RGB color definitions for different game object types
    int[] whiteRGB = {236, 218, 185};
    int[] blackRGB = {174, 138, 104};
    int[] greenRGB = {112, 137, 83};
    int[] blueRGB = {201, 223, 231};
    int[] lightRedRGB = {242, 168, 113};
    int[] redRGB = {197, 41, 28};
    int[] lightYellowRGB = {206, 210, 120};
    int[] purpleRGB = {75, 0, 130};

    /**
     * Draws the game object using the provided PApplet.
     * 
     * @param app The PApplet used for rendering graphics
     */
    void draw(PApplet app);

}
