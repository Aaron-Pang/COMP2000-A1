package growables;
import java.awt.*;

public interface Growable extends GrowableSubject {
    public void spread();     //Different things spread in different ways, e.g. wind, bees, spores
    public Point getPosition();
    public String getState();
    public void increaseSpreadNum(double factor);
    public void tick();
    public Rectangle getHitbox();
    //public void kill();
}
