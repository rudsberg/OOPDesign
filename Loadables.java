/**
 * Loadables is an interface describing all objects that can load something upon them. For example a car ferry or a semi-truck
 * carrying a number of cars.
 */
public interface Loadables {
    /**
     * Loads said item upon the loadable.
     *
     * @param v a vehicle, which is about to be loaded upon a loadable.
     */
    void loadItem(Vehicle v);

    /**
     * Unloads the item next up in line for unloading.
     */
    void unLoadItem();

    /**
     * Closes the truck bed.
     */
    void closeTruckBed();

    /**
     * Opens the truck bed.
     */
    void openTruckBed();

    /**
     * Checks if the loadable has reached maximum capacity.
     *
     * @return true or false.
     */
    boolean isFull();

    /**
     * Checks if the truck bed is closed.
     *
     * @return true or false.
     */
    boolean isTruckBedClosed();

    /**
     * Checks if the truck bed is open.
     *
     * @return true or false.
     */
    boolean isTruckBedOpen();

    /**
     * Checks if said item is within maximum load distance and returns true or false depending on the outcome.
     */
    boolean inRange(Positionable v);

    /**
     * Returns maximum load amount.
     */

    int getMaxItemWeight();

    /**
     * Returns the maximum distance between an object and the loader.
     */

    int getMaxDistanceToLoad();

    /**
     * Returns maximum amount of objects allowed to be loaded.
     */

    int getMaxLoadCapacity();

    /**
     * Returns the amount of items loaded.
     */

    int getAmountItemLoaded();

}
