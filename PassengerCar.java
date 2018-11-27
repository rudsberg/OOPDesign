import java.awt.*;
/**
 *
 * A PassengerCar is an abstract class which is a more specific kind of {@link Car} . It inherits all functions
 * from the {@link Car} but can be used to extend the {@link Car}-tree.
 */

public abstract class PassengerCar extends Car {
    public PassengerCar(double enginePower, double currentSpeed, Color color, int nrDoors, double x, double y, Direction direction, int weight,String modelName) {
        super(enginePower, currentSpeed, color, nrDoors, x, y, direction, weight,modelName);
    }
}
