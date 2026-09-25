package growables.plant;
import exceptions.InvalidPositionException;
import growables.Growable;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import placed_objects.Ground;
import placed_objects.sky.Sky;
import placed_objects.sky.SkyObserver;
import supplementary.Window;

public abstract class Plant extends JPanel implements Growable, SkyObserver{
    public PlantState seedState;
    public PlantState seedlingState;
    public PlantState juvenileState;
    public PlantState adultState;
    public PlantState deadState;

    public PlantState state;
    
    Instant startTime;
    public int size = 60;
    public Point position;
    public Sky sky;
    public Ground ground;

    public int growthDelay;      //How long between growth states in milliseconds
    public int spreadRadius;     //How far a plant can spread its seeds

    public Plant(Point p, int growthDelay, int size, Sky sky, Ground ground) {
        seedState = new SeedState(this);
        seedlingState = new SeedlingState(this);
        juvenileState = new JuvenileState(this);
        adultState = new AdultState(this);
        deadState = new DeadState(this);

        state = seedState;

        this.sky = sky;
        this.ground = ground;
        startTime = Instant.now();
        position = p;
        spreadRadius = 100;
        if(position == null) {
            throw new InvalidPositionException("Position is null");
        } else if (position.x > Window.WIN_WIDTH || position.x < 0 || position.y < 0 || position.y > Window.WIN_HEIGHT/4*3) {
            throw new InvalidPositionException("Position: " + position.x + ", " + position.y);
        }
        this.growthDelay = growthDelay;

        this.position = p;
        this.setBounds(position.x, position.y, size, size);

        //Check if overlapping with other growables
        

        this.setBackground(Color.darkGray);
        sky.registerObserver(this);
    }

    public boolean isColliding() {
        ArrayList<Growable> growables = ground.getGrowables();
        for(Growable g : growables) {
            if(g != this && this.getHitbox().intersects(g.getHitbox())) {
                System.out.println("Colliding");
                return true;
            }
        }
        return false;
    }

    public boolean isColliding(Growable g) {
        return this.getHitbox().intersects(g.getHitbox());
    }

    @Override
    public void tick() {
        if(isColliding()) {
            delete();
        }

        Instant now = Instant.now();
        long lifespan = (Duration.between(startTime, now)).toMillis();

        state.checkChange(lifespan);
        revalidate();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        state.paintComponent(g);
    }

    public void delete() {
        Container parent = this.getParent();
        parent.remove(this);
        parent.revalidate();
        parent.repaint();
    }

    @Override
    public String getState() {
        return state.getName();
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public Rectangle getHitbox() {
        return this.getBounds();
    }

    @Override
    public String toString() {
        return "Replace this function";
    }
}