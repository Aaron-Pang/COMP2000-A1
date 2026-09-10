package growables.plant.flower;
import growables.plant.Plant;
import java.awt.*;

public abstract class Flower extends Plant {
    public BloomState bloomState;

    public Flower(Point p, int growthDelay, int size) {
        super(p, growthDelay, size);
        bloomState = new BloomState(this);
    }

    public void bloom(){    //Display the flower blooming
        this.setBackground(Color.RED);
        if ((int) (Math.random() * 100) == 0) {
            spread();
        }
    }
}
