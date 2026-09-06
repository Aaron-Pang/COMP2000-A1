import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Patch<T extends Growable> extends JPanel{
    //Within a small area, growable objects of type T are more likely to spread seeds
    Radius patchArea;
    Container parent;  //A patch can only be placed on the ground
    ArrayList<T> collection;
    
    Patch(int radius, Point position, Container container) {
        patchArea = new Radius(position, radius);
        this.setBounds(position.x-radius, position.y-radius, 2*radius, 2*radius);
        this.setBackground(Color.BLUE);
        this.collection = new ArrayList<T>();
    }

    void addToPatch(T item) throws OutOfPatchBoundsException {
        if(patchArea.isPointInRadius(item.getPosition())) {
            collection.add(item);
        } else {
            throw new OutOfPatchBoundsException();
        }
        item.increaseSpreadNum(3);
    }
}
