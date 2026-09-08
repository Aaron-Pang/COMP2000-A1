package growables;
import java.awt.*;

public interface Growable {
    public void spread();     //Different things spread in different ways, e.g. wind, bees, spores
    public void grow();
    public Point getPosition();
    public boolean isColliding();
    public int getState();
    public void increaseSpreadNum(double factor);
    //public void kill();
}
