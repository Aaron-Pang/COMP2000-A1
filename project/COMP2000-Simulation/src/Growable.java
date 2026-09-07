import java.awt.*;

public interface Growable{
    abstract void spread();     //Different things spread in different ways, e.g. wind, bees, spores
    abstract void grow();
    abstract Point getPosition();
    abstract boolean isColliding();
    abstract void increaseSpreadNum(double factor);
    abstract void kill();
}
