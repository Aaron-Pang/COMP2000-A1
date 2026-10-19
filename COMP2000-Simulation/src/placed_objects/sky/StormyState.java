package placed_objects.sky;

import java.awt.*;

public class StormyState implements SkyState {
    public static final String NAME = "STORMY";
    Sky sky;

    public StormyState(Sky sky) {
        this.sky = sky;
    }

    @Override
    public void paintComponent(Graphics g) {
        sky.setBackground(new Color(0,0,0));
        //Add random chance for lightning to strike
    }

    @Override
    public void checkChange() {
        if(sky.hour >= 12000) {
            //Change to night
            sky.state = sky.nightState;
            sky.notifyObservers();
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
