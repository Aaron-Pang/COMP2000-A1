package supplementary;

import java.util.Random;

public class Direction {
    public double x;
    public double y;

    //These two create a unit vector
    public double dx;
    public double dy;

    public Direction(double x, double y) {
        Math.clamp(x, -20, 20);
        Math.clamp(y, -20, 20);

        this.x = x;
        this.y = y;

        double magnitude = Math.sqrt(x*x + y*y);
        dx = x/magnitude;
        dy = y/magnitude;
    }

    public static Direction getRandomDirection() {
        Random rand = new Random();
        double newDx = 20 * rand.nextDouble();
        double newDy = 20 * rand.nextDouble();

        return new Direction(newDx, newDy);
    }
}
