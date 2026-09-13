import growables.plant.flower.sunflower.Sunflower;
import growables.plant.weed.Weed;
import java.awt.Point;
import placed_objects.*;
import supplementary.Window;

public class App {
    public static void main(String[] args) {
        Window window = new Window();

        
        Patch<Sunflower> p = new Patch(100, new Point(200, 200));

        window.addToGround(p);
        
        Sunflower sf1 = new Sunflower(new Point(200, 200), window.getSky());
        window.addToGround(sf1);
        p.addToPatch(sf1);

        Sunflower sf2 = new Sunflower(new Point(1000, 500), window.getSky());
        Weed w = new Weed(new Point(600, 350), window.getSky());
        
        window.addToGround(sf2);
        window.addToGround(w);
        
    }
}
