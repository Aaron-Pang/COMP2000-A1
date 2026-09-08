package growables;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

abstract class Plant extends JPanel implements Growable {
    static final int SEED = 0;
    static final int SEEDLING = 1;
    static final int JUVENILE = 2;
    static final int ADULT = 3;
    static final int DEAD = 4;
    
    int growthState = SEED;
    int size = 60;
    Point position;

    int spreadNum;        //Max number of seeds a plant can produce
    int growthDelay;      //How long between growth states in milliseconds
    int spreadRadius;     //How far a plant can spread its seeds
    Timer timer;

    Plant(Point p, int growthDelay, int size) {
        //These numbers are all arbitrary placeholders for now
        //spreadNum = 2;
        spreadRadius = 100;
        this.growthDelay = growthDelay;
        timer = new Timer();
        TimerTask grow = new TimerTask() {
            @Override
            public void run() {
                grow();
            }
        };

        TimerTask tick = new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        };

        timer.schedule(grow, growthDelay, growthDelay);
        timer.schedule(tick, 25, 25);

        this.position = p;
        this.setBounds(position.x, position.y, size, size);
        this.setBackground(Color.darkGray);
    }

    //All plants will have these stages. The ___Action() methods allow each phase
    //to be customised per specific plant.
    public void tick() {
        switch(growthState) {
            case SEED:
                this.setBackground(new Color(79, 46, 9));
                seedAction();
                break;
            case SEEDLING:
                this.setBackground(new Color(2, 184, 9));
                seedlingAction();
                break;
            case JUVENILE:
                this.setBackground(new Color(1, 120, 5));
                juvenileAction();
                break;
            case ADULT:
                this.setBackground(new Color(1, 71, 4));
                adultAction();
                break;
            case DEAD:
                this.setBackground(Color.BLACK);
                timer.cancel();
                deadAction();
                break;
        }
    }

    //Progress the lifespan of the plant
    public void grow() {
        if (growthState < DEAD){
            growthState++;
        }
    }

    abstract void seedAction();
    abstract void seedlingAction();
    abstract void juvenileAction();
    abstract void adultAction();
    abstract void deadAction();

    @Override
    public Point getPosition() {
        return position;
    }

    @Override 
    public int getState() {
        return growthState;
    }

    //TODO
    @Override
    public boolean isColliding() {
        return false;
    }

    public String toString() {
        return "Replace this function";
    }
}