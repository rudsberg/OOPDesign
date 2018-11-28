import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController carController = new CarController();

        Vehicle volvo240 = new Volvo240(200, 2, Color.red, 4, 0, 600, Direction.DOWN, 2000);
        Vehicle saab95 = new Saab95(200, 2, Color.red, 4, 0, 100, Direction.DOWN, 2000);
        Vehicle scania = new Scania(200,2,Color.ORANGE,3,0,200,Direction.DOWN,2000);

        // Start a new view and send a reference of self
        carController.frame = new CarView("CarSim 1.0", carController);

        // Start the timer
        carController.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                if (isOutsideOfScreen(car.getPosition(), car.getDirectionOfTravel()))
                    stopReverseAndGasVehicle(car);

                car.move();

                frame.drawPanel.moveit(car.getPosition(), car.getModelName());
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    private void stopReverseAndGasVehicle(Vehicle car) {
        car.stopEngine();
        car.turn180Degress();
        car.startEngine();
        car.gas(0.1);
    }

    private boolean isOutsideOfScreen(Positioner position, Direction directionOfTravel) {
        boolean tooFarRight = CarView.getRideableScreenWidth() < position.getX() + 100 && Direction.RIGHT == directionOfTravel;
        boolean tooFarDown = CarView.getRideableScreenHeight() < position.getY() + 60 && Direction.DOWN == directionOfTravel;
        boolean tooFarLeft = 0 > position.getX() && Direction.LEFT == directionOfTravel;
        boolean tooFarUp = 0 > position.getY() && Direction.UP == directionOfTravel;

        return tooFarRight || tooFarDown || tooFarLeft || tooFarUp;
    }

    // Calls the gas method for each car once
    void gasAll(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars
        ) {
            car.gas(gas);
        }
    }

    void stopAll() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }

    void startAll() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }

    void brakeAll(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    void turboOn() {
        for (Vehicle car : cars) {
            if (car.hasTurbo()) {
                Turboable t = (Turboable) car;
                t.setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Vehicle car : cars) {
            if (car.hasTurbo()) {
                Turboable t = (Turboable) car;
                t.setTurboOff();
            }
        }
    }

    public void liftBed() {
        for (Vehicle car : cars) {
            if (car.hasTruckBed()) {
                Scania scania = (Scania) car;
                scania.setTruckBedAngle(scania.getMaxTruckBedAngle());
                System.out.println(scania.getTruckBedAngle());



            }
        }


    }

    public void lowerBed() {
        for (Vehicle car : cars) {
            if (car.hasTruckBed()) {
                Scania scania = (Scania) car;
                scania.setTruckBedAngle(scania.getMinTruckBedAngle());
                System.out.println(scania.getTruckBedAngle());
            }
        }
    }
}