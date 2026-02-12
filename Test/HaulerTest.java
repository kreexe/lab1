package Test;

import Main.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;


class TestHauler extends Hauler {

    public TestHauler() {
        nrDoors = 2;
        enginePower = 300;
        color = Color.RED;
        modelName = "TestHauler";
    }

    @Override
    protected double speedFactor() {
        return enginePower * 0.01;
    }
}

// Enkel testbil
class TestCar extends Car {

    public TestCar() {
        nrDoors = 4;
        enginePower = 100;
        color = Color.BLACK;
        modelName = "TestCar";
    }

    @Override
    protected double speedFactor() {
        return enginePower * 0.01;
    }
}

public class HaulerTest {

    @Test
    void canLoadCarWithinDistance() {
        TestHauler hauler = new TestHauler();
        TestCar car = new TestCar();

        Car loaded = hauler.loadCar(car);

        assertNotNull(loaded);
        assertEquals(car, loaded);
        assertEquals(1, hauler.getLoadedCount());
    }

    @Test
    void cannotLoadIfBedRaised() {
        TestHauler hauler = new TestHauler();
        TestCar car = new TestCar();

        hauler.raiseBed(20);

        Car loaded = hauler.loadCar(car);

        assertNull(loaded);
        assertEquals(0, hauler.getLoadedCount());
    }

    @Test
    void cannotLoadIfTooFarAway() {
        TestHauler hauler = new TestHauler();
        TestCar car = new TestCar();

        car.startEngine();
        car.gas(1);
        for (int i = 0; i < 50; i++) {
            car.move();
        }

        Car loaded = hauler.loadCar(car);

        assertNull(loaded);
        assertEquals(0, hauler.getLoadedCount());
    }

    @Test
    void unloadReturnsLastLoadedCar() {
        TestHauler hauler = new TestHauler();
        TestCar car1 = new TestCar();
        TestCar car2 = new TestCar();

        hauler.loadCar(car1);
        hauler.loadCar(car2);

        Car unloaded = hauler.unloadCar();

        assertEquals(car2, unloaded); 
        assertEquals(1, hauler.getLoadedCount());
    }

    @Test
    void unloadReturnsNullIfEmpty() {
        TestHauler hauler = new TestHauler();

        Car unloaded = hauler.unloadCar();

        assertNull(unloaded);
    }

    @Test
    void loadedCarsMoveWithHauler() {
        TestHauler hauler = new TestHauler();
        TestCar car = new TestCar();

        hauler.loadCar(car);

        hauler.startEngine();
        hauler.gas(1);

        double oldCarY = car.getY();

        hauler.move();

        assertTrue(car.getY() > oldCarY);
    }
}


//    find . -name "*.class" -delete


//    javac -d bin -cp lib/junit-platform-console-standalone-6.0.2.jar Main/*.java Test/*.java


//    java -jar lib/junit-platform-console-standalone-6.0.2.jar execute --class-path bin --scan-class-path




