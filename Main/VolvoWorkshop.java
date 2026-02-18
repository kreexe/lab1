package Main;

import java.util.ArrayList;

public class VolvoWorkshop {

    private int capacity;
    private ArrayList<Volvo240> cars;

    public VolvoWorkshop(int capacity) {
        this.capacity = capacity;
        cars = new ArrayList<>();
    }

    public void loadCar(Volvo240 car) {
        if (cars.size() < capacity) {
            cars.add(car);
        }
    }

    public Volvo240 unloadCar() {
        if (cars.size() > 0) {
            return cars.remove(cars.size() - 1);
        }
        return null;
    }
}
