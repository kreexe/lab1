import java.awt.*;

public class Scania extends Truck {

    public Scania() {
        nrDoors = 2;
        enginePower = 90;
        color = Color.gray;
        modelName = "Scania";
        stopEngine();
    }

    @Override
    protected double speedFactor() {
        return enginePower * 0.01;
    }
}
