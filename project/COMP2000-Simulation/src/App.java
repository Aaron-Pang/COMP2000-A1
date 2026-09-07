import java.awt.*;

public class App {
    public static void main(String[] args) {
        Window window = new Window();

        int delay = 50; //Refresh 20 times per second

        //TODO: needs refresh rate code here or in controller - Allie

        Patch<Sunflower> p = new Patch(50, new Point(75, 50));

        window.addToGround(p);

        Sunflower sf = new Sunflower(new Point(75, 50));

        window.addToGround(sf);

        p.addToPatch(sf);
    }
}
