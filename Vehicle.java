import java.awt.*;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * An abstract class representing a vehicle. It also implements from movable and positionable.
 * It has a modelname, current directionOfTravel, engine power, current speed, x and y position and a weight
 */
public abstract class Vehicle implements Movable, Positionable {
    private final static double MAX_SPEED_CHANGE = 1;
    private final String modelName; // The car model name
    private final List<Direction> directions;
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private Positioner position;
    private Direction directionOfTravel;
    private Weight weight;

    public Vehicle(double enginePower, double currentSpeed, Color color, double x, double y, Direction directionOfTravel, int weight, String modelName) {
        this.enginePower = enginePower;
        this.currentSpeed = currentSpeed;
        this.color = color;
        this.position = new Positioner(x, y);
        this.directions = asList(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT);
        this.directionOfTravel = directionOfTravel;
        this.weight = new Weight(weight);
        this.modelName = modelName;

    }

    public Positioner getPosition() {
        return position;
    }

    /**
     * If vehicle is moving (ie speed greater than zero) it will return true else false.
     *
     * @return A boolean
     */
    public boolean isVehicleMoving() {
        return this.currentSpeed > 0;
    }

    public static double getMaxSpeedChange() {
        return MAX_SPEED_CHANGE;
    }

    /**
     * Given another positionable it will set the position equal to the active object (this).
     *
     * @param
     */
     void setsPositionToSameAs(Positioner loader) {
        if (this.getPosition() == loader)
            return;
        this.position=loader;
    }

    /**
     * Depending on the directionOfTravel of travel and its current speed the car will change its x and y coordinates accordingly.
     */
    @Override
    public void move() {
        double x = getX();
        double y = getY();

        switch (directionOfTravel) {
            case UP:
                setY(y - currentSpeed);
                break;

            case RIGHT:
                setX(x + currentSpeed);
                break;

            case DOWN:
                setY(y + currentSpeed);
                break;

            case LEFT:
                setX(x - currentSpeed);
                break;
        }
    }

    /**
     * Turns 90 degrees counter clockwise.
     */
    @Override
    public void turnLeft() {
        directionOfTravel = directions.get((directions.indexOf(directionOfTravel) - 1 + directions.size()) % directions.size());
    }

    /**
     * Turns 90 degrees clockwise.
     */
    @Override
    public void turnRight() {
        directionOfTravel = directions.get((directions.indexOf(directionOfTravel) + 1 + directions.size()) % directions.size());
    }

    /**
     * Turns 180 degrees directionOfTravel.
     */
    public void turn180Degress() {
        turnLeft();
        turnLeft();
    }

    public Weight getReferenceWeight() {
        return weight.getReferenceWeight();
    }

    public int getWeight() {
        return weight.getWeight();
    }

    public void setDirectionOfTravel(Direction directionOfTravel) {
        this.directionOfTravel = directionOfTravel;
    }

    public Direction getDirectionOfTravel() {
        return directionOfTravel;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public void setY(double y) {
        position.setY(y);
    }

    public void setX(double x) {
        position.setX(x);
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }

    public String getModelName() {
        return modelName;
    }

    public void setColor(Color clr) {
        color = clr;
    }


    /**
     * Sets the starting speed.
     */
    void startEngine() {
        if (currentSpeed == 0)
            currentSpeed = 0.1;
    }

    public boolean isTruck() {
        // Different better improvement will be added
        return (this instanceof Truck);
    }

    /**
     * Checks if said Vehicle is a model which has turbo.
     *
     * @return a boolean specifying true or false.
     */
    public boolean hasTurbo() {
        return this instanceof Turboable;
    }

    /**
     * Turns off the engine and sets the speed to 0.
     */
    void stopEngine() {
        currentSpeed = 0;
    }

    abstract double speedFactor();

    /**
     * Increments the speed depending on the cars speed factor, which is inherent to the car model.
     * It will not increase the speed if it exceeds maximum engine power.
     *
     * @param amount of increase in speed between 0 and MAX_SPEED_CHANGE
     */
    private void incrementSpeed(double amount) {
        double tempCurrentSpeed = getCurrentSpeed() + speedFactor() * amount;
        if (tempCurrentSpeed < enginePower) {
            currentSpeed = tempCurrentSpeed;
        } else {
            currentSpeed = enginePower;
        }
    }

    /**
     * Decrements the speed depending on the cars speed factor, which is inherent to the car model.
     * Decrementing the speed will not allow the car to travel backwards.
     *
     * @param amount of increase in speed between 0 and MAX_SPPED_CHANGE
     */
    private void decrementSpeed(double amount) {
        double tempCurrentSpeed = getCurrentSpeed() - speedFactor() * amount;
        if (currentSpeed > 0 && tempCurrentSpeed >= 0) {
            currentSpeed = tempCurrentSpeed;
        } else {
            currentSpeed = 0;
        }
    }

    /**
     * Increases the car speed if the amount parameter is within 0 and MAX_SPEED_CHANGE
     *
     * @param amount the car should increase its speed
     */
    void gas(double amount) {
        if (amount <= MAX_SPEED_CHANGE && amount >= 0) {
            incrementSpeed(amount);
        }
    }

    /**
     * Decreases the car speed if the amount parameter is within 0 and MAX_SPEED_CHANGE
     *
     * @param amount the car should decrease its speed
     */
    void brake(double amount) {
        if (amount <= MAX_SPEED_CHANGE && amount >= 0) {
            decrementSpeed(amount);
        }
    }
}
