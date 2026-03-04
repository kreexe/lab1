package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarController {

    private final int delay = 5;
    private Timer timer = new Timer(delay, new TimerListener());

    private CarView frame;
    private CarSimModel model;

    public CarController() {

        model = new CarSimModel();

        frame = new CarView("CarSim 1.0", this, model.getCars());

        timer.start();
    }

    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            int panelWidth = frame.drawPanel.getWidth();
            int panelHeight = frame.drawPanel.getHeight();

            model.update(panelWidth, panelHeight);

            frame.repaint();
        }
    }

    void gas(int amount) {
        model.gas(amount);
    }

    void brake(int amount) {
        model.brake(amount);
    }

    void startAll() {
        model.startAll();
    }

    void stopAll() {
        model.stopAll();
    }

    void turnLeft() {
        model.turnLeft();
    }

    void turnRight() {
        model.turnRight();
    }

    void turboOn() {
        model.turboOn();
    }

    void turboOff() {
        model.turboOff();
    }

    void liftBed() {
        model.liftBed();
    }

    void lowerBed() {
        model.lowerBed();
    }

    public static void main(String[] args) {
        new CarController();
    }
}