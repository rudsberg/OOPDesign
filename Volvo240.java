import java.awt.*;

/**
 * Extends the abstract car class. Volvo240 also has a trim factor which will affect its speed factor.
 */
public class Volvo240 extends PassengerCar {
    private final static String modelName = "Volvo240";
    private final static double trimFactor = 1.25;

    Volvo240(double enginePower, double currentSpeed, Color color, int nrDoors, double x, double y, Direction direction, int weight) {
        super(enginePower, currentSpeed, color, nrDoors, x, y, direction, weight, modelName);

    }

    /**
     * Calculates and return the speed factor. The trim factor will affect the speed factor.
     * @return  a double value of the speed factor.
     */
    @Override
    public double speedFactor(){
        return getEnginePower() * 0.1 * trimFactor;
    }
}