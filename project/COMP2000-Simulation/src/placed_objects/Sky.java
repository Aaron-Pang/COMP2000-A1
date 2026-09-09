package placed_objects;
import java.awt.*;
import javax.swing.JPanel;
import supplementary.Window;

public class Sky extends JPanel{
    public static final int SUNNY = 0;
    public static final int CLOUDY = 1;
    public static final int OVERCAST = 2;
    public static final int RAINY = 3;

    public static final int DAWN = 0;
    public static final int DAY = 1;
    public static final int DUSK = 2;
    public static final int NIGHT = 3;

    private int timeState;
    private int weatherState;

    public Sky() {
        weatherState = SUNNY;
        timeState = DAWN;
        this.setBackground(new Color(140,200,255));
        this.setPreferredSize(new Dimension(Window.WIN_WIDTH, Window.WIN_HEIGHT/4));
    }

    public void tick() {
        
    }

    public void changeWeather(int newWeather) {
        if(newWeather >= DAWN && newWeather <= NIGHT) {
            weatherState = newWeather;
        }
    }

    public void progressTime() {
        timeState = timeState++ % 4;
    }
}
