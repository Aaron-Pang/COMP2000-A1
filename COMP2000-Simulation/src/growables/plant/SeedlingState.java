package growables.plant;

import java.awt.*;

public class SeedlingState implements PlantState{
    public static final String name = "SEEDLING";

    Plant plant;

    public SeedlingState(Plant plant) {
        this.plant = plant;
    }

    @Override
    public void paintComponent(Graphics g) {
        plant.setBackground(new Color(2, 184, 9));
    }

    @Override
    public void checkChange(long lifespan) {
        if(lifespan > plant.growthDelay * 2) {
            plant.state = plant.juvenileState;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
