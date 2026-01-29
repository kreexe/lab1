import java.awt.*;

public abstract class Car implements Movable {

    protected int nrDoors;
    protected double enginePower;
    protected double currentSpeed;
    protected Color color;
    protected String modelName;

   
    protected double xPosition = 0.0;
    protected double yPosition = 0.0;

    protected enum Direction { NORTH, EAST, SOUTH, WEST }
    protected Direction direction = Direction.NORTH;

    protected Car() {
        stopEngine();
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    protected abstract double speedFactor();

    protected void incrementSpeed(double amount) {
        currentSpeed = Math.min(enginePower, currentSpeed + speedFactor() * amount);
    }

    protected void decrementSpeed(double amount) {
        currentSpeed = Math.max(0, currentSpeed - speedFactor() * amount);
    }


    public void gas(double amount) {
        if (amount < 0 || amount > 1) {
            return;
        }
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1) {
            return;
        }
        decrementSpeed(amount);
    }

    @Override
    public void move() {
        switch (direction) {
            case NORTH -> yPosition += currentSpeed;
            case EAST  -> xPosition += currentSpeed;
            case SOUTH -> yPosition -= currentSpeed;
            case WEST  -> xPosition -= currentSpeed;
        }
    }

    @Override
    public void turnLeft() {
        switch (direction) {
            case NORTH -> direction = Direction.WEST;
            case WEST  -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.EAST;
            case EAST  -> direction = Direction.NORTH;
        }
    }

    @Override
    public void turnRight() {
        switch (direction) {
            case NORTH -> direction = Direction.EAST;
            case EAST  -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.WEST;
            case WEST  -> direction = Direction.NORTH;
        }
    }

    public double getX() { return xPosition; }
    public double getY() { return yPosition; }
}
