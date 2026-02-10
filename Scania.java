import java.awt.*;

public class Scania extends Truck {

    private static final double trimFactor = 1.25;

    public Scania() {
        nrDoors = 2;
        enginePower = 100;
        color = Color.black;
        modelName = "Scania";
        stopEngine();
    }

    @Override
    protected double speedFactor() {
        return enginePower * 0.01 * trimFactor;
    }
}
