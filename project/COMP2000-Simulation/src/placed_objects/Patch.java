package placed_objects;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import exceptions.InvalidParentException;
import exceptions.OutOfPatchBoundsException;
import growables.Growable;
import supplementary.Observer;
import supplementary.Radius;

public class Patch<T extends Growable> extends JPanel implements Observer{
    //Within a small area, growable objects of type T are more likely to spread seeds
    Radius patchArea;
    Container parent;  //A patch can only be placed on the ground
    ArrayList<T> collection;
    
    public Patch(int radius, Point position) {
        patchArea = new Radius(position, radius);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBounds(position.x-radius, position.y-radius, 2*radius, 2*radius);
        
        this.setOpaque(false);
        this.setBackground(new Color(0, 0, 255, 125));
        this.collection = new ArrayList<T>();
    }

    //When this component added to a container, make sure it is of type ground
    @Override
    public void addNotify() throws InvalidParentException {
        super.addNotify();
        parent = getParent();
        if(!(parent instanceof Ground)) {
            throw new InvalidParentException("Error: Attempting to add Patch to non-Ground Panel");
        }
    }

    @Override
    public void update() {
        //Check 
    }

    public void addToPatch(T item) throws OutOfPatchBoundsException {
        if(patchArea.isPointInRadius(item.getPosition())) {
            collection.add(item);
        } else {
            throw new OutOfPatchBoundsException();
        }
        item.increaseSpreadNum(2);
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

    /*
    void killInArea() {
        ArrayList<Growable> items = getObjectsInRadius();
        for(Growable g : items) {
            if(patchArea.isPointInRadius(g.getPosition())) {
                g.kill();
            }
        }
    }
    */

}
