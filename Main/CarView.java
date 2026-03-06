package Main;

import javax.swing.*;
import java.awt.*;

public class CarView extends JFrame {

    private static final int X = 800;
    private static final int Y = 600;

    private DrawPanel drawPanel;
    private CarSimModel model;
    private CarController carC;

    private JPanel controlPanel = new JPanel();

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");

    JButton startButton = new JButton("Start");
    JButton stopButton = new JButton("Stop");

    JButton leftButton = new JButton("Left");
    JButton rightButton = new JButton("Right");

    JButton turboOnButton = new JButton("Turbo On");
    JButton turboOffButton = new JButton("Turbo Off");

    JButton liftBedButton = new JButton("Lift Bed");
    JButton lowerBedButton = new JButton("Lower Bed");

    JButton addCarButton = new JButton("Add Car");
    JButton removeCarButton = new JButton("Remove Car");

    JSpinner gasSpinner = new JSpinner();

    public CarView(String framename, CarController cc, CarSimModel model) {
        super(framename);
        this.carC = cc;
        this.model = model;
        drawPanel = new DrawPanel(X, Y - 240, model);
        model.addObserver(drawPanel); // <-- lägg till denna rad

        this.setTitle(framename);
        this.setLayout(new BorderLayout());

        this.add(drawPanel, BorderLayout.CENTER);

        controlPanel.setLayout(new GridLayout(3, 5));

        controlPanel.add(gasSpinner);
        controlPanel.add(gasButton);
        controlPanel.add(brakeButton);
        controlPanel.add(startButton);
        controlPanel.add(stopButton);

        controlPanel.add(leftButton);
        controlPanel.add(rightButton);
        controlPanel.add(turboOnButton);
        controlPanel.add(turboOffButton);

        controlPanel.add(liftBedButton);
        controlPanel.add(lowerBedButton);

        controlPanel.add(addCarButton);
        controlPanel.add(removeCarButton);

        this.add(controlPanel, BorderLayout.SOUTH);

        gasButton.addActionListener(e -> carC.gas((int) gasSpinner.getValue()));
        brakeButton.addActionListener(e -> carC.brake((int) gasSpinner.getValue()));

        startButton.addActionListener(e -> carC.startAllCars());
        stopButton.addActionListener(e -> carC.stopAllCars());

        leftButton.addActionListener(e -> carC.turnLeft());
        rightButton.addActionListener(e -> carC.turnRight());

        turboOnButton.addActionListener(e -> carC.turboOn());
        turboOffButton.addActionListener(e -> carC.turboOff());

        liftBedButton.addActionListener(e -> carC.liftBed());
        lowerBedButton.addActionListener(e -> carC.lowerBed());

        addCarButton.addActionListener(e -> carC.addCar());
        removeCarButton.addActionListener(e -> carC.removeCar());

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public DrawPanel getDrawPanel() {
        return drawPanel;
    }
}