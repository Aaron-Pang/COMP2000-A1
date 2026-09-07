import java.awt.*;

public class Radius {
    int minX;
    int maxX;
    int minY;
    int maxY;

    Radius(Point center, int radius) {
        minX = (int) center.getX() - radius;
        maxX = (int) center.getX() + radius;

        minY = (int) center.getY() - radius;
        maxY = (int) center.getY() + radius;
    }

    //To be completed
    Point getRandomPoint() {
        int x = (int) ((Math.random() * (maxX - minX)) + minX);
        int y = (int) ((Math.random() * (maxY - minY)) + minY);
        
        return new Point(x, y);
    }

    boolean isPointInRadius(Point p) {
        return(p.x >= minX && p.x <= maxX && p.y >= minY && p.y <= maxY);
    }
}
