package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarController {

    private static final int DELAY = 20;
    private final CarSimModel model;
    private CarView view;
    private final Timer timer;

    public CarController(CarSimModel model) {
        this.model = model;
        this.timer = new Timer(DELAY, new TimerListener());
    }

    public void setView(CarView view) {
        this.view = view;
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view == null) return;
            int w = view.getDrawPanel().getWidth();
            int h = view.getDrawPanel().getHeight();
            model.update(w, h);
            view.repaint();
        }
    }

    // gjorde det lite mer kompakt
    public void addCar()       { model.addCar(); }
    public void addCar(Vehicle v){ model.addCar(v); }
    public void removeCar()    { model.removeCar(); }
    public void gas(int amt)   { model.gas(amt); }
    public void brake(int amt) { model.brake(amt); }
    public void startAllCars() { model.startAll(); }
    public void stopAllCars() { model.stopAll(); }
    public void turnLeft()     { model.turnLeft(); }
    public void turnRight()    { model.turnRight(); }
    public void turboOn()      { model.turboOn(); }
    public void turboOff()     { model.turboOff(); }
    public void liftBed()      { model.liftBed(); }
    public void lowerBed()     { model.lowerBed(); }
}