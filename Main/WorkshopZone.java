package Main;

import java.awt.Rectangle;

public class WorkshopZone<T extends Car> {

    private int width = 175;
    private int height = 175;
    private Workshop<T> workshop;

    public WorkshopZone(int width, int height, Workshop<T> workshop) {
        this.width = width;
        this.height = height;
        this.workshop = workshop;
    }

    public Rectangle getBounds(int panelWidth, int panelHeight) {
        int x = (panelWidth - width) / 2;
        int y = (panelHeight - height) / 2;
        return new Rectangle(x, y, width, height);
    }

    public boolean contains(T car, int panelWidth, int panelHeight) {
        Rectangle bounds = getBounds(panelWidth, panelHeight);
        return bounds.contains(car.getX(), car.getY());
    }

    public boolean tryAddCar(T car, int panelWidth, int panelHeight) {
        if (contains(car, panelWidth, panelHeight)) {
            return workshop.addCar(car);
        }
        return false;
    }

    public Workshop<T> getWorkshop() {
        return workshop;
    }
}