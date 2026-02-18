package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class CarController {

    private final int delay = 50;
    private Timer timer = new Timer(delay, new TimerListener());

    private CarView frame;
    private ArrayList<Vehicle> cars = new ArrayList<>();

    public CarController() {

        // Skapa fordon
        cars.add(new Volvo240());
        cars.add(new Saab95());
        cars.add(new Scania());

        // Skapa view
        frame = new CarView("CarSim 1.0", this);

        timer.start();
    }

    // Timer uppdaterar rörelse
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                car.move();
            }
            frame.repaint();
        }
    }

   

    void gas(int amount) {
        double gas = amount / 100.0;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = amount / 100.0;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    void startEngine() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }

    void stopEngine() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }

    // Dessa behövs för CarView
    void startAll() {
        startEngine();
    }

    void stopAll() {
        stopEngine();
    }

    void turboOn() {
        for (Vehicle car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Vehicle car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }


    void liftBed() {
        for (Vehicle car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).raiseBed(10);
            }
        }
    }

    void lowerBed() {
        for (Vehicle car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).lowerBed(10);
            }
        }
    }

    public ArrayList<Vehicle> getCars() {
        return cars;
    }



    public static void main(String[] args) {
    new CarController();    
    }
}