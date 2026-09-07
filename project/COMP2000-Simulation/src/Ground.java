import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


public class Ground extends JPanel{
    Ground() {
        this.setPreferredSize(new Dimension(Window.WIN_WIDTH, Window.WIN_HEIGHT/4*3));
        this.setBackground(Color.green);
        this.setLayout(null);   //Freeform layout
    }

    public void tick() {
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
