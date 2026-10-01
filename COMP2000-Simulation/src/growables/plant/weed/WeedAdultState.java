package growables.plant.weed;

import growables.plant.PlantState;
import java.awt.Graphics;

public class WeedAdultState implements PlantState {
    public static final String NAME = "ADULT";
    Weed weed;

    public WeedAdultState(Weed weed) {
        this.weed = weed;
    }

    @Override
    public void checkChange(long lifespan) {
        weed.fightingPower = 5;
        weed.spread();
        if (lifespan > 20000 + (weed.random.nextInt(0, 20) * 1000)) {
            weed.state = weed.deadState;
        }
    }

    @Override
    public void paintComponent(Graphics g) {

    }

    @Override
    public String getName() {
        return NAME;
    }
}
