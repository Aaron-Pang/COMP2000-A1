package growables.plant.flower.sunflower;

import growables.plant.*;
import java.awt.*;

public class SunflowerAdultState implements PlantState{
    public static final String name = "ADULT";

    Sunflower flower;

    public SunflowerAdultState(Sunflower flower) {
        this.flower = flower;
    }

    @Override
    public void paintComponent(Graphics g) {
        flower.setBackground(new Color(1, 71, 4));
        flower.setBounds(flower.position.x-flower.size/2, flower.position.y-flower.size/2, flower.size, flower.size);
    }

    @Override
    public void checkChange(long lifespan) {
        //If day and adult, set state to blooming
        if(lifespan > flower.growthDelay * 6) {
            flower.state = flower.deadState;
        } else if(flower.environmentState.equals("SUNNY")){    //TODO: Change condition to only bloom at night usisng observer
            flower.state = flower.bloomState;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
