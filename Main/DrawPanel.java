package Main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawPanel extends JPanel {

    private List<Vehicle> cars;

    private Image volvoImage;
    private Image saabImage;
    private Image scaniaImage;
    private Image workshopImage;

    public DrawPanel(int x, int y, List<Vehicle> cars) {
        this.cars = cars;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));

        try {
            volvoImage = new ImageIcon(getClass().getResource("/Main/pics/Volvo240.jpg")).getImage();
            saabImage = new ImageIcon(getClass().getResource("/Main/pics/Saab95.jpg")).getImage();
            scaniaImage = new ImageIcon(getClass().getResource("/Main/pics/Scania.jpg")).getImage();
            workshopImage = new ImageIcon(getClass().getResource("/Main/pics/Workshop.png")).getImage();
        } catch (Exception e) {
            System.out.println("Bilder kunde inte laddas!");
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Rita workshop i mitten
        int workshopWidth = 200;
        int workshopHeight = 200;

        int wx = (getWidth() - workshopWidth) / 2;
        int wy = (getHeight() - workshopHeight) / 2;

        if (workshopImage != null) {
            g.drawImage(workshopImage, wx, wy, workshopWidth, workshopHeight, null);
        }

        // Rita bilar
        for (Vehicle car : cars) {

            Image img = null;

            if (car instanceof Volvo240)
                img = volvoImage;
            else if (car instanceof Saab95)
                img = saabImage;
            else if (car instanceof Scania)
                img = scaniaImage;

            if (img != null) {
                g.drawImage(img,
                        (int) car.getX(),
                        (int) car.getY(),
                        100,
                        60,
                        null);
            }
        }
    }
}
