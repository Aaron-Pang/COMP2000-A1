package growables.plant.weed;

import growables.plant.PlantState;
import java.awt.Graphics;

public class WeedAdultState implements PlantState {
    public static final String name = "ADULT";
    Weed weed;

    public WeedAdultState(Weed weed) {
        this.weed = weed;
    }

    @Override
    public void checkChange(long lifespan) {
        //System.out.println("Checking");
        weed.spread();
        if ((int) (Math.random() * 3) == 0) {
            weed.state = weed.deadState;
        }
    }

    @Override
    public void paintComponent(Graphics g) {

    }

    @Override
    public String getName() {
        return name;
    }
}
