import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HaulerTest {

    @Test
    void haulerIsSubclassOfCarOrTruck() throws ClassNotFoundException {
        Class<?> haulerCls = Class.forName("Hauler");
        boolean isCarSubclass = Car.class.isAssignableFrom(haulerCls);
        boolean isTruckSubclass = false;
        try {
            Class<?> truckCls = Class.forName("Truck");
            isTruckSubclass = truckCls.isAssignableFrom(haulerCls);
        } catch (ClassNotFoundException ignored) { }
        assertTrue(isCarSubclass || isTruckSubclass, "Hauler måste ärva från Car eller Truck");
    }

    @Test
    void cannotLoadWhenBedRaised() {
        TestHauler h = new TestHauler();
        h.setPosition(0, 0);
        h.setBedAngle(15.0); // uppfälld
        TestBil c = new TestBil("röd");
        c.setPosition(0, 0);
        assertFalse(h.loadCar(c), "Ska inte kunna ladda när bedAngle != 0");
        assertEquals(0, h.getLoadedCount());
    }

    @Test
    void cannotUnloadWhenBedRaised() {
        TestHauler h = new TestHauler();
        h.setPosition(0, 0);
        h.setBedAngle(0.0);
        TestBil c = new TestBil("blå");
        c.setPosition(0, 0);
        assertTrue(h.loadCar(c));
        h.setBedAngle(20.0); // uppfälld
        assertNull(h.unloadCar(), "Ska inte kunna lossa när bedAngle != 0");
        assertEquals(1, h.getLoadedCount());
    }

}