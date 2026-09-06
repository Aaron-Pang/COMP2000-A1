import java.awt.*;
import javax.swing.*;

public class Patch<T extends Growable> extends JPanel{
    //Within a small area, growable objects of type T will grow more frequently
    Radius patchArea;
    Ground parent;  //A patch can only be placed on the ground
    
    Patch(int radius, Point position) {
        patchArea = new Radius(position, radius);
        this.setBounds(position.x-radius, position.y-radius, 2*radius, 2*radius);
        this.setBackground(Color.BLUE);
        //When a patch is created, kill all growables in radius not of patch type
        /*
        Component temp = this.getParent();
        try {
            parent = (Ground) temp;
        } catch(Exception e) {
            System.out.println("Oops!");
        }

        ArrayList<Growable> items = patchArea.getObjectsInRadius(parent);
        */

        //Create a few growables inside the patch that grow a bit faster
    }
}
