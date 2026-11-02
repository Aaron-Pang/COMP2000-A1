package placed_objects.sky;

import java.awt.*;
import supplementary.Lightning;


public class StormyState implements SkyState {
    public static final String NAME = "STORMY";
    Sky sky;

    public StormyState(Sky sky) {
        this.sky = sky;
    }

    @Override
    public void paintComponent(Graphics g) {
        sky.setBackground(new Color(128,128,128));
        //random chance for lightning to strike
        if((int) (Math.random() * 200) == 0) {
            //Summon lightning
            Point p = new Point((int) (Math.random() * supplementary.Window.WIN_WIDTH), (int) (Math.random() * supplementary.Window.WIN_HEIGHT));
            Lightning.strike(sky.ground, p);
        }

    }

    @Override
    public void checkChange() {
        if(sky.hour < 12000) {
            sky.state = sky.sunnyState;
            sky.notifyObservers();
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
