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

    public int growthDelay = 5000;      //How long between growth states in milliseconds
    public int spreadRadius;     //How far a plant can spread its seeds
    public int fightingPower = 5; //Scaled from 0 to 10 where

    public Plant(Point p, int size, Sky sky, Ground ground) {
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

        this.position = p;
        this.setBounds(position.x, position.y, size, size);

        //Check if overlapping with other growables
        

        this.setBackground(Color.darkGray);
        sky.registerObserver(this);
    }

    @Override
    public Growable isColliding() {
        ArrayList<Growable> growables = ground.getGrowables();
        for(Growable g : growables) {
            if(g != this && this.getHitbox().intersects(g.getHitbox())) {
                return g;
            }
        }
        return null;
    }

    @Override
    public void fightAgainst(Growable opponent) {
        System.out.println("Fighting");
        int opponentPower = opponent.getFightingPower();
        int outcome = fightingPower - opponentPower;
        // Base chance to win 50%
        // add 10% * difference in fighting power
        double victoryChance = 0.5 + 0.1 * outcome;
        if(Math.random() <= victoryChance) {
            //This plant wins, opponent dies
            opponent.loseFight();
        } else {
            //This plant loses
            loseFight();
        }
    }

    @Override
    public void loseFight() {
        //Mark this plant for deletion
        state = deadState;
    }

    @Override
    public int getFightingPower() {
        return fightingPower;
    }

    public boolean isColliding(Growable g) {
        return this.getHitbox().intersects(g.getHitbox());
    }

    @Override
    public void tick() {
        Growable g = isColliding();
        if(g != null) {
          fightAgainst(g);  
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

    @Override
    //plant will by default simply increase the growth delay
    public void increaseLifespan(double factor) {
        growthDelay *= factor;
    }

    public void delete() {
        ground.delete(this);
        ground.revalidate();
        ground.repaint();
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