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
        this.setBackground(Color.WHITE);

        
        // Samma sökväg som bilerna i filerna

        volvoImage = new ImageIcon("lab1/Main/pics/Volvo240.jpg").getImage();
        saabImage = new ImageIcon("lab1/Main/pics/Saab95.jpg").getImage();
        scaniaImage = new ImageIcon("lab1/Main/pics/Scania.jpg").getImage();
        workshopImage = new ImageIcon("lab1/Main/pics/VolvoBrand.jpg").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Storlek och plats på workshop
        int workshopWidth = 175;
        int workshopHeight = 175;

        int wx = (getWidth() - workshopWidth) / 2;
        int wy = (getHeight() - workshopHeight) / 2;

        g.drawImage(workshopImage, wx, wy, workshopWidth, workshopHeight, null);

        // rita bilarna
        for (Vehicle car : cars) {

            Image img = null;

            if (car instanceof Volvo240)
                img = volvoImage;
            else if (car instanceof Saab95)
                img = saabImage;
            else if (car instanceof Scania)
                img = scaniaImage;

            if (img != null) {
                g.drawImage(
                        img,
                        (int) Math.round(car.getX()),
                        (int) Math.round(car.getY()),
                        100,
                        60,
                        null
                );
            }
        }
    }
}
