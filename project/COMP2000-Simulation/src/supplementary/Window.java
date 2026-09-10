package supplementary;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import placed_objects.Ground;
import placed_objects.Sky;

public class Window extends JFrame{
    //Window of the application
    //Defines screenspace

    public static int WIN_WIDTH = 800;
    public static int WIN_HEIGHT = 600;
    private final Sky sky; //changed to final - Allie
    private final Ground ground; //changed to final - Allie

    Timer timer;

    public Window() {
        sky = new Sky();
        ground = new Ground();

        timer = new Timer();
        TimerTask tick = new TimerTask() {
            @Override 
            public void run() {
                ground.tick();
                sky.tick();
            }
        };
        timer.schedule(tick, 20, 20);

        //Basic window props
        this.setTitle("GAASK Plant Simulation COMP2000");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Exit appliction when x pressed
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new GridBagLayout());

        //Keep the main sky and ground in a single column
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth=GridBagConstraints.REMAINDER;
        c.fill=GridBagConstraints.HORIZONTAL;

        this.add(sky,c);
        this.add(ground,c);
        
        this.pack();

        this.setVisible(true);
    }

    //Sky will deal with it's own components, so anything added to the window 
    //must be added to the ground.
    public void addToGround(Component comp) {
        ground.add(comp);
    }

    public void removeFromGround(Component comp) {
        ground.remove(comp);
    }

    public void refresh() {
        ground.revalidate();
        ground.repaint();
    }
}
