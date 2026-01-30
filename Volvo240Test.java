import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

public class Volvo240Test {

    private static final double EPS = 1e-9;

    @Test
    void constructorAndGetters() {
        Volvo240 v = new Volvo240();
        assertEquals(4, v.getNrDoors());
        assertEquals(100.0, v.getEnginePower(), EPS);
        assertEquals(Color.black, v.getColor());
        assertEquals(0.0, v.getCurrentSpeed(), EPS);
    }

    @Test
    void startAndStopEngine() {
        Volvo240 v = new Volvo240();
        v.startEngine();
        assertTrue(v.getCurrentSpeed() > 0);
        v.stopEngine();
        assertEquals(0.0, v.getCurrentSpeed(), EPS);
    }

    @Test
    void gasAndBrake() {
        Volvo240 v = new Volvo240();
        v.startEngine();
        v.gas(1.0);
        double speed = v.getCurrentSpeed();
        v.brake(1.0);
        assertTrue(v.getCurrentSpeed() < speed);
    }

    @Test
    void speedIsClamped() {
        Volvo240 v = new Volvo240();
        v.startEngine();

        for (int i = 0; i < 1000; i++) v.gas(1.0);
        assertTrue(v.getCurrentSpeed() <= v.getEnginePower() + EPS);

        for (int i = 0; i < 1000; i++) v.brake(1.0);
        assertTrue(v.getCurrentSpeed() >= 0);
    }

    @Test
    void gasOutsideIntervalDoesNothing() {
        Volvo240 v = new Volvo240();
        v.startEngine();
        double speed = v.getCurrentSpeed();

        v.gas(2.0);
        v.gas(-1.0);

        assertEquals(speed, v.getCurrentSpeed(), EPS);
    }

    @Test
    void brakeOutsideIntervalDoesNothing() {
        Volvo240 v = new Volvo240();
        v.startEngine();
        double speed = v.getCurrentSpeed();

        v.brake(2.0);
        v.brake(-1.0);

        assertEquals(speed, v.getCurrentSpeed(), EPS);
    }

    @Test
    void movementAndTurns() {
        Volvo240 v = new Volvo240();
        v.startEngine();

        double y0 = v.getY();
        v.move();
        assertTrue(v.getY() > y0);

        v.turnRight();
        double x0 = v.getX();
        v.move();
        assertTrue(v.getX() > x0);
    }

    @Test
    void colorCanBeChanged() {
        Volvo240 v = new Volvo240();
        v.setColor(Color.GREEN);
        assertEquals(Color.GREEN, v.getColor());
    }
}


// kompilera allt-- javac -cp .:lib/junit-platform-console-standalone-6.0.2.jar *.java
// kör volvo test bara--  
//java -jar lib/junit-platform-console-standalone-6.0.2.jar execute \
//--class-path . \
//--select-class Volvo240Test

