package growables.plant;

import java.awt.*;

public class DeadState implements PlantState{
    public static final String name = "DEAD";

    Plant plant;

    public DeadState(Plant plant) {
        this.plant = plant;
    }

    @Override
    public void paintComponent(Graphics g) {
        plant.setBackground(Color.BLACK);
    }

    @Override
    public void checkChange(long lifespan) {
        if(lifespan > plant.growthDelay * 5) {
            //Delete plant
            plant.delete();
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
