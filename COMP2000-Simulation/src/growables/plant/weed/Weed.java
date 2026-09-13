package growables.plant.weed;
import exceptions.InvalidPositionException;
import growables.plant.Plant;
import java.awt.*;
import java.util.Random;
import javax.swing.BorderFactory;
import placed_objects.sky.Sky;
import supplementary.Direction;
import supplementary.Window;

public class Weed extends Plant {

    int spreadNum = 1;
    static final int growthDelay = 1000;
    static final int size = 10;
    String environmentState;
    Sky sky;
    public boolean spent = false;

    Direction direction;
    Random random;

    public Weed(Point position, Sky sky) {
        super(position, growthDelay, size, sky);
        super.adultState = new WeedAdultState(this);

        this.sky = sky;
        environmentState = sky.getState();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        random = new Random();
        direction = new Direction(random.nextInt(-20, 20), random.nextInt(-20, 20));

        //Check if very close to another plant. If so, immediately die.
        //TODO
    }

    Weed(Point position, Direction direction, Sky sky) {
        this(position, sky);

        if(position.x > Window.WIN_WIDTH || position.x < 0 || position.y < 0 || position.y > Window.WIN_HEIGHT/4*3) {
            throw new InvalidPositionException("Position: " + position.x + ", " + position.y);
        }

        this.direction = direction;

        //Check if very close to another plant. If so, immediately die.
        //TODO
    }

    Weed(Point position, double growthFactor, Sky sky) {
        super(position, (int) (growthDelay / growthFactor), size, sky); //grow at a different rate relative to standard sunflower
        this.position = position;
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
            int newX = position.x + direction.dx + random.nextInt(-2, 2);
            int newY = position.y + direction.dy + random.nextInt(-2, 2);
            try {
                Point newPoint = new Point(newX, newY);
                getParent().add(new Weed(newPoint, direction, sky));
                spent = true;
            } catch(InvalidPositionException p) {
                System.out.println("Stopped OOB Weed");
            } catch(Exception e) {
                System.out.println("Could not create weed");
            }
        }
    }

    public String toString() {
        return  ("Pos: " + position.x + ", " + position.y);
    }
}
