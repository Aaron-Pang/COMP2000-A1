package growables.plant.flower.sunflower;
import exceptions.InvalidPositionException;
import growables.plant.flower.*;
import java.awt.*;

import placed_objects.ground.Ground;
import placed_objects.sky.Sky;
import supplementary.Radius;

public class Sunflower extends Flower {
    int spreadNum = 1;
    static final int growthDelay = 5000;
    static final int size = 50;
    String environmentState;

    public Sunflower(Point position, Sky sky, Ground ground) {
        super(position, growthDelay, size, sky, ground);

        super.seedState = new SunflowerSeedState(this);
        super.juvenileState = new SunflowerJuvenileState(this);
        super.adultState = new SunflowerAdultState(this);
        super.bloomState = new SunflowerBloomState(this);

        super.state = seedState;

        environmentState = sky.getState();

        //Check if very close to another plant. If so, immediately die.
        //TODO
    }

    Sunflower(Point position, double growthFactor, Sky sky, Ground ground) {
        super(position, (int) (growthDelay / growthFactor), size, sky, ground); //grow at a different rate relative to standard sunflower
        this.position = position;
    }

    @Override
    public void bloom() {
        if ((int) (Math.random() * 150) == 0) {
            spread();
        }
    }

    @Override
    public void update(String timeState, int hour){
        environmentState = timeState;
    }

    @Override
    public void increaseSpreadNum(double factor) {
        spreadNum = (int) factor * spreadNum;
    }

    @Override
    public void spread() {
        Radius radius = new Radius(position, spreadRadius);
        for(int i = 0; i < spreadNum; i++) {
            Point newPoint = radius.getRandomPoint();
            try {
                getParent().add(new Sunflower(newPoint, sky, ground));
            } catch(InvalidPositionException p) {
                System.out.println("Stopped OOB Sunflower");
            } catch(Exception e) {
                System.out.println("Cannot spread sunflower");
            }
        }
    }

    public String toString() {
        return  ("Pos: " + position.x + ", " + position.y);
    }
}
