import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Patch<T extends Growable> extends JPanel{
    //Within a small area, growable objects of type T are more likely to spread seeds
    Radius patchArea;
    Container parent;  //A patch can only be placed on the ground
    
    Patch(int radius, Point position, Container container) {
        patchArea = new Radius(position, radius);
        this.setBounds(position.x-radius, position.y-radius, 2*radius, 2*radius);
        this.setBackground(Color.BLUE);
        //When a patch is created, kill all growables in radius not of patch type
        /*
        ArrayList<Growable> items = patchArea.getObjectsInRadius(parent);
        */

        //When a patch is created, add all plants of type T to be "in" this patch.
        ArrayList<T> growables = new ArrayList<>();
        //Check for all Ts in radius
        
    }
}
