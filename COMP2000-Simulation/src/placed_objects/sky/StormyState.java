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
        sky.setBackground(new Color(0,0,0));
        //Add random chance for lightning to strike
        if((int) (Math.random() * 100) == 0) {
            //Summon lightning
            Point p = new Point((int) (Math.random() * supplementary.Window.WIN_WIDTH), (int) (Math.random() * supplementary.Window.WIN_HEIGHT));
            Lightning.strike(sky.ground, p);
        }

    }

    @Override
    public void checkChange() {
        if(sky.hour % 12000 == 0) {
            if(sky.hour < 12000) {
                //Day
                sky.state = sky.sunnyState;
            } else {
                //Night
                sky.state = sky.nightState;
            }
            sky.notifyObservers();
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
