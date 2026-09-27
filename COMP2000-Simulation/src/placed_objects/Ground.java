package placed_objects;
import exceptions.ObjectLimitExceededException;
import growables.Growable;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import supplementary.Window;

public class Ground extends JPanel{
    static final int MAX_OBJECTS = 512;
    final ArrayList<Growable> growables;

    public Ground() {
        this.setPreferredSize(new Dimension(Window.WIN_WIDTH, Window.WIN_HEIGHT/4*3));
        this.setBackground(Color.green);
        this.setLayout(null);   //Freeform layout

        growables = new ArrayList<>();

        //Trigger event whenever a new component is added to ground
        this.addContainerListener(new ContainerAdapter() {
            @Override
            public void componentAdded(ContainerEvent e) {
                //Notify observers
            }
        });
    }

    public void tick() {
        for(Growable g : getGrowables()) {
            g.tick();
        }
        getDead().stream().forEach(i -> delete(i));
    }

    public void addGrowable(Growable g) throws ObjectLimitExceededException {
        if(growables.size() >= MAX_OBJECTS) {
            throw new ObjectLimitExceededException();
        }
        growables.add(g);
        super.add((Component) g);
    }

    public void addPatch(Patch<?> p) {
        super.add((Component) p);
    }

    public void delete(Growable g) {
        growables.remove(g);
        remove((Component) g);
    }

    public ArrayList<Growable> getGrowables() {
        //Create a new copy of the ArrayList to prevent concurrent modification
        return new ArrayList<>(growables);
    }

    public ArrayList<Growable> getDead() {
        //Filter ArrayList for dead growables
        ArrayList<Growable> list = getGrowables();
        list.removeIf(i -> !i.getState().equals("DEAD"));
        return list;
    }
}
