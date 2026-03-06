package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CarSimModel {

    private final List<Vehicle> cars = new ArrayList<>();
    private static final int Y_SPACING = 100;
    private WorkshopZone<Volvo240> workshopZone;
    private final List<Observer> observers = new ArrayList<>();
    private static final int CAR_WIDTH = 100;
    private static final int CAR_HEIGHT = 60;
    private final Random rand = new Random();

    public CarSimModel() {
        workshopZone = new WorkshopZone<Volvo240>(200, 200, new Workshop.VolvoWorkshop());
        initializeCars();
    }

    private void initializeCars() {
        addCar(VehicleFactory.create("volvo"));
        addCar(VehicleFactory.create("saab"));
        addCar(VehicleFactory.create("scania"));
    }

    public void addCar(Vehicle car) {
        double x = 0;
        double y = cars.size() * Y_SPACING;
        car.setPosition(x, y);
        cars.add(car);
        notifyObservers();
    }

    public void addCar() {
        addCar(VehicleFactory.createRandom());
    }

    public void removeCar() {
        if (cars.isEmpty()) return;
        int idx = rand.nextInt(cars.size());
        cars.remove(idx);
        notifyObservers();
    }

    public List<Vehicle> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    private void notifyObservers() {
        for (Observer o : observers) o.update();
    }

    public void update(int panelWidth, int panelHeight) {
        for (int i = 0; i < cars.size(); i++) {
            Vehicle car = cars.get(i);
            car.move();
            bounceOnCollision(car, panelWidth, panelHeight);
            if (workshopZone != null && car instanceof Volvo240 &&
                    workshopZone.tryAddCar((Volvo240) car, panelWidth, panelHeight)) {
                cars.remove(i);
                i--;
            }
        }
        notifyObservers();
    }

    private void bounceOnCollision(Vehicle car, int panelWidth, int panelHeight) {
        int x = (int) Math.round(car.getX());
        int y = (int) Math.round(car.getY());
        boolean collided = false;

        if (x < 0) {
            car.setPosition(0, car.getY());
            collided = true;
        } else if (x > panelWidth - CAR_WIDTH) {
            car.setPosition(panelWidth - CAR_WIDTH, car.getY());
            collided = true;
        }

        if (y < 0) {
            car.setPosition(car.getX(), 0);
            collided = true;
        } else if (y > panelHeight - CAR_HEIGHT) {
            car.setPosition(car.getX(), panelHeight - CAR_HEIGHT);
            collided = true;
        }

        if (collided) {
            car.turnLeft();
            car.turnLeft();
        }
    }

    public void gas(int amount) {
        double gas = amount / 100.0;
        for (Vehicle car : cars) car.gas(gas);
        notifyObservers();
    }

    public void brake(int amount) {
        double brake = amount / 100.0;
        for (Vehicle car : cars) car.brake(brake);
        notifyObservers();
    }

    public void startAll() {
        for (Vehicle car : cars) car.startEngine();
        notifyObservers();
    }

    public void stopAll() {
        for (Vehicle car : cars) car.stopEngine();
        notifyObservers();
    }

    public void turnLeft() {
        for (Vehicle car : cars) car.turnLeft();
        notifyObservers();
    }

    public void turnRight() {
        for (Vehicle car : cars) car.turnRight();
        notifyObservers();
    }


    //byter ut instanceof mot modelname
    public void turboOn() {
        for (Vehicle car : cars) {
            String name = car.getModelName();
            if (name != null && name.equalsIgnoreCase("Saab95")) {
                ((Saab95) car).setTurboOn();
            }
        }
        notifyObservers();
    }

    public void turboOff() {
        for (Vehicle car : cars) {
            String name = car.getModelName();
            if (name != null && name.equalsIgnoreCase("Saab95")) {
                ((Saab95) car).setTurboOff();
            }
        }
        notifyObservers();
    }

    public void liftBed() {
        for (Vehicle car : cars) {
            String name = car.getModelName();
            if (name != null && name.equalsIgnoreCase("Scania")) {
                ((Scania) car).raiseBed(10);
            }
        }
        notifyObservers();
    }

    public void lowerBed() {
        for (Vehicle car : cars) {
            String name = car.getModelName();
            if (name != null && name.equalsIgnoreCase("Scania")) {
                ((Scania) car).lowerBed(10);
            }
        }
        notifyObservers();
    }
k
    public WorkshopZone<?> getWorkshopZone() {
        return workshopZone;
    }
}
