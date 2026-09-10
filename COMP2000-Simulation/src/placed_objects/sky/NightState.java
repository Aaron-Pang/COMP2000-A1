package placed_objects.sky;

import java.awt.*;
import supplementary.Window;

public class NightState implements SkyState {
    public static final String name = "NIGHT";
    Sky sky;

    public NightState(Sky sky) {
        this.sky = sky;
    }

    @Override
    public void paintComponent(Graphics g) {
        sky.setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        double p = (((double) sky.hour)/12000) - 1;
        g.fillOval((int) (p * (double) Window.WIN_WIDTH) - 50, 0, 50, 50);
    }

    @Override
    public void checkChange() {
        if(sky.hour < 12000) {
            //Change to day
            sky.state = sky.sunnyState;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
