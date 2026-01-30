import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

public class Saab95Test {

    private static final double EPS = 1e-9;

    @Test
    void constructorAndGetters() {
        Saab95 s = new Saab95();
        assertEquals(2, s.getNrDoors());
        assertEquals(125.0, s.getEnginePower(), EPS);
        assertEquals(Color.red, s.getColor());
        assertEquals(0.0, s.getCurrentSpeed(), EPS);
    }

    @Test
    void startAndStopEngine() {
        Saab95 s = new Saab95();
        s.startEngine();
        assertTrue(s.getCurrentSpeed() > 0);
        s.stopEngine();
        assertEquals(0.0, s.getCurrentSpeed(), EPS);
    }

    @Test
    void turboOnGivesHigherAcceleration() {
        Saab95 off = new Saab95();
        Saab95 on = new Saab95();

        off.startEngine();
        off.setTurboOff();
        off.gas(1.0);

        on.startEngine();
        on.setTurboOn();
        on.gas(1.0);

        assertTrue(on.getCurrentSpeed() > off.getCurrentSpeed());
    }

    @Test
    void gasAndBrake() {
        Saab95 s = new Saab95();
        s.startEngine();
        s.gas(1.0);
        double speed = s.getCurrentSpeed();
        s.brake(1.0);
        assertTrue(s.getCurrentSpeed() < speed);
    }

    @Test
    void speedIsClamped() {
        Saab95 s = new Saab95();
        s.startEngine();

        for (int i = 0; i < 1000; i++) s.gas(1.0);
        assertTrue(s.getCurrentSpeed() <= s.getEnginePower() + EPS);

        for (int i = 0; i < 1000; i++) s.brake(1.0);
        assertTrue(s.getCurrentSpeed() >= 0);
    }

    @Test
    void gasOutsideIntervalDoesNothing() {
        Saab95 s = new Saab95();
        s.startEngine();
        double speed = s.getCurrentSpeed();

        s.gas(2.0);
        s.gas(-1.0);

        assertEquals(speed, s.getCurrentSpeed(), EPS);
    }

    @Test
    void brakeOutsideIntervalDoesNothing() {
        Saab95 s = new Saab95();
        s.startEngine();
        double speed = s.getCurrentSpeed();

        s.brake(2.0);
        s.brake(-1.0);

        assertEquals(speed, s.getCurrentSpeed(), EPS);
    }

    @Test
    void movementAndTurns() {
        Saab95 s = new Saab95();
        s.startEngine();

        double y0 = s.getY();
        s.move();
        assertTrue(s.getY() > y0);

        s.turnRight();
        double x0 = s.getX();
        s.move();
        assertTrue(s.getX() > x0);
    }

    @Test
    void colorCanBeChanged() {
        Saab95 s = new Saab95();
        s.setColor(Color.BLUE);
        assertEquals(Color.BLUE, s.getColor());
    }
}


// kompilera allt.   javac -cp .:lib/junit-platform-console-standalone-6.0.2.jar *.java
// kör saab test bara-- 
//  java -jar lib/junit-platform-console-standalone-6.0.2.jar execute \
//  --class-path . \
//  --select-class Saab95Test

