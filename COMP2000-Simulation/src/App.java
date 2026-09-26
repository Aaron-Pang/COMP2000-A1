import growables.plant.flower.sunflower.Sunflower;
import growables.plant.weed.Weed;
import java.awt.Point;
import placed_objects.*;
import supplementary.Window;

public class App {
    public static void main(String[] args) {
        Window window = new Window();

        
        Patch<Sunflower> p = new Patch<Sunflower>(100, new Point(200, 200), window.getGround());

        window.addToGround(p);
        
        Sunflower sf1 = new Sunflower(new Point(200, 200), window.getSky(), window.getGround());
        window.addToGround(sf1);
        p.addToPatch(sf1);

        Sunflower sf2 = new Sunflower(new Point(1000, 500), window.getSky(), window.getGround());
        Weed w = new Weed(new Point(600, 350), window.getSky(), window.getGround());
        
        window.addToGround(sf2);
        window.addToGround(w);
        
    }
}
