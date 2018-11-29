import java.awt.Color;

/**
 * Semitruck inherits all functionalities from {@link Car} and {@link Vehicle}. It also implements from {@link Loadables}, it therefore
 * has a functionalities to load other vehicles. Naturally, semi-trucks load from the back and removes items from the back.
 */
public abstract class SemiTruck extends Car implements Loadables {
    private VehicleLoader vehicleLoader;

    public SemiTruck(double enginePower, double currentSpeed, Color color, int nrDoors, int x, int y, Direction direction, int MAX_LOAD_CAPACATY, int MAX_VEHICLE_SIZE, int weight, int MAX_DISTANCE_TO_LOAD, String modelName) {
        super(enginePower, currentSpeed, color, nrDoors, x, y, direction, weight, modelName);
        this.vehicleLoader = new VehicleLoader(MAX_DISTANCE_TO_LOAD, MAX_VEHICLE_SIZE, MAX_LOAD_CAPACATY, Load_Type.BACK_TO_BACK, getPosition(), getReferenceWeight());
    }

    public VehicleLoader getVehicleLoader() {
        return vehicleLoader;
    }

    /**
     * Delegates to {@see VehicleLoader#getAmountItemLoaded()}
     *
     * @return
     */
    @Override
    public int getAmountItemLoaded() {
        return vehicleLoader.getAmountItemLoaded();
    }

    /**
     * Delegates to {@see VehicleLoader#getMaxLoadCapacaty()}
     *
     * @return
     */
    @Override
    public int getMaxLoadCapacity() {
        return vehicleLoader.getMaxLoadCapacity();
    }

    /**
     * Delegates to {@see VehicleLoader#startEngine()}
     *
     * @return
     */
    @Override
    void startEngine() {
        if (isTruckBedClosed()) {
            super.startEngine();
        } else {
            System.out.println("TRUCK BED IS OPEN");
        }
    }

    @Override
    void gas(double amount) {
        if (isTruckBedClosed()) {
            super.gas(amount);
        } else {
            System.out.println("TRUCK BED IS OPEN");
        }
    }

    @Override
    public void closeTruckBed() {
        vehicleLoader.closeTruckBed();
    }

    @Override
    public void openTruckBed() {
        vehicleLoader.openTruckBed();
    }

    @Override
    public void loadItem(Vehicle v) {
        if (v == this)
            return;

        vehicleLoader.loadItem(v);
    }

    @Override
    public void unLoadItem() {
        vehicleLoader.unLoadItem();
    }

    @Override
    public boolean isFull() {
        return vehicleLoader.isFull();
    }

    @Override
    public boolean isTruckBedClosed() {
        return vehicleLoader.isTruckBedClosed();
    }

    @Override
    public boolean isTruckBedOpen() {
        return vehicleLoader.isTruckBedOpen();
    }

    @Override
    public boolean inRange(Positionable v) {
        return vehicleLoader.inRange(v);
    }

    @Override
    public int getMaxItemWeight() {
        return vehicleLoader.getMaxItemWeight();
    }

    @Override
    public int getMaxDistanceToLoad() {
        return vehicleLoader.getMaxDistanceToLoad();
    }
}
