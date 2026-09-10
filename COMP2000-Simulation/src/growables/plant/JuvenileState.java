package growables.plant;

import java.awt.*;

public class JuvenileState implements PlantState{
    public static final String name = "JUVENILE";

    Plant plant;

    public JuvenileState(Plant plant) {
        this.plant = plant;
    }

    @Override
    public void paintComponent(Graphics g) {
        plant.setBackground(new Color(1, 120, 5));
    }

    @Override
    public void checkChange(long lifespan) {
        if(lifespan > plant.growthDelay * 3) {
            plant.state = plant.adultState;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
