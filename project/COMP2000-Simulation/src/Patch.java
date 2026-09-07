import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Patch<T extends Growable> extends JPanel{
    //Within a small area, growable objects of type T are more likely to spread seeds
    Radius patchArea;
    Container parent;  //A patch can only be placed on the ground
    ArrayList<T> collection;
    
    Patch(int radius, Point position) {
        patchArea = new Radius(position, radius);

        this.setBounds(position.x-radius, position.y-radius, 2*radius, 2*radius);
        
        this.setOpaque(false);
        this.setBackground(new Color(0, 0, 255, 125));
        this.collection = new ArrayList<T>();
    }
    @Override
    public void addNotify() throws InvalidParentException {
        super.addNotify();
        parent = getParent();
        //System.out.println(parent.getClass());
        if(!(parent instanceof Ground)) {
            throw new InvalidParentException("Error: Attempting to add Patch to non-Ground Panel");
        }
    }

    void addToPatch(T item) throws OutOfPatchBoundsException {
        if(patchArea.isPointInRadius(item.getPosition())) {
            collection.add(item);
        } else {
            throw new OutOfPatchBoundsException();
        }
        item.increaseSpreadNum(3);
    }

    ArrayList<Growable> getObjectsInRadius() {
        ArrayList<Growable> items = new ArrayList<>();
        Component[] comps = parent.getComponents();
        for(int i = 0; i < comps.length; i++) {
            if(comps[i] instanceof Growable) {
                Growable temp = (Growable) comps[i];
                Point p = temp.getPosition();
                if(patchArea.isPointInRadius(p)) {
                    items.add(temp);
                }
            }
        }
        return items;
    }

    void killInArea() {

    }
}
