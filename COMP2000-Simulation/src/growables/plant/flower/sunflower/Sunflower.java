package growables.plant.flower.sunflower;
import exceptions.InvalidPositionException;
import exceptions.ObjectLimitExceededException;
import growables.plant.flower.*;
import java.awt.*;
import placed_objects.Ground;
import placed_objects.sky.Sky;
import supplementary.Radius;

public class Sunflower extends Flower {
    int spreadNum = 1;
    int sunflowerGrowthDelay = 5000;
    static final int SIZE = 50;
    String environmentState;

    public Sunflower(Point position, Sky sky, Ground ground) {
        super(position, SIZE, sky, ground);
        super.growthDelay = sunflowerGrowthDelay;

        super.seedState = new SunflowerSeedState(this);
        super.juvenileState = new SunflowerJuvenileState(this);
        super.adultState = new SunflowerAdultState(this);
        super.bloomState = new SunflowerBloomState(this);

        super.state = seedState;

        environmentState = sky.getState();
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
            //small chance to create patch around seedling
            try {
                ground.addGrowable(new Sunflower(newPoint, sky, ground));
            } catch(InvalidPositionException p) {
                System.out.println("Stopped OOB Sunflower");
            } catch(ObjectLimitExceededException e) {
                System.out.println("Too many objects, cannot add sunflower");
            }
        }
    }

    @Override
    public String toString() {
        return  ("Pos: " + position.x + ", " + position.y);
    }
}
