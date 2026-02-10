

public abstract class Truck extends Vehicle {

    // flak för ALLA truck-modeller, som ska implemetneras i uppg 2 också
    protected double bedAngle = 0.0;
    protected static final double MAX_BED_ANGLE = 70.0;

    protected Truck() {
        super();
    }

    // läser av det första värdet som anges i modellen (scania)
    public double getBedAngle() {
        return bedAngle;
    }

    // gör så att flaket kan höjas bara när den står
    public void raiseBed(double degrees) {
        if (getCurrentSpeed() != 0) return;
        bedAngle = Math.min(MAX_BED_ANGLE, bedAngle + degrees);
    }

    // gör så att flaket kan sänkas när lastbilen står stilla
    public void lowerBed(double degrees) {
        if (getCurrentSpeed() != 0) return;
        bedAngle = Math.max(0.0, bedAngle - degrees);
    }

    // förhindrar acceleration eller att man startar motorn om inte flaket är helt nerfält, eller också att den ens kan röra på sig
    @Override
    public void startEngine() {
        if (bedAngle == 0.0) super.startEngine();
    }

    @Override
    public void gas(double amount) {
        if (bedAngle == 0.0) super.gas(amount);
    }

    @Override
    public void move() {
        if (bedAngle == 0.0) super.move();
    }
}
