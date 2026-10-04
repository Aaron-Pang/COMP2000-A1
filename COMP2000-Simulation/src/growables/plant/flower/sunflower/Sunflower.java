package growables.plant.flower.sunflower;
import exceptions.InvalidPositionException;
import exceptions.ObjectLimitExceededException;
import exceptions.OutOfPatchBoundsException;
import growables.plant.flower.*;
import java.awt.*;
import placed_objects.Ground;
import placed_objects.Patch;
import placed_objects.sky.Sky;
import supplementary.Radius;

public class Sunflower extends Flower {
    int spreadNum = 1;
    int growthDelay = 5000;
    static final int SIZE = 50;
    String environmentState;
    Patch<Sunflower> patch;

    public Sunflower(Point position, Sky sky, Ground ground) {
        super(position, SIZE, sky, ground);
        super.growthDelay = growthDelay;

        super.seedState = new SunflowerSeedState(this);
        super.juvenileState = new SunflowerJuvenileState(this);
        super.adultState = new SunflowerAdultState(this);
        super.bloomState = new SunflowerBloomState(this);

        super.state = seedState;

        environmentState = sky.getState();
    }

    @Override
    public void prepareForRemoval() {
        if(patch != null) {
            patch.removeFromPatch(this);
        }
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
    //Increasing a sunflower's lifespan actually increases the time it spends as an adult/blooming
    public void increaseLifespan(double factor) {
        bloomTime *= factor;
    }

    @Override
    public void spread() {
        Radius radius = new Radius(position, spreadRadius);
        for(int i = 0; i < spreadNum; i++) {
            Point newPoint = radius.getRandomPoint();
            try {
                Sunflower child = new Sunflower(newPoint, sky, ground);
                ground.addGrowable(child);
                if(patch != null) {
                    child.givePatch(patch);
                } else {
                    child.checkPatchCreate();
                }
            } catch(InvalidPositionException e) {
                System.out.println("Stopped OOB Sunflower");
            } catch(ObjectLimitExceededException e) {
                System.out.println("Too many objects, cannot add sunflower");
            } catch(OutOfPatchBoundsException e) {
                //Could not add child to patch
            }
        }
    }

    public void givePatch(Patch<Sunflower> p) {
        try {
            p.addToPatch(this);
            patch = p;
        } catch (OutOfPatchBoundsException e) {
            checkPatchCreate();
        }
            
    }

    public void checkPatchCreate() {
        System.out.println("Chancing");
        if(patch == null && (int) (Math.random() * 10) == 0) {
            System.out.println("Creating patch");
            patch = new Patch<>(100, position);
            ground.addPatch(patch);
            patch.addToPatch(this);
        }
    }

    @Override
    public String toString() {
        return  ("Pos: " + position.x + ", " + position.y);
    }
}
