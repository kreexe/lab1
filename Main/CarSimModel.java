package Main;

import java.util.ArrayList;
import java.util.List;

public class CarSimModel {

    private final List<Vehicle> cars = new ArrayList<>();
    private static final int Y_SPACING = 100; // avstånd mellan bilarna som spawnas in
    private WorkshopZone<Volvo240> workshopZone;
    private List<Observer> observers = new ArrayList<>();
    private static final int CAR_WIDTH = 100;
    private static final int CAR_HEIGHT = 60;

    public CarSimModel() {
        workshopZone = new WorkshopZone<Volvo240>(200, 200, new Workshop.VolvoWorkshop());
        initializeCars();
    }

    private void initializeCars() {
        addCar(new Volvo240());
        addCar(new Saab95());
        addCar(new Scania());
    
    }

    public void addCar(Vehicle car) {
        double x = 0;
        double y = cars.size() * Y_SPACING; // automatisk y-position baserat på antal bilar
        car.setPosition(x, y);
        cars.add(car);
    }

    public List<Vehicle> getCars() {
        return cars;
    }

    // Observer

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    private void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }


    public void update(int panelWidth, int panelHeight) {
        for (int i = 0; i < cars.size(); i++) {
            Vehicle car = cars.get(i);

            car.move();

            // vänd bilen vid kollision
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
            // vänd 180°
            car.turnLeft();
            car.turnLeft();
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