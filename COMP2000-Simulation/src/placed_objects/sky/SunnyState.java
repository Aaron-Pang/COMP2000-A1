package placed_objects.sky;

import java.awt.*;
import supplementary.Window;

public class SunnyState implements SkyState{
    public static final String name = "SUNNY";
    Sky sky;

    public SunnyState(Sky sky) {
        this.sky = sky;
    }

    @Override
    public void paintComponent(Graphics g) {
        sky.setBackground(new Color(140,200,255));
        g.setColor(Color.YELLOW);
        double p = (((double) sky.hour)/24000);
        System.out.println(p);
        g.fillOval((int) (p * (double) Window.WIN_WIDTH) - 50, 0, 50, 50);
    }

    @Override
    public void checkChange() {

    }

    @Override
    public String getName() {
        return name;
    }
}
