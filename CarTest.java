import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {


    @Test
    void sanityTest() {
        assertEquals(2, 1 + 1);
    }


    @Test
    void carStartsWithZeroSpeed() {
        Car car = new Volvo240();
        assertEquals(0.0, car.getCurrentSpeed());
    }

    @Test
    void gasIncreasesSpeed() {
        Car car = new Saab95();
        car.gas(1.0);
        assertTrue(car.getCurrentSpeed() > 0);
    }

    @Test
    void gasDoesNotDecreaseSpeed() {
        Car car = new Volvo240();
        car.gas(0.5);
        double speed = car.getCurrentSpeed();
        car.gas(0.5);
        assertTrue(car.getCurrentSpeed() >= speed);
    }

    @Test
    void gasOutsideIntervalDoesNothing() {
        Car car = new Volvo240();
        car.gas(2.0);
        car.gas(-1.0);
        assertEquals(0.0, car.getCurrentSpeed());
    }


    @Test
    void brakeDecreasesSpeed() {
        Car car = new Saab95();
        car.gas(1.0);
        double speed = car.getCurrentSpeed();
        car.brake(1.0);
        assertTrue(car.getCurrentSpeed() < speed);
    }

    @Test
    void brakeNeverIncreasesSpeed() {
        Car car = new Volvo240();
        car.brake(1.0);
        assertEquals(0.0, car.getCurrentSpeed());
    }

    @Test
    void speedNeverNegative() {
        Car car = new Volvo240();
        car.brake(1.0);
        assertEquals(0.0, car.getCurrentSpeed());
    }

    @Test
    void speedNeverExceedsEnginePower() {
        Car car = new Saab95();
        for (int i = 0; i < 100; i++) {
            car.gas(1.0);
        }
        assertTrue(car.getCurrentSpeed() <= car.getEnginePower());
    }
}




// java -jar lib/junit-platform-console-standalone-6.0.2.jar execute --class-path . --scan-class-path
