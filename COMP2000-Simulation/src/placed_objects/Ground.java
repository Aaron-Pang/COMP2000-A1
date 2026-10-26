package placed_objects;

import exceptions.ObjectLimitExceededException;
import growables.Growable;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.*;
import supplementary.LightningEvent;
import supplementary.Window;

public class Ground extends JPanel {
    static final int MAX_OBJECTS = 512;
    final ArrayList<Growable> growables;
    final ArrayList<Patch<? extends Growable>> patches;
    LightningEvent e;

    public Ground() {
        this.setPreferredSize(new Dimension(Window.WIN_WIDTH, Window.WIN_HEIGHT / 4 * 3));
        this.setBackground(Color.green);
        this.setLayout(null); // Freeform layout

        growables = new ArrayList<>();
        patches = new ArrayList<>();

        // Trigger event whenever a new component is added to ground
        this.addContainerListener(new ContainerAdapter() {
            @Override
            public void componentAdded(ContainerEvent e) {
                // Notify observers
            }
        });
        e = new LightningEvent(this);
        this.addMouseListener(e);
    }

    public void tick() {
        ArrayList<Growable> dead = getDead();
        getGrowables().stream().forEach(i -> i.tick());
        dead.stream().forEach(i -> delete(i));
        getEmptyPatches().stream().forEach(i -> delete(i));
        repaint();
        revalidate();
    }

    public void addGrowable(Growable g) throws ObjectLimitExceededException {
        if (growables.size() >= MAX_OBJECTS) {
            throw new ObjectLimitExceededException();
        }
        growables.add(g);
        super.add((Component) g);
    }

    public void addPatch(Patch<? extends Growable> p) {
        patches.add(p);
        super.add((Component) p);
    }

    public void delete(Growable g) {
        growables.remove(g);
        remove((Component) g);
    }

    public void delete(Patch<? extends Growable> p) {
        patches.remove(p);
        remove(p);
    }

    public ArrayList<Growable> getGrowables() {
        // Create a new copy of the ArrayList to prevent concurrent modification
        return new ArrayList<>(growables);
    }

    public ArrayList<Patch<? extends Growable>> getEmptyPatches() {
        return patches.stream()
                      .filter(Patch::isEmpty)
                      .parallel()
                      .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Growable> getDead() {
        return getGrowables().stream()
                             .filter(x -> x.getState().equals("DEAD"))
                             .parallel()
                             .collect(Collectors.toCollection(ArrayList::new));
    }
}
