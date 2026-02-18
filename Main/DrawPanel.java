
package Main;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawPanel extends JPanel {

    ArrayList<BufferedImage> images = new ArrayList<>();
    ArrayList<Point> points = new ArrayList<>();

    BufferedImage volvoWorkshopImage;
    Point workshopPoint = new Point(300, 300);

    public DrawPanel(int x, int y) {

        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        try {
            images.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            images.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            images.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));

            points.add(new Point(0, 0));
            points.add(new Point(0, 100));
            points.add(new Point(0, 200));

            volvoWorkshopImage =
                    ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void moveit(int index, int x, int y) {
        points.get(index).x = x;
        points.get(index).y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < images.size(); i++) {
            g.drawImage(images.get(i), points.get(i).x, points.get(i).y, null);
        }

        g.drawImage(volvoWorkshopImage,
                workshopPoint.x,
                workshopPoint.y,
                null);
    }
}
