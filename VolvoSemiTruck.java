import java.awt.*;

/**
 * VolvoSemiTruck is a specific type of non-abstract {@link SemiTruck}.
 */

public class VolvoSemiTruck extends SemiTruck {
    private static final int MAX_LOAD_AMOUNT = 3;
    private static final int MAX_VEHICLE_SIZE = 5000;
    private static final int MAX_DISTANCE_TO_LOAD = 10;
    private final static String modelName = "VolvoSemiTruck";

    public VolvoSemiTruck(double enginePower, double currentSpeed, Color color, int nrDoors, int x, int y, Direction direction, int weight) {
        super(enginePower, currentSpeed, color, nrDoors, x, y, direction, MAX_LOAD_AMOUNT, MAX_VEHICLE_SIZE, weight, MAX_DISTANCE_TO_LOAD,modelName);
    }

    @Override
    double speedFactor() {
        return getEnginePower() * 0.01;
    }

}
