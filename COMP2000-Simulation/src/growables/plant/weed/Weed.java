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
    int spreadRadius = 30;
    static final int growthDelay = 1000;
    static final int size = 10;
    String environmentState;
    Sky sky;

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

    public void grow() {
        if ((int) (Math.random() * 3) == 0) {
            state = deadState;
        }
    }

    @Override
    public void increaseSpreadNum(double factor) {
        spreadNum = (int) factor * spreadNum;
    }

    @Override
    public void spread() {
        //Radius radius = new Radius(position, spreadRadius);
        for(int i = 0; i < spreadNum; i++) {
            int newX = position.x + direction.dx;
            int newY = position.y + direction.dy;
            try {
                System.out.println("Adding new weed");
                Point newPoint = new Point(newX, newY);
                getParent().add(new Weed(newPoint, direction, sky));
                grow();
            } catch(InvalidPositionException p) {
                System.out.println("Stopped OOB Weed");
                die();
            } catch(Exception e) {
                System.out.println("Could not create weed");
            }
        }
    }

    public void die() {
        state = deadState;
    }

    public String toString() {
        return  ("Pos: " + position.x + ", " + position.y);
    }
}
