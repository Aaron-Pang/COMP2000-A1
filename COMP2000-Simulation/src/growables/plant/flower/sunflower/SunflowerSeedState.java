package growables.plant.flower.sunflower;

import growables.plant.*;
import java.awt.*;

public class SunflowerSeedState implements PlantState{
    public static final String name = "SEED";

    Sunflower flower;

    public SunflowerSeedState(Sunflower flower) {
        this.flower = flower;
    }

    @Override
    public void paintComponent(Graphics g) {
        flower.setOpaque(false);
        g.setColor(new Color(79, 46, 9));
        g.fillOval(0, 0, flower.getBounds().width, flower.getBounds().height);
        flower.setBounds(flower.position.x-flower.size/4, flower.position.y-flower.size/4, flower.size/4, flower.size/4);
    }

    @Override
    public void checkChange(long lifespan) {
        if(lifespan > flower.growthDelay) {
            flower.state = flower.seedlingState;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
