package growables.plant.flower.sunflower;
import exceptions.InvalidPositionException;
import growables.plant.*;
import growables.plant.flower.*;
import java.awt.*;
import supplementary.Radius;

public class Sunflower extends Flower {
    PlantState seedState;
    PlantState seedlingState;
    PlantState juvenileState;
    PlantState adultState;
    PlantState deadState;

    PlantState state;

    int spreadNum = 1;
    static final int growthDelay = 3000;
    static final int size = 50;

    public Sunflower(Point position) {
        super(position, growthDelay, size);

        seedState = new SunflowerSeedState(this);
        juvenileState = new SunflowerJuvenileState(this);
        adultState = new AdultState(this);

        //Check if very close to another plant. If so, immediately die.
        //TODO
    }

    Sunflower(Point position, double growthFactor) {
        super(position, (int) (growthDelay / growthFactor), size); //grow at a different rate relative to standard sunflower
        this.position = position;
    }

    @Override
    public void bloom() {
        this.setBackground(Color.YELLOW);
        if ((int) (Math.random() * 100) == 0) {
            spread();
        }
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
                getParent().add(new Sunflower(newPoint));
            } catch(InvalidPositionException p) {
                System.out.println("Stopped OOB Sunflower");
            } catch(Exception e) {

            }
        }
    }

    public String toString() {
        return  ("Pos: " + position.x + ", " + position.y);
    }
}
