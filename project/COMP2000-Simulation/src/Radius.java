import java.awt.*;
import java.util.ArrayList;

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
        if(p.x >= minX && p.x <= maxX && p.y >= minY && p.y <= maxY) {
            return true;
        }
        return false;
    }

    ArrayList<Growable> getObjectsInRadius(Ground ground) {
        ArrayList<Growable> items = new ArrayList<>();
        Component[] comps = ground.getComponents();
        for(int i = 0; i < comps.length; i++) {
            if(comps[i] instanceof Growable) {
                Growable temp = (Growable) comps[i];
                Point p = temp.getPosition();
                if(isPointInRadius(p)) {
                    items.add(temp);
                }
            }
        }
        return null;
    }

    <T extends Growable> ArrayList<T> getObjects(Ground ground) {
        ArrayList<T> items = new ArrayList<>();
        

        return items;
    }
}
