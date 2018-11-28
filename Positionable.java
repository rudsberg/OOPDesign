/**
 * Positionables represent classes which have a position in a two dimensional coordinate system.
 */
public interface Positionable {
    /**
     * Returns the objects x-position.
     */
    double getX();
    /**
     * Returns the objects y-position.
     */
    double getY();

    /**
     * Sets the objects x-position.
     *
     */
    void setX(double x);

    /**
     * Sets the objects y-position.
     *
     */
    void setY(double y);
}
