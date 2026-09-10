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
    public int hour;    //Divide by 10 for the hour

    public Sky() {
        startTime = Instant.now();
        weatherState = SUNNY;
        timeState = DAWN;
        this.setBackground(new Color(140,200,255));
        this.setPreferredSize(new Dimension(Window.WIN_WIDTH, Window.WIN_HEIGHT/4));
    }

    public void tick() {
        //1 real-life second = 1 hour, 1 minute = ~17ms
        dayTime = Duration.between(startTime, Instant.now()).toMillis();
        hour = (int) ((dayTime % 24000));
        revalidate();
        repaint();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        double p = (((double) hour)/24000);
        System.out.println((int) (p * (double) Window.WIN_WIDTH));
        g.fillOval((int) (p * (double) Window.WIN_WIDTH) - 50, 0, 50, 50);
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
