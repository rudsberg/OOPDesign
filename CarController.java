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

        carController.cars.add(new Volvo240(200, 2, Color.red, 4, 0, 0, Direction.DOWN, 2000));
        carController.cars.add(new Saab95(200, 2, Color.red, 4, 100, 0, Direction.DOWN, 2000));
        carController.cars.add(new Scania(200,2,Color.ORANGE,3,0,200,Direction.DOWN,2000));
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
                car.move();
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());
                frame.drawPanel.moveit(car.getPosition(), car.getModelName());
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
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

            }
        }


    }

    public void lowerBed() {
        for (Vehicle car : cars) {


        }
    }
}