package XXL.Chess.physics;

/**
 * A class representing a 2D vector with x and y components.
 */
public class Vector2D {
    private float x; // x component of the vector
    private float y; // y component of the vector

    /**
     * Constructs a Vector2D object with specified x and y components.
     * 
     * @param x The x component of the vector
     * @param y The y component of the vector
     */
    public Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves the x component of the vector.
     * 
     * @return The x component of the vector
     */
    public float getX(){
        return this.x;
    }

    /**
     * Retrieves the y component of the vector.
     * 
     * @return The y component of the vector
     */
    public float getY(){
        return this.y;
    }

    /**
     * Sets the x component of the vector.
     * 
     * @param x The value to set as the new x component of the vector
     */
    public void setX(float x){
        this.x = x;
    }

    /**
     * Sets the y component of the vector.
     * 
     * @param y The value to set as the new y component of the vector
     */
    public void setY(float y){
        this.y = y;
    }

    /**
     * Checks if this vector is equal to another vector.
     * 
     * @param coord The Vector2D object to compare with
     * @return true if the vectors have the same x and y components, false otherwise
     */
    public boolean isEqual(Vector2D coord) {
        return (this.x == coord.getX() && this.y == coord.getY());
    }
}
