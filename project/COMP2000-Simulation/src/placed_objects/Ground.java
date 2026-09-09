package placed_objects;
import growables.Growable;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import supplementary.Window;

public class Ground extends JPanel{
    static final int MAX_OBJECTS = 1024;

    public Ground() {
        this.setPreferredSize(new Dimension(Window.WIN_WIDTH, Window.WIN_HEIGHT/4*3));
        this.setBackground(Color.green);
        this.setLayout(null);   //Freeform layout

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

    ArrayList<Growable> getGrowables() {
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
