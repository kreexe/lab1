package Test;

import Main.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorkshopTest {

    private static class TestCar extends Car {

        public TestCar() {
            super();
        }

        @Override
        protected double speedFactor() {
            return 1.0;
        }
    }

    @Test
    void workshopStartsEmpty() {
        Workshop<TestCar> workshop = new Workshop<>(3);

        assertTrue(workshop.isEmpty());
        assertFalse(workshop.isFull());
        assertEquals(0, workshop.getCurrentCount());
        assertEquals(3, workshop.getMaxCapacity());
    }

    @Test
    void addCarWorks() {
        Workshop<TestCar> workshop = new Workshop<>(2);
        TestCar car = new TestCar();

        assertTrue(workshop.addCar(car));
        assertEquals(1, workshop.getCurrentCount());
    }

    @Test
    void cannotAddWhenFull() {
        Workshop<TestCar> workshop = new Workshop<>(1);

        TestCar car1 = new TestCar();
        TestCar car2 = new TestCar();

        assertTrue(workshop.addCar(car1));
        assertFalse(workshop.addCar(car2));

        assertTrue(workshop.isFull());
        assertEquals(1, workshop.getCurrentCount());
    }

    @Test
    void removeCarWorks() {
        Workshop<TestCar> workshop = new Workshop<>(2);
        TestCar car = new TestCar();

        workshop.addCar(car);

        TestCar removed = workshop.removeCar();

        assertEquals(car, removed);
        assertTrue(workshop.isEmpty());
    }

    @Test
    void removeFromEmptyReturnsNull() {
        Workshop<TestCar> workshop = new Workshop<>(2);

        assertNull(workshop.removeCar());
    }

    @Test
    void lifoOrderWorks() {
        Workshop<TestCar> workshop = new Workshop<>(3);

        TestCar car1 = new TestCar();
        TestCar car2 = new TestCar();
        TestCar car3 = new TestCar();

        workshop.addCar(car1);
        workshop.addCar(car2);
        workshop.addCar(car3);

        assertEquals(car3, workshop.removeCar());
        assertEquals(car2, workshop.removeCar());
        assertEquals(car1, workshop.removeCar());
    }
}
