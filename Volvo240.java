import java.awt.*;

public class Volvo240 extends Car {

    private static final double trimFactor = 1.25;

    public Volvo240() {
        nrDoors = 4;
        enginePower = 100;
        color = Color.black;
        modelName = "Volvo240";
        stopEngine();
    }

    @Override
    protected double speedFactor() {
        return enginePower * 0.01 * trimFactor;
    }
}
