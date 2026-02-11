import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorkshopTest {

    @Test
    void addAndRemoveWorks() {
        Workshop<Car> shop = new Workshop<>(2);
        Car a = new Car("röd") { public String model() { return "A"; } };
        Car b = new Car("blå") { public String model() { return "B"; } };

        assertTrue(shop.addCar(a));
        assertTrue(shop.addCar(b));
        assertEquals(2, shop.getCurrentCount());

        Car removed = shop.removeCar();
        assertSame(b, removed, "Sista in ska ut först");
        assertEquals(1, shop.getCurrentCount());
    }

    @Test
    void capacityEnforced() {
        Workshop<Car> shop = new Workshop<>(1);
        Car a = new Car("svart") { public String model() { return "A"; } };
        Car b = new Car("vit")   { public String model() { return "B"; } };

        assertTrue(shop.addCar(a));
        assertFalse(shop.addCar(b), "Ska inte kunna lägga till när fullt");
        assertTrue(shop.isFull());
    }

    @Test
    void emptyRemoveReturnsNull() {
        Workshop<Car> shop = new Workshop<>(1);
        assertNull(shop.removeCar(), "removeCar på tom verkstad ska returnera null");
        assertTrue(shop.isEmpty());
    }
}