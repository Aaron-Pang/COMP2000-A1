import java.awt.*;

public class App {
    public static void main(String[] args) {
        Window window = new Window();

        Patch<Sunflower> p = new Patch(100, new Point(200, 200));

        window.addToGround(p);

        Sunflower sf = new Sunflower(new Point(200, 200));
        Weed w = new Weed(new Point(300, 300));

        window.addToGround(sf);
        window.addToGround(w);

        p.addToPatch(sf);
    }
}
