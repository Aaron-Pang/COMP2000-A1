package growables.plant.weed;
import exceptions.InvalidPositionException;
import exceptions.ObjectLimitExceededException;
import growables.plant.Plant;
import java.awt.*;
import java.util.Random;
import javax.swing.BorderFactory;
import placed_objects.Ground;
import placed_objects.sky.Sky;
import supplementary.Direction;
import supplementary.Window;

public class Weed extends Plant {

    int spreadNum = 1;
    static final int growthDelay = 1000;
    static final int size = 10;
    String environmentState;
    public boolean spent = false;

    Direction direction;
    Random random;

    public Weed(Point position, Sky sky, Ground ground) {
        super(position, size, sky, ground);
        super.adultState = new WeedAdultState(this);
        super.growthDelay = growthDelay;

        this.sky = sky;
        environmentState = sky.getState();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        random = new Random();
        direction = new Direction(random.nextInt(-20, 20), random.nextInt(-20, 20));
    }

    Weed(Point position, Direction direction, Sky sky, Ground ground) {
        this(position, sky, ground);

        if(position.x > Window.WIN_WIDTH || position.x < 0 || position.y < 0 || position.y > Window.WIN_HEIGHT/4*3) {
            throw new InvalidPositionException("Position: " + position.x + ", " + position.y);
        }

        this.direction = direction;
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
        if(spent) {
            return;
        }

        for(int i = 0; i < spreadNum; i++) {
            double newX = position.x + direction.dx * random.nextInt(15, 20);
            double newY = position.y + direction.dy * random.nextInt(15, 20);
            try {
                Point newPoint = new Point((int) newX, (int) newY);
                Weed child1 = new Weed(newPoint, direction, sky, ground);
                ground.addGrowable(child1);
                //Small chance to create an offshoot vine
                if(random.nextInt(0, 5) == 0) {
                    //Create new weed with random direction
                    Direction newDir = new Direction(random.nextInt(-20, 20), random.nextInt(-20, 20));
                    double startX = position.x + newDir.dx * random.nextInt(15, 20);
                    double startY = position.y + newDir.dy * random.nextInt(15, 20);
                    Point startPoint = new Point((int) startX, (int) startY);
                    Weed child2 = new Weed(startPoint, newDir, sky, ground);
                    ground.addGrowable(child2);
                }
                spent = true;
            } catch(InvalidPositionException p) {
                System.out.println("Stopped OOB Weed");
            } catch(ObjectLimitExceededException e) {
                System.out.println("Too many objects, cannot create weed");
            }
        }
    }

    public String toString() {
        return  ("Pos: " + position.x + ", " + position.y);
    }
}
