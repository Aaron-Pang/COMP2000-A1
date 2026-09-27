package growables.plant.flower.sunflower;

import growables.plant.*;
import java.awt.*;

public class SunflowerBloomState implements PlantState{
    public static final String NAME = "BLOOMING";

    Sunflower flower;

    public SunflowerBloomState(Sunflower flower) {
        this.flower = flower;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(0, 0, flower.getBounds().width, flower.getBounds().height);
        g.setColor(new Color(79, 46, 9));
        g.fillOval(flower.getBounds().width/4, flower.getBounds().height/4, flower.getBounds().width/2, flower.getBounds().height/2);
    }

    @Override
    public void checkChange(long lifespan) {
        if(lifespan > Sunflower.GROWTH_DELAY * 6) {
            flower.state = flower.deadState;
        } else if(flower.environmentState.equals("SUNNY")) {
            flower.bloom();
        } else {
            flower.state = flower.adultState;
        }
        
    }

    @Override
    public String getName() {
        return NAME;
    }
}
