package placed_objects.ground;
import growables.Growable;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import supplementary.Window;

public class Ground extends JPanel implements GroundSubject{
    static final int MAX_OBJECTS = 1024;
    final ArrayList<GroundObserver> observers;

    public Ground() {
        this.setPreferredSize(new Dimension(Window.WIN_WIDTH, Window.WIN_HEIGHT/4*3));
        this.setBackground(Color.green);
        this.setLayout(null);   //Freeform layout

        observers = new ArrayList<GroundObserver>();

        //Trigger event whenever a new component is added to ground
        this.addContainerListener(new ContainerAdapter() {
            @Override
            public void componentAdded(ContainerEvent e) {
                //If added component implments Growable, notify observers
                if(Growable.class.isAssignableFrom(e.getChild().getClass())) {
                    notifyObservers((Growable) e.getChild());
                }
            }
        });
    }

    public void tick() {
        for(Growable g : getGrowables()) {
            g.tick();
        }
    }

    @Override
    public void registerObserver(GroundObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(GroundObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Growable g) {
        for(GroundObserver o : observers) {
            o.update(g);
        }
    }

    public ArrayList<Growable> getGrowables() {
        Component[] items = getComponents();
        ArrayList<Growable> growables = new ArrayList<>();
        for(int i = 0; i < items.length; i++) {
            if(items[i] instanceof Growable) {
                growables.add((Growable) items[i]);
            }
        }
        return growables;
    }
}
