package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarController {

    private CarSimModel model;
    private CarView frame;

    int delay = 5;

    public CarController() {

        model = new CarSimModel();
        frame = new CarView("CarSim 1.0", this, model);

        Timer timer = new Timer(delay, new TimerListener());
        timer.start();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            model.update(frame.getDrawPanel().getWidth(), frame.getDrawPanel().getHeight());
            frame.repaint();
        }
    }

    public void gas(int amount) {
        model.gas(amount);
    }

    public void brake(int amount) {
        model.brake(amount);
    }

    public void startAllCars() {
        model.startAll();
    }

    public void stopAllCars() {
        model.stopAll();
    }

    public void turnLeft() {
        model.turnLeft();
    }

    public void turnRight() {
        model.turnRight();
    }

    public void turboOn() {
        model.turboOn();
    }

    public void turboOff() {
        model.turboOff();
    }

    public void liftBed() {
        model.liftBed();
    }

    public void lowerBed() {
        model.lowerBed();
    }

    public void addCar() {
        model.addCar();
    }

    public void removeCar() {
        model.removeCar();
    }

    public static void main(String[] args) {
        new CarController();
    }
}