package growables.plant.flower;

import java.awt.*;
import growables.plant.*;

public class BloomState implements PlantState{
    public static final String name = "BLOOMING";

    Flower flower;

    public BloomState(Flower flower) {
        this.flower = flower;
    }

    @Override
    public void paintComponent(Graphics g) {
        flower.setBackground(Color.RED);
    }

    @Override
    public void checkChange(long lifespan) {
        if(lifespan < flower.growthDelay * 4) {
            flower.state = flower.deadState;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
