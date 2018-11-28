/**
 * Movable represents objects that have the ability to move in a two-dimensional coordinate system.
 */
public interface Movable {
    /**
     * Moves the object in its current direction and according to its current speed.
     */
    void move();

    /**
     *Turns the object 90 degrees counter-clockwise.
     */
    void turnLeft();

    /**
     * Turns the object 90 degrees clockwise.
     */

    void turnRight();

    /**
     * Turns the object 180 degrees.
     */

    void turn180Degress();
}
