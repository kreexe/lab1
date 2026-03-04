package Main;

import java.util.ArrayList;
import java.util.List;

// Abstrakt basklass
public abstract class Workshop<T extends Car> {

    private List<T> cars;

    public Workshop() {
        cars = new ArrayList<>();
    }

    public boolean addCar(T car) {
        if (!cars.contains(car)) {
            cars.add(car);
            return true;
        }
        return false;
    }

    public boolean removeCar(T car) {
        return cars.remove(car);
    }

    public List<T> getCars() {
        return cars;
    }

    public void loadCar(T car) {
        addCar(car);
        brandSpecificAction(car);
    }

    // Abstrakt metod som subklasser måste implementera
    protected abstract void brandSpecificAction(T car);

    // ==== Brand-specifika workshops ====
    public static class VolvoWorkshop extends Workshop<Volvo240> {
        @Override
        protected void brandSpecificAction(Volvo240 car) {
            System.out.println("Loaded Volvo: " + car);
        }
    }

    public static class SaabWorkshop extends Workshop<Saab95> {
        @Override
        protected void brandSpecificAction(Saab95 car) {
            System.out.println("Loaded Saab: " + car);
        }
    }
}