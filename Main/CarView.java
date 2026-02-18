package Main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CarView extends JFrame {

    private static final int X = 800;
    private static final int Y = 800;

    CarController carC;
    public DrawPanel drawPanel;

    JPanel controlPanel = new JPanel();
    JPanel gasPanel = new JPanel();

    JSpinner gasSpinner;
    int gasAmount = 0;

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton leftButton = new JButton("Turn Left");
    JButton rightButton = new JButton("Turn Right");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");
    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    public CarView(String framename, CarController cc, List<Vehicle> cars) {
        this.carC = cc;

        drawPanel = new DrawPanel(X, Y - 240, cars);

        initComponents(framename);
    }

    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);

        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, 0, 100, 1);

        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(e ->
                gasAmount = (int) gasSpinner.getValue());

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(new JLabel("Amount of gas"), BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(3, 4));
        controlPanel.setBackground(Color.CYAN);

        controlPanel.add(gasButton);
        controlPanel.add(brakeButton);
        controlPanel.add(leftButton);
        controlPanel.add(rightButton);
        controlPanel.add(turboOnButton);
        controlPanel.add(turboOffButton);
        controlPanel.add(liftBedButton);
        controlPanel.add(lowerBedButton);
        controlPanel.add(startButton);
        controlPanel.add(stopButton);

        this.add(controlPanel);

        gasButton.addActionListener(e -> carC.gas(gasAmount));
        brakeButton.addActionListener(e -> carC.brake(gasAmount));
        startButton.addActionListener(e -> carC.startAll());
        stopButton.addActionListener(e -> carC.stopAll());
        leftButton.addActionListener(e -> carC.turnLeft());
        rightButton.addActionListener(e -> carC.turnRight());
        turboOnButton.addActionListener(e -> carC.turboOn());
        turboOffButton.addActionListener(e -> carC.turboOff());
        liftBedButton.addActionListener(e -> carC.liftBed());
        lowerBedButton.addActionListener(e -> carC.lowerBed());

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
