package growables.plant.flower.sunflower;

import growables.plant.*;
import java.awt.*;

public class SunflowerSeedState implements PlantState{
    public static final String NAME = "SEED";

    Sunflower flower;

    public SunflowerSeedState(Sunflower flower) {
        this.flower = flower;
    }

    @Override
    public void paintComponent(Graphics g) {
        flower.setOpaque(false);
        g.setColor(new Color(79, 46, 9));
        g.fillOval(0, 0, flower.getBounds().width, flower.getBounds().height);
        flower.setBounds(flower.position.x-Sunflower.SIZE/4, flower.position.y-Sunflower.SIZE/4, Sunflower.SIZE/4, Sunflower.SIZE/4);
    }

    @Override
    public void checkChange(long lifespan) {
        if(lifespan > flower.sunflowerGrowthDelay) {
            flower.state = flower.seedlingState;
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
