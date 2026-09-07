import java.awt.*;

public class Sunflower extends Flower{

    int spreadNum = 1;
    int growthDelay = 5000;
    static final int size = 50;

    Sunflower(Point position) {
        super(position, 3000, size);
        this.position = position;
        if(position.x > Window.WIN_WIDTH || position.x < 0 || position.y < 0 || position.y > Window.WIN_HEIGHT/4*3) {
            throw new InvalidPositionException("Position: " + position.x + ", " + position.y);
        }

        //Check if very close to another plant. If so, immediately die.
        //TODO
    }

    Sunflower(Point position, double growthFactor) {
        super(position, (int) (3000 / growthFactor), size); //grow at a different rate relative to standard sunflower
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
    public void seedAction() {
        this.setBounds(position.x-size/8, position.y-size/8, size/4, size/4);
    }

    @Override
    public void seedlingAction() {

    }

    @Override
    public void juvenileAction() {
        this.setBounds(position.x-size/4, position.y-size/4, size/2, size/2);
    }

    @Override
    public void adultAction() {
        bloom();
        this.setBounds(position.x-size/2, position.y-size/2, size, size);
    }

    @Override
    public void deadAction() {

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
