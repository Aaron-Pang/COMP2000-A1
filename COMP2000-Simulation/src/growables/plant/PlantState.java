package growables.plant;

import java.awt.Graphics;

public interface PlantState {
    public void paintComponent(Graphics g);
    public void checkChange(long lifespan);
    public String getName();
}
