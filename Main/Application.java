package Main;

public class Application {
    public static void main(String[] args) {
        CarSimModel model = new CarSimModel();
        CarController controller = new CarController(model);
        CarView view = new CarView("CarSim", controller, model);
        controller.setView(view);
        controller.start();
    }
}