package growables.plant;

import java.awt.*;

public class SeedState implements PlantState{
    public static final String name = "SEED";

    Plant plant;

    public SeedState(Plant plant) {
        this.plant = plant;
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("Seed");
        plant.setBackground(new Color(79, 46, 9));
    }

    @Override
    public void checkChange(long lifespan) {
        System.out.println(lifespan);
        if(lifespan > plant.growthDelay) {
            plant.state = plant.seedlingState;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
