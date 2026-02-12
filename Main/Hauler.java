package Main;
import java.util.Stack;

public abstract class Hauler extends Truck {

    private static final double LOADDISTANCE = 10.0;
    private static final int MAXKAPACITET = 8;
    
    private Stack<Car> loadedCars = new Stack<>();

    protected Hauler() {
        super();
    }

   public Car loadCar(Car car) {
        if (bedAngle != 0) return null;
        if (loadedCars.size() >= MAXKAPACITET) return null;

        double dx = car.getX() - this.getX();
        double dy = car.getY() - this.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance > LOADDISTANCE) return null;

        loadedCars.push(car);
        return car;

    }

    public Car unloadCar() {
        if (bedAngle != 0) return null;
        if (loadedCars.isEmpty()) return null;

        Car car = loadedCars.pop();
        car.xPosition = this.getX() + 2.0;
        car.yPosition = this.getY();
        return car;
    }

    public int getLoadedCount() {
        return loadedCars.size();
    }

    @Override
    public void move() {
        double oldX = this.getX();
        double oldY = this.getY();
        
        super.move();
        
        double newX = this.getX();
        double newY = this.getY();
        double deltaX = newX - oldX;
        double deltaY = newY - oldY;
        
        for (Car car : loadedCars) {
            car.xPosition += deltaX;
            car.yPosition += deltaY;
        }
    }
}