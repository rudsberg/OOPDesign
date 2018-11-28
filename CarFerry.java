import java.awt.*;

/**
 * A Car Ferry is a subclass of  {@link Ferry} but which also implements {@link Loadables}, meaning it can carry up to a
 * certain amount of vehicles up to a certain weight.
 * A Car Ferry has the following unique variables:
 * MAX_DISTANCE_TO_LOAD, MAX_VEHICLE_SIZE, MAX_LOAD_CAPACATY, Load_Type and a vehicleLoader which all of the loader
 * methods are delegated to.
 */

public class CarFerry extends Ferry implements Loadables {
    private VehicleLoader vehicleLoader;
    private final static String modelName= "CarFerry";

    public CarFerry(double enginePower, double currentSpeed, Color color, double x, double y, Direction direction, int MAX_LOAD_CAPACATY, int MAX_VEHICLE_SIZE, int weight, int MAX_DISTANCE_TO_LOAD) {
        super(enginePower, currentSpeed, color, modelName, x, y, direction, MAX_LOAD_CAPACATY, MAX_VEHICLE_SIZE, weight, MAX_DISTANCE_TO_LOAD);
        this.vehicleLoader = new VehicleLoader(MAX_DISTANCE_TO_LOAD, MAX_VEHICLE_SIZE, MAX_LOAD_CAPACATY, Load_Type.FRONT_TO_BACK, getPosition(), getReferenceWeight());
    }

    /**
     * Delegates to {@see VehicleLoader#getAmountItemLoaded()}
     */
    @Override
    public int getAmountItemLoaded() {
        return vehicleLoader.getAmountItemLoaded();
    }

    /**
     * Delegates to {@see VehicleLoader#getMaxLoadCapacity()}
     * @return
     */
    @Override
    public int getMaxLoadCapacity() {
        return vehicleLoader.getMaxLoadCapacity();
    }

    /**
     * speedFactor is used in {@see Vehicle#incrementSpeed()} and {@see Vehicle#decrementSpeed} to determine the amount
     * it should either increment or decrement its speed.
     * @return a double which is used in the methods named above.
     */

    @Override
    double speedFactor() {
        return getEnginePower() * 0.01;
    }

    /**
     *
     * Delegates to {@see VehicleLoader#loadItem(v)} and also makes sure that the CarFerry can't load itself upon itself
     * @param v The vehicle which we want to load upon the CarFerry.
     */
    @Override
    public void loadItem(Vehicle v) {
        if (this == v)
            return;
        vehicleLoader.loadItem(v);
    }

    /**
     *  Delegates to {@see VehicleLoader#unLoadItem()}
     */
    @Override
    public void unLoadItem() {
        vehicleLoader.unLoadItem();
    }
    /**
     *  Delegates to {@see VehicleLoader#closeTruckBed()}
     */
    @Override
    public void closeTruckBed() {
        vehicleLoader.closeTruckBed();
    }
    /**
     *  Delegates to {@see VehicleLoader#openTruckBed()}
     */
    @Override
    public void openTruckBed() {
        vehicleLoader.openTruckBed();
    }
    /**
     *  Delegates to {@see VehicleLoader#isFull()}
     */
    @Override
    public boolean isFull() {
        return vehicleLoader.isFull();
    }
    /**
     *  Delegates to {@see VehicleLoader#isTruckBedClosed()}
     */
    @Override
    public boolean isTruckBedClosed() {
        return vehicleLoader.isTruckBedClosed();
    }
    /**
     *  Delegates to {@see VehicleLoader#isTruckBedOpen()}
     */
    @Override
    public boolean isTruckBedOpen() {
        return vehicleLoader.isTruckBedOpen();
    }
    /**
     *  Delegates to {@see VehicleLoader#inRange()}
     */
    @Override
    public boolean inRange(Positionable v) {
        return vehicleLoader.inRange(v);
    }
    /**
     *  Delegates to {@see VehicleLoader#getMaxLoadAmount()}
     */
    @Override
    public int getMaxLoadAmount() {
        return vehicleLoader.getMaxLoadAmount();
    }

    /**
     * Delegates to {@see VehicleLoader#getMaxItemWeight()}
     */
    @Override
    public int getMaxItemWeight() {
        return vehicleLoader.getMaxItemWeight();
    }
    /**
     * Delegates to {@see VehicleLoader#getMaxDistanceToLoad()}
     */
    @Override
    public int getMaxDistanceToLoad() {
        return vehicleLoader.getMaxDistanceToLoad();
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
    void startEngine() {
        if (isTruckBedClosed()) {
            super.startEngine();
        } else {
            System.out.println("TRUCK BED IS OPEN");
        }
    }
}
