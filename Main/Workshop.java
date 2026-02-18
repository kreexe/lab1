package Main;
import java.util.ArrayList;
import java.util.List;

public class Workshop<T extends Car> {
    
    private final int maxCapacity;
    private List<T> cars;

    public Workshop(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.cars = new ArrayList<>();
    }

    public boolean addCar(T car) {
        if (cars.size() >= maxCapacity) {
            return false;
        }
        cars.add(car);
        return true;
    }

    public T removeCar() {
        if (cars.isEmpty()) {
            return null;
        }
        return cars.remove(cars.size() - 1);
    }

    public int getCurrentCount() {
        return cars.size();
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public boolean isFull() {
        return cars.size() >= maxCapacity;
    }

    public boolean isEmpty() {
        return cars.isEmpty();
    }

    public static class GeneralWorkshop extends Workshop<Car> {
        public GeneralWorkshop(int maxCapacity) {
            super(maxCapacity);
        }
    }

    public static class VolvoWorkshop extends Workshop<Volvo240> {
        public VolvoWorkshop(int maxCapacity) {
            super(maxCapacity);
        }
    }

    public static class SaabWorkshop extends Workshop<Saab95> {
        public SaabWorkshop(int maxCapacity) {
            super(maxCapacity);
        }
    }
}
