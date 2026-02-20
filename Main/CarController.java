package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CarController {

    private final int delay = 5;
    private Timer timer = new Timer(delay, new TimerListener());

    private CarView frame;
    private List<Vehicle> cars = new ArrayList<>();

    private VolvoWorkshop workshop = new VolvoWorkshop(5);

    public CarController() {

        Volvo240 volvo = new Volvo240();
        Saab95 saab = new Saab95();
        Scania scania = new Scania();

        // 100px mellanrum 
        volvo.xPosition = 0;
        volvo.yPosition = 0;

        saab.xPosition = 0;
        saab.yPosition = 100;

        scania.xPosition = 0;
        scania.yPosition = 200;

        cars.add(volvo);
        cars.add(saab);
        cars.add(scania);

        frame = new CarView("CarSim 1.0", this, cars);

        timer.start();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            int panelWidth = frame.drawPanel.getWidth();
            int panelHeight = frame.drawPanel.getHeight();

            int carWidth = 100;
            int carHeight = 60;

            for (int i = 0; i < cars.size(); i++) {
                //Hämtar bilen, flyttar den (move) sen hämtar positionen igen.
                Vehicle car = cars.get(i);
                car.move();

                int x = (int) car.getX();
                int y = (int) car.getY();

                //  Kollar om bilen är inom gränsvärderna (inom panelen) annars gör vänster vänster = 180
                if (x < 0 || x > panelWidth - carWidth) {
                    car.turnLeft();
                    car.turnLeft();
                }

                if (y < 0 || y > panelHeight - carHeight) {
                    car.turnLeft();
                    car.turnLeft();
                }

                // Storlek och position av workshop
                int workshopWidth = 200;
                int workshopHeight = 200;
                int wx = (panelWidth - workshopWidth) / 2;
                int wy = (panelHeight - workshopHeight) / 2;

                // om x koordinaten på volvo är mindre och större än workshop koordinaterna (samma)
                if (car instanceof Volvo240) {
                    if (x > wx && x < wx + workshopWidth &&
                        y > wy && y < wy + workshopHeight) {

                        workshop.loadCar((Volvo240) car);
                        cars.remove(i);
                        i--;
                        continue;
                }
                }
            }

            frame.repaint();
        }
    }

    //borde inte ligga här.
    
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

    void startAll() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }

    void stopAll() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }

    void turnLeft() {
        for (Vehicle car : cars) {
            car.turnLeft();
        }
    }

    void turnRight() {
        for (Vehicle car : cars) {
            car.turnRight();
        }
    }

    //  SAAB TURBO 

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

    //  SCANIA FLAK 

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

    public static void main(String[] args) {
        new CarController();
    }
}
