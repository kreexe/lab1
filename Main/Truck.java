package Main;

public abstract class Truck extends Vehicle {

    protected double bedAngle = 0.0;
    protected static final double MAX_BED_ANGLE = 70.0;

    protected Truck() {
        super();
    }

    public double getBedAngle() {
        return bedAngle;
    }

    // Höj flak – bara när bilen står helt stilla
    public void raiseBed(double degrees) {
        if (getCurrentSpeed() == 0) {
            bedAngle = Math.min(MAX_BED_ANGLE, bedAngle + degrees);
        }
    }

    // Sänk flak – bara när bilen står helt stilla
    public void lowerBed(double degrees) {
        if (getCurrentSpeed() == 0) {
            bedAngle = Math.max(0.0, bedAngle - degrees);
        }
    }

    // Starta bara om flaket är nere
    @Override
    public void startEngine() {
        if (bedAngle == 0.0) {
            super.startEngine();
        }
    }

    // Gasa bara om flaket är nere
    @Override
    public void gas(double amount) {
        if (bedAngle == 0.0) {
            super.gas(amount);
        }
    }

    // Rör sig bara om flaket är nere
    @Override
    public void move() {
        if (bedAngle == 0.0) {
            super.move();
        }
    }
}