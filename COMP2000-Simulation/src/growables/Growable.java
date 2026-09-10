package growables;
import java.awt.*;

public interface Growable {
    public void spread();     //Different things spread in different ways, e.g. wind, bees, spores
    public Point getPosition();
    public boolean isColliding();
    public String getState();
    public void increaseSpreadNum(double factor);
    public void tick();
    //public void kill();
}
