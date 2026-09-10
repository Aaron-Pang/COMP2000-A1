package placed_objects.sky;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.JPanel;
import supplementary.Window;

public class Sky extends JPanel implements SkySubject{
    SkyState sunnyState;
    SkyState nightState;

    SkyState state;

    private int timeState;
    private int weatherState;
    public Instant startTime;
    public long dayTime;
    public int hour;    //Divide by 1000 for the hour

    private ArrayList<SkyObserver> observers;

    public Sky() {
        sunnyState = new SunnyState(this);
        nightState = new NightState(this);
        state = sunnyState;

        observers = new ArrayList();

        startTime = Instant.now();
        this.setPreferredSize(new Dimension(Window.WIN_WIDTH, Window.WIN_HEIGHT/4));
    }

    public void tick() {
        //1 real-life second = 1 hour, 1 minute = ~17ms
        dayTime = Duration.between(startTime, Instant.now()).toMillis();
        hour = (int) ((dayTime % 24000));
        state.checkChange();
        revalidate();
        repaint();
    }

    public String getState() {
        return state.getName();
    }

    @Override
    public void registerObserver(SkyObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(SkyObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(SkyObserver o : observers) {
            o.update(state.getName(), hour);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        state.paintComponent(g);
    }

    public void progressTime() {
        timeState = timeState++ % 4;
    }
}
