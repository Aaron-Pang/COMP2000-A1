import java.awt.*;
import javax.swing.BorderFactory;

public class Weed extends Plant{

    int spreadNum = 2;
    //int growthDelay = 5000;
    int spreadRadius = 30;
    static final int size = 30;

    Container window;

    Weed(Point position) {
        super(position, 1000, size);
        this.position = position;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        if(position.x > Window.WIN_WIDTH || position.x < 0 || position.y < 0 || position.y > Window.WIN_HEIGHT/4*3) {
            throw new InvalidPositionException("Position: " + position.x + ", " + position.y);
        }

        //Check if very close to another plant. If so, immediately die.
        //TODO
    }

    Weed(Point position, double growthFactor) {
        super(position, (int) (1000 / growthFactor), size); //grow at a different rate relative to standard sunflower
        this.position = position;
    }

    @Override
    public void adultAction() {
        if ((int) (Math.random() * 100) == 0) {
            spread();
        }
    }

    @Override
    public void grow() {
        if (!(growthState == ADULT)) {
            growthState++;
        } else if ((int) (Math.random() * 100) == 0) {
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
        Radius radius = new Radius(position, spreadRadius);
        //Sunflower[] children = new Sunflower[spreadNum];
        for(int i = 0; i < spreadNum; i++) {
            Point newPoint = radius.getRandomPoint();
            try {
                getParent().add(new Weed(newPoint));
            } catch(InvalidPositionException p) {
                System.out.println("Stopped OOB Weed");
            } catch(Exception e) {

            }
        }
    }

    public String toString() {
        return  ("Pos: " + position.x + ", " + position.y);
    }
}
