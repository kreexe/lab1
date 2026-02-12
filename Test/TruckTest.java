package Test;

import Main.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

// Enkel konkret test-klass eftersom Truck är abstract
class TestTruck extends Truck {

    public TestTruck() {
        nrDoors = 2;
        enginePower = 200;
        color = Color.BLUE;
        modelName = "TestTruck";
    }

    @Override
    protected double speedFactor() {
        return enginePower * 0.01;
    }
}

public class TruckTest {

    @Test
    void bedCannotRaiseWhileMoving() {
        TestTruck truck = new TestTruck();
        truck.startEngine();
        truck.gas(1);
        truck.raiseBed(20);

        assertEquals(0.0, truck.getBedAngle());
    }

    @Test
    void bedCanRaiseWhenStopped() {
        TestTruck truck = new TestTruck();
        truck.raiseBed(30);

        assertEquals(30.0, truck.getBedAngle());
    }

    @Test
    void bedCannotExceedMaxAngle() {
        TestTruck truck = new TestTruck();
        truck.raiseBed(100);

        assertEquals(70.0, truck.getBedAngle());
    }

    @Test
    void truckCannotMoveIfBedRaised() {
        TestTruck truck = new TestTruck();
        truck.raiseBed(20);
        truck.startEngine();
        truck.gas(1);

        double yBefore = truck.getY();
        truck.move();

        assertEquals(yBefore, truck.getY());
    }
}

