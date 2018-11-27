public interface Loadables {
    void loadItem(Vehicle v);
    void unLoadItem();
    void closeTruckBed();
    void openTruckBed();

    boolean isFull();
    boolean isTruckBedClosed();
    boolean isTruckBedOpen();
    boolean inRange(Positionable v);

    int getMaxLoadAmount();
    int getMaxItemWeight();
    int getMaxDistanceToLoad();
    int getMaxLoadCapacity();
    int getAmountItemLoaded();
}
