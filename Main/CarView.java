package Main;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class CarView extends JFrame {

    private static final int X = 800;
    private static final int Y = 800;

    CarController carC;

    DrawPanel drawPanel = new DrawPanel(X, Y - 240);

    JPanel controlPanel = new JPanel();
    JPanel gasPanel = new JPanel();

    JSpinner gasSpinner;
    int gasAmount = 0;

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    public CarView(String framename, CarController cc) {
        this.carC = cc;
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

        controlPanel.setLayout(new GridLayout(2, 4));
        controlPanel.add(gasButton);
        controlPanel.add(turboOnButton);
        controlPanel.add(liftBedButton);
        controlPanel.add(brakeButton);
        controlPanel.add(turboOffButton);
        controlPanel.add(lowerBedButton);

        controlPanel.setPreferredSize(new Dimension((X / 2) + 4, 200));
        controlPanel.setBackground(Color.CYAN);

        this.add(controlPanel);

        this.add(startButton);
        this.add(stopButton);

        
        gasButton.addActionListener(e -> carC.gas(gasAmount));
        brakeButton.addActionListener(e -> carC.brake(gasAmount));
        startButton.addActionListener(e -> carC.startAll());
        stopButton.addActionListener(e -> carC.stopAll());
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
