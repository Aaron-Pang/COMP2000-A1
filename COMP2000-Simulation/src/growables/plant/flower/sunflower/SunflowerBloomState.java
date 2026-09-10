package growables.plant.flower.sunflower;

import growables.plant.*;
import java.awt.*;

public class SunflowerBloomState implements PlantState{
    public static final String name = "BLOOMING";

    Sunflower flower;

    public SunflowerBloomState(Sunflower flower) {
        this.flower = flower;
    }

    @Override
    public void paintComponent(Graphics g) {
        flower.setBackground(Color.YELLOW);
    }

    @Override
    public void checkChange(long lifespan) {
        if(lifespan > flower.growthDelay * 4) {     //TODO: If night, stop blooming
            flower.state = flower.deadState;
        }
        flower.bloom();
    }

    @Override
    public String getName() {
        return name;
    }
}
