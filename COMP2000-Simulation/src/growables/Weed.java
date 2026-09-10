package growables;
import exceptions.InvalidPositionException;
import growables.plant.Plant;

import java.awt.*;
import java.util.Random;
import javax.swing.BorderFactory;
import supplementary.Direction;
import supplementary.Window;

public class Weed extends Plant {

    int spreadNum = 1;
    int spreadRadius = 30;
    static final int growthDelay = 1000;
    static final int size = 10;

    Direction direction;
    Random random;

    public Weed(Point position) {
        super(position, growthDelay, size);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        

        random = new Random();
        direction = new Direction(random.nextInt(-20, 20), random.nextInt(-20, 20));

        //Check if very close to another plant. If so, immediately die.
        //TODO
    }

    Weed(Point position, Direction direction) {
        super(position, growthDelay, size);
        this.position = position;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        if(position.x > Window.WIN_WIDTH || position.x < 0 || position.y < 0 || position.y > Window.WIN_HEIGHT/4*3) {
            throw new InvalidPositionException("Position: " + position.x + ", " + position.y);
        }

        this.direction = direction;

        //Check if very close to another plant. If so, immediately die.
        //TODO
    }

    Weed(Point position, double growthFactor) {
        super(position, (int) (growthDelay / growthFactor), size); //grow at a different rate relative to standard sunflower
        this.position = position;
    }

    @Override
    public void adultAction() {
        if ((int) (Math.random() * 21) == 0) {
            spread();
        }
    }

    @Override
    public void grow() {
        if (!(growthState == ADULT)) {
            growthState++;
        } else if ((int) (Math.random() * 3) == 0) {
            growthState++;
        }
    }

    @Override
    public void increaseSpreadNum(double factor) {
        spreadNum = (int) factor * spreadNum;
    }

    @Override
    public void seedAction() {
    }

    @Override
    public void seedlingAction() {
    }

    @Override
    public void juvenileAction() {
    }

    @Override
    public void deadAction() {
        
    }

    @Override
    public void spread() {
        //Radius radius = new Radius(position, spreadRadius);
        for(int i = 0; i < spreadNum; i++) {
            int newX = position.x + direction.dx;
            int newY = position.y + direction.dy;
            //int newX = position.x + direction.dx;
            //int newY = position.y + direction.dy;
            try {
                Point newPoint = new Point(newX, newY);
                getParent().add(new Weed(newPoint, direction));
                grow();
            } catch(InvalidPositionException p) {
                System.out.println("Stopped OOB Weed");
                die();
            } catch(Exception e) {

            }
        }
    }

    public void die() {
        growthState = DEAD;
    }

    public String toString() {
        return  ("Pos: " + position.x + ", " + position.y);
    }
}
