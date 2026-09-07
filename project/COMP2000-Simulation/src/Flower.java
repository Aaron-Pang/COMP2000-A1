import java.awt.*;

abstract class Flower extends Plant {
    Flower(Point p, int growthDelay, int size) {
        super(p, growthDelay, size);
    }

    public void bloom(){    //Display the flower blooming
        this.setBackground(Color.RED);
        if ((int) (Math.random() * 100) == 0) {
            spread();
        }
    }  

    //Flowers will always bloom when they are adults
    @Override
    public void adultAction() {
        bloom();
    }
}
