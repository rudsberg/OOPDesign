import java.awt.*;

/**
 * Extends the abstract car class. Saab95 also has a turbo functionallity which will affect its speed factor.
 */
public class Saab95 extends PassengerCar implements Turboable {
    private boolean turboOn;
    private final static String modelName = VehicleModel.Saab95.name();

    Saab95(double enginePower, double currentSpeed, Color color, int nrDoors, double x, double y, Direction direction, int weight) {
        super(enginePower, currentSpeed, color, nrDoors, x, y, direction, weight,modelName);
    }

    public void setTurboOn(){
        turboOn = true;
    }

    public void setTurboOff(){
        turboOn = false;
    }

    /**
     * Calculates and return the speed factor. If the turbo is turned it will increase the speed factor.
     * @return  a double value of the speed factor.
     */
    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}