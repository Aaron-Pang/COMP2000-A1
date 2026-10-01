package growables.plant.flower.sunflower;

import growables.plant.*;
import java.awt.*;

public class SunflowerJuvenileState implements PlantState{
    public static final String NAME = "JUVENILE";

    Sunflower flower;

    public SunflowerJuvenileState(Sunflower flower) {
        this.flower = flower;
    }

    @Override
    public void paintComponent(Graphics g) {
        flower.setBounds(flower.position.x-Sunflower.SIZE/4, flower.position.y-Sunflower.SIZE/4, Sunflower.SIZE/2, Sunflower.SIZE/2);
        g.setColor(new Color(2, 184, 9));
        g.fillOval(0, 0, flower.getBounds().width, flower.getBounds().height);
    }

    @Override
    public void checkChange(long lifespan) {
        flower.fightingPower = 4;
        if(lifespan > flower.growthDelay * 3) {
            flower.state = flower.adultState;
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
