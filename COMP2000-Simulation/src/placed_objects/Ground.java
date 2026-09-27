package placed_objects;
import growables.Growable;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import supplementary.Window;

public class Ground extends JPanel{
    static final int MAX_OBJECTS = 1024;
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
    }

    //Should overload JPanel's add function
    public void addGrowable(Growable g) {
        System.out.println("Object added");
        growables.add(g);
        super.add((Component) g);
    }

    public void delete(Growable g) {
        System.out.println("Removing object");
        growables.remove(g);
        remove((Component) g);
    }

    public ArrayList<Growable> getGrowables() {
        //Create a new copy of the ArrayList to prevent concurrent modification
        return new ArrayList<>(growables);
    }
}
