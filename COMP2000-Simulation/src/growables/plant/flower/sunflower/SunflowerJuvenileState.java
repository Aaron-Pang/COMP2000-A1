package growables.plant.flower.sunflower;

import growables.plant.*;
import java.awt.*;

public class SunflowerJuvenileState implements PlantState{
    public static final String name = "JUVENILE";

    Sunflower flower;

    public SunflowerJuvenileState(Sunflower flower) {
        this.flower = flower;
    }

    @Override
    public void paintComponent(Graphics g) {
        flower.setBounds(flower.position.x-flower.size/4, flower.position.y-flower.size/4, flower.size/2, flower.size/2);
        g.setColor(new Color(2, 184, 9));
        g.fillOval(0, 0, flower.getBounds().width, flower.getBounds().height);
    }

    @Override
    public void checkChange(long lifespan) {
        if(lifespan > flower.growthDelay * 3) {
            flower.state = flower.adultState;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
