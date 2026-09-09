package growables;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.time.*;

import exceptions.InvalidPositionException;
import supplementary.Window;

abstract class Plant extends JPanel implements Growable {
    static final int SEED = 1;
    static final int SEEDLING = 2;
    static final int JUVENILE = 3;
    static final int ADULT = 4;
    static final int DEAD = 5;
    
    Instant startTime;
    int growthState = SEED;
    int size = 60;
    Point position;

    int spreadNum;        //Max number of seeds a plant can produce
    int growthDelay;      //How long between growth states in milliseconds
    int spreadRadius;     //How far a plant can spread its seeds
    Timer timer;

    public Plant(Point p, int growthDelay, int size) {
        startTime = Instant.now();
        position = p;
        spreadRadius = 100;
        if(position == null) {
            throw new InvalidPositionException("Position is null");
        } else if (position.x > Window.WIN_WIDTH || position.x < 0 || position.y < 0 || position.y > Window.WIN_HEIGHT/4*3) {
            throw new InvalidPositionException("Position: " + position.x + ", " + position.y);
        }
        this.growthDelay = growthDelay;

        /*
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
        */

        this.position = p;
        this.setBounds(position.x, position.y, size, size);
        this.setBackground(Color.darkGray);
    }

    //All plants will have these stages. The ___Action() methods allow each phase
    //to be customised per specific plant.
    public void tick() {
        Instant now = Instant.now();
        int lifespan = (int) (Duration.between(startTime, now)).toMillis();

        if(lifespan < growthDelay * SEED) {
            this.setBackground(new Color(79, 46, 9));
            seedAction();
        } else if(lifespan < growthDelay * SEEDLING) {
            this.setBackground(new Color(2, 184, 9));
            seedlingAction();
        } else if(lifespan < growthDelay * JUVENILE) {
            this.setBackground(new Color(1, 120, 5));
            juvenileAction();
        } else if(lifespan < growthDelay * ADULT) {
            this.setBackground(new Color(1, 71, 4));
            adultAction();
        } else {
            this.setBackground(Color.BLACK);
            Container parent = this.getParent();
            parent.remove(this);
            parent.revalidate();
            parent.repaint();
            deadAction();
        }

        /*
        switch(lifespan) {
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
        */
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