package placed_objects;
import java.awt.*;
import java.time.*;
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
    private Instant startTime;
    public long dayTime;
    public int hour;

    public Sky() {
        startTime = Instant.now();
        weatherState = SUNNY;
        timeState = DAWN;
        this.setBackground(new Color(140,200,255));
        this.setPreferredSize(new Dimension(Window.WIN_WIDTH, Window.WIN_HEIGHT/4));
    }

    public void tick() {
        dayTime = Duration.between(startTime, Instant.now()).toMillis();
        hour = (int) ((dayTime % 24000)/1000);
        //System.out.println(hour);
        //this.setBackground(new Color(100, 50, 10*hour/1000));
        revalidate();
        repaint();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        double p = (((double) hour)/24);
        System.out.println(p);
        g.fillOval((int) (p * (double) Window.WIN_WIDTH), 0, 50, 50);
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
