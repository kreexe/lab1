package Main;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel implements Observer {

    private CarSimModel model;

    private Image volvoImage;
    private Image saabImage;
    private Image scaniaImage;
    private Image workshopImage;

    public DrawPanel(int x, int y, CarSimModel model) {

        this.model = model;

        model.addObserver(this);

        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.WHITE);

        volvoImage = new ImageIcon(getClass().getResource("pics/Volvo240.jpg")).getImage();
        saabImage = new ImageIcon(getClass().getResource("pics/Saab95.jpg")).getImage();
        scaniaImage = new ImageIcon(getClass().getResource("pics/Scania.jpg")).getImage();
        workshopImage = new ImageIcon(getClass().getResource("pics/VolvoBrand.jpg")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int workshopWidth = 175;
        int workshopHeight = 175;

        int wx = (getWidth() - workshopWidth) / 2;
        int wy = (getHeight() - workshopHeight) / 2;

        g.drawImage(workshopImage, wx, wy, workshopWidth, workshopHeight, null);

        for (Vehicle car : model.getCars()) {
            Image img = null;

            if (car.getModelName().equals("Volvo240"))
                img = volvoImage;
            else if (car.getModelName().equals("Saab95"))
                img = saabImage;
            else if (car.getModelName().equals("Scania"))
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

    @Override
    public void update() {
        repaint();
    }
}