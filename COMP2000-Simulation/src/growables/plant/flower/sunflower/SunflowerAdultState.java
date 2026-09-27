package growables.plant.flower.sunflower;

import growables.plant.*;
import java.awt.*;

public class SunflowerAdultState implements PlantState{
    public static final String NAME = "ADULT";

    Sunflower flower;

    public SunflowerAdultState(Sunflower flower) {
        this.flower = flower;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(1, 71, 4));
        g.fillOval(0, 0, Sunflower.SIZE, Sunflower.SIZE);
        flower.setBounds(flower.position.x-Sunflower.SIZE/2, flower.position.y-Sunflower.SIZE/2, Sunflower.SIZE, Sunflower.SIZE);
    }

    @Override
    public void checkChange(long lifespan) {
        //If day and adult, set state to blooming
        if(lifespan > Sunflower.GROWTH_DELAY * 6) {
            flower.state = flower.deadState;
        } else if(flower.environmentState.equals("SUNNY")){
            flower.state = flower.bloomState;
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
