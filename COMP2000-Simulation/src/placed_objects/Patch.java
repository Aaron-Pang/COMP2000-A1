package placed_objects;
import exceptions.InvalidParentException;
import exceptions.OutOfPatchBoundsException;
import growables.Growable;
import growables.GrowableObserver;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import placed_objects.ground.Ground;
import supplementary.Radius;

public class Patch<T extends Growable> extends JPanel implements GrowableObserver {
    //Within a small area, growable objects of type T are more likely to spread seeds
    Radius patchArea;
    Container parent;  //A patch can only be placed on the ground
    ArrayList<T> collection;
    
    public Patch(int radius, Point position, Ground ground) {
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
    public void update(ArrayList<? extends Growable> incoming) {
        
        for(Growable g : incoming) {
            if(patchArea.isPointInRadius(g.getPosition())) {
                addToPatch((T) g);  //Children only ever pass their own type, find better solution for this
                g.registerObserver(this);
                System.out.println(collection);
            }
        }
    }

    @Override
    public void update(Growable g) {
        if(collection.contains(g)) {
            collection.remove(g);
            System.out.println("Removing from patch");
            System.out.println(collection);
        }
    }

    public void addToPatch(T item) throws OutOfPatchBoundsException {
        if(patchArea.isPointInRadius(item.getPosition()) && !collection.contains(item)) {
            collection.add(item);
            item.registerObserver(this);
            item.increaseSpreadNum(2);
        } else {
            throw new OutOfPatchBoundsException();
        }
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
}
