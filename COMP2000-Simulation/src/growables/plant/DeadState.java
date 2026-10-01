package growables.plant;

import java.awt.*;

public class DeadState implements PlantState{
    public static final String NAME = "DEAD";

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
        plant.fightingPower = 0;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
