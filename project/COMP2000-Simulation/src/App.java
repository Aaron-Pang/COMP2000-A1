import java.awt.*;

import growables.Sunflower;
import growables.Weed;
import placed_objects.Patch;
import supplementary.Window;

public class App {
    public static void main(String[] args) {
        Window window = new Window();

        Patch<Sunflower> p = new Patch(100, new Point(200, 200));

        window.addToGround(p);

        Sunflower sf1 = new Sunflower(new Point(200, 200));
        window.addToGround(sf1);
        p.addToPatch(sf1);

        Sunflower sf2 = new Sunflower(new Point(650, 300));
        Weed w = new Weed(new Point(350, 300));
        
        window.addToGround(sf2);
        window.addToGround(w);

        
    }
}
