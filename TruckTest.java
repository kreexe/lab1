import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TruckTest {

    @Test
    void truckIsSubclassOfVehicle() throws ClassNotFoundException {
        Class<?> truckCls = Class.forName("Truck");
        assertTrue(Vehicle.class.isAssignableFrom(truckCls), "Truck måste ärva från Vehicle");
    }

    @Test
    void cannotRaiseOrLowerBedWhenMoving() {
        TestTruck t = new TestTruck();
        // simulera rörelse
        t.setCurrentSpeedForTest(5.0);
        double before = t.getBedAngle();
        t.raiseBed(10.0);
        assertEquals(before, t.getBedAngle(), 1e-9, "Flaket ska inte kunna höjas när lastbilen rör sig");
        t.lowerBed(10.0);
        assertEquals(before, t.getBedAngle(), 1e-9, "Flaket ska inte kunna sänkas när lastbilen rör sig");
    }

    @Test
    void cannotAccelerateWhenBedNotFullyDown() {
        TestTruck t = new TestTruck();
        // sätt flaket upp <0
        t.setBedAngleForTest(15.0);

        double before = t.getCurrentSpeed();
        t.startEngine();        // ska inte starta eftersom bedAngle != 0
        t.gas(0.5);             // ska ignoreras eftersom bedAngle != 0
        double after = t.getCurrentSpeed();
        assertEquals(before, after, 1e-9, "Ska inte kunna accelerera när flaket inte är helt nerfällt");

        // verifiera att när flaket är nerfällt så går det att accelerera
        TestTruck t2 = new TestTruck();
        t2.setBedAngleForTest(0.0);
        double before2 = t2.getCurrentSpeed();
        t2.startEngine();
        t2.gas(0.5);
        double after2 = t2.getCurrentSpeed();
        assertTrue(after2 >= before2, "När flaket är nerfällt ska gas påverka hastigheten (minst ej mindre än före)");
        assertTrue(after2 > before2 || before2 > 0 || after2 > 0, "Förväntar någon ökning eller åtminstone ingen minskning (implementation kan variera)");
    }
}