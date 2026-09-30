package growables;
import java.awt.*;
import placed_objects.Patch;

public interface Growable {
    public void spread();     //Different things spread in different ways, e.g. wind, bees, spores
    public Point getPosition();
    public String getState();
    public void increaseSpreadNum(double factor);
    public void increaseLifespan(double factor);
    public void tick();
    public Rectangle getHitbox();
    public void givePatch(Patch<? extends Growable> p);
}
