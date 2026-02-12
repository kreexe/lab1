package Main;
import java.awt.*;

public class Saab95 extends Car {

    private boolean turboOn;

    public Saab95() {
        nrDoors = 2;
        enginePower = 125;
        color = Color.red;
        modelName = "Saab95";
        turboOn = false;
        stopEngine();
    }

    public void setTurboOn() {
        turboOn = true;
    }

    public void setTurboOff() {
        turboOn = false;
    }

    @Override
    protected double speedFactor() {
        double turbo = turboOn ? 1.3 : 1.0;
        return enginePower * 0.01 * turbo;
    }
}
