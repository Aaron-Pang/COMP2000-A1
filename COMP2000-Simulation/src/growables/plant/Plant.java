package growables.plant;
import exceptions.InvalidPositionException;
import growables.Growable;
import java.awt.*;
import java.time.*;
import javax.swing.*;
import supplementary.Window;

public abstract class Plant extends JPanel implements Growable{
    PlantState seedState;
    PlantState seedlingState;
    PlantState juvenileState;
    PlantState adultState;
    PlantState deadState;

    PlantState state;
    
    Instant startTime;
    public int size = 60;
    public Point position;

    int spreadNum;        //Max number of seeds a plant can produce
    int growthDelay;      //How long between growth states in milliseconds
    public int spreadRadius;     //How far a plant can spread its seeds

    public Plant(Point p, int growthDelay, int size) {
        seedState = new SeedState(this);
        seedlingState = new SeedlingState(this);
        juvenileState = new JuvenileState(this);
        adultState = new AdultState(this);
        deadState = new DeadState(this);

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
        this.setBackground(Color.darkGray);
    }

    //All plants will have these stages. The ___Action() methods allow each phase
    //to be customised per specific plant.
    public void tick() {
        Instant now = Instant.now();
        long lifespan = (Duration.between(startTime, now)).toMillis();

        state.checkChange(lifespan);
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

    //TODO
    @Override
    public boolean isColliding() {
        return false;
    }

    public String toString() {
        return "Replace this function";
    }
}