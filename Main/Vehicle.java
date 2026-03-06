package Main;
import java.awt.*;

public abstract class Vehicle implements Movable {
    protected int nrDoors;
    protected double enginePower;
    protected double currentSpeed;
    protected Color color;
    protected String modelName;

    // Position via Location 
    protected Location location = new Location(0,0);

    protected enum Direction { NORTH, EAST, SOUTH, WEST }
    protected Direction direction = Direction.NORTH;

    protected Vehicle() {
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
        currentSpeed = Math.min(currentSpeed + speedFactor() * amount, enginePower);
    }

    protected void decrementSpeed(double amount) {
        currentSpeed = Math.max(currentSpeed - speedFactor() * amount, 0);
    }

   
    public void gas(double amount) {
        if (amount < 0 || amount > 1) return;
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1) return;
        decrementSpeed(amount);
    }

    // implementation av movable
    @Override
    public void move() {
        switch (direction) {
            case NORTH -> location.setY(location.getY() + currentSpeed);
            case SOUTH -> location.setY(location.getY() - currentSpeed);
            case EAST  -> location.setX(location.getX() - currentSpeed);
            case WEST  -> location.setX(location.getX() + currentSpeed);
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

    public void setPosition(double x, double y) {
        location.setPosition(x, y);
    }

    // getters för testerna
    public double getX() { return location.getX(); }
    public double getY() { return location.getY(); }
    public Direction getDirection() { return direction; }
}