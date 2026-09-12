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
        if(lifespan > flower.growthDelay * 6) {
            flower.state = flower.deadState;
        } else if(flower.environmentState.equals("SUNNY")) {
            flower.bloom();
        } else {
            flower.state = flower.adultState;
        }
        
    }

    @Override
    public String getName() {
        return name;
    }
}
