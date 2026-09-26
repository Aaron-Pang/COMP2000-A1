package growables.plant.flower;

import growables.plant.*;
import java.awt.*;

import placed_objects.ground.Ground;
import placed_objects.sky.Sky;

public abstract class Flower extends Plant {
    public PlantState bloomState;

    public Flower(Point p, int growthDelay, int size, Sky sky, Ground ground) {
        super(p, growthDelay, size, sky, ground);
        bloomState = new BloomState(this);
    }

    public void bloom(){    //Display the flower blooming
        this.setBackground(Color.RED);
        if ((int) (Math.random() * 100) == 0) {
            spread();
        }
    }
}
