package Main;

import java.util.ArrayList;
import java.util.List;

public class CarSimModel {

    private List<Vehicle> cars = new ArrayList<>();
    private Workshop<Volvo240> workshop = new Workshop.VolvoWorkshop();

    public CarSimModel() {

        Volvo240 volvo = new Volvo240();
        Saab95 saab = new Saab95();
        Scania scania = new Scania();

        volvo.xPosition = 0;
        volvo.yPosition = 0;

        saab.xPosition = 0;
        saab.yPosition = 100;

        scania.xPosition = 0;
        scania.yPosition = 200;

        cars.add(volvo);
        cars.add(saab);
        cars.add(scania);
    }

    public List<Vehicle> getCars() {
        return cars;
    }

    public void update(int panelWidth, int panelHeight) {

        int carWidth = 100;
        int carHeight = 60;

        for (int i = 0; i < cars.size(); i++) {

            Vehicle car = cars.get(i);
            car.move();

            int x = (int) car.getX();
            int y = (int) car.getY();

            if (x < 0 || x > panelWidth - carWidth) {
                car.turnLeft();
                car.turnLeft();
            }

            if (y < 0 || y > panelHeight - carHeight) {
                car.turnLeft();
                car.turnLeft();
            }

            int workshopWidth = 200;
            int workshopHeight = 200;

            int wx = (panelWidth - workshopWidth) / 2;
            int wy = (panelHeight - workshopHeight) / 2;

            if (car instanceof Volvo240) {
                if (x > wx && x < wx + workshopWidth &&
                        y > wy && y < wy + workshopHeight) {

                    workshop.loadCar((Volvo240) car);
                    cars.remove(i);
                    i--;
                }
            }
        }
    }

    public void gas(int amount) {
        double gas = amount / 100.0;

        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = amount / 100.0;

        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    public void startAll() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }

    public void stopAll() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }

    public void turnLeft() {
        for (Vehicle car : cars) {
            car.turnLeft();
        }
    }

    public void turnRight() {
        for (Vehicle car : cars) {
            car.turnRight();
        }
    }

    public void turboOn() {
        for (Vehicle car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    public void turboOff() {
        for (Vehicle car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    public void liftBed() {
        for (Vehicle car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).raiseBed(10);
            }
        }
    }

    public void lowerBed() {
        for (Vehicle car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).lowerBed(10);
            }
        }
    }
}