package Main;

import java.util.Random;

public final class VehicleFactory {
    private static final Random RAND = new Random();

    public static Vehicle createRandom() {
        return switch (RAND.nextInt(3)) {
            case 0 -> new Volvo240();
            case 1 -> new Saab95();
            default -> new Scania();
        };
    }

    public static Vehicle create(String type) {
        return switch (type.toLowerCase()) {
            case "volvo", "volvo240" -> new Volvo240();
            case "saab", "saab95"     -> new Saab95();
            case "scania"             -> new Scania();
            default                   -> createRandom();
        };
    }
}