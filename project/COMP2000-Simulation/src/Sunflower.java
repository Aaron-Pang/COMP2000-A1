import java.awt.*;

public class Sunflower extends Flower{

    int spreadNum = 2;
    int growthDelay = 5000;

    Container window;

    Sunflower(Point position) {
        super(position, 5000);
        this.position = position;
        this.window = this.getParent();

        //Check if very close to another plant. If so, immediately die.
        //TODO
    }

    Sunflower(Point position, double growthFactor) {
        super(position, (int) (5000 / growthFactor)); //grow at a different rate relative to standard sunflower
        this.position = position;
    }

    @Override
    public void bloom() {
        //this.setBackground(Color.YELLOW);
        Graphics g = this.getGraphics();
        g.setColor(Color.YELLOW);
        g.fillOval(position.x, position.y, size, size);
        g.dispose();
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
        Sunflower[] children = new Sunflower[spreadNum];
        for(int i = 0; i < spreadNum; i++) {
            Point newPoint = radius.getRandomPoint();
            children[i] = new Sunflower(newPoint);    //Will immediately go out of scope for now
        }
    }

    public String toString() {
        return  ("Pos: " + position.x + ", " + position.y);
    }
}
